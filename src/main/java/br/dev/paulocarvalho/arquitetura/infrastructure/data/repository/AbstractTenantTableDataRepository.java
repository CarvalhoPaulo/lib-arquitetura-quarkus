package br.dev.paulocarvalho.arquitetura.infrastructure.data.repository;

import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationErrorCodeEnum;
import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationException;
import br.dev.paulocarvalho.arquitetura.domain.entity.Entity;
import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import br.dev.paulocarvalho.arquitetura.domain.mapper.AbstractModelMapper;
import br.dev.paulocarvalho.arquitetura.domain.repository.TenantTableBaseRepository;
import br.dev.paulocarvalho.arquitetura.infrastructure.data.TenantTableData;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.transaction.Transactional;

import java.util.List;

public abstract class AbstractTenantTableDataRepository<MODEL extends Entity<ID>, DATA extends TenantTableData<ID, TENANT_ID>, ID, TENANT_ID>
        implements PanacheRepositoryBase<DATA, ID>, TenantTableBaseRepository<MODEL, ID, TENANT_ID> {

    private static final String TENANT_ID_PARAMETER_NAME = "tenant";
    private static final String ID_TENANT_ID_CONDITION = "id = :id and tenant = :"
            + TENANT_ID_PARAMETER_NAME + " and active is true";

    protected abstract AbstractModelMapper<MODEL, DATA> getMapper();

    @Override
    public List<MODEL> getAll(TENANT_ID tenant) throws BusinessException {
        List<DATA> dataList = find("tenant = :tenant and active is true", Parameters.with("tenant", tenant)).list();
        return this.getMapper().toModelList(dataList);
    }

    @Override
    public MODEL get(TENANT_ID tenant, ID id) throws ApplicationException, BusinessException {
        DATA data = find(
                ID_TENANT_ID_CONDITION,
                Parameters.with("id", id).and(TENANT_ID_PARAMETER_NAME, tenant)
        )
                .firstResultOptional()
                .orElseThrow(() -> new ApplicationException(ApplicationErrorCodeEnum.RECURSO_NAO_ENCONTRADO));
        return this.getMapper().toModel(data);
    }

    @Transactional
    @Override
    public MODEL create(TENANT_ID tenant, MODEL model) throws BusinessException {
        DATA data = this.getMapper().toData(model);
        data.setTenant(tenant);
        persist(data);
        return this.getMapper().toModel(data);
    }

    @Transactional
    @Override
    public void delete(TENANT_ID tenant, MODEL model) throws ApplicationException {
        delete(tenant, model.getId());
    }

    @Transactional
    @Override
    public void delete(TENANT_ID tenant, ID id) throws ApplicationException {
        find(
                ID_TENANT_ID_CONDITION,
                Parameters.with("id", id).and(TENANT_ID_PARAMETER_NAME, tenant)
        )
                .firstResultOptional()
                .orElseThrow(() -> new ApplicationException(ApplicationErrorCodeEnum.RECURSO_NAO_ENCONTRADO));

        update(
                "active = false WHERE " + ID_TENANT_ID_CONDITION,
                Parameters.with("id", id).and(TENANT_ID_PARAMETER_NAME, tenant)
        );
    }

}
