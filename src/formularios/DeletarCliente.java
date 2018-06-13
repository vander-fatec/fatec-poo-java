package formularios;

import bancodedados.dao.ClienteDAO;
import java.util.Scanner;
import negocio.Cliente;
import uteis.Funcoes;

public class DeletarCliente {
    
    public DeletarCliente(){
        inicializar();
    }
        
    private void inicializar(){
        Funcoes.cabecalho("Deletar cliente");
        Scanner scanner = new Scanner(System.in);
        System.out.print("BUSCAR ID: ");
        try {
            String cpf_cnpj = scanner.nextLine(); 
            if(new ClienteDAO().getClienteCount(cpf_cnpj) > 0){
                Cliente cliente = new ClienteDAO().getCliente(cpf_cnpj);
                
                System.out.println("\n:: CPF/CNPJ: " + cliente.getCpfCnpj());
                System.out.println(":: NOME: " + cliente.getNome() + "\n");  
                
                Funcoes.subCabecalho("Confirmação");                
                System.out.print("Deseja realmente deletar esse evento? [s/n]: ");
                if(Funcoes.opcSimNao()){
                    new ClienteDAO().removerCliente(cliente);
                    System.out.println("Cliente foi removido com exito.");
                }
                else{
                    System.out.println("Cliente foi mantido cadastrado");
                }
            }
            else{
                System.out.println("Nenhum cliente encontrado com esse CPF/CNPJ.");
            }
        } catch (Exception e) {
            System.out.println("Entrada inconsistente");
        }
    }
}
