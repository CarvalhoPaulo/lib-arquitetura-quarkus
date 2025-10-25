package br.dev.paulocarvalho.arquitetura.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Page<T> {
    private List<T> data;
    private Integer page;
    private Integer size;
    private Long totalRecords;

    public <N> PageBuilder<N> toBuilder() {
        return Page.<N>builder()
                .page(this.page)
                .size(this.size)
                .totalRecords(this.totalRecords);
    }
}
