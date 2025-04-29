package br.com.familyfinance.arquitetura.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "DTO que contém os metadados da resposta, como a data e hora da requisição")
public class MetaDTO implements Serializable {

    @Schema(
            description = "Data e hora em que a requisição foi feita",
            required = true
    )
    private LocalDateTime requestDateTime;

    public static MetaDTO of(LocalDateTime requestDateTime) {
        return new MetaDTO(requestDateTime);
    }
}
