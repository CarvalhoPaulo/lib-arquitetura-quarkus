package br.dev.paulocarvalho.arquitetura.infrastructure.data.repository;

import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationErrorCodeEnum;
import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationException;
import br.dev.paulocarvalho.arquitetura.domain.entity.Entity;
import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import br.dev.paulocarvalho.arquitetura.domain.mapper.AbstractModelMapper;
import br.dev.paulocarvalho.arquitetura.domain.repository.BaseRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.transaction.Transactional;

import java.util.Optional;

public abstract class AbstractDataRepository<MODEL extends Entity<ID>, DATA, ID>
        implements PanacheRepositoryBase<DATA, ID>, BaseRepository<MODEL, ID> {

    protected abstract AbstractModelMapper<MODEL, DATA> getMapper();

    @Override
    public Optional<MODEL> get(ID id) throws BusinessException {
        Optional<DATA> data = findByIdOptional(id);
        if (data.isPresent()) {
            return Optional.of(getMapper().toModel(data.get()));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public MODEL create(MODEL model) throws BusinessException {
        DATA data = getMapper().toData(model);
        persist(data);
        return getMapper().toModel(data);
    }

    @Transactional
    @Override
    public MODEL update(MODEL model) throws ApplicationException, BusinessException {
        DATA data = findByIdOptional(model.getId())
                .orElseThrow(() -> new ApplicationException(ApplicationErrorCodeEnum.RECURSO_NAO_ENCONTRADO));
        DATA mergedData = getMapper().mergeData(model, data);
        persist(mergedData);
        return getMapper().toModel(mergedData);
    }

    @Transactional
    @Override
    public void delete(MODEL model) {
        deleteById(model.getId());
    }

}
