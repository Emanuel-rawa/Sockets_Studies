# Projeto de Sockets em Java

Este projeto demonstra a comunicação entre cliente e servidor usando sockets TCP com protocolo personalizado (sem HTTP).

## Funcionalidades

- Autenticação de usuário (LOGIN)
- Consulta de dados (GET_INFO)
- Listagem de usuários (LIST_KEYS)
- Ajuda (HELP)
- Logout (LOGOUT)

## Como usar

1. Compile:

- javac Server.java
- javac Client.java

2. Rode o servidor:

- java Server

3. Rodeo cliente em outro terminal:

- java Client

## Exemplo de comandos disponíveis

| Comando                   | Descrição                                    | Exemplo            |
| ------------------------- | -------------------------------------------- | ------------------ |
| `LOGIN:<usuario>:<senha>` | Autentica o usuário                          | `LOGIN:admin:1234` |
| `GET_INFO:<usuario>`      | Retorna os dados do usuário especificado     | `GET_INFO:joao`    |
| `LIST_KEYS`               | Lista todos os nomes de usuários disponíveis | `LIST_KEYS`        |
| `HELP`                    | Mostra os comandos disponíveis               | `HELP`             |
| `LOGOUT`                  | Encerra a conexão com o servidor             | `LOGOUT`           |
