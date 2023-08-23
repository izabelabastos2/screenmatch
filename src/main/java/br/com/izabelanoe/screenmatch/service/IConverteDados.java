package br.com.izabelanoe.screenmatch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
