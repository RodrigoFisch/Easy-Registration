
# Easy Registration

## Descrição do Projeto
O Easy Registration é uma aplicação que facilita o gerenciamento e registro de pessoas, oferecendo funcionalidades para cadastro, consulta, atualização e exclusão de dados. O sistema foi projetado para atender a demandas de controle de registros de maneira eficiente, escalável e segura.

## Funcionalidades
- Cadastro de usuários com dados detalhados.
- Consulta de usuários por CPF.
- Atualização de informações do usuário.
- Exclusão de registros.
- **Autenticação e autorização via JWT (JSON Web Tokens) com chave segura para proteção dos endpoints.**
- **Documentação da API integrada via Swagger UI configurada por arquivo YAML customizado.**

## Tecnologias Utilizadas
- Linguagem: Java 17
- Framework: Spring Boot 3.x
- Banco de Dados: MongoDB
- Ferramentas de Build: Maven
- Servidor: Tomcat (embutido no Spring Boot)
- Outras Bibliotecas: Lombok, Spring Data MongoDB, jjwt (JWT), Springdoc OpenAPI

## Autenticação JWT
- Implementação de autenticação baseada em tokens JWT, utilizando o algoritmo HS512 para assinatura.
- Geração de chave secreta com tamanho mínimo de 512 bits para garantir a segurança conforme especificação RFC 7518.
- Chave gerada utilizando `io.jsonwebtoken.security.Keys.secretKeyFor(SignatureAlgorithm.HS512)`.
- Tratamento de exceção para casos de chave fraca, com recomendação para sempre gerar chave segura.
- Filtro de autorização JWT integrado ao Spring Security para proteger endpoints da API.

## Documentação com Swagger UI
- Swagger UI configurado para buscar o arquivo de configuração `swagger-config.yaml` na pasta `src/main/resources/static`.
- Documentação acessível via URL: `http://localhost:8080/swagger-ui/index.html`
- Arquivo YAML personalizado permite configuração avançada do Swagger UI, como URLs e layout.
- Desabilitação do `springdoc.api-docs.enabled=false` para evitar conflito com a configuração personalizada.

## Pré-Requisitos
- Java JDK 17 ou superior instalado.
- Maven configurado no ambiente.
- MongoDB em execução.
- Ferramenta de controle de versão como Git.

## Instalação
Clone este repositório:

```bash
git clone https://github.com/RodrigoFisch/Easy-Registration.git
```

Acesse o diretório do projeto:

```bash
cd Easy-Registration
```

Configure o arquivo `application.yml` (ou `application.properties`) com as credenciais do MongoDB e a chave JWT:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/easy_registration
jwt:
  secret: "chave-gerada-com-512bits-ou-mais"
```

Compile e execute a aplicação:

```bash
mvn spring-boot:run
```

## Como Usar
### Endpoints Principais:
- `GET /register-person/cpf/{cpf}`: Consulta uma pessoa pelo CPF.
- `POST /register-person`: Cadastra uma nova pessoa.
- `PUT /register-person/cpf/{cpf}`: Atualiza os dados de uma pessoa pelo CPF.
- `DELETE /register-person/cpf/{cpf}`: Exclui uma pessoa pelo CPF.

**Todos os endpoints estão protegidos via JWT e requerem token válido no cabeçalho Authorization.**

### Exemplo de JSON para Cadastro:

```json
{
  "name": "João Silva",
  "cpf": "12345678901",
  "email": "joao.silva@example.com",
  "password": "12345@Ab"
}
```

### Acesso à documentação Swagger UI:
Abra no navegador:  
`http://localhost:8080/swagger-ui/index.html`

## Contribuindo
Sinta-se à vontade para contribuir com este projeto. Para isso:

- Faça um fork do repositório.
- Crie uma branch para sua feature ou correção:  
  `git checkout -b feature/nova-funcionalidade`
- Envie um pull request com suas alterações.

## Autor
Rodrigo Fisch

- [LinkedIn](https://www.linkedin.com/in/rodrigofisch)
- [GitHub](https://github.com/RodrigoFisch)

## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.