# Anotações

## Sumário

- [Anotações](#anotações)
  - [Sumário](#sumário)
  - [Dicas](#dicas)
    - [Como executar projetos Java com Maven e Spring Boot?](#como-executar-projetos-java-com-maven-e-spring-boot)
    - [Para que serve o `@ComponentScan`?](#para-que-serve-o-componentscan)
    - [`ResponseEntity`](#responseentity)
    - [Como utilziar a anotação `@Autowired`?](#como-utilziar-a-anotação-autowired)
  - [Conceitos](#conceitos)
    - [O que é Spring Boot?](#o-que-é-spring-boot)
    - [O que é Maven?](#o-que-é-maven)
    - [O que é API REST?](#o-que-é-api-rest)
    - [O que é Inversão de Controle (IOC)?](#o-que-é-inversão-de-controle-ioc)
  - [Quiz](#quiz)

## Dicas

### Como executar projetos Java com Maven e Spring Boot?

Para executar projetos que utilizam o Apache Maven e o framework Spring Boot, siga as etapas abaixo. Certifique-se de que você já tenha o Maven e o Spring Boot instalados em seu ambiente de desenvolvimento.

1. **Configurar o Ambiente**:
   - Certifique-se de ter o Maven instalado. Você pode verificar se o Maven está instalado executando o comando `mvn -version` no terminal. Caso não esteja instalado, você pode baixá-lo e instalá-lo a partir do site oficial do Apache Maven.

   - Certifique-se de ter o Spring Boot configurado em seu projeto. Normalmente, você pode adicionar a dependência do Spring Boot ao seu arquivo `pom.xml` para configurar o projeto Spring Boot. O arquivo `pom.xml` é o arquivo de configuração do Maven.

2. **Criar um Projeto Spring Boot**:
   - Você pode criar um novo projeto Spring Boot usando o Spring Initializr, que é um assistente de configuração de projeto Spring Boot online. Após configurar as opções desejadas, você pode baixar o projeto como um arquivo ZIP. Em seguida, extraia o arquivo ZIP em um diretório de sua escolha.

3. **Navegar para o Diretório do Projeto**:
   - Use o terminal ou a linha de comando para navegar até o diretório raiz do projeto Spring Boot.

4. **Executar o Projeto Spring Boot**:
   - No diretório raiz do projeto, você pode usar o Maven para executar o aplicativo Spring Boot com o seguinte comando:

   ```cmd
   mvn spring-boot:run
   ```

   Esse comando compila e executa o aplicativo Spring Boot. Após a conclusão da compilação, o aplicativo será iniciado e estará disponível em `http://localhost:8080` por padrão, a menos que você tenha configurado uma porta diferente.

5. **Acessar o Aplicativo**:
   - Abra um navegador da web e acesse `http://localhost:8080` ou a porta que você configurou para ver o aplicativo em execução. Dependendo do tipo de aplicativo que você criou, você verá a página inicial do aplicativo ou uma mensagem de boas-vindas do Spring Boot.

6. **Interromper a Execução**:
   - Para parar a execução do aplicativo Spring Boot, pressione `Ctrl + C` no terminal onde o aplicativo está sendo executado. Isso encerrará o aplicativo.

Lembre-se de que essas são as etapas básicas para executar um projeto Spring Boot com Maven. Dependendo das configurações do seu projeto e dos plugins que você está usando, as etapas podem variar ligeiramente. Certifique-se de que o arquivo `pom.xml` do seu projeto esteja devidamente configurado com as dependências e plugins necessários. Além disso, você pode personalizar ainda mais a execução do seu aplicativo Spring Boot por meio de opções de configuração e propriedades no arquivo `application.properties` ou `application.yml`.

### Para que serve o `@ComponentScan`?

A anotação `@ComponentScan` é usada no framework Spring, incluindo o Spring Boot, para especificar pacotes que devem ser examinados em busca de componentes gerenciados pelo Spring, como controladores, serviços, repositórios e outros tipos de beans. Ela é usada para configurar a varredura de componentes no contexto da aplicação Spring.

A principal finalidade do `@ComponentScan` é permitir que o Spring encontre automaticamente e registre componentes que estão localizados nos pacotes especificados, evitando a necessidade de registrar manualmente cada componente no arquivo de configuração.

Aqui estão alguns usos comuns do `@ComponentScan` no Spring Boot:

1. **Descoberta de Componentes**: O Spring Boot irá examinar os pacotes especificados em busca de classes anotadas com `@Component`, `@Service`, `@Repository`, `@Controller`, e outras anotações relacionadas a componentes do Spring.

2. **Configuração Automática**: Muitas vezes, o `@ComponentScan` é usado em conjunto com as anotações `@SpringBootApplication` ou `@Configuration` para configurar automaticamente o contexto da aplicação Spring. Ele varre os pacotes da aplicação e registra todos os beans apropriados.

Exemplo de uso do `@ComponentScan`:

```java
@SpringBootApplication
@ComponentScan(basePackages = "com.exemplo")
public class MinhaAplicacao {
    public static void main(String[] args) {
        SpringApplication.run(MinhaAplicacao.class, args);
    }
}
```

Neste exemplo, o `@ComponentScan` está configurado para examinar o pacote "com.exemplo" e seus subpacotes em busca de componentes Spring. Ele irá registrar todos os componentes encontrados no contexto da aplicação.

Em resumo, o `@ComponentScan` no Spring Boot é uma anotação útil para configurar a varredura automática de componentes da aplicação, tornando o desenvolvimento mais fácil e eficiente, especialmente em aplicativos grandes e complexos.

### `ResponseEntity`

`ResponseEntity` é uma classe da estrutura Spring Boot que permite que você controle a resposta HTTP retornada por um controlador ou método de serviço. Ela é muito útil quando você precisa ter controle completo sobre o código de status, cabeçalhos e corpo da resposta. Aqui estão algumas maneiras comuns de lidar com `ResponseEntity` no Spring Boot:

1. **Retornando ResponseEntity de um Método do Controlador:**

   No seu controlador, você pode retornar um `ResponseEntity` em vez de um objeto simples. Isso lhe dá controle total sobre a resposta HTTP. Por exemplo:

   ```java
   @GetMapping("/exemplo")
   public ResponseEntity<String> exemplo() {
       String mensagem = "Exemplo de ResponseEntity no Spring Boot";
       HttpHeaders headers = new HttpHeaders();
       headers.add("Custom-Header", "Valor-Custom");
       return new ResponseEntity<>(mensagem, headers, HttpStatus.OK);
   }
   ```

   Neste exemplo, estamos retornando um `ResponseEntity` com uma mensagem, cabeçalhos personalizados e um código de status OK (200).

2. **Manipulando Códigos de Status e Cabeçalhos:**

   Você pode definir o código de status e os cabeçalhos do `ResponseEntity` conforme necessário. Por exemplo:

   ```java
   ResponseEntity<String> responseEntity = new ResponseEntity<>("Mensagem de erro", HttpStatus.BAD_REQUEST);
   HttpHeaders headers = new HttpHeaders();
   headers.add("Custom-Header", "Valor-Custom");
   responseEntity = responseEntity.headers(headers);
   ```

3. **Lidando com Respostas Personalizadas:**

   Às vezes, você pode precisar de respostas personalizadas, como erros personalizados. Você pode criar uma classe personalizada para representar essas respostas e retornar um `ResponseEntity` correspondente:

   ```java
   @ExceptionHandler(MinhaExcecaoPersonalizada.class)
   public ResponseEntity<ErroPersonalizado> handleMinhaExcecao(MinhaExcecaoPersonalizada ex) {
       ErroPersonalizado erro = new ErroPersonalizado(ex.getMessage());
       return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
   }
   ```

   Neste exemplo, um controlador de exceção é usado para lidar com uma exceção personalizada e retornar um `ResponseEntity` com um objeto de erro personalizado e um código de status personalizado.

4. **Usando ResponseEntity com Tipos Genéricos:**

   Você pode usar `ResponseEntity` com tipos genéricos para serializar e deserializar automaticamente objetos Java em JSON, XML, etc.:

   ```java
   ResponseEntity<List<Produto>> responseEntity = new ResponseEntity<>(listaDeProdutos, HttpStatus.OK);
   ```

   Neste exemplo, estamos retornando uma lista de objetos `Produto` como JSON com um código de status OK.

Em resumo, `ResponseEntity` no Spring Boot oferece flexibilidade e controle sobre as respostas HTTP retornadas pelos seus controladores. Você pode personalizar o código de status, os cabeçalhos e o corpo da resposta de acordo com suas necessidades.

### Como utilziar a anotação `@Autowired`?

A anotação `@Autowired` é usada no contexto do Spring Framework (incluindo o Spring Boot) para realizar a injeção de dependência automática. Sua função é simples: ela permite que você injete automaticamente beans (componentes gerenciados pelo Spring) em outros beans, sem a necessidade de criar instâncias manualmente. Isso facilita a configuração e o gerenciamento de dependências em uma aplicação Spring.

Aqui estão algumas funcionalidades-chave do `@Autowired`:

1. **Injeção de Dependência Automática:** Quando você anota um campo, construtor, método setter ou método de configuração com `@Autowired`, o Spring Framework identificará automaticamente o bean apropriado e o injetará no local anotado.

2. **Resolução de Dependência por Tipo:** O Spring tentará encontrar uma instância do tipo necessário a ser injetado. Se houver apenas uma correspondência, ela será injetada automaticamente. Se houver múltiplas correspondências, você precisará especificar qual delas deve ser injetada com mais precisão usando outras anotações, como `@Qualifier`.

3. **Redução da Configuração Manual:** O uso do `@Autowired` reduz a necessidade de configuração manual de dependências no arquivo de configuração do Spring (geralmente um arquivo XML). Em vez disso, as dependências são gerenciadas por meio de anotações e configurações em classes Java.

Aqui está um exemplo de uso do `@Autowired` em uma classe de serviço Spring:

```java
@Service
public class MinhaClasseServico {

    private MinhaOutraClasseDependencia dependencia;

    @Autowired
    public MinhaClasseServico(MinhaOutraClasseDependencia dependencia) {
        this.dependencia = dependencia;
    }

    // ...
}
```

Neste exemplo, a classe `MinhaClasseServico` tem uma dependência da classe `MinhaOutraClasseDependencia`, e a injeção de dependência é realizada automaticamente no construtor da classe de serviço.

Em resumo, o `@Autowired` é uma anotação fundamental do Spring que torna a injeção de dependência mais conveniente e eficaz. Ela ajuda a criar um acoplamento mais fraco entre os componentes da sua aplicação e facilita o desenvolvimento de aplicações mais testáveis e flexíveis.

## Conceitos

### O que é Spring Boot?

O Spring Boot é um projeto dentro do ecossistema Spring Framework que simplifica o desenvolvimento de aplicativos Java baseados em Spring. Ele fornece uma plataforma para criar aplicativos Java de maneira rápida e fácil, com configurações mínimas e uma abordagem opinativa. Aqui estão informações detalhadas sobre o Spring Boot:

1. **Origens e História**:
   O Spring Boot foi desenvolvido pela Pivotal Software (agora parte da VMware) e foi lançado em 2014 como uma resposta à complexidade e à configuração exagerada necessária para desenvolver aplicativos Spring tradicionais.

2. **Objetivos**:
   O principal objetivo do Spring Boot é simplificar o desenvolvimento, a configuração e a implantação de aplicativos Spring. Ele faz isso fornecendo uma maneira fácil de criar aplicativos Spring com configurações padrão sensatas e predefinidas. Também promove práticas de "opinião sobre configuração", onde as configurações padrão são usadas, mas podem ser personalizadas conforme necessário.

3. **Características Importantes**:
   - **Configuração Automática**: O Spring Boot usa a "convenção sobre configuração", o que significa que muitas configurações são feitas automaticamente com base nas dependências no projeto.
   - **Embutido**: Os aplicativos Spring Boot podem ser executados como JARs autônomos que incorporam um servidor da web, como Tomcat, Jetty ou Undertow, eliminando a necessidade de configurar servidores externos.
   - **Fornecimento de Dependências**: O Spring Boot fornece um mecanismo simples para gerenciar dependências, permitindo que você declare as dependências necessárias em seu arquivo de configuração.
   - **Microsserviços**: É amplamente utilizado no desenvolvimento de microsserviços, pois facilita a criação e a implantação de serviços independentes.

4. **Spring Initializr**:
   O Spring Initializr é uma ferramenta on-line que ajuda os desenvolvedores a iniciar rapidamente projetos Spring Boot personalizados. Ele permite que você selecione as dependências desejadas, como persistência de dados (JPA, MongoDB, etc.), sistemas de mensagens (Kafka, RabbitMQ, etc.) e muito mais.

5. **Anotações**:
   O Spring Boot usa uma série de anotações para simplificar o desenvolvimento, incluindo `@SpringBootApplication`, que marca a classe principal, `@RestController` para criar endpoints REST, `@Autowired` para injeção de dependência, entre outras.

6. **Suporte a Banco de Dados**:
   Spring Boot simplifica a integração com bancos de dados, fornecendo configurações padrão para muitos bancos de dados populares. Ele suporta JPA (Java Persistence API), JDBC (Java Database Connectivity) e várias outras opções.

7. **Desenvolvimento Web**:
   Spring Boot facilita o desenvolvimento de aplicativos da web com suporte embutido para servidores da web e configurações padrão para controladores e visualizações. Ele suporta a criação de aplicativos RESTful com facilidade.

8. **Segurança**:
   Oferece recursos de segurança para proteger aplicativos, incluindo autenticação e autorização.

9. **Monitoramento e Gerenciamento**:
   O Spring Boot fornece endpoints para monitoramento, métricas e gerenciamento de aplicativos, facilitando a integração com ferramentas de monitoramento como o Spring Boot Admin, o Actuator e o Micrometer.

10. **Comunidade Ativa**:
    O Spring Boot possui uma comunidade de desenvolvedores ativa e é amplamente utilizado em todo o mundo, o que significa que você pode encontrar uma grande quantidade de recursos, tutoriais e suporte online.

Em resumo, o Spring Boot é uma plataforma que visa simplificar o desenvolvimento de aplicativos Java baseados em Spring, fornecendo configurações padrão sensatas, suporte a muitos aspectos do desenvolvimento de aplicativos e uma abordagem de "opinião sobre configuração". Isso permite que os desenvolvedores se concentrem mais na lógica de negócios de seus aplicativos e menos na configuração e na infraestrutura.

### O que é Maven?

O Apache Maven é uma ferramenta de automação de construção e gerenciamento de projetos amplamente utilizada no desenvolvimento de software. Ele fornece uma estrutura poderosa e flexível para gerenciar todo o ciclo de vida de um projeto, desde a compilação e teste até a empacotagem e implantação. Aqui estão informações detalhadas sobre o Maven:

1. **História**:
   O Maven foi criado por Jason van Zyl em 2002 como parte do projeto Apache Turbine. Desde então, tornou-se um projeto independente e uma das ferramentas de gerenciamento de projetos mais populares no mundo Java.

2. **Gerenciamento de Dependências**:
   Uma das principais funcionalidades do Maven é o gerenciamento de dependências. Ele permite que os desenvolvedores especifiquem as bibliotecas e frameworks necessários para seus projetos em um arquivo chamado "pom.xml" (Project Object Model), e o Maven se encarrega de baixar essas dependências automaticamente.

3. **Ciclo de Vida do Build**:
   O Maven define um ciclo de vida de construção padrão, que inclui fases como "clean" (limpeza do projeto), "compile" (compilação do código-fonte), "test" (execução de testes), "package" (empacotamento), "install" (instalação no repositório local) e "deploy" (implantação em um repositório remoto). Os desenvolvedores podem invocar essas fases do ciclo de vida para automatizar tarefas comuns.

4. **Plugins**:
   Maven é altamente extensível por meio de plugins. Existem muitos plugins disponíveis para várias tarefas, como compilação, testes, empacotamento, geração de documentação, integração com IDEs e muito mais. Os desenvolvedores também podem criar seus próprios plugins personalizados.

5. **Convenções e Estrutura de Diretórios**:
   O Maven segue convenções e estruturas de diretórios padrão. Por exemplo, os arquivos de código-fonte Java devem estar no diretório "src/main/java", os recursos no diretório "src/main/resources" e os testes no diretório "src/test". Isso facilita a compreensão e a colaboração em projetos Maven.

6. **Integração com IDEs**:
   Maven é amplamente suportado por várias IDEs, como Eclipse, IntelliJ IDEA e NetBeans. As IDEs podem importar projetos Maven diretamente, simplificando o desenvolvimento e o gerenciamento de dependências.

7. **Repositórios**:
   O Maven utiliza repositórios para armazenar bibliotecas e artefatos. O repositório central do Maven é o "Central Repository," mas os desenvolvedores podem configurar repositórios locais ou privados para armazenar dependências específicas.

8. **Relatórios e Documentação**:
   Maven pode gerar relatórios e documentação automaticamente, como relatórios de cobertura de código, relatórios de análise estática e documentação de projetos. Isso é útil para manter a qualidade do código e a documentação atualizados.

9. **Integração Contínua**:
   Maven é frequentemente usado em sistemas de integração contínua (CI) para automatizar compilação, teste e implantação contínua de projetos. Jenkins, Travis CI e muitas outras ferramentas de CI oferecem suporte ao Maven.

10. **Comunidade e Suporte**:
    Maven é uma tecnologia de código aberto mantida pela Apache Software Foundation. Ele tem uma comunidade ativa de desenvolvedores e usuários que fornecem suporte e atualizações regulares.

Em resumo, o Apache Maven é uma ferramenta fundamental no desenvolvimento de software Java e outras linguagens. Ele simplifica a gestão de dependências, automação de construção e distribuição de projetos, permitindo aos desenvolvedores focar mais na lógica de negócios de seus aplicativos e menos na configuração e gerenciamento de build.

### O que é API REST?

Uma API REST (Representational State Transfer) é um estilo de arquitetura para projetar redes de computadores que se baseia em princípios simples e padrões HTTP. Ela é amplamente utilizada para criar serviços web que são escaláveis, flexíveis e fáceis de manter. APIs RESTful são usadas para permitir a comunicação entre diferentes sistemas e aplicativos por meio da internet.

Aqui estão os principais conceitos associados a uma API REST e como eles podem ser implementados em um aplicativo Java com Spring Boot:

1. **Recursos (Resources)**: Em uma API REST, tudo é considerado um recurso, que pode ser uma entidade, objeto ou conjunto de dados. Por exemplo, em um sistema de gerenciamento de tarefas, um recurso pode ser uma tarefa.

2. **Endpoints**: Cada recurso é acessado por meio de um URL específico, que é chamado de endpoint. Por exemplo, para acessar informações de uma tarefa, o endpoint pode ser algo como `https://sua-api.com/tarefas/123`, onde "123" é o identificador único da tarefa.

3. **Métodos HTTP**: Para realizar operações em recursos, as APIs REST usam os métodos HTTP, como GET (para buscar informações), POST (para criar novos recursos), PUT (para atualizar recursos) e DELETE (para excluir recursos).

4. **Representações**: As informações são representadas em um formato padrão, geralmente JSON ou XML. Isso permite que sistemas diferentes compreendam os dados uns dos outros.

> URL é a sigla para "Uniform Resource Locator," que em português significa "Localizador Uniforme de Recursos." Uma URL é um endereço que é usado para identificar recursos na internet, como páginas da web, imagens, vídeos, arquivos, serviços da web e muito mais. Ela é uma sequência de caracteres que descreve o local e o método de acesso a um recurso na rede.

Agora, como você pode criar uma API REST em Java com Spring Boot:

1. **Configurar um projeto Spring Boot**: Você pode criar um novo projeto Spring Boot usando o Spring Initializr ou configurando manualmente seu ambiente de desenvolvimento.

2. **Definir Entidades**: Em Java, você define as entidades que representam os recursos do seu aplicativo. Por exemplo, você pode criar uma classe `Task` para representar uma tarefa.

3. **Criar Controladores (Controllers)**: Os controladores Spring Boot definem os endpoints da sua API e tratam as solicitações HTTP. Eles mapeiam URLs para métodos que executam as operações nos recursos.

   ```java
   @RestController
   @RequestMapping("/tarefas")
   public class TarefaController {
      // Mapeamento para buscar uma tarefa pelo ID
      @GetMapping("/{id}")
      public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
         // Lógica para buscar a tarefa no banco de dados
      }

      // Mapeamento para criar uma nova tarefa
      @PostMapping
      public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
         // Lógica para criar a tarefa no banco de dados
      }

      // Outros mapeamentos para atualizar e excluir tarefas
   }
   ```

4. **Camada de Serviço**: É comum criar uma camada de serviço que encapsula a lógica de negócios. Os controladores delegam as chamadas de serviço para executar operações nos recursos.

5. **Conexão com o Banco de Dados**: Use o Spring Data JPA ou outra tecnologia para conectar-se ao banco de dados e armazenar/recuperar informações.

6. **Respostas HTTP**: Garanta que suas operações respondam com as respostas HTTP apropriadas, como códigos de status, para indicar o resultado da operação.

7. **Teste sua API**: É importante criar testes unitários e testes de integração para garantir o funcionamento correto da sua API.

Em resumo, uma API RESTful é uma maneira de projetar serviços web que seguem princípios simples e padronizados, tornando-os fáceis de usar e entender. O Spring Boot facilita a criação de APIs REST em Java, fornecendo um conjunto de ferramentas e recursos para simplificar o desenvolvimento.

### O que é Inversão de Controle (IOC)?

A Inversão de Controle (IoC) é um princípio de design de software e um conceito-chave na arquitetura de sistemas, onde a responsabilidade de controlar a execução de um programa é transferida de um componente para outro. Em vez de um componente controlar diretamente a criação e a gestão de suas dependências, a IoC inverte essa responsabilidade, permitindo que um componente seja "injetado" com suas dependências por um componente externo.

A IoC é frequentemente associada ao Framework Spring (incluindo o Spring Boot) e a outros contêineres de injeção de dependência. Aqui estão alguns conceitos importantes relacionados à IoC:

1. **Contêiner de Injeção de Dependência (DI Container):** O contêiner de IoC é responsável por gerenciar a criação, configuração e fornecimento de objetos (ou beans) em uma aplicação. Ele mantém um registro de todas as dependências e injeta-as nos componentes apropriados quando necessário.

2. **Injeção de Dependência (DI):** A IoC é frequentemente usada em conjunto com a injeção de dependência. A injeção de dependência é o ato de fornecer as dependências de um componente (como serviços ou objetos) a ele, em vez de permitir que o componente as crie ou gerencie. Isso ajuda a alcançar o baixo acoplamento entre componentes e a reutilização de código.

3. **Controle Direto vs. Controle Invertido:** No modelo tradicional, o controle direto é quando um componente é responsável por criar e gerenciar suas próprias dependências. No modelo IoC, o controle é invertido, com um contêiner ou componente externo injetando as dependências nos componentes que precisam delas. Isso permite uma maior flexibilidade e facilita a manutenção e o teste de componentes individuais.

4. **Benefícios da IoC:** A IoC promove a modularidade, reutilização de código, testabilidade e flexibilidade no desenvolvimento de software. Ela simplifica a configuração e a gestão de dependências e ajuda a reduzir o acoplamento entre componentes.

5. **Exemplos de Frameworks IoC:** Além do Spring Framework, outros exemplos de frameworks IoC incluem o Google Guice, CDI (Contexts and Dependency Injection), e frameworks de IoC específicos da linguagem, como o AngularJS no contexto do JavaScript.

Em resumo, a Inversão de Controle (IoC) é um princípio de design que promove a transferência do controle da criação e gestão de dependências de um componente para outro. Isso leva a um código mais modular, flexível e facilmente testável, e é amplamente usado na construção de sistemas modernos, especialmente em estruturas e contêineres de injeção de dependência.

## Quiz

1 - *Qual é o propósito principal do Spring Boot?* **Resposta:** Spring Boot é um framework para o desenvolvimento de aplicações Java que facilita e agiliza o desenvolvimento de aplicativos da web e de microsserviços.

2 - *O que são dependências em um projeto de software?* **Resposta:** Bibliotecas de código, módulos ou componentes que o projeto utiliza para adicionar funcionalidades específicas.

3 - *Qual é a principal finalidade do módulo Spring Web em um aplicativo Spring?* **Resposta:** Desenvolvimento de aplicativos web e manipulação HTTP.

4 - *Qual é a função principal do arquivo pom.xml em um projeto Java utilizando o Maven?* **Resposta:** Especificar as dependências do projeto e as configurações de compilação.

5- *O que melhor descreve as annotations (anotações) em Java?* **Resposta:** Marcações especiais que fornecem informações sobre o código, podendo influenciar o comportamento em tempo de compilação e execução.

6 - *O que a Injeção de Dependência (DI) faz em um contexto de desenvolvimento de software?* **Resposta:** A DI é uma prática que permite que objetos recebam suas dependências de fontes externas, sem criá-las internamente.

7 - *O que melhor descreve a função da anotação `@Autowired` em Java* **Resposta:** Especifica que um campo ou método `setter` deve ser injetado automaticamente pelo Spring.

8 - *Qual das seguintes opções melhor descreve a diferença entre body params, path params, query params e header params em uma API REST?* **Resposta:** *Body Params*: Contêm informações no corpo da requisição, geralmente usados em métodos POST. *Path Params*: São utilizados para incluir parâmetros diretamente na URL. *Query Params*: São parâmetros opcionais anexados à URL. *Header Params*: São parâmetros que fornecem informações adicionais na requisição, como autenticação.
