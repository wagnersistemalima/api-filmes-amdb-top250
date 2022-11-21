package br.com.sistemalima.filmes.controller;

import br.com.sistemalima.filmes.dto.FilmeDTO;
import br.com.sistemalima.filmes.mapper.ObservabilidadeMapper;
import br.com.sistemalima.filmes.model.Observabilidade;
import br.com.sistemalima.filmes.service.FilmeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private ObservabilidadeMapper observabilidadeMapper;

    private final Logger logger = LoggerFactory.getLogger(FilmeController.class);
    private final static String listarTop250Filmes = "pesquisar os Top250 filmes ";
    private final static String tagStart = "inicio do processo, class: FilmeController, ";
    private final static String tagEnd = "Fim do processo, class: FilmeController, ";

    @GetMapping("/top250")
    @Cacheable(value = "listaTopFilme250")
    @CircuitBreaker(name = "listarTop250Filmes", fallbackMethod = "")
    public ResponseEntity<List<FilmeDTO>> listar(
            @RequestHeader("Accept-Version") @NotEmpty(message = "informe o cabeçalho") String version
    ) throws IOException {

        String correlationId = UUID.randomUUID().toString();

        Observabilidade observabilidade = observabilidadeMapper.map(version, listarTop250Filmes, correlationId);

        logger.info(String.format(tagStart + observabilidade));

        List<FilmeDTO> listFilmeDTO = filmeService.listarFilmesTop250(observabilidade);

        logger.info(String.format(tagEnd + observabilidade));

        return ResponseEntity.ok().body(listFilmeDTO);
    }

}
