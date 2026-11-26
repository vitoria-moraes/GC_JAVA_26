package com.gc26.spring.apirest.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@RestControllerAdvice
public class TratadorDeErros {

    private static final int NAO_ENCONTRADO = 404;
    private static final int BAD_GATEWAY = 502;

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<Map<String, String>> respostaDeErro(RestClientResponseException e) {
        if (e.getStatusCode().value() == NAO_ENCONTRADO) {
            return ResponseEntity.status(NAO_ENCONTRADO).body(Map.of("erro", "Recurso não encontrado na API externa"));
        }
        return ResponseEntity.status(BAD_GATEWAY).body(Map.of("erro", "A API externa respondeu com erro"));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Map<String, String>> apiIndisponivel(RestClientException e) {
        return ResponseEntity.status(BAD_GATEWAY).body(Map.of("erro", "API externa indisponível"));
    }
}
