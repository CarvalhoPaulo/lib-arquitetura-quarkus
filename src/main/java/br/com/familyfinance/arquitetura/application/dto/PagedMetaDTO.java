package br.com.familyfinance.arquitetura.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PagedMetaDTO extends MetaDTO {
    private Integer page;
    private Integer size;
    private Integer totalOfRecords;

    @Builder
    public PagedMetaDTO(LocalDateTime requestDateTime,
                        Integer totalOfRecords,
                        Integer size,
                        Integer page) {
        super(requestDateTime);
        this.totalOfRecords = totalOfRecords;
        this.size = size;
        this.page = page;
    }
}
