<p align="center">
  <img src="public/banner.svg" alt="SunShare Banner"/>
</p>

# SunShare

SunShare é uma plataforma digital com interface em linha de comando (CLI) para negociação direta de energia renovável entre produtores e consumidores, promovendo eficiência, transparência e sustentabilidade no mercado energético brasileiro.

> Projeto acadêmico interdisciplinar de semestre realizado para a faculdade.

---

## 1. Descrição da Proposta do Projeto

O SunShare foi criado para solucionar o problema do desperdício de excedentes de energia renovável, especialmente solar, no Brasil. Tradicionalmente, produtores de energia direcionam seu excedente para estatais ou empresas privadas, recebendo apenas créditos ou descontos limitados. O SunShare propõe uma alternativa inovadora e eficiente, permitindo que fornecedores e compradores negociem diretamente, sem intermediários, por meio de uma plataforma segura, flexível e transparente. Dessa forma, a solução contribui para a descentralização do mercado de energia, amplia oportunidades de negócio, incentiva o uso de energia limpa e está alinhada aos Objetivos de Desenvolvimento Sustentável (ODS 7), promovendo o aumento da participação de energias renováveis na matriz energética nacional.

---

## 2. Funcionalidades

-   Cadastro e autenticação de usuários (compradores e fornecedores)
-   Cadastro de ofertas de energia (kW ou MW), com valores e quantidades flexíveis
-   Consulta e filtragem de ofertas disponíveis
-   Propostas de compra para ofertas abertas
-   Aceite, rejeição e cancelamento de propostas
-   Notificações automáticas para eventos relevantes (novas propostas, aceite/rejeição, conclusão de transações)
-   Visualização de histórico de compras, vendas e notificações
-   Armazenamento seguro e persistente de dados em arquivos JSON
-   Suporte a múltiplos perfis de usuário (residencial, comercial, industrial)
-   Estrutura modular, facilitando manutenção e evolução do sistema

---

## 3. Tecnologias Utilizadas

-   **Java 17 ou superior**
-   **Gradle** (automação de build)
-   **Jackson** (serialização e manipulação de JSON)
-   **Jansi** (cores e formatação no terminal)
-   Estrutura de dados persistente em arquivos JSON (sem dependência de banco de dados externo)

---

## 4. Instalação e Execução

### Usuários Finais (Release)

1. Baixe o arquivo `sunshare-app-fat.jar` na seção de [Releases](https://github.com/ngracioli/SunShare/releases) deste repositório.
2. Certifique-se de que a pasta `database/` esteja no mesmo diretório do JAR ou copie-a do repositório.
3. Execute o sistema com o comando:

    ```sh
    java -jar sunshare-app-fat.jar
    ```

4. Siga as instruções do menu interativo no terminal.

> Todos os dados são salvos na pasta `database/`.

### Desenvolvedores

1. Clone o repositório:

    ```sh
    git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
    cd SunShare/app
    ```

2. Gere o JAR executável (fat JAR):

    ```sh
    ./gradlew fatJar
    ```

    O arquivo será criado em `app/build/libs/sunshare-app-fat.jar`.

3. Para rodar em modo desenvolvimento, utilize:

    ```sh
    ./gradlew run
    ```

    ou

    ```sh
    ./gradlew installDist
    ./app/build/install/app/bin/app
    ```
