package br.com.sistemalima.filmes.advice;

import br.com.sistemalima.filmes.advice.dto.ErroView;
import br.com.sistemalima.filmes.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ResourceExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ResourceExceptionHandler.class);
    private final static String tag = "class: ResourceExceptionHandler, ";


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroView handlerEntityException(Exception exception, HttpServletRequest request) {
        logger.error(String.format("Error, " + tag + "method: handlerEntityException, message: " + exception.getMessage()));
        return new ErroView(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                exception.getMessage(),
                request.getServletPath()
        );
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErroView handlerIoException(IOException exception, HttpServletRequest request) {
        logger.error(String.format("Error, " + tag + "method: handlerIoException, message: " + exception.getMessage()));
        return new ErroView(
                LocalDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                HttpStatus.BAD_GATEWAY.name(),
                exception.getMessage(),
                request.getServletPath()
        );
    }

    @ExceptionHandler(BadRequestExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroView handlerBadRequestException(BadRequestExceptions exception, HttpServletRequest request) {
        logger.error(String.format("Error, " + tag + "method: handlerBadRequestException, message: " + exception.getMessage()));
        return new ErroView(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage(),
                request.getServletPath()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroView handlerEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        logger.error(String.format("Error, " + tag + "method: EntityNotFoundException, message: " + exception.getMessage()));
        return new ErroView(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                exception.getMessage(),
                request.getServletPath()
        );
    }

}
