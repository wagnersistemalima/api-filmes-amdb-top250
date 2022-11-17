package br.com.sistemalima.filmes.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class FilmeServiceTest {

    @InjectMocks
    private FilmeService filmeService;

    @Test
    @DisplayName("deve listar filmes top 250 com sucesso")
    public void deveListarFilmesTop250() {

    }

}