package br.com.familyfinance.arquitetura.application.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public enum ApplicationErrorCodeEnum implements ApplicationErrorCode {
    ERRO_INTERNO("ARQA0001", "Erro interno.", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()),
    RECURSO_NAO_ENCONTRADO("ARQA0002", "Recurso não encontrado.");

    private final String codigo;
    private final String message;
    private final int httpStatus;

    ApplicationErrorCodeEnum(String codigo, String message, int httpStatus) {
        this.message = message;
        this.codigo = codigo;
        this.httpStatus = httpStatus;
    }

    ApplicationErrorCodeEnum(String codigo, String message) {
        this.message = message;
        this.codigo = codigo;
        this.httpStatus = Response.Status.BAD_REQUEST.getStatusCode();
    }
}
