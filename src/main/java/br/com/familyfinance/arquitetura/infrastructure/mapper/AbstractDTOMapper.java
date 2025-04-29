package br.com.familyfinance.arquitetura.infrastructure.mapper;

import java.util.List;

public interface AbstractDTOMapper<MODEL, DTO> {
    MODEL toModel(DTO data);
    DTO toDTO(MODEL entity);
    List<MODEL> toModelList(List<DTO> dataList);
    List<DTO> toDTOList(List<MODEL> entityList);
}
