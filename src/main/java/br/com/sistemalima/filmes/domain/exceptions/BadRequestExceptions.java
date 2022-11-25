package br.com.sistemalima.filmes.domain.exceptions;

public class BadRequestExceptions extends RuntimeException{



    public BadRequestExceptions(String message) {
        super(message);
    }
}
