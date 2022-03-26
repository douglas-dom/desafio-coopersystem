package org.coop.cotacao.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.coop.cotacao.exceptions.ParametroInvalidoException;
import org.coop.cotacao.service.interfaces.CotacaoBCBServiceInterface;
import org.coop.cotacao.service.interfaces.CotacaoBCBServiceInterface.Cotacao;
import org.coop.cotacao.service.interfaces.CotacaoBCBServiceInterface.CotacaoDia;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CotacaoBCBService {
	
	@Inject
	@RestClient
	CotacaoBCBServiceInterface cotacaoBCB;
	
	@Inject
	Logger log;
	
	private static final String FORMAT = "json";
	private static final DateTimeFormatter DATA_FORMAT = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	
	public Cotacao getCotacaoDolarDia(LocalDate data) throws ParametroInvalidoException {
		
		if(data == null) {
			throw new ParametroInvalidoException("Data deve ser preenchida."); 
		} else if(data.isAfter(LocalDate.now())) {
			return null;
		}
		
		
		log.info("Solicitando cotação da data - "+data.format(DATA_FORMAT).toString());
		CotacaoDia cotacaoDia = cotacaoBCB.getCotacaoDolarDia("'" + data.format(DATA_FORMAT).toString() + "'", FORMAT);
		log.info(cotacaoDia);
		return cotacaoDia.value.stream().findFirst().orElse(null);
		
	}

}
