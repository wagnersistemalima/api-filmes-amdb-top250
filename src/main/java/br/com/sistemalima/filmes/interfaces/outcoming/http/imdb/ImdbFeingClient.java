package br.com.sistemalima.filmes.interfaces.outcoming.http.imdb;

import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.dto.Top250Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "api-imdb",  url = "${servers.imdb-filmes.url}")
@Component
public interface ImdbFeingClient {

    @RequestMapping(method = RequestMethod.GET, value = "{lang}/API/Top250Movies/{apiKey}")
    Top250Data buscar250TopFilmes(@PathVariable String apiKey);

}
