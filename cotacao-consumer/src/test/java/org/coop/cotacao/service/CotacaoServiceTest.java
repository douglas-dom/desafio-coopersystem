package org.coop.cotacao.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import javax.inject.Inject;

import org.coop.cotacao.exceptions.ParametroInvalidoException;
import org.coop.cotacao.model.CotacaoResponse;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CotacaoServiceTest {
	
	@Inject
	CotacaoService service;
	
	@Inject
	Logger log;

	@Test
	void testGetCotacaoDolarDia() throws ParametroInvalidoException {
		CotacaoResponse cotacao = null;
		
		cotacao = service.getCotacaoDolarDia(LocalDate.of(2022,03,25));
		log.info(cotacao);
		assertNotNull(cotacao);
		
		cotacao = service.getCotacaoDolarDia(LocalDate.now().plusYears(1));
		log.info(cotacao);
		assertNull(cotacao);
		
	}

}
