// MainApp.java
package view;

import Controller.PacienteController;
import Model.Paciente;
import Controller.HospitalController;
import Model.Hospital;
import Model.Medico;
import Controller.MedicoController;
import Controller.LeitoController;
import Model.Leito;
import Controller.FuncionarioController;
import Model.Funcionario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    // Controladores
    private static final PacienteController pacienteController = new PacienteController();
    private static final MedicoController medicoController = new MedicoController();
    private static final HospitalController hospitalController = new HospitalController();
    private static final LeitoController leitoController = new LeitoController();
    private static final FuncionarioController funcionarioController = new FuncionarioController();

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
                    menuGerenciarMedicos();
                    break;
                case 3:
                    menuGerenciarHospitais();
                    break;
                case 4:
                    menuGerenciarLeitos();
                    break;
                case 5:
                    menuGerenciarFuncionarios();
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

    //  Gerenciamento de Pacientes

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
            String dataNascimentoStr = scanner.nextLine();
            Date dataNascimento = dateFormat.parse(dataNascimentoStr);

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

            System.out.print("Endereço (" + paciente.getEndereco() + "): ");
            String endereco = scanner.nextLine();
            if (!endereco.isEmpty()) {
                paciente.setEndereco(endereco);
            }

            System.out.print("Sexo (" + paciente.getSexo() + "): ");
            String sexo = scanner.nextLine();
            if (!sexo.isEmpty()) {
                paciente.setSexo(sexo);
            }


            System.out.print("Data de Nascimento (" + dateFormat.format(paciente.getData_nascimento()) + ") (dd/MM/yyyy): ");
            String dataNascStr = scanner.nextLine();
            if (!dataNascStr.isEmpty()) {
                try {
                    paciente.setData_nascimento(dateFormat.parse(dataNascStr));
                } catch (ParseException e) {
                    System.out.println("Aviso: Formato de data inválido. Mantendo a data anterior.");
                }
            }

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
    // Gerenciamento de Médicos

    private static void menuGerenciarMedicos() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Gerenciar Médicos ---");
            System.out.println("1. Cadastrar Novo Médico");
            System.out.println("2. Listar Todos os Médicos");
            System.out.println("3. Atualizar Médico");
            System.out.println("4. Excluir Médico");
            System.out.println("5. Buscar Médico por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    cadastrarMedico();
                    break;
                case 2:
                    listarMedicos();
                    break;
                case 3:
                    atualizarMedico();
                    break;
                case 4:
                    excluirMedico();
                    break;
                case 5:
                    buscarMedicoPorId();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarMedico() {
        try {
            System.out.println("\n-- Cadastrar Médico --");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("CRM: ");
            String crm = scanner.nextLine();
            System.out.print("Especialidade: ");
            String especialidade = scanner.nextLine();

            Medico medico = new Medico();
            medico.setNome(nome);
            medico.setEmail(email);
            medico.setTelefone(telefone);
            medico.setCrm(crm);
            medico.setEspecialidade(especialidade);

            medicoController.salvar(medico);
            System.out.println("Médico salvo com sucesso! ID gerado: " + medico.getId_medico());

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar médico: " + e.getMessage());
        }
    }

    private static void listarMedicos() {
        System.out.println("\n-- Lista de Médicos --");
        List<Medico> medicos = medicoController.listarTodos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        for (Medico m : medicos) {
            System.out.printf("ID: %d | Nome: %s | Email: %s | Telefone: %s | CRM: %s | Especialidade: %s\n",
                    m.getId_medico(),
                    m.getNome(),
                    m.getEmail(),
                    m.getTelefone(),
                    m.getCrm(),
                    m.getEspecialidade());
        }
    }

    private static void atualizarMedico() {
        try {
            System.out.println("\n-- Atualizar Médico --");
            System.out.print("Digite o ID do médico a atualizar: ");
            int id = lerOpcao();
            Medico medico = medicoController.buscarPorId(id);

            if (medico == null) {
                System.out.println("Médico não encontrado.");
                return;
            }

            System.out.println("Deixe o campo em branco para manter a informação atual.");

            System.out.print("Nome (" + medico.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                medico.setNome(nome);
            }

            System.out.print("Email (" + medico.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                medico.setEmail(email);
            }

            System.out.print("Telefone (" + medico.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) {
                medico.setTelefone(telefone);
            }

            System.out.print("CRM (" + medico.getCrm() + "): ");
            String crm = scanner.nextLine();
            if (!crm.isEmpty()) {
                medico.setCrm(crm);
            }

            System.out.print("Especialidade (" + medico.getEspecialidade() + "): ");
            String especialidade = scanner.nextLine();
            if (!especialidade.isEmpty()) {
                medico.setEspecialidade(especialidade);
            }

            medicoController.atualizar(medico);
            System.out.println("Médico atualizado com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar médico: " + e.getMessage());
        }
    }

    private static void excluirMedico() {
        try {
            System.out.println("\n-- Excluir Médico --");
            System.out.print("Digite o ID do médico a excluir: ");
            int id = lerOpcao();
            Medico medico = medicoController.buscarPorId(id);

            if (medico == null) {
                System.out.println("Médico não encontrado.");
                return;
            }

            System.out.print("Tem certeza que deseja excluir " + medico.getNome() + "? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                medicoController.excluir(id);
                System.out.println("Médico excluído com sucesso.");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir médico: " + e.getMessage());
        }
    }

    private static void buscarMedicoPorId() {
        try {
            System.out.println("\n-- Buscar Médico por ID --");
            System.out.print("Digite o ID do médico: ");
            int id = lerOpcao();
            Medico m = medicoController.buscarPorId(id);

            if (m == null) {
                System.out.println("Médico não encontrado.");
                return;
            }

            System.out.println("Médico encontrado:");
            System.out.printf("ID: %d\n", m.getId_medico());
            System.out.printf("Nome: %s\n", m.getNome());
            System.out.printf("Email: %s\n", m.getEmail());
            System.out.printf("Telefone: %s\n", m.getTelefone());
            System.out.printf("CRM: %s\n", m.getCrm());
            System.out.printf("Especialidade: %s\n", m.getEspecialidade());

        } catch (Exception e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
        }
    }
    // Gerenciamento de Hospitais

    private static void menuGerenciarHospitais() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Gerenciar Hospitais ---");
            System.out.println("1. Cadastrar Novo Hospital");
            System.out.println("2. Listar Todos os Hospitais");
            System.out.println("3. Atualizar Hospital");
            System.out.println("4. Excluir Hospital");
            System.out.println("5. Buscar Hospital por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    cadastrarHospital();
                    break;
                case 2:
                    listarHospitais();
                    break;
                case 3:
                    atualizarHospital();
                    break;
                case 4:
                    excluirHospital();
                    break;
                case 5:
                    buscarHospitalPorId();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarHospital() {
        try {
            System.out.println("\n-- Cadastrar Hospital --");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            Hospital hospital = new Hospital();
            hospital.setNome(nome);
            hospital.setEndereco(endereco);
            hospital.setTelefone(telefone);

            hospitalController.salvar(hospital);
            System.out.println("Hospital salvo com sucesso! ID gerado: " + hospital.getId_hospital());

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar hospital: " + e.getMessage());
        }
    }

    private static void listarHospitais() {
        System.out.println("\n-- Lista de Hospitais --");
        List<Hospital> hospitais = hospitalController.listarTodos();
        if (hospitais.isEmpty()) {
            System.out.println("Nenhum hospital cadastrado.");
            return;
        }
        for (Hospital h : hospitais) {
            System.out.printf("ID: %d | Nome: %s | Endereço: %s | Telefone: %s\n",
                    h.getId_hospital(),
                    h.getNome(),
                    h.getEndereco(),
                    h.getTelefone());
        }
    }

    private static void atualizarHospital() {
        try {
            System.out.println("\n-- Atualizar Hospital --");
            System.out.print("Digite o ID do hospital a atualizar: ");
            int id = lerOpcao();
            Hospital hospital = hospitalController.buscarPorId(id);

            if (hospital == null) {
                System.out.println("Hospital não encontrado.");
                return;
            }

            System.out.println("Deixe o campo em branco para manter a informação atual.");

            System.out.print("Nome (" + hospital.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                hospital.setNome(nome);
            }

            System.out.print("Endereço (" + hospital.getEndereco() + "): ");
            String endereco = scanner.nextLine();
            if (!endereco.isEmpty()) {
                hospital.setEndereco(endereco);
            }

            System.out.print("Telefone (" + hospital.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) {
                hospital.setTelefone(telefone);
            }

            hospitalController.atualizar(hospital);
            System.out.println("Hospital atualizado com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar hospital: " + e.getMessage());
        }
    }

    private static void excluirHospital() {
        try {
            System.out.println("\n-- Excluir Hospital --");
            System.out.print("Digite o ID do hospital a excluir: ");
            int id = lerOpcao();
            Hospital hospital = hospitalController.buscarPorId(id);

            if (hospital == null) {
                System.out.println("Hospital não encontrado.");
                return;
            }

            System.out.print("Tem certeza que deseja excluir " + hospital.getNome() + "? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                hospitalController.excluir(id);
                System.out.println("Hospital excluído com sucesso.");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir hospital: " + e.getMessage());
        }
    }

    private static void buscarHospitalPorId() {
        try {
            System.out.println("\n-- Buscar Hospital por ID --");
            System.out.print("Digite o ID do hospital: ");
            int id = lerOpcao();
            Hospital h = hospitalController.buscarPorId(id);

            if (h == null) {
                System.out.println("Hospital não encontrado.");
                return;
            }

            System.out.println("Hospital encontrado:");
            System.out.printf("ID: %d\n", h.getId_hospital());
            System.out.printf("Nome: %s\n", h.getNome());
            System.out.printf("Endereço: %s\n", h.getEndereco());
            System.out.printf("Telefone: %s\n", h.getTelefone());

        } catch (Exception e) {
            System.out.println("Erro ao buscar hospital: " + e.getMessage());
        }
    }
    // Gerenciamento de Leitos

    private static void menuGerenciarLeitos() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Gerenciar Leitos ---");
            System.out.println("1. Cadastrar Novo Leito");
            System.out.println("2. Listar Todos os Leitos");
            System.out.println("3. Atualizar Leito");
            System.out.println("4. Excluir Leito");
            System.out.println("5. Buscar Leito por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    cadastrarLeito();
                    break;
                case 2:
                    listarLeitos();
                    break;
                case 3:
                    atualizarLeito();
                    break;
                case 4:
                    excluirLeito();
                    break;
                case 5:
                    buscarLeitoPorId();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarLeito() {
        try {
            System.out.println("\n-- Cadastrar Leito --");
            System.out.print("Número do Leito (ex: 101-A, 205-B): ");
            String numero = scanner.nextLine();
            System.out.print("Tipo do Leito (ex: UTI, Enfermaria, Apartamento): ");
            String tipo = scanner.nextLine();
            System.out.print("Status (ex: Disponível, Ocupado, Manutenção): ");
            String status = scanner.nextLine();

            Leito leito = new Leito();
            leito.setNumero(numero);
            leito.setTipo(tipo);
            leito.setStatus(status);

            leitoController.salvar(leito);
            System.out.println("Leito salvo com sucesso! ID gerado: " + leito.getId_leito());

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar leito: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarLeitos() {
        System.out.println("\n-- Lista de Leitos --");
        List<Leito> leitos = leitoController.listarTodos();
        if (leitos.isEmpty()) {
            System.out.println("Nenhum leito cadastrado.");
            return;
        }
        for (Leito l : leitos) {
            System.out.printf("ID: %d | Número: %s | Tipo: %s | Status: %s\n",
                    l.getId_leito(),
                    l.getNumero(),
                    l.getTipo(),
                    l.getStatus());
        }
    }

    private static void atualizarLeito() {
        try {
            System.out.println("\n-- Atualizar Leito --");
            System.out.print("Digite o ID do leito a atualizar: ");
            int id = lerOpcao();
            Leito leito = leitoController.buscarPorId(id);

            if (leito == null) {
                System.out.println("Leito não encontrado.");
                return;
            }

            System.out.println("Deixe o campo em branco para manter a informação atual.");

            System.out.print("Número (" + leito.getNumero() + "): ");
            String numero = scanner.nextLine();
            if (!numero.isEmpty()) {
                leito.setNumero(numero);
            }

            System.out.print("Tipo (" + leito.getTipo() + "): ");
            String tipo = scanner.nextLine();
            if (!tipo.isEmpty()) {
                leito.setTipo(tipo);
            }

            System.out.print("Status (" + leito.getStatus() + "): ");
            String status = scanner.nextLine();
            if (!status.isEmpty()) {
                leito.setStatus(status);
            }

            leitoController.atualizar(leito);
            System.out.println("Leito atualizado com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar leito: " + e.getMessage());
        }
    }

    private static void excluirLeito() {
        try {
            System.out.println("\n-- Excluir Leito --");
            System.out.print("Digite o ID do leito a excluir: ");
            int id = lerOpcao();
            Leito leito = leitoController.buscarPorId(id);

            if (leito == null) {
                System.out.println("Leito não encontrado.");
                return;
            }

            System.out.print("Tem certeza que deseja excluir o leito " + leito.getNumero() + "? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                leitoController.excluir(id);
                System.out.println("Leito excluído com sucesso.");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir leito: " + e.getMessage());
        }
    }

    private static void buscarLeitoPorId() {
        try {
            System.out.println("\n-- Buscar Leito por ID --");
            System.out.print("Digite o ID do leito: ");
            int id = lerOpcao();
            Leito l = leitoController.buscarPorId(id);

            if (l == null) {
                System.out.println("Leito não encontrado.");
                return;
            }

            System.out.println("Leito encontrado:");
            System.out.printf("ID do Leito: %d\n", l.getId_leito());
            System.out.printf("Número: %s\n", l.getNumero());
            System.out.printf("Tipo: %s\n", l.getTipo());
            System.out.printf("Status: %s\n", l.getStatus());

        } catch (Exception e) {
            System.out.println("Erro ao buscar leito: " + e.getMessage());
        }
    }
    //  Gerenciamento de Funcionários

    private static void menuGerenciarFuncionarios() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n--- Gerenciar Funcionários ---");
            System.out.println("1. Cadastrar Novo Funcionário");
            System.out.println("2. Listar Todos os Funcionários");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Excluir Funcionário");
            System.out.println("5. Buscar Funcionário por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    atualizarFuncionario();
                    break;
                case 4:
                    excluirFuncionario();
                    break;
                case 5:
                    buscarFuncionarioPorId();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }


//Gerenciamento de Funcionários

    private static void cadastrarFuncionario() {
        try {
            System.out.println("\n-- Cadastrar Funcionário --");


            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Cargo (ex: Enfermeiro, Recepcionista): ");
            String cargo = scanner.nextLine();


            System.out.print("ID do Hospital de Lotação: ");
            int idHospital = lerOpcao();


            Hospital hospital = hospitalController.buscarPorId(idHospital);

            if (hospital == null) {
                System.out.println("Hospital com ID " + idHospital + " não encontrado. O funcionário não será cadastrado.");
                return;
            }


            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(cpf);
            funcionario.setNome(nome);
            funcionario.setEmail(email);
            funcionario.setTelefone(telefone);
            funcionario.setCargo(cargo);
            funcionario.setHospital(hospital);

            funcionarioController.salvar(funcionario);
            System.out.println("Funcionário salvo com sucesso! ID gerado: " + funcionario.getId_funcionario());

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }



    private static void listarFuncionarios() {
        System.out.println("\n-- Lista de Funcionários --");
        List<Funcionario> funcionarios = funcionarioController.listarTodos();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.printf("ID: %d | Nome: %s | Email: %s | Telefone: %s | Cargo: %s\n",
                    f.getId_funcionario(),
                    f.getNome(),
                    f.getEmail(),
                    f.getTelefone(),
                    f.getCargo());
        }
    }

    private static void atualizarFuncionario() {
        try {
            System.out.println("\n-- Atualizar Funcionário --");
            System.out.print("Digite o ID do funcionário a atualizar: ");
            int id = lerOpcao();
            Funcionario funcionario = funcionarioController.buscarPorId(id);

            if (funcionario == null) {
                System.out.println("Funcionário não encontrado.");
                return;
            }

            System.out.println("Deixe o campo em branco para manter a informação atual.");

            System.out.print("Nome (" + funcionario.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                funcionario.setNome(nome);
            }

            System.out.print("Email (" + funcionario.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                funcionario.setEmail(email);
            }

            System.out.print("Telefone (" + funcionario.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) {
                funcionario.setTelefone(telefone);
            }

            System.out.print("Cargo (" + funcionario.getCargo() + "): ");
            String cargo = scanner.nextLine();
            if (!cargo.isEmpty()) {
                funcionario.setCargo(cargo);
            }

            funcionarioController.atualizar(funcionario);
            System.out.println("Funcionário atualizado com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    private static void excluirFuncionario() {
        try {
            System.out.println("\n-- Excluir Funcionário --");
            System.out.print("Digite o ID do funcionário a excluir: ");
            int id = lerOpcao();
            Funcionario funcionario = funcionarioController.buscarPorId(id);

            if (funcionario == null) {
                System.out.println("Funcionário não encontrado.");
                return;
            }

            System.out.print("Tem certeza que deseja excluir " + funcionario.getNome() + "? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                funcionarioController.excluir(id);
                System.out.println("Funcionário excluído com sucesso.");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }

    private static void buscarFuncionarioPorId() {
        try {
            System.out.println("\n-- Buscar Funcionário por ID --");
            System.out.print("Digite o ID do funcionário: ");
            int id = lerOpcao();
            Funcionario f = funcionarioController.buscarPorId(id);

            if (f == null) {
                System.out.println("Funcionário não encontrado.");
                return;
            }

            System.out.println("Funcionário encontrado:");
            System.out.printf("ID: %d\n", f.getId_funcionario());
            System.out.printf("Nome: %s\n", f.getNome());
            System.out.printf("Email: %s\n", f.getEmail());
            System.out.printf("Telefone: %s\n", f.getTelefone());
            System.out.printf("Cargo: %s\n", f.getCargo());

        } catch (Exception e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }
    }
}