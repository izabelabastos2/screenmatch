package br.com.izabelanoe.screenmatch.principal;

import br.com.izabelanoe.screenmatch.model.DadosEpisodio;
import br.com.izabelanoe.screenmatch.model.DadosSerie;
import br.com.izabelanoe.screenmatch.model.Temporada;
import br.com.izabelanoe.screenmatch.service.ConsumoApi;
import br.com.izabelanoe.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;


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

        for (int i = 1; i <= totalTemporadas  ; i++) {
			json = consumoApi.obterDados(enderecoAPI+nomeSerie.replace(" ", "+")+"&season="+i+chaveCliente);
			Temporada dadosTemporada = conversor.obterDados(json, Temporada.class);
			temporadaList.add(dadosTemporada);
		}
        temporadaList.forEach(System.out::println);

//************************ FORMA DE FAZER ANTES DO JAVA 8 ***************************
//        List<DadosEpisodio> episodiosTemporada = new ArrayList<>();
//
//        for (int j = 0; j < totalTemporadas; j++) {
//            episodiosTemporada = temporadaList.get(j).episodios();
//            for (DadosEpisodio dadosEpisodio : episodiosTemporada) {
//                System.out.println(dadosEpisodio.titulo());
//            }
//        }
//************************************************************************************

        temporadaList.forEach((t->t.episodios().forEach( e -> System.out.println(e.titulo()))));

//JUNTAR DADOS DE EPISÓDIOS E TEMPORADAS EM UMA LISTA SÓ

        List<DadosEpisodio> dadosEpisodios = temporadaList
                .stream()
                .flatMap(t -> t.episodios().stream())
              //  .collect(Collectors.toList());
                .toList(); //rretorna uma lista imutável, se tentar alterá-la retorna uma exeção

        System.out.println("Top 5 episódios");
        dadosEpisodios
                .stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);


        //pegar numero temporada
        //pegar todos epsódios dessa temporada
        //adicionar os dois na lista
        //fazer o mesmo para próxima temporada
        //executar para todas temporadas relacionadas a série





    }
}
