package br.com.sistemalima.filmes.controller;

import br.com.sistemalima.filmes.dto.FilmeDTO;
import br.com.sistemalima.filmes.http.imdb.dto.Top250Data;
import br.com.sistemalima.filmes.mapper.ObservabilidadeMapper;
import br.com.sistemalima.filmes.model.Observabilidade;
import br.com.sistemalima.filmes.service.FilmeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<FilmeDTO>> listar(
            @RequestHeader("Accept-Version") @NotEmpty(message = "informe o cabeçalho") String version,
            @RequestHeader("Api-Key") @NotEmpty(message = "informe o cabeçalho") String apiKey
    ) throws IOException {

        String correlationId = UUID.randomUUID().toString();

        Observabilidade observabilidade = observabilidadeMapper.map(version, apiKey, listarTop250Filmes, correlationId);

        logger.info(String.format(tagStart + observabilidade));

        List<FilmeDTO> listFilmeDTO = filmeService.listarFilmesTop250(apiKey, observabilidade);

        logger.info(String.format(tagEnd + observabilidade));

        return ResponseEntity.ok().body(listFilmeDTO);
    }
}
