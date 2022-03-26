package org.coop.cotacao.model;

import java.time.LocalDate;

import org.coop.cotacao.service.interfaces.CotacaoServiceInterface.Cotacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;;

public class CotacaoResponse {
	
	@JsonFormat(shape = Shape.STRING)
	private LocalDate dataCotacao;
	private Cotacao valorCotacao;
	
	@JsonFormat(shape = Shape.STRING)
	private LocalDate dataCotacaoAnterior;
	private Cotacao valorCotacaoAnterior;
	
	public CotacaoResponse() {}
	
	public CotacaoResponse(LocalDate dataCotacao, Cotacao valorCotacao, LocalDate dataCotacaoAnterior,
			Cotacao valorCotacaoAnterior) {
		this.dataCotacao = dataCotacao;
		this.valorCotacao = valorCotacao;
		this.dataCotacaoAnterior = dataCotacaoAnterior;
		this.valorCotacaoAnterior = valorCotacaoAnterior;
	}

	public LocalDate getDataCotacao() {
		return dataCotacao;
	}



	public void setDataCotacao(LocalDate dataCotacao) {
		this.dataCotacao = dataCotacao;
	}



	public Cotacao getValorCotacao() {
		return valorCotacao;
	}



	public void setValorCotacao(Cotacao valorCotacao) {
		this.valorCotacao = valorCotacao;
	}



	public LocalDate getDataCotacaoAnterior() {
		return dataCotacaoAnterior;
	}



	public void setDataCotacaoAnterior(LocalDate dataCotacaoAnterior) {
		this.dataCotacaoAnterior = dataCotacaoAnterior;
	}



	public Cotacao getValorCotacaoAnterior() {
		return valorCotacaoAnterior;
	}



	public void setValorCotacaoAnterior(Cotacao valorCotacaoAnterior) {
		this.valorCotacaoAnterior = valorCotacaoAnterior;
	}



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
