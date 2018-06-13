package formularios;

import bancodedados.dao.ClienteDAO;
import java.util.Scanner;
import negocio.Cliente;
import uteis.Funcoes;

public class AlterarCliente {
    
    public AlterarCliente(){
        inicializar();
    }
    
    private void inicializar(){
        Funcoes.cabecalho("Alterar cliente");
        Scanner scanner = new Scanner(System.in);
       
        System.out.print("BUSCAR CPF/CNPJ: ");
        
        try {
            String cpf_cnpj = scanner.nextLine(); 
            if(new ClienteDAO().getClienteCount(cpf_cnpj) > 0){
                Cliente cliente = new ClienteDAO().getCliente(cpf_cnpj);
                
                System.out.println("\n:: CPF/CNPJ: " + cliente.getCpfCnpj());
                System.out.println(":: NOME: " + cliente.getNome() + "\n"); 
                
                Funcoes.subCabecalho("Novos dados");
                 
                while(true){
                    System.out.print("Alterar CPF/CNPJ  [s/n]: ");
                    String entrada = scanner.nextLine();
                    boolean sn = false;
                    
                    if(entrada.equals("n") || entrada.equals("N")){
                        sn = false;
                        break;
                    }
                    else if (entrada.equals("s") || entrada.equals("S")){
                        System.out.print("NOVO CPF/CNPJ: ");
                        cliente.setCpfCnpj(scanner.nextLine());
                        break;
                    }
                    else{
                        System.out.println("Entrada inconsistente.");
                    }                    
                }
                
                System.out.print("\n");
                
                while(true){
                    System.out.print("Alterar NOME  [s/n]: ");
                    String entrada = scanner.nextLine();
                    boolean sn = false;
                    
                    if(entrada.equals("n") || entrada.equals("N")){
                        sn = false;
                        break;
                    }
                    else if (entrada.equals("s") || entrada.equals("S")){
                        System.out.print("NOVO NOME: ");
                        cliente.setNome(scanner.nextLine());
                        break;
                    }
                    else{
                        System.out.println("Entrada inconsistente.");
                    }                    
                }                             
                
                new ClienteDAO().altera(cliente);
                System.out.println("\nDados do cliente " + cliente.getNome() + " atualizados!");
            }
            else{
                System.out.println("Cliente não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Entrada inconsistente");
        }
    }
    
}
