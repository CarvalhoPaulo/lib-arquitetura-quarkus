package br.com.familyfinance.arquitetura.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Provider
@Slf4j
public class RequestLoggingFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getRequestUri().toString();
        String headers = requestContext.getHeaders().toString();
        String cookies = requestContext.getCookies().toString();

        log.info("→ [{}] {}", method, path);
        log.debug("Headers: {}", headers);
        log.debug("Cookies: {}", cookies);

        if (requestContext.hasEntity()) {
            String body = readBody(requestContext);
            log.debug("Request Body: {}", body);
        }
    }

    private String readBody(ContainerRequestContext context) throws IOException {
        byte[] bytes = context.getEntityStream().readAllBytes();
        context.setEntityStream(new ByteArrayInputStream(bytes));
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
