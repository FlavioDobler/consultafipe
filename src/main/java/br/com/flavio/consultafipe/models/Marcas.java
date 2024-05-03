package br.com.flavio.consultafipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Marcas(String nome, String codigo) {

}
