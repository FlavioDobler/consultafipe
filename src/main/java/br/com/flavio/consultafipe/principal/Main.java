package br.com.flavio.consultafipe.principal;

import br.com.flavio.consultafipe.service.ConsultaApi;
import br.com.flavio.consultafipe.service.ConverterDados;

import java.util.Scanner;

public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConsultaApi send = new ConsultaApi();
    private ConverterDados conversor = new ConverterDados();
    private  String url = "https://parallelum.com.br/fipe/api/v1/";

    int tipo = 0;

    public void exibeMenu() {

        do {
            System.out.println("Qual o tipo de veículo desejado?");
            System.out.println("");
            System.out.println("1 - Carro");
            System.out.println("2 - Motos");
            System.out.println("3 - Caminhoes");
            System.out.println("4 - Sair");

            tipo = leitura.nextInt();

            switch (tipo){
                case 1:
                    send.consulta(url+"carros/marcas");
                    break;
                case 2:
                    send.consulta(url+"motos/marcas");
                    break;
                case 3:
                    send.consulta(url+"caminhoes/mascas");
                    break;
            }

        } while (tipo != 4);


    }
}