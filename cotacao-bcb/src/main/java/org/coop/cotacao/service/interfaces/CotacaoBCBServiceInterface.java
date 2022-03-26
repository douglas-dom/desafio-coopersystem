package org.coop.cotacao.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/")
public interface CotacaoBCBServiceInterface {

    @GET
    @Path("/CotacaoDolarDia(dataCotacao=@dataCotacao)")
    CotacaoDia getCotacaoDolarDia(@QueryParam("@dataCotacao") String data, @QueryParam("$format") String format);

    class CotacaoDia {
    	@JsonProperty("@odata.context")
        public String context;
        public List<Cotacao> value;
        
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
    
    class Cotacao {
    	public float cotacaoCompra;
    	public float cotacaoVenda;
    	
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
