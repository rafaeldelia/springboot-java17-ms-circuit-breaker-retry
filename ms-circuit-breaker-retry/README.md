### 1. Tecnologias Utilizadas

   - Java 17
   - Spring Boot Framework 2.7.12
   - Maven
   - Logback Framework
   - Resilience4j - Circuit Breaker e Retry
   - Open Feign
   - Project Lombok
   - Spring Actuator: monitoramento e gerenciamento de aplicações
   - Mapped Diagnostic Context (MDC)
   - SonarLint by Sonar
   
### 2. Configuração

##### Instale

* [Java JDK 17](https://openjdk.java.net/projects/jdk/17/)
* [Git](https://git-scm.com/downloads)
* [Postman Agent](https://www.postman.com/downloads/);
* [Sonar Lint](https://www.sonarsource.com/products/sonarlint/)

### 3. Build e Deploy 

#### 3.1 Ambiente de desenvolvimento (LocalHost)

##### Spring Boot 

   1. Importe o projeto como Maven project
   2. Realize o Maven Build...
   3. Digite mvn clean install
   
##### SonarLint

   1. Adicione o plugin sonarLint na IDE
   2. Selecione a pasta raiz do projeto
   3. Clique com o botão direito do mouse
   4. Escolha SonarLint -> Analize  
   5. Vizualize os erros indicados pelo sonarLint
   6. Corrija-os
   7. Repita o passo 4 até zerar os codes smells

##### LOGBACK

##### CURL

-- Para recuperar os dados.
curl --location 'http://localhost:8081/ms-circuit-breaker/data'

##### ACTUATOR

http://localhost:8081/ms-circuit-breaker/actuator

##### CENÁRIOS PARA CIRCUIT BREAKER + RETRY

Caso I) Vamos interromper o microsserviço "${data.url}" e observar como funciona o aplicativo principal, ou seja, o microsserviço. 
Após interromper o "${data.url}", você verá que o serviço principal tenta novamente 3 vezes após algum intervalo de tempo. 
Você pode verificar pelos logs ou colocando o ponto de depuração no método de fallback do serviço.

a) Logo que der o erro pode observar o Health Check que o estado ficou como OPEN (aberto).

O Circuit Breaker passou para o estado aberto, pois o limite de falha era de 50% e, em nosso caso, como todos os casos falharam, nossa taxa de falha é de 100%.

http://localhost:8081/ms-circuit-breaker/actuator/health

Nota: Independentemente de mais chamadas ou nenhuma chamada, após 30 segundos o Circuit Breaker entrará em CIRCUIT_HALF_OPEN automaticamente porque definimos "automaticTransitionFromOpenToHalfOpenEnabled" como verdadeiro.


Caso II : Vamos pegar outro cenário do exemplo acima apenas o que teria acontecido após falha de 2 chamadas. 
O circuito estaria no estado Fechado ou Aberto?
Se a sua resposta for Fechado, então está correto, pois mencionamos o número mínimo de chamadas como 3 (maxAttempts: 3).

Caso III: Pense no mesmo cenário acima e o serviço "${data.url}" ainda está inativo ou lançando exceções, eles serão descartados pelo circuit breaker (OPEN).