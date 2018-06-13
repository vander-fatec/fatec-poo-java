package formularios;

import bancodedados.dao.ClienteDAO;
import java.util.Scanner;
import negocio.Cliente;
import uteis.Funcoes;

public class CadastrarCliente {
    
    public CadastrarCliente(){
        inicializar();
    }

    private void inicializar(){
        Scanner scanner = new Scanner(System.in);
        boolean aux = true;

        Funcoes.cabecalho("Cadastro cliente");
        System.out.print("CPF/CNPJ: ");
        String cpf_cnpj = scanner.nextLine();

        if(new bancodedados.dao.ClienteDAO().getClienteCount(cpf_cnpj) == 0){
            Cliente cliente = new Cliente();
            cliente.setCpfCnpj(cpf_cnpj);

            System.out.print("NOME: ");
            cliente.setNome(scanner.nextLine());
            new ClienteDAO().adiciona(cliente);

            System.out.print("\n");
            System.out.println(":: CPF/CNPJ: " + cliente.getCpfCnpj());
            System.out.println(":: NOME: " + cliente.getNome());
            aux = false;                
        }
        else{
            System.out.println("Cliente cadastrado.");
        }
    }
    
}
