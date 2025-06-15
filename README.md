
# 🎉 Atividade Spring Boot | JWT | MySQL

## 📌 Endpoints

| Método | URL               | Descrição                        | Roles       | 
|--------|-------------------|----------------------------------|-------------|
| POST   | /auth/register    | Registro de novo usuário         | Pública     |
| POST   | /auth/login       | Login com retorno de JWT         | Pública     |
| GET    | /users/me         | Perfil do usuário logado         | USER, ADMIN |
| PUT    | /users/me         | Editar o próprio login           | USER, ADMIN |
| GET    | /users/list-all   | Listar todos os usuários         | ADMIN       |
| GET    | /users/{id}       | Visualizar usuário por ID        | ADMIN       |
| PUT    | /users/{id}       | Editar login/role de usuário     | ADMIN       |
| DELETE | /users/{id}       | Deletar usuário por ID           | ADMIN       |

### Exemplos detalhados de JSON para cada endpoint que requer envio de body

#### Registro de novo usuário POST `/auth/register`
```json
{
  "login": "teste4",
  "password": "teste",
  "role": "ADMIN"
}
```

#### Login POST `/auth/login`
```json
{
  "login": "teste4",
  "password": "teste"
}
```

#### Editar o próprio login PUT `/users/me`
```json
{
  "login": "usuario_atualizado"
}
```

#### Editar login/role de usuário PUT `/users/{id}`
```json
{
  "login": "novo_login",
  "role": "USER"
}
```

#### Exemplo do retorno do token
```json
{
    "token": "TOKEN"
}
```
