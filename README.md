# Easy Registration

## **Descrição do Projeto**
O Easy Registration é uma aplicação que facilita o gerenciamento e registro de pessoas, oferecendo funcionalidades para cadastro, consulta, atualização e exclusão de dados.
O sistema foi projetado para atender a demandas de controle de registros de maneira eficiente e escalável.

---

## **Funcionalidades**
- Cadastro de usuários com dados detalhados.
- Consulta de usuários por CPF.
- Atualização de informações do usuário.
- Exclusão de registros.

---

## **Tecnologias Utilizadas**
- **Linguagem:** Java 17
- **Framework:** Spring Boot 3.x
- **Banco de Dados:** MongoDB
- **Ferramentas de Build:** Maven
- **Servidor:** Tomcat (embutido no Spring Boot)
- **Outras Bibliotecas:** Lombok, Spring Data MongoDB

---

## **Pré-Requisitos**
1. **Java JDK 17** ou superior instalado.
2. **Maven** configurado no ambiente.
3. **MongoDB** em execução.
4. Ferramenta de controle de versão como **Git**.

---

## **Instalação**
1. Clone este repositório:
   ```bash
   git clone https://github.com/RodrigoFisch/Easy-Registration.git
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd Easy-Registration
   ```

3. Configure o arquivo `application.properties` com as credenciais do MongoDB:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/easy_registration
   ```

4. Compile e execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

---

## **Como Usar**
### **Endpoints Principais:**
- **GET** `/register-person/cpf/{cpf}`: Consulta uma pessoa pelo CPF.
- **POST** `/register-person`: Cadastra uma nova pessoa.
- **PUT** `/register-person/cpf/{cpf}`: Atualiza os dados de uma pessoa pelo CPF.
- **DELETE** `/register-person/cpf/{cpf}`: Exclui uma pessoa pelo CPF.

### **Exemplo de JSON para Cadastro:**
```json
{
  "name": "João Silva",
  "cpf": "12345678901",
  "email": "joao.silva@example.com",
  "birthDate": "1990-05-20"
}
```

---

## **Contribuindo**
Sinta-se à vontade para contribuir com este projeto. Para isso:
1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção:
   ```bash
   git checkout -b feature/nova-funcionalidade
   ```
3. Envie um pull request com suas alterações.

---

## **Autor**
**Rodrigo Fisch**
- [LinkedIn](https://www.linkedin.com/in/rodrigofisch)
- [GitHub](https://github.com/RodrigoFisch)

---

## **Licença**
Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.

