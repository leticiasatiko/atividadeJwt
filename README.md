# 🛡️ Atividade Spring Boot | JWT | MySQL

## ⚙️ Configuração do Banco de Dados

1. **Crie um banco de dados no MySQL:**

   ```sql
   CREATE DATABASE atividadejwt;
   ```

2. **Atualize o arquivo `src/main/resources/application.properties` com suas credenciais:**

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/atividadejwt
   spring.datasource.username=SEU_USUARIO
   spring.datasource.password=SUA_SENHA
   ```

---

## 🏗️ Como rodar o projeto

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/leticiasatiko/atividadeJwt.git
   cd atividadeJwt
   ```

2. **Compile e rode o projeto**

   O servidor estará disponível em [http://localhost:8080](http://localhost:8080)

---

## 📌 Endpoints disponíveis

| Método | URL                          | Descrição                             | Auth necessária |
|--------|------------------------------|---------------------------------------|-----------------|
| POST   | `/auth/register`             | Registro de novo usuário              | Não             |
| POST   | `/auth/login`                | Login e retorna JWT                   | Não             |
| GET    | `/users/me`                  | Perfil do usuário logado              | Sim             |
| PUT    | `/users/me`                  | Editar o próprio perfil               | Sim             |
| GET    | `/users/list-all`            | Listar todos os usuários              | ADMIN           |
| GET    | `/users/{id}`                | Visualizar usuário por ID             | ADMIN           |
| PUT    | `/users/{id}`                | Editar login/role de usuário por ID   | ADMIN           |
| DELETE | `/users/{id}`                | Deletar usuário por ID                | ADMIN           |

---

## 🧪 Testando com o Postman

### 1. **Registro de usuário**

- **Endpoint:** `POST http://localhost:8080/auth/register`
- **Body (JSON):**
  ```json
  {
    "login": "usuario1",
    "password": "senha123",
    "role": "USER"
  }
  ```

### 2. **Login**

- **Endpoint:** `POST http://localhost:8080/auth/login`
- **Body (JSON):**
  ```json
  {
    "login": "usuario1",
    "password": "senha123"
  }
  ```
- **Resposta esperada:**
  ```json
  {
    "token": "JWT_TOKEN_AQUI"
  }
  ```

### 3. **Autenticação nos próximos requests**

- Copie o valor do `token` da resposta do login.
- Nos próximos endpoints protegidos, adicione um header:
  ```
  Authorization: Bearer JWT_TOKEN_AQUI
  ```

### 4. **Editar o próprio login**

- **Endpoint:** `PUT http://localhost:8080/users/me`
- **Headers:** Authorization: Bearer {token}
- **Body (JSON):**
  ```json
  {
    "login": "novo_login"
  }
  ```

### 5. **Editar login/role de outro usuário (ADMIN)**

- **Endpoint:** `PUT http://localhost:8080/users/{id}`
- **Headers:** Authorization: Bearer {token_admin}
- **Body (JSON):**
  ```json
  {
    "login": "novo_login",
    "role": "ADMIN"
  }
  ```

---

## 📝 Observações

- Sempre envie o token JWT no header `Authorization` para endpoints protegidos.
- Roles possíveis: `USER` e `ADMIN`.
- Para testar endpoints ADMIN, registre um usuário com role "ADMIN".

---
