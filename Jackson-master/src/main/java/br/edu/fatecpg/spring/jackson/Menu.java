package br.edu.fatecpg.spring.jackson;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.edu.fatecpg.spring.jackson.model.Carrinho;
import br.edu.fatecpg.spring.jackson.model.RespostaCarrinho;
import br.edu.fatecpg.spring.jackson.service.ConsomeApi;

public class Menu {

    public void exibirMenu() throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        int op = 0;

        while (op != 7) {

            System.out.println("-".repeat(50));
            System.out.println("SISTEMA DE ANÁLISE DE PEDIDOS");
            System.out.println("-".repeat(50));
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Carrinhos acima de US$ 1.000");
            System.out.println("2 - Produtos com desconto acima de 15%");
            System.out.println("3 - Carrinhos ordenados por economia");
            System.out.println("4 - Soma dos valores com desconto");
            System.out.println("5 - Quantidade de carrinhos por número de produtos");
            System.out.println("6 - Desafios extras");
            System.out.println("7 - Sair do programa");
            System.out.println("-".repeat(50));

            op = scanner.nextInt();

            switch (op) {

                case 1:

                    ConsomeApi apiService = new ConsomeApi();
                    RespostaCarrinho resposta = apiService.buscarCarrinhos();

                    if (resposta != null) {

                        System.out.println("\n=== CARRINHOS ACIMA DE US$ 1.000 ===");

                        resposta.getCarts()
                                .stream()
                                .filter(carrinho -> carrinho.getTotal() > 1000)
                                .forEach(carrinho ->
                                        System.out.println(carrinho)
                                );
                    }

                    break;

                case 2:

                    ConsomeApi apiService2 = new ConsomeApi();
                    RespostaCarrinho resposta2 = apiService2.buscarCarrinhos();

                    if (resposta2 != null) {

                        System.out.println(
                                "\n=== PRODUTOS COM DESCONTO ACIMA DE 15% ==="
                        );

                        resposta2.getCarts()
                                .stream()
                                .flatMap(carrinho ->
                                        carrinho.getProducts().stream()
                                )
                                .filter(produto ->
                                        produto.getDiscountPercentage() > 15
                                )
                                .map(produto ->
                                        produto.getTitle()
                                )
                                .forEach(titulo ->
                                        System.out.println(titulo)
                                );
                    }

                    break;

                case 3:

                    ConsomeApi apiService3 = new ConsomeApi();
                    RespostaCarrinho resposta3 = apiService3.buscarCarrinhos();

                    if (resposta3 != null) {

                        System.out.println(
                                "\n=== CARRINHOS ORDENADOS POR ECONOMIA ==="
                        );

                        resposta3.getCarts()
                                .stream()
                                .sorted(
                                        Comparator.comparingDouble(
                                                Carrinho::getEconomia
                                        ).reversed()
                                )
                                .forEach(carrinho ->
                                        System.out.println(
                                                "Carrinho #" + carrinho.getId()
                                                        + " | Economia: US$ "
                                                        + carrinho.getEconomia()
                                        )
                                );
                    }

                    break;

                case 4:

                    ConsomeApi apiService4 = new ConsomeApi();
                    RespostaCarrinho resposta4 = apiService4.buscarCarrinhos();

                    if (resposta4 != null) {

                        System.out.println(
                                "\n=== SOMA DOS VALORES COM DESCONTO ==="
                        );

                        double soma = resposta4.getCarts()
                                .stream()
                                .map(Carrinho::getDiscountedTotal)
                                .reduce(0.0, Double::sum);

                        System.out.println(
                                "Soma dos discountedTotal: US$ " + soma
                        );
                    }

                    break;

                case 5:

                    ConsomeApi apiService5 = new ConsomeApi();
                    RespostaCarrinho resposta5 = apiService5.buscarCarrinhos();

                    if (resposta5 != null) {

                        System.out.println(
                                "\n=== CARRINHOS POR NÚMERO DE PRODUTOS ==="
                        );

                        Map<Integer, Long> agrupamento =
                                resposta5.getCarts()
                                        .stream()
                                        .collect(
                                                Collectors.groupingBy(
                                                        Carrinho::getTotalProducts,
                                                        Collectors.counting()
                                                )
                                        );

                        agrupamento.forEach(
                                (quantidadeProdutos, quantidadeCarrinhos) ->
                                        System.out.println(
                                                quantidadeProdutos
                                                        + " produtos: "
                                                        + quantidadeCarrinhos
                                                        + " carrinho(s)"
                                        )
                        );
                    }

                    break;

                case 6:

                    ConsomeApi apiService6 = new ConsomeApi();
                    RespostaCarrinho resposta6 = apiService6.buscarCarrinhos();

                    if (resposta6 != null) {

                        System.out.println(
                                "\n=== DESAFIO 1 - MAIOR CARRINHO ==="
                        );

                        Optional<Carrinho> maiorCarrinho =
                                resposta6.getCarts()
                                        .stream()
                                        .max(
                                                Comparator.comparingDouble(
                                                        Carrinho::getTotal
                                                )
                                        );

                        maiorCarrinho.ifPresent(carrinho ->
                                System.out.println(
                                        "Carrinho #" + carrinho.getId()
                                                + " | Total: US$ "
                                                + carrinho.getTotal()
                                )
                        );

                        System.out.println(
                                "\n=== DESAFIO 2 - FORMATAÇÃO ==="
                        );

                        resposta6.getCarts()
                                .stream()
                                .forEach(carrinho ->
                                        System.out.println(
                                                "Carrinho #" + carrinho.getId()
                                                        + " | Usuário: "
                                                        + carrinho.getUserId()
                                                        + " | Itens: "
                                                        + carrinho.getTotalQuantity()
                                                        + " | Total: US$ "
                                                        + carrinho.getTotal()
                                        )
                                );
                    }

                    break;

                case 7:

                    System.out.println("Encerrando o programa...");

                    break;

                default:

                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}