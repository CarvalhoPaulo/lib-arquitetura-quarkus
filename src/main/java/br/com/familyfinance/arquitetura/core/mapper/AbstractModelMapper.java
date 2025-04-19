package br.com.familyfinance.arquitetura.core.mapper;

import br.com.familyfinance.arquitetura.core.Model;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface AbstractModelMapper<MODEL extends Model<?>, DATA> {

    MODEL toModel(DATA data);
    DATA toData(MODEL entity);
    DATA mergeData(MODEL entity, @MappingTarget DATA data);
    List<MODEL> toModelList(List<DATA> dataList);
    List<DATA> toDataList(List<MODEL> entityList);
}
