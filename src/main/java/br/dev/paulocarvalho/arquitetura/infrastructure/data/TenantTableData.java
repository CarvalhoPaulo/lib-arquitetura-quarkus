package br.dev.paulocarvalho.arquitetura.infrastructure.data;

public interface TenantTableData<ID, TENANT_ID> {
    void setTenant(TENANT_ID tenant);
    ID getId();
}
