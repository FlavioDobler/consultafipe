package br.com.flavio.consultafipe.principal;

import br.com.flavio.consultafipe.models.Dados;
import br.com.flavio.consultafipe.models.Modelos;
import br.com.flavio.consultafipe.models.Veiculo;
import br.com.flavio.consultafipe.service.ConsultaApi;
import br.com.flavio.consultafipe.service.ConverterDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConsultaApi send = new ConsultaApi();
    private ConverterDados conversor = new ConverterDados();
    private  final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";



    public void exibeMenu() {

        var menu = """
                ****    OPÇOES      ****
                    Carro   
                    Moto
                    Caminhão
                    
                Digite uma das opções para consultar
                """;
        System.out.println(menu);

        var opcao = leitura.nextLine();
        String endereco;

        if(opcao.toLowerCase().contains("carro")){
            endereco = URL_BASE +"carros/marcas";
        } else if (opcao.toLowerCase().contains("motos")) {
            endereco = URL_BASE +"motos/marcas";
        } else {
            endereco = URL_BASE +"caminhoes/marcas";
        }

        var json = send.consulta(endereco);

        var dadosMarca = conversor.obterLista(json, Dados.class);
        dadosMarca.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

        System.out.println("Informe o código da marca para Consulta");
        var codMarca = leitura.nextLine();

        endereco = endereco + "/" + codMarca+"/modelos";
        json = send.consulta(endereco);

        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("Modelos dessa marca");

        modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\n Digite um trecho do nome do carro a ser buscado");
        var nomeVeiculo = leitura.nextLine();

        //Criar uma nova lista de modelos
        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m-> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos Filtrados ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo para buscas os valores de avaliacao");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/"+codigoModelo+"/anos";
        json = send.consulta(endereco);
        List<Dados> anos = conversor.obterLista(json,Dados.class);

        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = send.consulta(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        veiculos.forEach(System.out::println);

    }
}