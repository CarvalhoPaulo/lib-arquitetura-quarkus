package br.dev.paulocarvalho.arquitetura.domain.repository;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import br.dev.paulocarvalho.arquitetura.domain.model.Model;
import io.smallrye.mutiny.Uni;

public interface BaseRepository<MODEL extends Model<ID>, ID> {
    Uni<MODEL> buscarPorId(ID id);

    Uni<MODEL> inserir(MODEL usuario) throws BusinessException;

    Uni<MODEL> alterar(MODEL usuario);

    Uni<Void> excluir(MODEL usuario);
}
