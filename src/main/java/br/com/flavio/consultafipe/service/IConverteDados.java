package br.com.flavio.consultafipe.service;

import java.util.List;

public interface IConverteDados {
        <T> T obterDados(String json, Class<T> classe); // Generic para aceitar qualquer coisa

        <T> List<T> obterLista(String json, Class<T> classe);

}
