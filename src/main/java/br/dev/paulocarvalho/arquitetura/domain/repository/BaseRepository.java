package br.dev.paulocarvalho.arquitetura.domain.repository;

import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationException;
import br.dev.paulocarvalho.arquitetura.domain.entity.Entity;
import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;

import java.util.Optional;

public interface BaseRepository<MODEL extends Entity<ID>, ID> {
    Optional<MODEL> get(ID id) throws BusinessException;

    MODEL create(MODEL model) throws BusinessException;

    MODEL update(MODEL model) throws ApplicationException, BusinessException;

    void delete(MODEL model);
}
