package br.com.familyfinance.arquitetura.core.exception;

public interface ErrorCodeEnum {
    String getCodigo();
    String getMessage();
    int getHttpStatus();

}
