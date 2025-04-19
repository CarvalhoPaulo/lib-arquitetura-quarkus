package br.com.familyfinance.arquitetura.core.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private ErrorCodeEnum code;
    private String details;
    private static MessageProvider messageProvider;

    public BusinessException(ErrorCodeEnum code) {
        super(obtemMensagem(code));
        this.code = code;
    }

    public BusinessException(ErrorCodeEnum code, String details) {
        super(obtemMensagem(code));
        this.code = code;
        this.details = details;
    }

    public BusinessException(ErrorCodeEnum code, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
    }

    public BusinessException(ErrorCodeEnum code, String details, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
        this.details = details;
    }


    private static String obtemMensagem(ErrorCodeEnum code) {
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
