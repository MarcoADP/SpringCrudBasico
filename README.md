# Exemplo de CRUD Básico utilizando Spring

CRUD Básico com Spring para validação técnica 

Stack:
- Java
- Spring
- Maven
- Hibernate
- Lombok
- OpenAPI
- PostgreSQL

IDE: IntelliJ

Run: mvn spring-boot:run

URL padrão: http://localhost:8080/

Documentação: http://localhost:8080/swagger-ui/index.html

Métodos disponíveis
- POST - http://localhost:8080/usuario - Chamada que recebe o form-data com 2 chaves: params (json dos dados) e foto (imagem)
- POST - http://localhost:8080/usuario/data - Chamada para criar um usuário apenas com os dados do usuário

- PUT - http://localhost:8080/usuario/{id} - Atualiza os dados e imagem do usuário
- PUT - http://localhost:8080/usuario/data/{id} - Atualiza somente os dados do usuário
- PUT - http://localhost:8080/usuario/foto/{id} - Atualiza somente a foto do usuário

- GET - http://localhost:8080/usuario/all - Recuperar as informações de todos os usuários
- GET - http://localhost:8080/usuario/foto/{id} - Recuperar a foto de um usuário
- GET - http://localhost:8080/usuario/{id} - Recuperar as informações de um usuário a partir do id
- GET - http://localhost:8080/usuario/codigo/{codigo} - Recuperar as informações de um usuário a partir do codigo

- DELETE - http://localhost:8080/usuario/{id} - Realiza a remoção física do usuário
- PUT - http://localhost:8080/usuario/remove/{id} - Realiza a remoção lógica do usuário
- PUT - http://localhost:8080/usuario/restore/{id} - Restabelece um usuário que foi removido de maneira lógica
