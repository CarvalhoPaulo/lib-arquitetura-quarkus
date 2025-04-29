package br.com.familyfinance.arquitetura.domain.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public enum ArquiteturaErrorCodeEnum implements ErrorCode {
    TOKEN_INVALIDO("ARQB0001", "Token inválido.");

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
