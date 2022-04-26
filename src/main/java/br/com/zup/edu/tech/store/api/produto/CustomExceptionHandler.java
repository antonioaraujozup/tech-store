package br.com.zup.edu.tech.store.api.produto;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<MensagemDeErro> mensagens = ex.getBindingResult().getFieldErrors().stream()
                .map(MensagemDeErro::new)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(mensagens);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {

        Map<String, Object> body = Map.of(
                "mensagem", "Produto já cadastrado",
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity.unprocessableEntity().body(body);

    }
}
