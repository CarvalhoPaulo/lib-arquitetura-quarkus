package br.com.familyfinance.arquitetura.application.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends Exception {

    private final ApplicationErrorCode code;
    private String details;
    private static ApplicationMessageProvider messageProvider;

    public ApplicationException(ApplicationErrorCode code) {
        super(obtemMensagem(code));
        this.code = code;
    }

    public ApplicationException(ApplicationErrorCode code, String details) {
        super(obtemMensagem(code));
        this.code = code;
        this.details = details;
    }

    public ApplicationException(ApplicationErrorCode code, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
    }

    public ApplicationException(ApplicationErrorCode code, String details, Throwable cause) {
        super(obtemMensagem(code), cause);
        this.code = code;
        this.details = details;
    }


    private static String obtemMensagem(ApplicationErrorCode code) {
        if (messageProvider != null) {
            return messageProvider.getMessage(code.getCodigo());
        } else {
            return code.getMessage();
        }
    }

    public static void configMessageProvider(ApplicationMessageProvider messageProvider) {
        ApplicationException.messageProvider = messageProvider;
    }

}
