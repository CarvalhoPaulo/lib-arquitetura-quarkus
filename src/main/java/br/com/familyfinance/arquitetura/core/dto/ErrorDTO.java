package br.com.familyfinance.arquitetura.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDTO {
    private String code;
    private String message;
    private String details;
}
