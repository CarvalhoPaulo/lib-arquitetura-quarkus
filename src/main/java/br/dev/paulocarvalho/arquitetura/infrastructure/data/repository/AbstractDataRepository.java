package br.dev.paulocarvalho.arquitetura.infrastructure.data.repository;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import br.dev.paulocarvalho.arquitetura.domain.repository.BaseRepository;
import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationErrorCodeEnum;
import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationException;
import br.dev.paulocarvalho.arquitetura.domain.mapper.AbstractModelMapper;
import br.dev.paulocarvalho.arquitetura.domain.model.Model;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.transaction.Transactional;

public abstract class AbstractDataRepository<MODEL extends Model<ID>, DATA, ID>
        implements PanacheRepositoryBase<DATA, ID>, BaseRepository<MODEL, ID> {

    protected abstract AbstractModelMapper<MODEL, DATA> getMapper();

    @Override
    public Uni<MODEL> buscarPorId(ID id) {
        return findById(id)
                .onItem()
                .transform(Unchecked.function(this.getMapper()::toModel));
    }

    @Transactional
    @Override
    public Uni<MODEL> inserir(MODEL model) throws BusinessException {
        return persist(this.getMapper().toData(model))
                .onItem()
                .transform(Unchecked.function(this.getMapper()::toModel));
    }

    @Transactional
    @Override
    public Uni<MODEL> alterar(MODEL model) {
        return findById(model.getId())
                .onItem()
                .ifNull()
                .failWith(() -> new ApplicationException(ApplicationErrorCodeEnum.RECURSO_NAO_ENCONTRADO))
                .onItem()
                .ifNotNull()
                .transformToUni(Unchecked.function(data -> persist(this.getMapper().mergeData(model, data))))
                .onItem()
                .transform(Unchecked.function(this.getMapper()::toModel));
    }

    @Transactional
    @Override
    public Uni<Void> excluir(MODEL model) {
        return findById(model.getId())
                .onItem()
                .transformToUni(data -> delete(data));
    }

}
