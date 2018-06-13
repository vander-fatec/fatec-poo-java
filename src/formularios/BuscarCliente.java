package formularios;

import java.util.ArrayList;
import java.util.Scanner;
import negocio.Cliente;
import uteis.Funcoes;

public class BuscarCliente {
    
    public BuscarCliente(){
        Funcoes.cabecalho("Buscar Cliente");
        String msg = (""
                + Funcoes.ANSI_PURPLE + "[1] " + Funcoes.ANSI_RESET + "Listar todos clientes\n"
                + Funcoes.ANSI_PURPLE + "[2] " + Funcoes.ANSI_RESET + "Buscar por CPF/CNPJ\n"
                + ":: ");
        switch(Funcoes.escolherOpcao(1, 2, msg)){
            case 1: listarTodos();
                break;
            case 2: buscarPorCpfCnpjNome();
                break;
        }
    }
    
    private void listarTodos(){
        Funcoes.cabecalho("Todos clientes");
        try{
            ArrayList<Cliente> clientes = new bancodedados.dao.ClienteDAO().getClientes();
            if(clientes.size() > 0){
                for(int x = 0; x < clientes.size(); x++){
                    System.out.println(":: CPF/CNPJ: " + clientes.get(x).getCpfCnpj());
                    System.out.println(":: NOME: " + clientes.get(x).getNome());                    
                    if(clientes.size() != (x+1)) System.out.print("\n");
                }
            }
            else{
                System.out.println("Não há clientes cadastrados");
            }
        } catch (Exception e) {
            System.out.println("Busca inconsistente");
        }
    }
    
    private void buscarPorCpfCnpjNome(){
        System.out.print("\n");
        Funcoes.subCabecalho("Cliente por CPF/CNPJ ou NOME");
        Scanner scanner = new Scanner(System.in);
        System.out.print("BUSCAR CPF/CNPJ ou NOME: ");
        try {
            String cpf_cnpj_nome = scanner.nextLine();
            ArrayList<Cliente> clientes = new bancodedados.dao.ClienteDAO().getClientes(cpf_cnpj_nome);
            if(clientes.size() > 0){
                for(int x=0; x<clientes.size(); x++){
                    System.out.println("\n:: CPF/CNPJ: " + clientes.get(x).getCpfCnpj());
                    System.out.println(":: NOME: " + clientes.get(x).getNome());
                    if(clientes.size() != (x+1)) System.out.print("\n");
                }
            }
            else{
                System.out.println("Nenhum cliente encontrado nessa busca.");
            }
        } catch (Exception e) {
            System.out.println("Busca inconsistente");
        }
    }
    
}
