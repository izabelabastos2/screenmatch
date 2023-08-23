package br.com.izabelanoe.screenmatch.principal;

import br.com.izabelanoe.screenmatch.model.DadosEpisodio;
import br.com.izabelanoe.screenmatch.model.DadosSerie;
import br.com.izabelanoe.screenmatch.model.Temporada;
import br.com.izabelanoe.screenmatch.service.ConsumoApi;
import br.com.izabelanoe.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Principal {
    Scanner scanner = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    String enderecoAPI = "https://www.omdbapi.com/?t=";
    String chaveCliente = "&apikey=eaebefb";
    String caminhoCompleto = " ";
    ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){

        System.out.println("Digite o nome da série: ");
        String nomeSerie = scanner.nextLine();
        String endPoint = enderecoAPI+nomeSerie.replace(" ", "+")+chaveCliente;
        var json = consumoApi.obterDados(endPoint);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);


    	Integer totalTemporadas = dados.totalTemporadas();
        List<Temporada> temporadaList = new ArrayList<>();
        List<DadosEpisodio> episodiosTemporada = new ArrayList<>();

        for (int i = 1; i <= totalTemporadas  ; i++) {
			json =consumoApi.obterDados(enderecoAPI+nomeSerie.replace(" ", "+")+"&season="+i+chaveCliente);
			Temporada dadosTemporada = conversor.obterDados(json, Temporada.class);
			temporadaList.add(dadosTemporada);
		}
        temporadaList.forEach(System.out::println);


//        for (int j = 0; j < totalTemporadas; j++) {
//            episodiosTemporada = temporadaList.get(j).episodios();
//            for (DadosEpisodio dadosEpisodio : episodiosTemporada) {
//                System.out.println(dadosEpisodio.titulo());
//            }
//        }

        temporadaList.forEach((t->t.episodios().forEach( e -> System.out.println(e.titulo()))));



    }
}
