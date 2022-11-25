package br.com.sistemalima.filmes.domain.mapper;

import br.com.sistemalima.filmes.domain.constant.ApiConstantVersion;
import br.com.sistemalima.filmes.domain.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.domain.entity.Observabilidade;
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

    public Observabilidade map(String version, String resourceName, String correlationId) {
        logger.info(String.format(tag + "validando headers"));

        return validaHeaders(version, resourceName, correlationId);
    }

    private Observabilidade validaHeaders(String version, String resourceName, String correlationId) {

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
                resourceName,
                correlationId

        );
    }
}
