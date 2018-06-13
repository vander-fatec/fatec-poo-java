package formularios;

import java.util.ArrayList;
import java.util.Scanner;
import negocio.Evento;
import uteis.Funcoes;

public class BuscarEvento {
    
    public BuscarEvento(){
        inicializar();        
    }
    
    private void inicializar(){
        Funcoes.cabecalho("Buscar Evento");
        String msg = (""
                + Funcoes.ANSI_PURPLE + "[1] " + Funcoes.ANSI_RESET + "Listar todos eventos\n"
                + Funcoes.ANSI_PURPLE + "[2] " + Funcoes.ANSI_RESET + "Buscar por id\n"
                + ":: ");
        switch(Funcoes.escolherOpcao(1, 2, msg)){
            case 1: listarTodos();
                break;
            case 2: buscarPorId();
                break;
        }
    }
    
    private void listarTodos(){
        Funcoes.cabecalho("Todos eventos");
        try{
            ArrayList<Evento> eventos = new bancodedados.dao.EventoDAO().getEventos();
            System.out.print("");
            if(eventos.size() > 0){
                for(int x = 0; x < eventos.size(); x++){
                    System.out.println(":: EVENTO " + eventos.get(x).getId());
                    System.out.println(":: NOME: " + eventos.get(x).getNome());
                    System.out.println(":: DATA: " + Funcoes.dateToString(eventos.get(x).getDataInicio()));
                    System.out.println(":: HORA: " + Funcoes.timeToString(eventos.get(x).getHoraInicio()));
                    System.out.println(":: ENDEREÇO: " + eventos.get(x).getEndereco());
                    System.out.println(":: INGRESSOS DISPONÍVEIS: " + eventos.get(x).getQuantidadeIngresso());
                    System.out.println(":: VALOR DO INGRESSO: " + Funcoes.valorToString(eventos.get(x).getValorIngresso()));
                    if(eventos.size() != (x+1)) System.out.print("\n");
                }
            }
            else{
                System.out.println("Não há eventos cadastrados");
            }
        } catch (Exception e) {
            System.out.println("Busca inconsistente");
        }
    }
    
    private void buscarPorId(){
        System.out.print("\n");
        Funcoes.subCabecalho("Evento por id");
        Scanner scanner = new Scanner(System.in);
        System.out.print("BUSCAR ID: ");
        try {
            Evento evento = new bancodedados.dao.EventoDAO().getEvento(scanner.nextInt());
            if(evento.getId() > 0){            
                System.out.println("\n:: EVENTO " + evento.getId());
                System.out.println(":: NOME: " + evento.getNome());
                System.out.println(":: DATA: " + Funcoes.dateToString(evento.getDataInicio()));
                System.out.println(":: HORA: " + Funcoes.timeToString(evento.getHoraInicio()));
                System.out.println(":: ENDEREÇO: " + evento.getEndereco());
                System.out.println("\n:: INGRESSOS DISPONÍVEIS: " + evento.getQuantidadeIngresso());
                System.out.println(":: VALOR DO INGRESSO: " +  Funcoes.valorToString(evento.getValorIngresso()));
            }
            else{
                System.out.println("Nenhum evento encontrado com essa id.");
            }
        } catch (Exception e) {
            System.out.println("Busca inconsistente");
        }
    }
    
}
