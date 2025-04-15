package br.com.familyfinance.arquitetura.core.repository;

import io.smallrye.mutiny.Uni;

public interface BaseRepository<MODEL, ID> {
    Uni<MODEL> findById(ID id);

    Uni<MODEL> insert(MODEL usuario);

    Uni<MODEL> update(MODEL usuario);

    void delete(MODEL usuario);
}
