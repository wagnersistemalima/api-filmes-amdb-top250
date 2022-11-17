package br.com.sistemalima.filmes.exceptions;

public class BadRequestExceptions extends RuntimeException{



    public BadRequestExceptions(String message) {
        super(message);
    }
}
