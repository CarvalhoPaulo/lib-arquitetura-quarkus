package br.com.familyfinance.arquitetura.domain.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private ErrorCode code;
    private String details;
    private static MessageProvider messageProvider;

    public BusinessException(ErrorCode code) {
        super(obtemMensagem(code));
        this.code = code;
    }

    public BusinessException(ErrorCode code, String details) {
        super(obtemMensagem(code));
        this.code = code;
        this.details = details;
    }

    public BusinessException(ErrorCode code, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
    }

    public BusinessException(ErrorCode code, String details, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
        this.details = details;
    }


    private static String obtemMensagem(ErrorCode code) {
        if (messageProvider != null) {
            return messageProvider.getMessage(code.getCodigo());
        } else {
            return code.getMessage();
        }
    }

    public static void configMessageProvider(MessageProvider messageProvider) {
        BusinessException.messageProvider = messageProvider;
    }
}
