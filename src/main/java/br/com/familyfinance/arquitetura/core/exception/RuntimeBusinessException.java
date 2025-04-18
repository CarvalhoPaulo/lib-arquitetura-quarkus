package br.com.familyfinance.arquitetura.core.exception;

import lombok.Getter;

@Getter
public class RuntimeBusinessException extends RuntimeException {
    private ErrorCodeEnum code;
    private static MessageProvider messageProvider;

    public RuntimeBusinessException(ErrorCodeEnum code) {
        super(obtemMensagem(code));
        this.code = code;
    }

    public RuntimeBusinessException(ErrorCodeEnum code, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
    }


    private static String obtemMensagem(ErrorCodeEnum code) {
        if (messageProvider != null) {
            return messageProvider.getMessage(code.getCodigo());
        } else {
            return code.getMessage();
        }
    }

    public static void configMessageProvider(MessageProvider messageProvider) {
        RuntimeBusinessException.messageProvider = messageProvider;
    }
}
