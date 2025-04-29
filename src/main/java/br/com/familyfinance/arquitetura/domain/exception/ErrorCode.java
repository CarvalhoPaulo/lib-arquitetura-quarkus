package br.com.familyfinance.arquitetura.domain.exception;

public interface ErrorCode {
    String getCodigo();
    String getMessage();
    int getHttpStatus();
}
