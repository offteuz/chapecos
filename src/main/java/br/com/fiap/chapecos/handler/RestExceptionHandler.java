package br.com.fiap.chapecos.handler;

import br.com.fiap.chapecos.config.configuration.ApiError;
import br.com.fiap.chapecos.exception.EmailAlreadyExistsException;
import br.com.fiap.chapecos.exception.RoleNotFoundException;
import br.com.fiap.chapecos.exception.UserAlreadyExistsException;
import br.com.fiap.chapecos.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, RoleNotFoundException.class})
    public ResponseEntity<ApiError> notFoundException(RuntimeException e) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(e.getMessage()))
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> emailAlreadyExistsException(RuntimeException e) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status((HttpStatus.INTERNAL_SERVER_ERROR.name()))
                .errors(List.of(e.getMessage()))
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> usernameAlreadyExistsException(RuntimeException e) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status((HttpStatus.INTERNAL_SERVER_ERROR.name()))
                .errors(List.of(e.getMessage()))
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> argumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errorList)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> illegalArgumentException(IllegalArgumentException e) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors((List.of(e.getMessage())))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
