package br.dev.paulocarvalho.arquitetura.domain.repository;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import br.dev.paulocarvalho.arquitetura.domain.model.Model;
import io.smallrye.mutiny.Uni;

public interface BaseRepository<MODEL extends Model<ID>, ID> {
    Uni<MODEL> get(ID id);

    Uni<MODEL> create(MODEL model) throws BusinessException;

    Uni<MODEL> update(MODEL model);

    Uni<Void> delete(MODEL model);
}
