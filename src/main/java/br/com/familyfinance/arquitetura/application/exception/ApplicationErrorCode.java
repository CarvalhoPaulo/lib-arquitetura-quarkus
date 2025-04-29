package br.com.familyfinance.arquitetura.application.exception;

public interface ApplicationErrorCode {
    String getCodigo();
    String getMessage();
    int getHttpStatus();
}
