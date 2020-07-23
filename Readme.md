# Password Validator

## Objetivo 

O objetivo deste projeto é criar uma API que realiza a validação de senhas retornando true ou false de acorodo com as premissas propostas:

- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial
  - Considere como especial os seguintes caracteres: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto
> **_Nota:_**  Espaços em branco não devem ser considerados como caracteres válidos.

## :rocket: Solução do problema
A solução do foi desenvolvida realizando um raciocínio lógico simples seguindo os seguintes passos:

  1º passo: Foi realizada uma verificação para validar se a string recebida é nula ou vazia. Caso positivo, é retornado false, caso contrario a solução segue para o passo seguinte.
  
  2º passo: Como espaços em branco não são considerados é realizada a remoção dos espaços em branco e realizada uma verificação para validar se a string possui 9 ou mais caracteres. Caso negativo, é retornado false,caso contrário a solução segue para o próximo passo.
  
  3º passo: Nesse passo é feita a verificação de caracteres repetidos, a solução que implementei primeiro transforma todos os caracteres em minusculo com a função toLowerCase() e em seguida crio um array de char com a função toCharArray(). Após isso realizo a ordenação do array com a função Arrays.sort e por fim preciso apenas criar um loop que verifica se o caracter anterior é igual ao próximo. Como eles estão ordenados, havendo caracteres repetidos eles estarão em sequência facilitando assim a busca e caso encontre um caracter repetido o loop é encerrado e returnado o valor true que indica a repetição e caso contrário é retornado false indicando que a função pode passar para o próximo passo.
  
  4º passo: Neste passo é verificado se a string está segundo o padrão correto, para isso utilizo uma Pattern com uma regex que valida o padrão da string. O regex que utilizei foi este (?=.*[!@#$%^&*()-+])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]):
   
   - (?=.*[!@#$%^&*()-+]): Verifica se a string possui ao menos um destes caracters especiais
   - (?=.*[A-Z]): Verifica se a string possui ao menos uma letra maiuscula
   - (?=.*[a-z]): Verifica se a string possui ao menos uma letra maiuscula
   - (?=.*[0-9]): Verifica se a string possui ao menos um dígito

Se alguma dessas validações não for encontrada é retornado um false indicando que a senha não passou, caso contrário,é retornado true permitindo que a função vá para o próximo passo.

  5º passo: Passando por todos os passos anteriores a senha é validada e retornado o valor true para a controller, que é onde é realizado o retorno da resposta para o cliente. 

## :computer: Tecnologias

  - Java 11
  - Spring boot 2.3.1
  - Spring Fox (Swagger-ui)
  - Lombok
  - JUnit para testes unitários e de integração
  
  #### **Utilitários**

- Commit Conventional: **[Commitlint][commitlint]**
- Teste de API: **[Insomnia][insomnia]**

## **:wine_glass: COMO UTILIZAR**

Primeiramente é necessário ter instalado em sua máquina o [jdk_11] da linguagem [Java] e o [maven], após realizar a instalação e configuração do jdk e do maven em sua máquina, você pode clonar o projeto utilizando um dos comandos abaixo.

Se já possui o ssh configurado no git de sua máquina 

```sh
  git clone git@github.com:EngTaian/password-validator.git
```

Caso não tenha o ssh configurado pode utilizar o https 

```sh
  git clone https://github.com/EngTaian/password-validator.git
```

Com a pasta do projeto em sua máquina, podemos acessar a pasta e utilizar o maven para realizar o build do projeto.

```sh
  mvn install
```
para executar sem executar os teste podemos utilizar o comando.

```sh
  mvn install -DskipTests
```
Executando um dos comandos acima o build será realizado, agora podemos acessar a pasta target que foi criada e executar o comando
````sh
  java -jar password-validator-0.0.1-SNAPSHOT.jar
````
Pronto o projeto foi executado e você já pode acessar o projeto.
  
  - Para acessar a documentaão da API basta acessar a url: http://localhost:8080/swagger-ui.html 

> **_Nota:_**  Também é possível acessar a API utilizando o Insomnia ou Postman. 


<!-- Techs -->

[commitlint]: https://github.com/conventional-changelog/commitlint
[insomnia]: https://insomnia.rest/
[jdk_11]: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
[Java]: https://pt.wikipedia.org/wiki/Java_(linguagem_de_programa%C3%A7%C3%A3o)
[maven]: https://maven.apache.org/download.cgi
