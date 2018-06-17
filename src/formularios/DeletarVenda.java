package formularios;

import bancodedados.dao.VendaDAO;
import java.util.Scanner;
import negocio.Venda;
import uteis.Funcoes;

public class DeletarVenda {
    
    public DeletarVenda(){
        inicializar();
    }
    
    private void inicializar(){
        Funcoes.cabecalho("Deletar evento");
        Scanner scanner = new Scanner(System.in);
        System.out.print("BUSCAR ID: ");
        try {
            //removerVenda
            Venda venda = new bancodedados.dao.VendaDAO().getVenda(scanner.nextInt()); 
            if(venda.getId() > 0){
                //System.out.println("\n:: ID: " + venda.getId());
                //System.out.println(":: NOME: " + venda.getNome());            
               // System.out.println(":: DATA: " + venda.getDataInicio());
                //System.out.println(":: HORA: " + venda.getHoraInicio());
                //System.out.println(":: LOCAL: " + venda.getEndereco() + "\n");
                
                
                Funcoes.subCabecalho("Confirmação");                
                System.out.print("Deseja realmente deletar esse evento? [s/n]: ");
                if(Funcoes.opcSimNao()){
                    new VendaDAO().removerVenda(venda);
                    System.out.println("Evento foi removido com exito.");
                }
                else{
                    System.out.println("Evento foi mantido cadastrado com ID: " + venda.getId());
                }
            }
            else{
                System.out.println("Nenhum evento encontrado com essa id.");
            }
        } catch (Exception e) {
            System.out.println("Entrada inconsistente");
        }
    }
    
}
