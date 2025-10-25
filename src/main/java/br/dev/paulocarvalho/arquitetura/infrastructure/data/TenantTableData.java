package br.dev.paulocarvalho.arquitetura.infrastructure.data;

public interface TenantTableData<ID> {
    void setTenant(ID tenant);
}
