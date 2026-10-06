package br.edu.fatecpg.spring.jackson.service;

import br.edu.fatecpg.spring.jackson.model.RespostaCarrinho;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsomeApi {

    private static final String URL = "https://dummyjson.com/carts?limit=0";

    public RespostaCarrinho buscarCarrinhos() {

        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println(
                        "Erro na requisição. Status HTTP: "
                                + response.statusCode()
                );
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(
                    response.body(),
                    RespostaCarrinho.class
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro de conexão ou leitura da resposta: "
                            + e.getMessage()
            );

        } catch (InterruptedException e) {

            System.out.println(
                    "A requisição foi interrompida."
            );

            Thread.currentThread().interrupt();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao processar o JSON: "
                            + e.getMessage()
            );
        }

        return null;
    }
}