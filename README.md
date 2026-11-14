# HospitalSys

Sistema hospitalar em **Java** com **MySQL**, projeto final da disciplina de Programação Orientada a Objetos. Pelo terminal, permite cadastrar, listar, atualizar, excluir e buscar pacientes, médicos, hospitais, leitos e funcionários.

O relatório do projeto está em [`POO -  Projeto Final.pdf`](./POO%20-%20%20Projeto%20Final.pdf).

## Arquitetura

O código segue MVC com DAO:

```
HospitalSystem/src/main/java/
├── view/MainApp.java        # Menus do terminal (main)
├── Controller/              # Um controller por entidade
├── Model/                   # Entidades (Paciente, Medico, Leito...)
│   └── dao/
│       ├── interfaces/      # Contratos dos DAOs
│       └── impl/            # Implementações JDBC
└── conexao/ConexaoBD.java   # Fábrica de conexões
```

O projeto Maven fica em `HospitalSys/HospitalSys-main/HospitalSystem/`.

## Tecnologias

- Java 17+
- Maven
- MySQL 8
- `mysql-connector-j` 8.0.33

## Como executar

1. Crie o banco `hospital_db` com os scripts do repositório [Hospital](https://github.com/IagoFel1x/Hospital).

2. Informe as credenciais por variáveis de ambiente (nenhuma senha fica no código):

| Variável | Padrão |
|----------|--------|
| `HOSPITAL_DB_URL` | `jdbc:mysql://localhost:3306/hospital_db` |
| `HOSPITAL_DB_USER` | `root` |
| `HOSPITAL_DB_PASSWORD` | *(vazia)* |

No PowerShell:

```powershell
$env:HOSPITAL_DB_PASSWORD = "sua_senha"
```

No Linux/macOS:

```bash
export HOSPITAL_DB_PASSWORD=sua_senha
```

3. Compile e execute:

```bash
cd HospitalSys/HospitalSys-main/HospitalSystem
mvn compile exec:java
```

## Pontos de melhoria

- A estrutura `HospitalSys/HospitalSys-main/HospitalSystem` veio de um ZIP extraído dentro do repositório. Mover o projeto Maven para a raiz deixaria tudo mais simples.
- `SistemHospital` na raiz é um arquivo vazio, resquício de um submódulo; pode ser removido.
- As pastas `.idea/` e `target/` estão versionadas. O `.gitignore` evita novos commits delas; para remover as existentes: `git rm -r --cached HospitalSys/.idea HospitalSys/HospitalSys-main/HospitalSystem/target`.
- Os DAOs capturam `SQLException` e apenas imprimem o erro, então o menu pode exibir "salvo com sucesso" mesmo quando o banco recusou a operação. Propagar a exceção até o controller resolveria isso.

## Autor

[Iago Cordeiro Felix](https://github.com/IagoFel1x)
