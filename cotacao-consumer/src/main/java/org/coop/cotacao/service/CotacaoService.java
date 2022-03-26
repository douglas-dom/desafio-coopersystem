package org.coop.cotacao.service;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.coop.cotacao.exceptions.ParametroInvalidoException;
import org.coop.cotacao.model.CotacaoResponse;
import org.coop.cotacao.service.interfaces.CotacaoServiceInterface;
import org.coop.cotacao.service.interfaces.CotacaoServiceInterface.Cotacao;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CotacaoService {
	
	@Inject
	@RestClient
	CotacaoServiceInterface cotacaoServiceInterface;
	
	@Inject
	Logger log;
	
	public CotacaoResponse getCotacaoDolarDia(LocalDate data) throws ParametroInvalidoException {
		
		if(data == null) {
			throw new ParametroInvalidoException("Data deve ser preenchida."); 
		} else if(data.isAfter(LocalDate.now())) {
			return null;
		}
		
		LocalDate dataAnterior = data.minusDays(1);
		
		log.info("Solicitando cotação da data - "+data.toString());
		Cotacao cotacaoDiaAtual = cotacaoServiceInterface.getCotacaoDolarDia(data.toString());
		log.info(cotacaoDiaAtual);
		log.info("Solicitando cotação da data - "+data.toString());
		Cotacao cotacaoDiaAnterior = cotacaoServiceInterface.getCotacaoDolarDia(dataAnterior.toString());
		log.info(cotacaoDiaAnterior);
		
		return new CotacaoResponse(data, cotacaoDiaAtual, dataAnterior, cotacaoDiaAnterior);
		
	}

}
