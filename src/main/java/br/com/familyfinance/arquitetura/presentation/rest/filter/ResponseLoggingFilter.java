package br.com.familyfinance.arquitetura.presentation.rest.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class ResponseLoggingFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) {

        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getRequestUri().toString();
        String headers = responseContext.getHeaders().toString();
        String cookies = responseContext.getCookies().toString();
        int status = responseContext.getStatus();

        log.info("← [{}] {} -> HTTP {}", method, path, status);
        log.debug("Headers: {}", headers);
        log.debug("Cookies: {}", cookies);
    }
}
