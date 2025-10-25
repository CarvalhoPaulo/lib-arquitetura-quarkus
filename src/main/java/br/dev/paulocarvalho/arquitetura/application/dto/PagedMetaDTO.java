package br.dev.paulocarvalho.arquitetura.application.dto;

import br.dev.paulocarvalho.arquitetura.domain.model.Page;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PagedMetaDTO extends MetaDTO {
    private Integer page;
    private Integer size;
    private Long totalRecords;

    @Builder
    public PagedMetaDTO(LocalDateTime requestDateTime,
                        Long totalRecords,
                        Integer size,
                        Integer page) {
        super(requestDateTime);
        this.totalRecords = totalRecords;
        this.size = size;
        this.page = page;
    }

    public static PagedMetaDTO of(Page<?> page) {
        return PagedMetaDTO.builder()
                .page(page.getPage() + 1)
                .size(page.getSize())
                .totalRecords(page.getTotalRecords())
                .build();
    }
}
