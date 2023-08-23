package br.com.izabelanoe.screenmatch.principal;

import br.com.izabelanoe.screenmatch.service.ConsumoApi;

import java.util.Scanner;


public class Principal {
    String enderecoAPI = "https://www.omdbapi.com/?t=";
    String chaveCliente = "&apikey=eaebefb";
    String caminhoCompleto = " ";
    ConsumoApi consumoApi = new ConsumoApi();
    public void exibeMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da série: ");
        String nomeSerie = scanner.nextLine();

        String endPoint = enderecoAPI+nomeSerie.replace(" ", "+")+chaveCliente;
        var json = consumoApi.obterDados(enderecoAPI);

    }
}
