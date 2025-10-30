// MainApp.java
package view;

import Controller.PacienteController;
import Model.Paciente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    // Controladores
    private static final PacienteController pacienteController = new PacienteController();
    // (Adicione os outros controllers aqui, ex: MedicoController)

    // Utilitários
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("== Bem-vindo ao HospitalSys (Modo Terminal) ==");
        loopPrincipal();
        scanner.close();
        System.out.println("Sistema encerrado.");
    }

    private static void loopPrincipal() {
        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuGerenciarPacientes();
                    break;
                case 2:
                    System.out.println("Gerenciamento de Médicos (Não implementado)");
                    // Chame menuGerenciarMedicos(); aqui
                    break;
                case 3:
                    System.out.println("Gerenciamento de Hospitais (Não implementado)");
                    break;
                case 4:
                    System.out.println("Gerenciamento de Leitos (Não implementado)");
                    break;
                case 5:
                    System.out.println("Gerenciamento de Funcionários (Não implementado)");
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Gerenciar Pacientes");
        System.out.println("2. Gerenciar Médicos");
        System.out.println("3. Gerenciar Hospitais");
        System.out.println("4. Gerenciar Leitos");
        System.out.println("5. Gerenciar Funcionários");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna -1 para opções inválidas
        }
    }

    // --- Módulo de Gerenciamento de Pacientes ---

    private static void menuGerenciarPacientes() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Gerenciar Pacientes ---");
            System.out.println("1. Cadastrar Novo Paciente");
            System.out.println("2. Listar Todos os Pacientes");
            System.out.println("3. Atualizar Paciente");
            System.out.println("4. Excluir Paciente");
            System.out.println("5. Buscar Paciente por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    cadastrarPaciente();
                    break;
                case 2:
                    listarPacientes();
                    break;
                case 3:
                    atualizarPaciente();
                    break;
                case 4:
                    excluirPaciente();
                    break;
                case 5:
                    buscarPacientePorId();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarPaciente() {
        try {
            System.out.println("\n-- Cadastrar Paciente --");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Sexo (M/F): ");
            String sexo = scanner.nextLine();
            System.out.print("Data de Nascimento (dd/MM/yyyy): ");
            Date dataNascimento = dateFormat.parse(scanner.nextLine());

            Paciente paciente = new Paciente();
            paciente.setNome(nome);
            paciente.setEmail(email);
            paciente.setTelefone(telefone);
            paciente.setEndereco(endereco);
            paciente.setSexo(sexo);
            paciente.setData_nascimento(dataNascimento);

            pacienteController.salvar(paciente);
            System.out.println("Paciente salvo com sucesso! ID gerado: " + paciente.getId_paciente());

        } catch (ParseException e) {
            System.out.println("Erro no formato da data. Use dd/MM/yyyy.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    private static void listarPacientes() {
        System.out.println("\n-- Lista de Pacientes --");
        List<Paciente> pacientes = pacienteController.listarTodos();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (Paciente p : pacientes) {
            System.out.printf("ID: %d | Nome: %s | Email: %s\n",
                    p.getId_paciente(), p.getNome(), p.getEmail());
        }
    }

    private static void atualizarPaciente() {
        try {
            System.out.println("\n-- Atualizar Paciente --");
            System.out.print("Digite o ID do paciente a atualizar: ");
            int id = lerOpcao();
            Paciente paciente = pacienteController.buscarPorId(id);

            if (paciente == null) {
                System.out.println("Paciente não encontrado.");
                return;
            }

            System.out.println("Deixe o campo em branco para manter a informação atual.");

            System.out.print("Nome (" + paciente.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                paciente.setNome(nome);
            }

            System.out.print("Email (" + paciente.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                paciente.setEmail(email);
            }
            
            System.out.print("Telefone (" + paciente.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) {
                paciente.setTelefone(telefone);
            }
            
            // (Repetir para os outros campos: endereco, sexo, data_nascimento)

            pacienteController.atualizar(paciente);
            System.out.println("Paciente atualizado com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    private static void excluirPaciente() {
        try {
            System.out.println("\n-- Excluir Paciente --");
            System.out.print("Digite o ID do paciente a excluir: ");
            int id = lerOpcao();
            Paciente paciente = pacienteController.buscarPorId(id);

            if (paciente == null) {
                System.out.println("Paciente não encontrado.");
                return;
            }

            System.out.print("Tem certeza que deseja excluir " + paciente.getNome() + "? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                pacienteController.excluir(id);
                System.out.println("Paciente excluído com sucesso.");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        }
    }

    private static void buscarPacientePorId() {
        try {
            System.out.println("\n-- Buscar Paciente por ID --");
            System.out.print("Digite o ID do paciente: ");
            int id = lerOpcao();
            Paciente p = pacienteController.buscarPorId(id);
            
            if (p == null) {
                System.out.println("Paciente não encontrado.");
                return;
            }
            
            System.out.println("Paciente encontrado:");
            System.out.printf("ID: %d\n", p.getId_paciente());
            System.out.printf("Nome: %s\n", p.getNome());
            System.out.printf("Email: %s\n", p.getEmail());
            System.out.printf("Telefone: %s\n", p.getTelefone());
            System.out.printf("Endereço: %s\n", p.getEndereco());
            System.out.printf("Sexo: %s\n", p.getSexo());
            System.out.printf("Nascimento: %s\n", dateFormat.format(p.getData_nascimento()));
            
        } catch (Exception e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
    }
}