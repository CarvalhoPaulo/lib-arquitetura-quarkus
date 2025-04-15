package br.com.familyfinance.arquitetura.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MetaDTO implements Serializable {
    private LocalDateTime requestDateTime;

    public static MetaDTO of(LocalDateTime requestDateTime) {
        return new MetaDTO(requestDateTime);
    }
}
