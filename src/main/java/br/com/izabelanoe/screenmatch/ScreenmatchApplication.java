package br.com.izabelanoe.screenmatch;

import br.com.izabelanoe.screenmatch.model.DadosEpisodio;
import br.com.izabelanoe.screenmatch.model.DadosSerie;
import br.com.izabelanoe.screenmatch.model.Temporada;
import br.com.izabelanoe.screenmatch.service.ConsumoApi;
import br.com.izabelanoe.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=eaebefb");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

		json =consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=eaebefb");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		Integer totalTemporadas = dados.totalTemporadas();
		for (int i = 1; i <= totalTemporadas  ; i++) {
			json =consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season="+ i +"&&apikey=eaebefb");
			Temporada dadosTemporada = conversor.obterDados(json, Temporada.class);
			List<Temporada> temporadaList = new ArrayList<>();
			temporadaList.add(dadosTemporada);

			temporadaList.forEach(System.out::println);
		}
	}
}
