package org.coop.cotacao.service.interfaces;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ApplicationScoped
@RegisterRestClient(baseUri = "http://localhost:9101/cotacao-bcb-api")
public interface CotacaoServiceInterface {

    @GET
    @Path("/cotacao-dolar-dia/{data}")
    Cotacao getCotacaoDolarDia(@PathParam("data") String data);
    
    @JsonInclude(Include.NON_NULL)
    class Cotacao {
    	public Float cotacaoCompra;
    	public Float cotacaoVenda;
    	
    	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    	public LocalDateTime dataHoraCotacao;
    	
    	 @Override
     	public String toString() {
     		ObjectMapper mapper = new ObjectMapper();
     		mapper.registerModule(new JavaTimeModule());
     		mapper.enable(SerializationFeature.INDENT_OUTPUT);

     		try {
     			return mapper.writeValueAsString(this);
     		} catch (JsonProcessingException e) {
     			e.printStackTrace();
     		}

     		return null;
     	}
    }
}
