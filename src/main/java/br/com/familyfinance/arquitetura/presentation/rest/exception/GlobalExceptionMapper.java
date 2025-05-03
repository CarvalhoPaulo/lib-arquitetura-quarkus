package br.com.familyfinance.arquitetura.presentation.rest.exception;

import br.com.familyfinance.arquitetura.application.dto.ErrorDTO;
import br.com.familyfinance.arquitetura.application.dto.MetaDTO;
import br.com.familyfinance.arquitetura.application.dto.ResponseDTO;
import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationErrorCodeEnum;
import br.dev.paulocarvalho.arquitetura.application.exception.ApplicationException;
import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof BusinessException) {
            return toResponse((BusinessException) exception);
        }
        if (exception.getCause() instanceof BusinessException) {
            return toResponse((BusinessException) exception.getCause());
        }
        if (exception instanceof ApplicationException) {
            return toResponse((ApplicationException) exception);
        }
        if (exception.getCause() instanceof ApplicationException) {
            return toResponse((ApplicationException) exception.getCause());
        }
        log.error(exception.getMessage(), exception);

        // Retornar a resposta com o código de status e o corpo da mensagem
        return Response.status(ApplicationErrorCodeEnum.ERRO_INTERNO.getHttpStatus())
                .entity(ResponseDTO
                        .builder()
                        .errors(Collections.singletonList(ErrorDTO.builder()
                                .code(ApplicationErrorCodeEnum.ERRO_INTERNO.getCodigo())
                                .message("Erro interno no servidor")
                                .details("Ocorreu um erro inesperado ao processar sua requisição. " +
                                        "Por favor, tente novamente mais tarde ou entre em contato " +
                                        "com o suporte.")
                                .build()))
                        .meta(MetaDTO.of(LocalDateTime.now()))
                        .build())
                .build();
    }

    public Response toResponse(BusinessException exception) {
        // Criar uma resposta com o código de status apropriado
        int statusCode = exception.getCode().getHttpStatus();

        // Retornar a resposta com o código de status e o corpo da mensagem
        return Response.status(statusCode)
                .entity(ResponseDTO
                        .builder()
                        .errors(Collections.singletonList(ErrorDTO.builder()
                                .code(exception.getCode().getCodigo())
                                .message(exception.getMessage())
                                .details(exception.getDetails())
                                .build()))
                        .meta(MetaDTO.of(LocalDateTime.now()))
                        .build())
                .build();
    }

    public Response toResponse(ApplicationException exception) {
        // Criar uma resposta com o código de status apropriado
        int statusCode = exception.getCode().getHttpStatus();

        // Retornar a resposta com o código de status e o corpo da mensagem
        return Response.status(statusCode)
                .entity(ResponseDTO
                        .builder()
                        .errors(Collections.singletonList(ErrorDTO.builder()
                                .code(exception.getCode().getCodigo())
                                .message(exception.getMessage())
                                .details(exception.getDetails())
                                .build()))
                        .meta(MetaDTO.of(LocalDateTime.now()))
                        .build())
                .build();
    }
}
