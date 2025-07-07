# 💻 Sistema de Análise de Sentimentos em Comentários de Produtos com IA Brasileira.

![](https://media.beehiiv.com/cdn-cgi/image/fit=scale-down,format=auto,onerror=redirect,quality=80/uploads/asset/file/7c810313-5150-49ba-8c7d-7fa5971bbb65/maritaca-ai-4-1920x1080.png?t=1743194476)

## 📖 Descrição do Projeto

Este projeto é uma API REST desenvolvida em Java com Spring Boot, focada na análise de sentimentos em comentários de produtos. Usuários podem cadastrar-se, criar produtos e enviar comentários, que são automaticamente avaliados por uma IA (Maritaca AI) para identificar o sentimento e a qualidade do texto. O sistema armazena essas informações e oferece endpoints para consulta, filtragem e relatórios estatísticos.

---

## 🚀 Como Rodar o Projeto

1. **Pré-requisitos**:
   - Faça o download do [Docker Desktop](https://www.docker.com/get-started)
     - IMPORTANTE: se você utiliza Windows faça a instalação do WSL2: [Tutorial oficial da Microsoft](https://learn.microsoft.com/pt-br/windows/wsl/install)
     - ATENÇÃO: Recomendo fazer a instalação do **Docker Desktop** porque ele já vem com o **Docker Compose** instalado, que será necessário para rodar o projeto 
   - Gerar uma chave da API da [Maritaca AI](https://www.maritaca.ai/)
       
2. **Configuração e Execução Passo a Passo**:
   - Clone o repositório.
      ```sh
      git clone https://github.com/cristiantebaldi/classificacao-de-comentarios-api.git
      ```
   - Mova-se até o diretório do projeto.
      ```sh
      cd classificacao-de-comentarios-api
      ```
   - Dentro da raiz do projeto crie um arquivo chamado `.env`

   - No arquivo `.env` siga a mesma estrutura do arquivo [`example.env`](example.env)
      ```sh
      MARITACA_API_KEY=chave-gerada
      ```
   - No terminal rode o seguinte comando
      ```sh
      docker-compose up
      ```
     - **IMPORTANTE!!!**
       - Certifique-se que você esteja na raiz do projeto 
       - Certifique-se que o Docker Desktop esteja aberto ou rodando em segundo plano
   - Aguarde os containers serem construídos e a aplicação iniciar
   - E PRONTO!!! - A API estará rodando em: `http://localhost:8080` 🚀

---

## 🛠️ Como Usar os Endpoints

💡**Dica:** Utilize um Cliente HTTP (Insomnia, Postman, Bruno...) para executar as rotas da API, com essas ferramentas você pode criar uma request através de um comando `cURL`.


### 🧑‍💻 Exemplo Prático: Avaliando um Comentário com a IA
Para avaliar um comentário, siga estes passos:

1. **Crie um usuário:**
   ```sh
   curl -X POST http://localhost:8080/account \
     -H "Content-Type: application/json" \
     -d '{"username":"user1","name":"Usuário 1"}'
   ```
   > Guarde o `id` retornado (exemplo: `1`).

2. **Crie um produto (Valor do produto em Inteiro):** 
   ```sh
   curl -X POST http://localhost:8080/product \
     -H "Content-Type: application/json" \
     -d '{"name":"Produto X","price":100,"description":"Descrição"}'
   ```
   > Guarde o `id` retornado (exemplo: `1`).

3. **Envie um comentário para avaliação:**
   ```sh
   curl -X POST http://localhost:8080/review \
     -H "Content-Type: application/json" \
     -d '{"accountID":1,"productID":1,"comment":"Ótimo produto, recomendo!"}'
   ```
4. **Liste todas as avaliações do sistema**
    ```sh
    curl http://localhost:8080/review
    ```
   A resposta trará o resultado da análise feita pela IA, incluindo sentimento e score.

   **Exemplo de Retorno**
   ```sh
    [
      {
        "id": 1,
        "accountID": 1,
        "productID": 1,
        "classificationID": 5,
        "scoreID": 41,
        "comment": "Este produto é muito ruim",
        "product": "Bola de Futebol",
        "classification": "MUITO RUIM",
        "score": "40",
        "accountName": "usuário"
      }
    ]
   ```

---


### 🛣️Todos os Endpoints da API

#### Usuários

- **Criar usuário**
  ```sh
  curl -X POST http://localhost:8080/account -H "Content-Type: application/json" -d '{"username":"user1","name":"Usuário 1"}'
  ```
- **Buscar usuário por ID**
  ```sh
  curl http://localhost:8080/account/1
  ```
- **Listar todos os usuários**
  ```sh
  curl http://localhost:8080/account
  ```
- **Atualizar usuário**
  ```sh
  curl -X PUT http://localhost:8080/account/1 -H "Content-Type: application/json" -d '{"username":"user1","name":"Novo Nome"}'
  ```
- **Remover usuário**
  ```sh
  curl -X DELETE http://localhost:8080/account/1
  ```

#### Produtos

- **Criar produto**
  ```sh
  curl -X POST http://localhost:8080/product -H "Content-Type: application/json" -d '{"name":"Produto X","price":100,"description":"Descrição"}'
  ```
- **Buscar produto por ID**
  ```sh
  curl http://localhost:8080/product/1
  ```
- **Listar todos os produtos**
  ```sh
  curl http://localhost:8080/product
  ```
- **Atualizar produto**
  ```sh
  curl -X PUT http://localhost:8080/product/1 -H "Content-Type: application/json" -d '{"name":"Produto Y","price":150,"description":"Nova descrição"}'
  ```
- **Remover produto**
  ```sh
  curl -X DELETE http://localhost:8080/product/1
  ```

#### Comentários

- **Enviar comentário (com análise automática)**
  ```sh
  curl -X POST http://localhost:8080/review -H "Content-Type: application/json" -d '{"accountID":1,"productID":1,"comment":"Ótimo produto, recomendo!"}'
  ```
- **Buscar comentário por ID**
  ```sh
  curl http://localhost:8080/review/1
  ```
- **Listar todos os comentários**
  ```sh
  curl http://localhost:8080/review
  ```
- **Filtrar por produto**
  ```sh
  curl http://localhost:8080/review?productID=1
  ```
- **Filtrar por usuário**
  ```sh
  curl http://localhost:8080/review?accountID=1
  ```
- **Filtrar por sentimento**
  ```sh
  curl http://localhost:8080/review?classification=RUIM
  ```

#### Relatórios

- **Total de comentários por sentimento**
  ```sh
  curl http://localhost:8080/report/classification
  ```
- **Classificações por produto**
  ```sh
  curl http://localhost:8080/report/product
  ```
- **Ranking de usuários mais ativos**
  ```sh
  curl http://localhost:8080/report/account
  ```
- **Visão geral do sistema**
  ```sh
  curl http://localhost:8080/report/general
  ```

## 🧩 Aplicação dos Princípios SOLID

- **Single Responsibility Principle (SRP):** Cada classe tem uma responsabilidade única, separando controllers, use cases e repositórios.
- **Open/Closed Principle (OCP):**  As interfaces permitem extensão de funcionalidades sem modificar implementações existentes.
- **Liskov Substitution Principle (LSP):** Não foi utilizado em sua essência esse princípio.
- **Interface Segregation Principle (ISP):** Interfaces específicas para cada contexto (ex: `ProductRepository`, `ReviewRepository`).
- **Dependency Inversion Principle (DIP):** Uso de injeção de dependências do Spring para desacoplar as camadas.

---

## 💡 Desafios e Aprendizados
Tive vários desafios nesse projeto; acredito que os principais foram a questão da conexão com a IA, organização do projeto - principalmente em relação as rotas de relatórios - e aplicação dos princípios SOLID. Mas, em resumo aprendi muitas coisas durante o desenvolvimento do projeto como: melhores formas de se estruturar um projeto e como utilizar um serviço de terceiro em uma aplicação Spring Boot.

---

## 🗂️ Como acessar o pgAdmin
Caso você tenha o interesse de saber como funciona o relacionamento entre as tabelas você pode acessar o PgAdmin. Veja o tutorial abaixo!  
➡️ [Como acessar o pgAdmin](docs/pgadmin-acesso.md)

---

## 🚧 Melhorias Planejadas para Próximas Atualizações

- Implementar tratamento de erros mais robusto em todos os endpoints.
- Criar DTOs específicos para cada entidade, melhorando a clareza e segurança dos dados trafegados.
- Adicionar documentação interativa da API utilizando Swagger.

---

## 📬 Contato com o Desenvolvedor

Em caso de dúvidas, sugestões ou para relatar problemas, entre em contato:

- **Nome:** Cristian Tebaldi
- **E-mail:** cristiantebaldi@gmail.com
- **GitHub:** [cristiantebaldi](https://github.com/cristiantebaldi)

---

## 📝 Licença

Este projeto está licenciado sob a Licença MIT.  
Consulte o arquivo [`LICENSE`](LICENSE) para mais detalhes.

---
