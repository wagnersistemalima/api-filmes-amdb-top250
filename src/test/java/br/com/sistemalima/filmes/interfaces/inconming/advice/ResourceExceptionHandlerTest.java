package br.com.sistemalima.filmes.interfaces.inconming.advice;

import br.com.sistemalima.filmes.interfaces.inconming.advice.dto.ErroView;
import br.com.sistemalima.filmes.domain.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.domain.exceptions.EntityNotFoundException;
import br.com.sistemalima.filmes.interfaces.inconming.advice.ResourceExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler resourceExceptionHandler;

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private final static String testMessage = "test message";

    @Test
    public void handlerEntityNotFoundException() {

        EntityNotFoundException exceptionNotFound = new EntityNotFoundException(testMessage);

        ErroView response = resourceExceptionHandler.handlerEntityNotFoundException(exceptionNotFound, request);

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        Assertions.assertEquals(testMessage, response.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND.name(), response.getError());
    }

    @Test
    public void handlerEntityException() {

        Exception exception = new Exception(testMessage);

        ErroView response = resourceExceptionHandler.handlerEntityException(exception, request);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        Assertions.assertEquals(testMessage, response.getMessage());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.name(), response.getError());
    }

    @Test
    public void handlerIoException() {

        IOException exception = new IOException(testMessage);

        ErroView response = resourceExceptionHandler.handlerIoException(exception, request);

        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), response.getStatus());
        Assertions.assertEquals(testMessage, response.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.name(), response.getError());
    }

    @Test
    public void handlerBadRequestException() {

        BadRequestExceptions exception = new BadRequestExceptions(testMessage);

        ErroView response = resourceExceptionHandler.handlerBadRequestException(exception, request);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertEquals(testMessage, response.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.name(), response.getError());
    }

}