package org.coop.cotacao.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import javax.inject.Inject;

import org.coop.cotacao.exceptions.ParametroInvalidoException;
import org.coop.cotacao.service.interfaces.CotacaoBCBServiceInterface.Cotacao;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CotacaoBCBServiceTest {
	
	@Inject
	CotacaoBCBService service;
	
	@Inject
	Logger log;

	@Test
	void testGetCotacaoDolarDia() throws ParametroInvalidoException {
		Cotacao cotacao = null;
		
		cotacao = service.getCotacaoDolarDia(LocalDate.now());
		log.info(cotacao);
		assertNotNull(cotacao);
		
		cotacao = service.getCotacaoDolarDia(LocalDate.now().plusYears(1));
		log.info(cotacao);
		assertNull(cotacao);
		
	}

}
