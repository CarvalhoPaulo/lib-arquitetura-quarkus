package br.dev.paulocarvalho.arquitetura.shared.constants;

public class ArquiteturaConstants {
    public static final String getBasePathContext(String api, String version) {
        return "/family-finance-api/" + api + "/v" + version + "/";
    }
}
