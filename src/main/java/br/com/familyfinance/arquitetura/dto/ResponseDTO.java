package br.com.familyfinance.arquitetura.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseDTO<T, M extends MetaDTO> {

    private T data;
    private M meta;

}
