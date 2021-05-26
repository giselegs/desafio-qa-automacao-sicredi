
# Projeto

Este desafio tem como objetivo automatizar as APIs do Sicredi.  
Os testes foram implementados em Java utilizando RestAssured. 

A API está dividida entre Restrições e Simulações.
Para as restrições, é possível apenas realizar a ação de consulta informando para isso o CPF desejado
Para as simulações, é possível criar, consultar todas as simulações existentes, consultar uma simulação específica informando o CPF, além de excluir e deletar simulações existentes.


## Como executar a aplicação 
Os testes foram implementados no Netbeans, e para a executar a aplicação nesta IDE é necessário utilizar o Spring Tools

Em outras IDEs, a aplicação pode ser executada através do comando abaixo
```bash
mvn clean spring-boot:run
```

A aplicação estará disponível através da URL [http://localhost:8080](http://localhost:8080)


## Execução dos testes

Os testes foram estruturados em uma pasta separada das demais existentes. Nesta pasta, foram criados três pacotes:
- core: onde estão armazenados as constantes e implementações necessárias a todos os testes
- tests: onde foram implementados todos os testes. Foram criadas classes de testes distintas para Restrições e Simulações
- suite: onde é possível executar de uma única vez todos os testes existentes nas classes informadas
