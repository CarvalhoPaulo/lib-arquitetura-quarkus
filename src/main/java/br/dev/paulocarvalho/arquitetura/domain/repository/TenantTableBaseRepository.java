package br.dev.paulocarvalho.arquitetura.domain.repository;

import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationException;
import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import br.dev.paulocarvalho.arquitetura.domain.entity.Entity;

import java.util.List;

public interface TenantTableBaseRepository<MODEL extends Entity<ID>, ID, TENANT> {
    List<MODEL> getAll(TENANT tenant) throws BusinessException;

    MODEL get(TENANT tenant, ID id) throws ApplicationException, BusinessException;

    MODEL create(TENANT tenant, MODEL model) throws BusinessException;

    void delete(TENANT tenant, MODEL model) throws ApplicationException;

    void delete(TENANT tenant, ID id) throws ApplicationException;
}
