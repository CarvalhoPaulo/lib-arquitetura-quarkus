package br.com.familyfinance.arquitetura.application.dto;

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Schema(description = "Resposta padrão com dados e metadados")
public class ResponseDTO<T, M extends MetaDTO> {

    @Schema(
            description = "Conteúdo da resposta",
            implementation = Object.class // Swagger precisa de um tipo concreto, mas deixamos genérico aqui
    )
    private T data;

    private List<ErrorDTO> errors;

    @Schema(
            description = "Informações adicionais da resposta",
            implementation = MetaDTO.class // Mesmo raciocínio
    )
    private M meta;
}
