package br.com.sistemalima.filmes.domain.mapper;

import br.com.sistemalima.filmes.domain.constant.ApiConstantVersion;
import br.com.sistemalima.filmes.domain.mapper.ObservabilidadeMapper;
import br.com.sistemalima.filmes.domain.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.domain.entity.Observabilidade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ObservabilidadeMapperTest {

    @InjectMocks
    private ObservabilidadeMapper observabilidadeMapper;

    private final static String version = ApiConstantVersion.versionApiOne;
    private final static String resourceName = "resourceName";
    private final static String correlationId = "correlationId";

    @Test
    public void deveMapearObservabilidade() {
        // Dado
        Observabilidade observabilidade = observabilidadeMapper.map(version, resourceName, correlationId);

        // Quando / Então

        assertEquals(version, observabilidade.getVersion());
        assertEquals(resourceName, observabilidade.getResourceName());
        assertEquals(correlationId, observabilidade.getCorrelationId());
    }

    @Test
    public void deveLancarBadRequestExceptionAoMapearObservabilidadeVersionInvalida() {
        // Dado / Quando / Então
        Assertions.assertThrows(BadRequestExceptions.class, () -> observabilidadeMapper.map("v2", resourceName, correlationId));


    }

}