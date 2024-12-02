package com.example.menus;

import java.util.Scanner;

import com.example.dados.Administrador;
import com.example.dados.Usuario;
import com.example.negocio.GerenciamentoSalas;

public class MenuInicial {

    public void apresentarMenuInicial(GerenciamentoSalas gerenciamento) {

        Scanner scanner = new Scanner(System.in);
        int executando = 1;

        while (executando == 1) {
            System.out.println("\n.-.-.-.-.-.- Gerenciamento de Salas .-.-.-.-.-.-");
            System.out.println("1. Cadastrar usario");
            System.out.println("2. Entrar");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            switch (opcao) {
                case 1:
                    System.out.print("Insira o nome ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.print("Insira o CPF ");
                    String CPF = scanner.nextLine();
                    System.out.print("Insira o telefone ");
                    String telefone = scanner.nextLine();
                    System.out.print("Insira uma senha ");
                    String senha = scanner.nextLine();

                    if(gerenciamento.cadastroUsuario(new Usuario(nomeUsuario, CPF, telefone, senha))) {
                        System.out.println("Usuario cadastrado com sucesso!");
                    }
                break;
                case 2:
                    System.out.println("Insira o CPF e senha");
                    String CPFU = scanner.nextLine();
                    String senhaU = scanner.nextLine();
                    
                    Usuario usuario = gerenciamento.entrar(CPFU, senhaU);
                    if(usuario == null) {
                        System.out.println("Usuario nao encontrado");
                    } else {
                        if (usuario instanceof Administrador) {
                            MenuADM menuADM = new MenuADM();
                            menuADM.apresentarMenuAdm(usuario, gerenciamento);
                        } else {
                            MenuComum menuComum = new MenuComum();
                            menuComum.apresentarMenuComum(usuario, gerenciamento);
                        }
                    }
                break;
                case 3:
                    System.out.println("Encerrando...");
                    executando = 0;
                break;
                case 4:
                    System.out.print("Insira o nome ");
                    String nomeAdm = scanner.nextLine();
                    System.out.print("Insira o CPF ");
                    String CPFAdm = scanner.nextLine();
                    System.out.print("Insira o telefone ");
                    String telefoneAdm = scanner.nextLine();
                    System.out.print("Insira uma senha ");
                    String senhaAdm = scanner.nextLine();
                    gerenciamento.cadastroUsuario(new Administrador(nomeAdm, CPFAdm, telefoneAdm, senhaAdm));
                break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}
