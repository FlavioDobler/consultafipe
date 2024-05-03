package br.com.flavio.consultafipe.service;

public interface IConverteDados {
        <T> T obterDados(String json, Class<T> classe); // Generic para aceitar qualquer coisa


}
