# Sistema de Análise de Pedidos

Projeto desenvolvido em Java para a **Tarefa de Recuperação da P1**, com o objetivo de consumir uma API REST pública, realizar a desserialização de dados JSON e gerar análises utilizando **Streams e Lambdas**.

## 📌 Objetivo

O sistema consome os dados de carrinhos de compras disponibilizados pela API pública **DummyJSON** e realiza diferentes operações de análise sobre os dados recebidos.

A proposta utiliza:

- Consumo de API REST;
- Desserialização de JSON;
- Classes Java para representação dos dados;
- Streams;
- Lambdas;
- `Optional`;
- Operações como `filter`, `map`, `flatMap`, `sorted`, `reduce` e `groupingBy`.

## 🚀 Tecnologias utilizadas

- **Java**
- **Maven**
- **Jackson**
- **Java HttpClient**
- **Streams API**
- **Lambdas**

## 🌐 API utilizada

O projeto utiliza a API pública DummyJSON:

```text
https://dummyjson.com/carts?limit=0
```

O parâmetro `limit=0` é utilizado para obter todos os carrinhos disponíveis.

## 📂 Estrutura do projeto

```text
src
└── main
    └── java
        └── br
            └── edu
                └── fatecpg
                    └── spring
                        └── jackson
                            ├── Main.java
                            ├── Menu.java
                            │
                            ├── model
                            │   ├── Carrinho.java
                            │   ├── ProdutoCarrinho.java
                            │   └── RespostaCarrinho.java
                            │
                            └── service
                                └── ConsomeApi.java
```

## 📊 Funcionalidades

O sistema possui um menu para executar as análises solicitadas na atividade.

### 1. Carrinhos acima de US$ 1.000

Utiliza `filter` para encontrar somente os carrinhos cujo valor total seja superior a US$ 1.000.

```java
.filter(carrinho -> carrinho.getTotal() > 1000)
```

### 2. Produtos com desconto acima de 15%

Utiliza `flatMap` para reunir os produtos de todos os carrinhos, `filter` para selecionar os produtos com desconto superior a 15% e `map` para obter seus títulos.

```java
.flatMap(carrinho -> carrinho.getProducts().stream())
.filter(produto -> produto.getDiscountPercentage() > 15)
.map(produto -> produto.getTitle())
```

### 3. Carrinhos ordenados por economia

Os carrinhos são ordenados pela economia, da maior para a menor.

A economia é calculada através de:

```text
total - discountedTotal
```

A ordenação utiliza `sorted` e `Comparator`.

### 4. Soma dos valores com desconto

Utiliza `reduce` para calcular a soma dos valores `discountedTotal` de todos os carrinhos.

```java
.reduce(0.0, Double::sum)
```

### 5. Quantidade de carrinhos por número de produtos

Utiliza `groupingBy` para agrupar os carrinhos de acordo com o valor de `totalProducts`.

```java
Collectors.groupingBy(
    Carrinho::getTotalProducts,
    Collectors.counting()
)
```

## ⭐ Desafios extras

O projeto também implementa os dois desafios propostos na atividade.

### Maior carrinho

Utiliza `max` e `Optional` para encontrar o carrinho de maior valor.

### Formatação da saída

Os dados do carrinho são apresentados no formato:

```text
Carrinho #[id] | Usuário: [userId] | Itens: [totalQuantity] | Total: US$ [valor]
```

## ⚙️ Tratamento de erros

O consumo da API possui tratamento para situações como:

- Falha de conexão;
- Status HTTP diferente de 200;
- Erros durante o processamento da resposta;
- JSON inválido.

Quando ocorre um erro, uma mensagem é exibida no console sem interromper o programa de forma inesperada.

## ▶️ Como executar

### 1. Clone o repositório

```bash
git clone URL_DO_REPOSITORIO
```

### 2. Abra o projeto

Abra o projeto em uma IDE compatível com Java e Maven, como IntelliJ IDEA ou Eclipse.

### 3. Aguarde o Maven carregar as dependências

As dependências utilizadas pelo projeto estão definidas no `pom.xml`.

### 4. Execute o programa

Execute a classe:

```text
Main.java
```

O sistema apresentará um menu no terminal para escolher a análise desejada.

## 📚 Objetivo acadêmico

Este projeto foi desenvolvido como atividade de recuperação da P1, com foco na prática de:

- Orientação a objetos;
- Consumo de APIs;
- Desserialização de JSON;
- Streams;
- Expressões Lambda;
- Manipulação e análise de coleções em Java.
