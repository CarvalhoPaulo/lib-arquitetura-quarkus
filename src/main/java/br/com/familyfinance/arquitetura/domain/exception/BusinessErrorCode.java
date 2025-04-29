package br.com.familyfinance.arquitetura.domain.exception;

public interface BusinessErrorCode {
    String getCodigo();
    String getMessage();
    int getHttpStatus();
}
