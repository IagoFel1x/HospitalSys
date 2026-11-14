# HospitalSys

Sistema hospitalar em **Java** (projeto final da disciplina de Programação Orientada a Objetos) para registro de pacientes, consultas e exames.

O relatório do projeto está em [`POO -  Projeto Final.pdf`](./POO%20-%20%20Projeto%20Final.pdf).

## Estrutura

```
HospitalSys/
├── POO -  Projeto Final.pdf
├── SistemHospital/                    # Scripts SQL do banco
└── HospitalSys/HospitalSys-main/
    └── HospitalSystem/               # Projeto Maven (pom.xml e src/)
```

## Tecnologias

- Java
- Maven
- MySQL

## Como executar

1. Crie o banco de dados com os scripts da pasta `SistemHospital/` (veja também o repositório [Hospital](https://github.com/IagoFel1x/Hospital)).
2. Compile o projeto:

```bash
cd HospitalSys/HospitalSys-main/HospitalSystem
mvn clean package
```

## Pontos de melhoria

- Há uma pasta duplicada (`HospitalSys/HospitalSys-main`), resultado de um ZIP do GitHub extraído dentro do repositório. Mover o projeto para a raiz deixaria a estrutura mais limpa.
- As pastas `.idea/` e `target/` estão versionadas. O `.gitignore` incluído evita novos commits delas; para remover as que já existem, use `git rm -r --cached`.

## Autor

[Iago Cordeiro Felix](https://github.com/IagoFel1x)
