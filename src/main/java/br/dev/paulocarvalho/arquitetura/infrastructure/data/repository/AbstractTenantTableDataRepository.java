package br.dev.paulocarvalho.arquitetura.infrastructure.data.repository;

import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationErrorCodeEnum;
import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationException;
import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import br.dev.paulocarvalho.arquitetura.domain.mapper.AbstractModelMapper;
import br.dev.paulocarvalho.arquitetura.domain.model.Model;
import br.dev.paulocarvalho.arquitetura.domain.repository.TenantTableBaseRepository;
import br.dev.paulocarvalho.arquitetura.infrastructure.data.TenantTableData;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.transaction.Transactional;

import java.util.List;

public abstract class AbstractTenantTableDataRepository<MODEL extends Model<ID>, DATA extends TenantTableData<ID, TENANT_ID>, ID, TENANT_ID>
        implements PanacheRepositoryBase<DATA, ID>, TenantTableBaseRepository<MODEL, ID, TENANT_ID> {

    private static final String TENANT_ID_PARAMETER_NAME = "tenant";
    private static final String ID_TENANT_ID_CONDITION = "id = :id and tenant = :"
            + TENANT_ID_PARAMETER_NAME + " and active is true";

    protected abstract AbstractModelMapper<MODEL, DATA> getMapper();

    @Override
    public Uni<List<MODEL>> getAll(TENANT_ID tenant) {
        return find("tenant", tenant)
                .list()
                .onItem()
                .transform(Unchecked.function(this.getMapper()::toModelList));
    }

    @Override
    public Uni<MODEL> get(TENANT_ID tenant, ID id) {
        return find(
                ID_TENANT_ID_CONDITION,
                Parameters.with("id", id).and(TENANT_ID_PARAMETER_NAME, tenant)
        )
                .singleResult()
                .onItem()
                .ifNull()
                .failWith(() -> new ApplicationException(ApplicationErrorCodeEnum.RECURSO_NAO_ENCONTRADO))
                .onItem()
                .transform(Unchecked.function(this.getMapper()::toModel));
    }

    @Transactional
    @Override
    public Uni<MODEL> create(TENANT_ID tenant, MODEL model) throws BusinessException {
        DATA data = this.getMapper().toData(model);
        data.setTenant(tenant);
        return persist(data)
                .onItem()
                .transformToUni(d -> findById(d.getId()))
                .onItem()
                .transform(Unchecked.function(this.getMapper()::toModel));
    }

    @Transactional
    @Override
    public Uni<Void> delete(TENANT_ID tenant, MODEL model) {
        return delete(tenant, model.getId());
    }

    @Transactional
    @Override
    public Uni<Void> delete(TENANT_ID tenant, ID id) {
        return find(
                ID_TENANT_ID_CONDITION,
                Parameters.with("id", id).and(TENANT_ID_PARAMETER_NAME, tenant)
        )
                .firstResult()
                .onItem()
                .ifNull()
                .failWith(() -> new ApplicationException(ApplicationErrorCodeEnum.RECURSO_NAO_ENCONTRADO))
                .onItem()
                .transformToUni(data -> update(
                        "active = false WHERE " + ID_TENANT_ID_CONDITION,
                        Parameters.with("id", id).and(TENANT_ID_PARAMETER_NAME, tenant)
                ))
                .onItem()
                .transformToUni(i -> Uni.createFrom().voidItem());
    }

}
