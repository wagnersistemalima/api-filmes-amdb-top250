package br.com.sistemalima.filmes.mapper;

import br.com.sistemalima.filmes.constant.ApiConstantVersion;
import br.com.sistemalima.filmes.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.model.Observabilidade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ObservabilidadeMapper {


    public ObservabilidadeMapper() {
    }

    private final Logger logger = LoggerFactory.getLogger(ObservabilidadeMapper.class);
    private final static String tag = "class: ObservabilidadeMapper, ";
    private final static String messageErrorVersion = "Cabeçalho da requisição version não validado!";

    public Observabilidade map(String version, String apiKey, String resourceName, String correlationId) {
        logger.info(String.format(tag + "validando headers"));

        return validaHeaders(version, apiKey, resourceName, correlationId);
    }

    private Observabilidade validaHeaders(String version, String requestId, String resourceName, String correlationId) {

        ApiConstantVersion.initialization();

        for (String v: ApiConstantVersion.listVersion) {
            if (v.equals(version)) {
               break;

            } else {
                logger.error(String.format("Error " + tag + messageErrorVersion));
                throw new BadRequestExceptions(messageErrorVersion);
            }
        }

        return new Observabilidade(
                version,
                requestId,
                resourceName,
                correlationId

        );
    }
}
