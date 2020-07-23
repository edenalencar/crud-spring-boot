/*
 * Copyright (c) 2020 BytesCorp Software
 * Todos os direitos reservados
 *
 * Este produto está protegido por direitos autorais e distribuído sob
 * licenças que restringem a cópia, distribuição e descompilação.
 * @author Eden Alencar
 * @since 1.0
 *
 */

package br.com.bytescorp.demo.handler;

import br.com.bytescorp.demo.error.ErrorDetalhes;
import br.com.bytescorp.demo.error.RecursoNaoEncontradoDetalhes;
import br.com.bytescorp.demo.error.RecursoNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<?> processarRecursoNaoEncontrado(RecursoNaoEncontradoException recursoNaoEcontradoException) {
        RecursoNaoEncontradoDetalhes recursoNaoEncontradoDetalhes = RecursoNaoEncontradoDetalhes.RecursoNaoEncontradoDetalhesBuilder.newBuilder()
                .titulo("Recurso não encontrado")
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .datalhes(recursoNaoEcontradoException.getMessage())
                .mensagemDesenvolvedor(recursoNaoEcontradoException.getClass().getName())
                .build();
        return new ResponseEntity<>(recursoNaoEncontradoDetalhes, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetalhes errorDetalhes = ErrorDetalhes.Builder.newBuilder()
                .titulo("Error interno")
                .timestamp(new Date().getTime())
                .status(status.value())
                .datalhes(ex.getMessage())
                .mensagemDesenvolvedor(ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(errorDetalhes, headers, status);
    }

}
