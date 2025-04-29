package br.com.familyfinance.arquitetura.domain.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private final BusinessErrorCode code;
    private String details;
    private static BusinessMessageProvider messageProvider;

    public BusinessException(BusinessErrorCode code) {
        super(obtemMensagem(code));
        this.code = code;
    }

    public BusinessException(BusinessErrorCode code, String details) {
        super(obtemMensagem(code));
        this.code = code;
        this.details = details;
    }

    public BusinessException(BusinessErrorCode code, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
    }

    public BusinessException(BusinessErrorCode code, String details, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
        this.details = details;
    }


    private static String obtemMensagem(BusinessErrorCode code) {
        if (messageProvider != null) {
            return messageProvider.getMessage(code.getCodigo());
        } else {
            return code.getMessage();
        }
    }

    public static void configMessageProvider(BusinessMessageProvider messageProvider) {
        BusinessException.messageProvider = messageProvider;
    }
}
