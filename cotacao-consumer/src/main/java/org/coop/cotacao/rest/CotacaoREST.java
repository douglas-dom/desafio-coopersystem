package org.coop.cotacao.rest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/cotacao-dolar-dia")
public class CotacaoREST {
	
	@Inject
	CotacaoService cotacaoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Traz a cotação do dolar na data informada, registrada no Banco Central do Brasil. Quando a data não é informada traz o valor da data atual.")
	@APIResponse(responseCode = "428", description = "Parametro de data inválido.")
	@APIResponse(responseCode = "200", description = "OK. OBS:  Objetos com valor nulo quando não existe cotação no dia.", 
		content = {@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, oneOf = CotacaoResponse.class))})
	public Response getCotacaoDia(@QueryParam("data") String dataParam) {
			
		CotacaoResponse cotacao;
		try {
			LocalDate data = null;
			
			if(Optional.ofNullable(dataParam).isEmpty()) {
				data = LocalDate.now();
			} else {				
				data = LocalDate.parse(dataParam);
			}
			cotacao = cotacaoService.getCotacaoDolarDia(data);
			return Response.ok(cotacao).build();
		} catch (ParametroInvalidoException|DateTimeParseException e) {
			return Response.status(Status.PRECONDITION_REQUIRED).entity(e.getMessage()).build();
		}
		
	}
}
