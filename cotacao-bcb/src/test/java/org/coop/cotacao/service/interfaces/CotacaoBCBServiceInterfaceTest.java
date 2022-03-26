package org.coop.cotacao.service.interfaces;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.coop.cotacao.service.interfaces.CotacaoBCBServiceInterface.CotacaoDia;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CotacaoBCBServiceInterfaceTest {
	
	@Inject
    @RestClient
    CotacaoBCBServiceInterface cotacaoBCB;
	
	@Inject
	Logger log;

	@Test
	void testCotacaoBCBServiceInteface() {
		CotacaoDia cotacao = cotacaoBCB.getCotacaoDolarDia("'03-23-2022'", "json");
		assertNotNull(cotacao);
		log.info(cotacao);
	}

}
