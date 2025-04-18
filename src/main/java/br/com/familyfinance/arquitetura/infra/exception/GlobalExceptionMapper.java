package br.com.familyfinance.arquitetura.infra.exception;

import br.com.familyfinance.arquitetura.core.dto.ErrorDTO;
import br.com.familyfinance.arquitetura.core.dto.MetaDTO;
import br.com.familyfinance.arquitetura.core.dto.ResponseDTO;
import br.com.familyfinance.arquitetura.core.exception.ArquiteturaErrorCodeEnum;
import br.com.familyfinance.arquitetura.core.exception.BusinessException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception.getCause() instanceof BusinessException) {
            return toResponse((BusinessException) exception);
        }
        log.error(exception.getMessage(), exception);

        // Retornar a resposta com o código de status e o corpo da mensagem
        return Response.status(ArquiteturaErrorCodeEnum.ERRO_INTERNO.getHttpStatus())
                .entity(ResponseDTO
                        .builder()
                        .errors(Collections.singletonList(ErrorDTO.builder()
                                .code(ArquiteturaErrorCodeEnum.ERRO_INTERNO.getCodigo())
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
                                .details(Arrays.toString(exception.getStackTrace()))
                                .build()))
                        .meta(MetaDTO.of(LocalDateTime.now()))
                        .build())
                .build();
    }
}
