# Projeto Final de Desenvolvimento de API Restful

Projeto desenvolvido durante a Residência TIC/Software do Serratec

Este repositório contém o código-fonte e a documentação relacionada ao projeto final de Desenvolvimento de API Restful desenvolvido pelo grupo 1 do curso Residência em TIC do Serratec. Aqui, registraremos nossa trajetória na realização do trabalho proposto pela disciplina!

Antes disso, vale ressaltar que o projeto foi iniciado em outro repositório, e devido a alguns problemas, foi movido para este.
- Link para o repositório inicial: https://github.com/ppzovsky/e-commerceAPI





## Objetivo do Projeto

O objetivo deste projeto é desenvolver uma API RESTFul de um E-commerce a partir das
regras de negócio e requisitos descritos no documento fornecido nesse repositório.
## Instalação
Para instalar e executar o projeto localmente, siga os passos abaixo:

Clone o repositório:
```bash
git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
```

Navegue até o diretório do projeto:

```bash
cd SEU_REPOSITORIO
```

Instale as dependências do projeto:
```bash
mvn install
```

Configure o banco de dados no arquivo application.properties:

```bash
spring.datasource.url  =  jdbc:postgresql://localhost:SUAPORTA/SEUBANCODEDADOS
spring.datasource.username  =  SEUUSERNAME
spring.datasource.password  =  SENHADOSEUBANCODEDADOS
```
Execute a aplicação:
```bash
 mvn spring-boot:run
```




    
## Como usar nosso projeto?

Após a instalação, a API estará disponível para uso. Você pode acessar a documentação da API via Swagger:

```javascript
http://localhost:8000/swagger-ui.html
```
> [!WARNING]
> Esse link só funcionará caso sua API estiver rodando!



É importante ressaltar que os relatórios de pedidos foram implementados usando o site mailtrap, lembre-se de alterar essas proprieades no seu application.properties:

```javascript
spring.mail.username=SEUUSERNAME
spring.mail.password=SUASENHA
```
- https://mailtrap.io/home

## Integrantes do Grupo

- **Alexandre De Oliveira Silveira**  
  LinkedIn -https://www.linkedin.com/in/alexandre-oliveira-siveira/
  
- **Ana Yukari Futigami Pereira**  
  LinkedIn - https://www.linkedin.com/in/yuyuka/

- **Emanuel Fonseca Rodrigues Silvério**  
  LinkedIn - (colocar link)

- **Giovanne Moreira Holanda**  
  LinkedIn - (colocar link)

- **João Pedro Soares De Brito**  
  LinkedIn - https://www.linkedin.com/in/jo%C3%A3o-pedro-soares-164964236/
