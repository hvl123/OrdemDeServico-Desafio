//package br.com.henriquemonteiro.ordemdeservicos.exceptions;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {}
////
////    @ExceptionHandler(MethodArgumentNotValidException.class)
//////    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//////        Map<String, String> errors = new HashMap<>();
////        ex.getBindingResult().getFieldErrors().forEach(error -> {
//////            String fieldName = error.getField();
////////            String errorMessage = error.getDefaultMessage();
//////            errors.put(fieldName, errorMessage);
////        });
////        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
////    }
//
////    @ExceptionHandler(Exception.class)
////    public ResponseEntity<String> handleGenericException(Exception ex) {
////        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + ex.getMessage());
////    }
////}