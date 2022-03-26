package org.coop.cotacao.service.interfaces;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.coop.cotacao.service.interfaces.CotacaoServiceInterface.Cotacao;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CotacaoServiceInterfaceTest {
	
	@Inject
    @RestClient
    CotacaoServiceInterface cotacaoBCB;
	
	@Inject
	Logger log;

	@Test
	void testCotacaoBCBServiceInteface() {
		
		Cotacao cotacao = cotacaoBCB.getCotacaoDolarDia("2022-03-25");
		assertNotNull(cotacao);
		log.info(cotacao);
	}

}
