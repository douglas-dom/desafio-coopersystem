package org.coop.cotacao.rest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.coop.cotacao.exceptions.ParametroInvalidoException;
import org.coop.cotacao.service.CotacaoBCBService;
import org.coop.cotacao.service.interfaces.CotacaoBCBServiceInterface.Cotacao;

@Path("/cotacao-dolar-dia")
public class CotacaoREST {
	
	@Inject
	CotacaoBCBService cotacaoBCB;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCotacaoDia(@QueryParam("data") String dataParam) {
			
		Cotacao cotacao;
		try {
			LocalDate data = LocalDate.parse(dataParam);
			cotacao = cotacaoBCB.getCotacaoDolarDia(data);
			return Response.ok(cotacao).build();
		} catch (ParametroInvalidoException|DateTimeParseException e) {
			return Response.status(Status.PRECONDITION_REQUIRED).entity(e.getMessage()).build();
		}
		
	}
}
