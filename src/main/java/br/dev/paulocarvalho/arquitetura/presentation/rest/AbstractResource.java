package br.dev.paulocarvalho.arquitetura.presentation.rest;

import java.util.Optional;

public class AbstractResource {
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private static final Integer DEFAULT_PAGE = 1;

    protected Integer getPage(Integer page) {
        return Optional.ofNullable(page).orElse(DEFAULT_PAGE) - 1;
    }

    protected Integer getPageSize(Integer pageSize) {
        return Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);
    }
}
