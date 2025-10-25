package br.dev.paulocarvalho.arquitetura.domain.repository;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import br.dev.paulocarvalho.arquitetura.domain.model.Model;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface TenantTableBaseRepository<MODEL extends Model<ID>, ID, TENANT> {
    Uni<List<MODEL>> getAll(TENANT tenant);

    Uni<MODEL> get(TENANT tenant, ID id);

    Uni<MODEL> create(TENANT tenant, MODEL model) throws BusinessException;

    Uni<Void> delete(TENANT tenant, MODEL model);

    Uni<Void> delete(TENANT tenant, ID id);
}
