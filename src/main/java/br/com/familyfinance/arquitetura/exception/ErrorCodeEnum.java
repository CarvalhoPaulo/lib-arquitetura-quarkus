package br.com.familyfinance.arquitetura.exception;

public interface ErrorCodeEnum {
    String getCodigo();
    String getMessage();
    int getHttpStatus();

}
