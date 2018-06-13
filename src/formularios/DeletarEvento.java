package formularios;

import bancodedados.dao.EventoDAO;
import java.util.Scanner;
import negocio.Evento;
import uteis.Funcoes;

public class DeletarEvento {
    
    public DeletarEvento(){
        inicializar();
    }
        
    private void inicializar(){
        Funcoes.cabecalho("Deletar evento");
        Scanner scanner = new Scanner(System.in);
        System.out.print("BUSCAR ID: ");
        try {
            Evento evento = new bancodedados.dao.EventoDAO().getEvento(scanner.nextInt()); 
            if(evento.getId() > 0){
                System.out.println("\n:: ID: " + evento.getId());
                System.out.println(":: NOME: " + evento.getNome());            
                System.out.println(":: DATA: " + evento.getDataInicio());
                System.out.println(":: HORA: " + evento.getHoraInicio());
                System.out.println(":: LOCAL: " + evento.getEndereco() + "\n");
                
                
                Funcoes.subCabecalho("Confirmação");                
                System.out.print("Deseja realmente deletar esse evento? [s/n]: ");
                if(Funcoes.opcSimNao()){
                    new EventoDAO().removerEvento(evento);
                    System.out.println("Evento foi removido com exito.");
                }
                else{
                    System.out.println("Evento foi mantido cadastrado com ID: " + evento.getId());
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
