package br.com.familyfinance.arquitetura.core.repository;

import br.com.familyfinance.arquitetura.core.Model;
import io.smallrye.mutiny.Uni;

public interface BaseRepository<MODEL extends Model<ID>, ID> {
    Uni<MODEL> buscarPorId(ID id);

    Uni<MODEL> inserir(MODEL usuario);

    Uni<MODEL> alterar(MODEL usuario);

    Uni<Void> excluir(MODEL usuario);
}
