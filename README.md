# GC Java 2026

Exercícios e projetos do curso de Java da Geração Caldeira, organizados por tema num único projeto Maven (Java 21, Spring Boot 3.5.7).

## Estrutura

```
gc-java-2026/
├── pom.xml
├── docs/
│   ├── aulas/            PDFs das aulas (Git, POO, aula 03)
│   ├── desafio-gc/       Material do Desafio GC (Fruki, missão dos grupos)
│   └── code-review/      Relatório "Sete erros" dos Autobots
└── src/
    ├── main/java/com/gc26/
    │   ├── fundamentos/
    │   │   ├── excecoes/        try/catch
    │   │   ├── oldsteve/        picaretas: procedural e orientado a objetos
    │   │   ├── stardew/         herança e interface (menu do curral)
    │   │   ├── barbie/          GuardaRoupa (atividade de testes)
    │   │   ├── cybertron/       autobot (code review) e forja (camadas)
    │   │   └── revisaocodigo/   checkout e transacao (desafio de refatoração)
    │   ├── injecao/
    │   │   ├── hello/           injeção de dependência manual
    │   │   └── pedidos/         controller, service e repository sem Spring
    │   ├── http/                requisições GET e POST com java.net.http
    │   └── spring/
    │       ├── apirest/         consome PokeAPI e DummyJSON (porta 8081)
    │       └── pokemoncrud/     CRUD de Pokémons em memória (porta 8080)
    └── test/java/com/gc26/      testes espelhando os pacotes acima
```

## Como rodar

Testes: `mvn test`

Aplicações simples (qualquer classe com `main`): no IntelliJ, botão direito na classe, Run.

APIs Spring:

```
mvn spring-boot:run
mvn spring-boot:run -Dspring-boot.run.main-class=com.gc26.spring.apirest.ApiRestApplication
```

A primeira sobe o CRUD em http://localhost:8080/pokemons. A segunda sobe em http://localhost:8081, com `GET /pokeApi/pokemon/{nome}` e `POST /productApi/product/add/{nome}` (precisam de internet).

## Rotas do CRUD de Pokémons

| Método | Rota | Descrição | Status |
|--------|------|-----------|--------|
| GET | /pokemons?limite=10 | Lista (máximo 50) | 200 |
| GET | /pokemons/{id} | Busca um | 200 / 404 |
| POST | /pokemons | Cria (`{"nome":"Eevee","tipo":"Normal"}`) | 201 / 400 |
| PUT | /pokemons/{id} | Atualiza nome e tipo | 200 / 400 / 404 |
| DELETE | /pokemons/{id} | Remove | 204 / 404 |

Os erros saem como `{"erro": "mensagem"}`.
