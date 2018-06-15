package formularios;

import bancodedados.dao.EventoDAO;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import negocio.Evento;
import uteis.Funcoes;

public class CadastrarEvento {
    
    public CadastrarEvento(){
        inicilizar();
    }
    
    private void inicilizar(){
        Funcoes.cabecalho("Cadastrar evento");  
        Funcoes.subCabecalho("Do evento");
        Scanner scanner = new Scanner(System.in);        
        Evento evento = new Evento();
        
        System.out.print("NOME: ");
        evento.setNome(scanner.nextLine());
        
        
        while(true){
        System.out.print("DATA [dd/MM/aaaa]: ");
        String dataAux = scanner.nextLine();
            if(dataAux.length()==10){
                try {
                    int dia = Integer.parseInt(dataAux.substring(0, 2));
                    int mes = Integer.parseInt(dataAux.substring(3, 5));
                    int ano = Integer.parseInt(dataAux.substring(6, 10)); 
                    Date data = new Date(ano-1900,mes-1,dia);
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                    evento.setDataInicio(data);
                    break;
                    /*
                    //para o banco
                        System.out.println("getTime " +data.getDate()+"/"+(data.getMonth()+1)+"/"+(data.getYear()+1900));            
                    //por extenso
                        Locale ptBR = new Locale("pt", "BR");
                        df = DateFormat.getDateInstance(DateFormat.FULL, ptBR);
                        System.out.println(df.format(data));
                    */
                } catch (NumberFormatException  ex) {
                    System.out.println("Entrada inconsistente.");
                }
                
            }
            else{
                System.out.println("Entrada inconsistente.");
            }
        }
        
        while(true){
            System.out.print("HORA [HH:mm]: ");
            String horaAux = scanner.nextLine();
            if(horaAux.length() == 5){
                try {
                    Date data = null;
                    int hora = Integer.parseInt(horaAux.substring(0, 2));
                    int minuto = Integer.parseInt(horaAux.substring(3, 5));

                    SimpleDateFormat sf = new SimpleDateFormat("HH:mm");

                    try{
                        data = sf.parse(Integer.toString(hora)+":"+Integer.toString(minuto));
                    }catch(ParseException e){
                        System.out.println("Entrada inconsistente.");
                        return;
                    }            
                    Time time = new Time(data.getTime());            
                    //System.out.println(time.getHours() + ":" + time.getMinutes());
                    evento.setHoraInicio(time);
                    break;
                } catch (NumberFormatException  e) {
                    System.out.println("Entrada inconsistente");
                }
            }
            else{
                System.out.println("Entrada inconsistente");
            }
        }
       
        System.out.print("LOCAL: ");
        evento.setEndereco(scanner.nextLine());
        
        ArrayList<Evento> eventos = new bancodedados.dao.EventoDAO().getEventos(); 
        if(eventos.size() > 0){
            for(int x = 0; x < eventos.size(); x++){
                if(evento.equals(eventos.get(x))){
                    System.out.println("\nEvento já existe cadastrado! ID: " + eventos.get(x).getId());
                    return;
                }
            }
        }
        
        try{       
            System.out.print("\n");
            Funcoes.subCabecalho("Do ingresso");
            //QUANTIDADE
            System.out.print("QUANTIDADE: ");
            evento.setQuantidadeIngresso(scanner.nextInt());
            //VALOR
            System.out.print("VALOR: R$ ");
            evento.setValorIngresso(scanner.nextFloat()); 
        }
        catch (InputMismatchException  e) {
            System.out.println("Entrada inconsistente.");
        }
        
        new EventoDAO().adiciona(evento);
        eventos = new bancodedados.dao.EventoDAO().getEventos(); 
        System.out.println("\nEvento cadastrado com sucesso! ID: " + eventos.get(eventos.size()-1).getId());
    }
}
