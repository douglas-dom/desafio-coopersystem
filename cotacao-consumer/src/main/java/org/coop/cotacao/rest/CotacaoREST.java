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
import org.coop.cotacao.model.CotacaoResponse;
import org.coop.cotacao.service.CotacaoService;

@Path("/cotacao-dolar-dia")
public class CotacaoREST {
	
	@Inject
	CotacaoService cotacaoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCotacaoDia(@QueryParam("data") String dataParam) {
			
		CotacaoResponse cotacao;
		try {
			LocalDate data = LocalDate.parse(dataParam);
			cotacao = cotacaoService.getCotacaoDolarDia(data);
			return Response.ok(cotacao).build();
		} catch (ParametroInvalidoException|DateTimeParseException e) {
			return Response.status(Status.PRECONDITION_REQUIRED).entity(e.getMessage()).build();
		}
		
	}
}
