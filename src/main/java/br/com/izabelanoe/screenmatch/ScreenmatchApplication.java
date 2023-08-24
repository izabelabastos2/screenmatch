package br.com.izabelanoe.screenmatch;

import br.com.izabelanoe.screenmatch.principal.Detalhes;
import br.com.izabelanoe.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
// ************ UTILIZADO PARA RODAR MENU APLICAÇÃO PRINCIPAL ************

		Principal principal = new Principal();
		principal.exibeMenu();

// **************** UTILIZADO PARA RODAR CLASSE DE EXEMPLO ***************
//
//		Detalhes detalhes = new Detalhes();
//		detalhes.testaStream();
//
//*************************************************************************


	}
}
