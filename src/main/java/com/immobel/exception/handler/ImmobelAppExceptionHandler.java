package com.immobel.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ImmobelAppExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleException(HttpServletRequest request, Exception e) {
        log.info("Unable to process request for URI({}) with ContentType({})", request.getRequestURI(), request.getContentType(), e);
        return new ResponseEntity<>("Error occurred. Unable to process request", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(ResponseStatusException.class)
    public @ResponseBody ResponseEntity<String> handleBadRequest(HttpServletRequest request, ResponseStatusException e) {
        
        return new ResponseEntity<>(e.getReason(), e.getStatus());
    }
}
