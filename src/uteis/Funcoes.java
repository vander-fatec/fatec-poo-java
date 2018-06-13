package uteis;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Funcoes {    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    public static boolean tentarNovamente(String msg){
        Scanner scanner = new Scanner(System.in);
        String entrada; boolean ret;
        while(true){
            System.out.print(msg + " Tentar novamente [S/N]: ");
            entrada = scanner.nextLine();
            if(entrada.equals("n") || entrada.equals("N")){
                ret = false;
                break;
            }
            else if (entrada.equals("s") || entrada.equals("S")){
                ret = true;
                break;
            }
            else{
                System.out.println("Entrada inconsistente.");
            }
        }
        return ret;
    }
    
    public static boolean opcSimNao(){
        Scanner scanner = new Scanner(System.in);
        String entrada; boolean ret;
        while(true){
            entrada = scanner.nextLine();
            if(entrada.equals("n") || entrada.equals("N")){
                ret = false;
                break;
            }
            else if (entrada.equals("s") || entrada.equals("S")){
                ret = true;
                break;
            }
            else{
                System.out.print("Entrada inconsistente, tente novamente [s/n]: ");
            }
        }
        return ret;
    }
    
    public static boolean tentarNovamente(){
        Scanner scanner = new Scanner(System.in);
        String entrada; boolean ret;
        while(true){
            System.out.print("Tentar novamente [S/N]: ");
            entrada = scanner.nextLine();
            if(entrada.equals("n") || entrada.equals("N")){
                ret = false;
                break;
            }
            else if (entrada.equals("s") || entrada.equals("S")){
                ret = true;
                break;
            }
            else{
                System.out.println("Entrada inconsistente.");
            }
        }
        return ret;
    }
    
    public static int escolherOpcao(int inic, int fim){
        Scanner scanner = new Scanner(System.in);
        int entrada;
        while(true){
            System.out.print(":: ");
            try {
                entrada = scanner.nextInt();
                if(entrada >= inic && entrada <= fim){
                    break;
                }
                else{
                    System.out.println("Entrada inconsistente.\n");
                }                
            }
            catch (InputMismatchException e) {
                System.out.println("Entrada inconsistente.\n");
                scanner.nextLine();
            }           
        }
        return entrada;
    }
    
    public static int escolherOpcao(int inic, int fim, String msg){
        Scanner scanner = new Scanner(System.in);
        int entrada;
        while(true){
            System.out.print(msg);
            try {
                entrada = scanner.nextInt();
                if(entrada >= inic && entrada <= fim){
                    break;
                }
                else{
                    System.out.println("Entrada inconsistente.\n");
                }                
            }
            catch (NumberFormatException e) {
                System.out.println("Entrada inconsistente.\n");
            }
        }
        return entrada;
    }
    
    

    public static void cabecalho(String titulo){
        int lado1 = (78-titulo.length())/2;
        int lado2 = (((78.0f-titulo.length())) % 2 == 0) ? lado1 : (int)((78-titulo.length())/2)+1;        
        System.out.print("\n");
        for(int x=0; x < lado1; x++){
            System.out.print(ANSI_BLUE + ":" + ANSI_RESET);
        }
        System.out.print(ANSI_BLUE + " " + titulo.toUpperCase() + " " + ANSI_RESET);
        for(int x=0; x < lado2; x++){
            System.out.print(ANSI_BLUE + ":" + ANSI_RESET);
        }
        System.out.print("\n");
    }
    
    public static void subCabecalho(String titulo){
        System.out.println(ANSI_PURPLE + ":::: " + titulo.toUpperCase() + " ::::" + ANSI_RESET);        
    }
    
    public static String dateToString(Date data){
        String ret;
        switch(data.getDate()){
            case 1: case 2: case 3:
            case 4: case 5: case 6:
            case 7: case 8: case 9: ret = "0" + Integer.toString(data.getDate());
                break;
            default : ret = Integer.toString(data.getDate());
                break;
        }
        switch((data.getMonth()+1)){
            case 1: case 2: case 3:
            case 4: case 5: case 6:
            case 7: case 8: case 9: ret += "/0" + Integer.toString((data.getMonth()+1));
                break;
            default : ret += "/" + Integer.toString((data.getMonth()+1));
                break;
        }
        ret += "/" + Integer.toString(data.getYear()+1900);
        return ret;
    }
    
    public static String timeToString(Time time){
        String ret;
        switch(time.getHours()){
            case 1: case 2: case 3:
            case 4: case 5: case 6:
            case 7: case 8: case 9: ret = "0" + Integer.toString(time.getHours());
                break;
            default : ret = Integer.toString(time.getHours());
                break;
        }
        switch(time.getMinutes()){
            case 1: case 2: case 3:
            case 4: case 5: case 6:
            case 7: case 8: case 9: ret += ":0" + Integer.toString(time.getMinutes());
                break;
            default : ret += ":" + Integer.toString(time.getMinutes());
                break;
        }        
        ret += (time.getHours() < 13) ?  " AM" : " PM" ;
        return ret;
    }
    
    public static String valorToString(Float valor){
        DecimalFormat df;
        //if(valor >= 1){
            df = new DecimalFormat("#.00");
        //}
        //else{
            //df = new DecimalFormat("0.00");
        //}            
        return ("R$" + df.format(valor));
    }
}
