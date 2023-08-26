package br.com.izabelanoe.screenmatch.principal;

import br.com.izabelanoe.screenmatch.model.DadosEpisodio;
import br.com.izabelanoe.screenmatch.model.DadosSerie;
import br.com.izabelanoe.screenmatch.model.Episodio;
//import br.com.izabelanoe.screenmatch.model.Episodio;
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

        /**
         * 
         */
        public void exibeMenu() {

                System.out.println("*******************************************");
                System.out.println("Digite o nome da série: ");
                System.out.println("*******************************************");

                String nomeSerie = scanner.nextLine();
                String endPoint = enderecoAPI + nomeSerie.replace(" ", "+") + chaveCliente;
                var json = consumoApi.obterDados(endPoint);

                DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

                System.out.println("*******************************************");
                System.out.println("Dados da série: \n");
                System.out.println("*******************************************");
                System.out.println(dados);

                Integer totalTemporadas = dados.totalTemporadas();
                List<Temporada> temporadaList = new ArrayList<>();

                for (int i = 1; i <= totalTemporadas; i++) {
                        json = consumoApi.obterDados(
                                        enderecoAPI + nomeSerie.replace(" ", "+") + "&season=" + i + chaveCliente);
                        Temporada dadosTemporada = conversor.obterDados(json, Temporada.class);
                        temporadaList.add(dadosTemporada);
                }
                System.out.println("*******************************************");
                System.out.println("Relação dos episódios de cada temporada: \n");
                System.out.println("*******************************************");
                temporadaList.forEach(System.out::println);

                // ************************ FORMA DE FAZER ANTES DO JAVA 8
                // ***************************
                // List<DadosEpisodio> episodiosTemporada = new ArrayList<>();
                //
                // for (int j = 0; j < totalTemporadas; j++) {
                // episodiosTemporada = temporadaList.get(j).episodios();
                // for (DadosEpisodio dadosEpisodio : episodiosTemporada) {
                // System.out.println(dadosEpisodio.titulo());
                // }
                // }
                // ************************************************************************************

                System.out.println("*******************************************");
                System.out.println("Título dos epsódios da lista de temporadasgameo: \n");
                System.out.println("*******************************************");
                temporadaList.forEach((t -> t.episodios().forEach(e -> System.out.println(e.titulo()))));

                System.out.println("*******************************************");
                System.out.println("Qtn de temporadas: \n");
                System.out.println("*******************************************");
                temporadaList.forEach(
                                (temporada -> System.out
                                                .println("Temporada: " + (temporadaList.indexOf(temporada) + 1) +
                                                                ": " + temporada.episodios().size() + " episódios")));
                // JUNTAR DADOS DE EPISÓDIOS E TEMPORADAS EM UMA LISTA SÓ

                List<DadosEpisodio> dadosEpisodios = temporadaList
                                .stream()
                                .flatMap(t -> t.episodios().stream())
                                // .collect(Collectors.toList());
                                .toList(); // rretorna uma lista imutável, se tentar alterá-la retorna uma exeção

                System.out.println("*******************************************************************");
                System.out.println("Top 5 episódios: \n");
                System.out.println("*******************************************************************");

                dadosEpisodios
                                .stream()
                                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                                .limit(5)
                                .forEach(System.out::println);

                System.out.println("*******************************************************************");
                System.out.println("Relação dos episódios para cada temporada mostrando a temporada: \n");
                System.out.println("*******************************************************************");
                List<Episodio> episodios = temporadaList.stream()
                                .flatMap(t -> t.episodios().stream()
                                                .map(de -> new Episodio(t.numero(), de)))
                                .collect(Collectors.toList());

                episodios.forEach(System.out::println);

                System.out.println("*******************************************************************");
                System.out.println("            BUSCA POR EPISÓDIO:                                    \n");
                System.out.println("*******************************************************************");
                System.out.println("Digite o nome do episódio\n");
                System.out.println("*******************************************************************");

                var buscaEpisodio = scanner.nextLine();
                var buscaEpisodioFiltrado = buscaEpisodio.replace(" ", "");

                List<Episodio> episodioBuscado = episodios.stream()
                                .filter(e -> e.contains(buscaEpisodioFiltrado.toUpperCase()))
                                .collect(Collectors.toList());

                episodioBuscado.forEach(e -> System.out
                                .println("Resultado: " + episodioBuscado));
        }
}
