package br.com.izabelanoe.screenmatch.principal;

import java.util.Arrays;
import java.util.List;

public class Detalhes {

    // STREAM permite realizar operações de forma mais eficiente e concisa
    // utilizando uma abordagem funcional
    // Um stream é uma sequência de elementos que pode ser processada em paralelo ou
    // em série
    // Pode ser criada a partir de uma coleção, um array, um arquivo, entre outros.

    // As operações intermediárias são aquelas que pode ser aplicadas em uma stream
    // e retornam uma nova stream como resultado
    // Essas operações não são executadas imediatamente, mas apenas quando uma
    // operação final é chamada

    // Exemplos de operações INTERMEDIARIAS:
    // FILTER: Filtra os elementos do stream com base em uma condição
    // MAP: Permite transformar cada elemento da stream em outro tipo de dado

    // As operações FINAIS são aquelas que encerram a stream e retornam um resultado
    // concreto
    // Algumas operações finais são: forEach;collect e count
    // COLLECT: permite coletar os elementos da stream em uma coleção ou em outro
    // tipo de dados.
    // Por exemplo, podemos coletar os números pares em um conjunto.

    // COLOCAR OS DADOS GERADOS EM OUTRA LISTA
    // LISTA IMUTÁVEL
    // TOLIST() -> Colocar os dados gerados em outra lista
    // LISTA MODIFICÁVEL:
    // collect(Collectors.toList())

    // Além das operações intermediárias e finais existem muitas outras disponíveis,
    // como:
    // DISTINCT -> REMOVE ELEMENTOS DUPLICADOS
    // LIMIT -> LIMITA O NÚMERO DE ELEMENTOS
    // SKIP -> PULA OS PRIMEIROS ELEMENTOS DA STREAM
    // REDUCE -> COMBINA OS ELEMENTOS DA STREAM EM UM ÚNICO RESULTADO
    // ETC

    public void testaStream() {
        List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");

        // ordenar
        nomes.stream() // stream = fluxo de dados -> novo fluxo de dados
                .sorted() // operação intermediária
                .limit(3)
                .filter(n -> n.startsWith("N"))
                .map(n -> n.toUpperCase()) // map = transformação
                .forEach(System.out::println); // operação final
    }

    // clientList.stream()
    // .filter(c -> c.getTipoConsta().equalsIgnoreCase("corrente"))
    // .sorted(Comparator.comparingDouble(Conta::gerSaldo).reversed())
    //
    // .forEach(System.out::println)

}
