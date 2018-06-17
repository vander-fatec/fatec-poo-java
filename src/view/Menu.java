package view;

import formularios.*;
import java.util.Scanner;
import negocio.Login;
import uteis.Funcoes;

public class Menu {
    Scanner scanner;
    
    public Menu(){
        scanner = new Scanner(System.in);
        if(new Login().getUsuario().getFuncao().getId()==1){
            menuAdmin();
        }else
        if(new Login().getUsuario().getFuncao().getId()==2){
            menuVendedor();
        }
    }
    
    protected void menuLogin(){        
        while(true){                    
            Funcoes.cabecalho("LOGIN");
            System.out.print(""
                + Funcoes.ANSI_PURPLE + "[1]" + Funcoes.ANSI_RESET + " Login\n"
                + Funcoes.ANSI_RED + "[0]" + Funcoes.ANSI_RESET + " Sair do programa\n");
            
            switch(Funcoes.escolherOpcao(0, 1)){
                case 1 : new LoginEntrar();
                    break;
                case 0 : System.exit(0); break;
                default: System.out.print("Entrada incoerente, tente novamente.\n");
                    break;
            }
        }
    }
    
    private void menuVendedor(){
        while(true){            
            Funcoes.cabecalho("MENU");
            System.out.println(Funcoes.ANSI_PURPLE + ""+
                    new Login().getUsuario().getNome() + " | " 
                    + new Login().getUsuario().getFuncao().getNome() + ""
                    + Funcoes.ANSI_RESET);
            System.out.print(""
            + Funcoes.ANSI_PURPLE +"[1]" + Funcoes.ANSI_RESET + " Venda\n"
            + Funcoes.ANSI_PURPLE +"[2]" + Funcoes.ANSI_RESET + " Cliente\n"
            + Funcoes.ANSI_PURPLE +"[3]" + Funcoes.ANSI_RESET + " Configurações\n"
            + Funcoes.ANSI_RED +"[0]" + Funcoes.ANSI_RESET + " Deslogar\n");
                        
            switch(Funcoes.escolherOpcao(0, 3)){
                case 1 : menuVenda();
                    break;
                case 2 : menuCliente();
                   break;
                case 3 : menuAlterarSenha();
                   break;
                case 0 : menuLogin(); 
                    break;
                default: System.out.print("Entrada incoerente, tente novamente.\n");
                    break;
            }
        }
    }
    
    private void menuAdmin(){
        while(true){            
            Funcoes.cabecalho("MENU");
            System.out.println(Funcoes.ANSI_PURPLE + ""+
                    new Login().getUsuario().getNome() + " | " 
                    + new Login().getUsuario().getFuncao().getNome() + ""
                    + Funcoes.ANSI_RESET);
            System.out.print(""
            + Funcoes.ANSI_PURPLE +"[1]" + Funcoes.ANSI_RESET + " Venda\n"
            + Funcoes.ANSI_PURPLE +"[2]" + Funcoes.ANSI_RESET + " Evento\n"
            + Funcoes.ANSI_PURPLE +"[3]" + Funcoes.ANSI_RESET + " Cliente\n"
            + Funcoes.ANSI_PURPLE +"[4]" + Funcoes.ANSI_RESET + " Usuario\n"                    
            + Funcoes.ANSI_PURPLE +"[5]" + Funcoes.ANSI_RESET + " Configurações\n"
            + Funcoes.ANSI_RED +"[0]" + Funcoes.ANSI_RESET + " Deslogar\n");
                        
            switch(Funcoes.escolherOpcao(0, 5)){
                case 1 : menuVenda();
                    break;
                case 2 : menuEvento();
                   break;
                case 3 : menuCliente();
                   break;
                case 4 : menuUsuario();
                   break;
                case 5 : menuAlterarSenha();
                   break;
                case 0 : menuLogin(); 
                    break;
                default: System.out.print("Entrada incoerente, tente novamente.\n");
                    break;
            }
        }
    }
    
    private void menuVenda(){        
        int entrada = -1;
        while(entrada != 0){
            Funcoes.cabecalho("MENU > VENDA");
            System.out.print(""
            + Funcoes.ANSI_PURPLE +"[1]" + Funcoes.ANSI_RESET + " Efetuar venda\n"
            + Funcoes.ANSI_PURPLE +"[2]" + Funcoes.ANSI_RESET + " Consultar venda\n"
            + Funcoes.ANSI_PURPLE +"[3]" + Funcoes.ANSI_RESET + " Estornar venda\n"
            + Funcoes.ANSI_RED +"[0]" + Funcoes.ANSI_RESET + " Voltar ao menu\n"
            + ":: ");
             
            switch(entrada = scanner.nextInt()){
                case 1 : new CadastrarVenda();
                    break;
                case 2 : new BuscarVenda();                 
                   break;
                case 3 : new DeletarVenda();                 
                   break;
                case 0 : new Menu(); 
                    break;
                default: System.out.print("Entrada incoerente, tente novamente.\n");
                    break;
            }
        }
    }
    
    private void menuEvento(){
        int entrada = -1;
        while(entrada != 0){
            Funcoes.cabecalho("MENU > EVENTO");
            System.out.print(""
            + Funcoes.ANSI_PURPLE +"[1]" + Funcoes.ANSI_RESET + " Cadastrar evento\n"
            + Funcoes.ANSI_PURPLE +"[2]" + Funcoes.ANSI_RESET + " Consultar evento\n"
            + Funcoes.ANSI_PURPLE +"[3]" + Funcoes.ANSI_RESET + " Alterar evento\n"
            + Funcoes.ANSI_PURPLE +"[4]" + Funcoes.ANSI_RESET + " Excluir evento\n"
            + Funcoes.ANSI_RED +"[0]" + Funcoes.ANSI_RESET + " Voltar ao menu\n"
            + ":: ");
             
            switch(entrada = scanner.nextInt()){
                case 1 : new CadastrarEvento();
                    break;
                case 2 : new BuscarEvento();                 
                   break;
                case 3 : new AlterarEvento();
                    break;
                case 4 : new DeletarEvento();
                    break;
                case 0 : new Menu(); 
                    break;
                default: System.out.print("Entrada incoerente, tente novamente.\n");
                    break;
            }
        }
    }
     
    private void menuCliente(){
        int entrada = -1;
        while(entrada != 0){           
            Funcoes.cabecalho("MENU > CLIENTE");
            System.out.print(""
            + Funcoes.ANSI_PURPLE +"[1]" + Funcoes.ANSI_RESET + " Cadastrar cliente\n"
            + Funcoes.ANSI_PURPLE +"[2]" + Funcoes.ANSI_RESET + " Consultar cliente\n"
            + Funcoes.ANSI_PURPLE +"[3]" + Funcoes.ANSI_RESET + " Alterar cliente\n"
            + Funcoes.ANSI_PURPLE +"[4]" + Funcoes.ANSI_RESET + " Excluir cliente\n"
            + Funcoes.ANSI_RED +"[0]" + Funcoes.ANSI_RESET + " Voltar ao menu\n"
            + ":: ");
             
            switch(entrada = scanner.nextInt()){
                case 1 : new CadastrarCliente();
                    break;
                case 2 : new BuscarCliente();                 
                   break;
                case 3 : new AlterarCliente();                 
                   break;
                case 4 : new DeletarCliente();                 
                   break;
                case 0 : new Menu(); 
                    break;
                default: System.out.print("Entrada incoerente, tente novamente.\n");
                    break;
            }
        }
    }
    
    private void menuUsuario(){
        int entrada = -1;
        while(entrada != 0){           
            Funcoes.cabecalho("MENU > USUÁRIO");
            System.out.print(""
            + Funcoes.ANSI_PURPLE +"[1]" + Funcoes.ANSI_RESET + " Cadastrar usuario\n"
            + Funcoes.ANSI_RED +"[0]" + Funcoes.ANSI_RESET + " Voltar ao menu\n"
            + ":: ");
             
            switch(entrada = scanner.nextInt()){
                case 1 : new CadastrarUsuario();
                    break;
                case 0 : new Menu(); 
                    break;
                default: System.out.print("Entrada incoerente, tente novamente.\n");
                    break;
            }
        }
    }
    
    private void menuAlterarSenha(){
        int entrada = -1;
        while(entrada != 0){           
            Funcoes.cabecalho("MENU > USUÁRIO");
            System.out.print(""
            + Funcoes.ANSI_PURPLE +"[1]" + Funcoes.ANSI_RESET + " Alterar minha senha\n"
            + Funcoes.ANSI_PURPLE +"[2]" + Funcoes.ANSI_RESET + " Resetar senha de usuario\n"
            + Funcoes.ANSI_RED +"[0]" + Funcoes.ANSI_RESET + " Voltar ao menu\n"
            + ":: ");
             
            switch(entrada = scanner.nextInt()){
                case 1 : new AlterarSenha();
                    break;
                case 2 : 
                    if(new Login().getUsuario().getFuncao().getId()==1){
                        new ResetarSenha();
                    }  
                    else{
                        System.out.print("É necessário ser Administrador para essa função.\n");
                    }
                    break;
                case 0 : new Menu(); 
                    break;
                default: System.out.print("Entrada incoerente, tente novamente.\n");
                    break;
            }
        }
    }
}