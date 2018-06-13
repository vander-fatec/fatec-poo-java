package formularios;

import bancodedados.dao.EventoDAO;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import negocio.Evento;
import uteis.Funcoes;

public class AlterarEvento {
    
    public AlterarEvento(){
        inicializar();
    }
    
    private void inicializar(){
        Funcoes.cabecalho("Alterar evento");
        Scanner scanner = new Scanner(System.in);
        System.out.print("BUSCAR ID: ");
        try {
            Evento evento = new bancodedados.dao.EventoDAO().getEvento(scanner.nextInt()); 
            if(evento.getId() > 0){
                System.out.println("\n:: ID EVENTO " + evento.getId());
                System.out.println(":: NOME: " + evento.getNome());
                System.out.println(":: DATA: " + Funcoes.dateToString(evento.getDataInicio()));
                System.out.println(":: HORA: " + Funcoes.timeToString(evento.getHoraInicio()));
                System.out.println(":: ENDEREÇO: " + evento.getEndereco());
                System.out.println("\n:: INGRESSOS DISPONÍVEIS: " + evento.getQuantidadeIngresso());
                System.out.println(":: VALOR DO INGRESSO: " +  Funcoes.valorToString(evento.getValorIngresso()) + "\n");
                
                Funcoes.subCabecalho("Novos dados");     
                scanner.nextLine();
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
                        evento.setNome(scanner.nextLine());
                        break;
                    }
                    else{
                        System.out.println("Entrada inconsistente.");
                    }                    
                }
                System.out.print("\n");
                
                while(true){
                    System.out.print("Alterar DATA  [s/n]: ");
                    String entrada = scanner.nextLine();
                    boolean sn = false;
                    
                    if(entrada.equals("n") || entrada.equals("N")){
                        sn = false;
                        break;
                    }
                    else if (entrada.equals("s") || entrada.equals("S")){
                        try {
                            System.out.print("NOVA DATA: ");
                            String dataAux = scanner.nextLine();
                            try {
                                int dia = Integer.parseInt(dataAux.substring(0, 2));
                                int mes = Integer.parseInt(dataAux.substring(3, 5));
                                int ano = Integer.parseInt(dataAux.substring(6, 10));
                                Date data = new Date(ano-1900,mes-1,dia);
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                                evento.setDataInicio(data);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Entrada inconsistente.");
                            }                            
                        } catch (NumberFormatException  ex) {
                            System.out.println("Entrada inconsistente.");
                            return;
                        }
                        break;
                    }
                    else{
                        System.out.println("Entrada inconsistente.");
                    }                    
                }
                
                System.out.print("\n");
                
                while(true){
                    System.out.print("Alterar HORA  [s/n]: ");
                    String entrada = scanner.nextLine();
                    boolean sn = false;
                    
                    if(entrada.equals("n") || entrada.equals("N")){
                        sn = false;
                        break;
                    }
                    else if (entrada.equals("s") || entrada.equals("S")){
                        try {
                            System.out.print("NOVA HORA: ");
                            String horaAux = scanner.nextLine();
                            Date data = null;
                            int hora = Integer.parseInt(horaAux.substring(0, 2));
                            int minuto = Integer.parseInt(horaAux.substring(3, 5));
                            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
                            try{
                                data = sf.parse(Integer.toString(hora)+":"+Integer.toString(minuto));
                            }catch(ParseException e){
                                System.out.println("Erro na conversão de hora.");
                                return;
                            }            
                            Time time = new Time(data.getTime());            
                            evento.setHoraInicio(time);
                        } catch (NumberFormatException  e) {
                            System.out.println("Entrada inconsistente.");
                            return;
                        }
                        break;
                    }
                    else{
                        System.out.println("Entrada inconsistente.");
                    }                    
                }
                
                System.out.print("\n");
                
                while(true){
                    System.out.print("Alterar ENDEREÇO [s/n]: ");
                    String entrada = scanner.nextLine();
                    boolean sn = false;
                    
                    if(entrada.equals("n") || entrada.equals("N")){
                        sn = false;
                        break;
                    }
                    else if (entrada.equals("s") || entrada.equals("S")){
                        System.out.print("NOVO ENDEREÇO: ");
                        evento.setEndereco(scanner.nextLine());
                        break;
                    }
                    else{
                        System.out.println("Entrada inconsistente.");
                    }                    
                }
                
                System.out.print("\n");

                while(true){
                    System.out.print("Alterar QUANTIDADE DE INGRESSOS [s/n]: ");
                    String entrada = scanner.nextLine();
                    boolean sn = false;
                    
                    if(entrada.equals("n") || entrada.equals("N")){
                        sn = false;
                        break;
                    }
                    else if (entrada.equals("s") || entrada.equals("S")){
                        System.out.print("NOVA QUANTIDADE: ");
                        evento.setQuantidadeIngresso(scanner.nextInt());
                        break;
                    }
                    else{
                        System.out.println("Entrada inconsistente.");
                    }                    
                }    
                
                System.out.print("\n");
                
                while(true){
                    System.out.print("Alterar VALOR DO INGRESSO [s/n]: ");
                    String entrada = scanner.nextLine();
                    boolean sn = false;
                    
                    if(entrada.equals("n") || entrada.equals("N")){
                        sn = false;
                        break;
                    }
                    else if (entrada.equals("s") || entrada.equals("S")){
                        System.out.print("NOVO VALOR: R$ ");
                        evento.setValorIngresso(scanner.nextFloat());
                        break;
                    }
                    else{
                        System.out.println("Entrada inconsistente.");
                    }                    
                }  
                
                new EventoDAO().altera(evento);
                System.out.println("\nEvento " + evento.getId() + " atualizado!");
            }
            else{
                System.out.println("Nenhum usário encontrado com essa id.");
            }
        } catch (Exception e) {
            System.out.println("Entrada inconsistente");
        }
    }
    
}
