package br.com.familyfinance.arquitetura.infra.interceptor;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Provider
@Slf4j
public class ResponseBodyInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {

        OutputStream originalStream = context.getOutputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        context.setOutputStream(buffer);

        context.proceed(); // continua a serialização

        byte[] entityBytes = buffer.toByteArray();
        String responseBody = new String(entityBytes, StandardCharsets.UTF_8);
        log.debug("Response Body: {}", responseBody);

        originalStream.write(entityBytes);
        originalStream.flush();
        context.setOutputStream(originalStream);
    }
}
