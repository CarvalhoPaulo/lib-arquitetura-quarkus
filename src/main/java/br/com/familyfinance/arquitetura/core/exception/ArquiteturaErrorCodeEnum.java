package br.com.familyfinance.arquitetura.core.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public enum ArquiteturaErrorCodeEnum implements ErrorCodeEnum {
    ERRO_INTERNO("ARQ0001", "Erro interno.", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()),
    TOKEN_INVALIDO("ARQ0002", "Token inválido.");

    private final String codigo;
    private final String message;
    private final int httpStatus;

    ArquiteturaErrorCodeEnum(String codigo, String message, int httpStatus) {
        this.message = message;
        this.codigo = codigo;
        this.httpStatus = httpStatus;
    }

    ArquiteturaErrorCodeEnum(String codigo, String message) {
        this.message = message;
        this.codigo = codigo;
        this.httpStatus = Response.Status.BAD_REQUEST.getStatusCode();
    }
}
