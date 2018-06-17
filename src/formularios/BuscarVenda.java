package formularios;

import bancodedados.dao.VendaDAO;
import java.util.ArrayList;
import java.util.Scanner;
import negocio.Ingresso;
import negocio.Venda;
import uteis.Funcoes;

public class BuscarVenda {
    
    public BuscarVenda(){
        inicializar();
    }
    
    private void inicializar(){
        Funcoes.cabecalho("Buscar Evento");
        String msg = (""
                + Funcoes.ANSI_PURPLE + "[1] " + Funcoes.ANSI_RESET + "Listar todas as vendas\n"
                + Funcoes.ANSI_PURPLE + "[2] " + Funcoes.ANSI_RESET + "Buscar pelo número da venda\n"
                + ":: ");
        switch(Funcoes.escolherOpcao(1, 2, msg)){
            case 1: listarTodos();
                break;
            case 2: buscarPorId();
                break;
        }
    }
    
    private void listarTodos(){
        Funcoes.cabecalho("Todas as vendas");
        try{
            ArrayList<Venda> vendas = new bancodedados.dao.VendaDAO().getVendas();
            ArrayList<String[]> ingressos;
            System.out.print("");
            if(vendas.size() > 0){
                for(int x = 0; x < vendas.size(); x++){
                    
                    System.out.print(Funcoes.ANSI_PURPLE + "VENDA" + Funcoes.ANSI_RESET);
                    System.out.print(" nº 0" + vendas.get(x).getId());
                    System.out.print(" | DATA: " + Funcoes.dateToString(vendas.get(x).getDataVenda()));
                    System.out.print(" | CLIENTE: " + vendas.get(x).getCliente().getNome());
                    System.out.println(" | VENDEDOR: " + vendas.get(x).getUsuario().getLogin() + "\n");
                                     
                    ingressos = new VendaDAO().getIngressosPorTipo(vendas.get(x).getId());  
                    float total_geral = 0;
                    for(int y=0; y < ingressos.size(); y++){                        
                        System.out.println("NOME EVENTO.............: " + ingressos.get(y)[0]);
                        System.out.println("TIPO INGRESSO...........: " + ingressos.get(y)[1]);
                        System.out.print("QUANTIDADE x VALOR UNIT.: " +ingressos.get(y)[3] + " x " + Funcoes.valorToString(Float.parseFloat(ingressos.get(y)[2])));
                        System.out.println(" (" + Funcoes.ANSI_RED + Funcoes.valorToString(Float.parseFloat(ingressos.get(y)[2]) * Float.parseFloat(ingressos.get(y)[3]))  + Funcoes.ANSI_RESET + ")");
                        total_geral += Float.parseFloat(ingressos.get(y)[2]) * Float.parseFloat(ingressos.get(y)[3]);
                        if(ingressos.size() != (y+1)){ System.out.print("\n");}
                        else{System.out.print(Funcoes.ANSI_PURPLE + "VALOR TOTAL DA COMPRA = " + Funcoes.valorToString(total_geral) + Funcoes.ANSI_RESET + "\n");}
                    }
                    if(vendas.size() != (x+1)) System.out.print("\n");
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
        Funcoes.subCabecalho("Venda por número");
        Scanner scanner = new Scanner(System.in);        
        
        try{
            System.out.print("NUMERO DA VENDA: ");
            Venda venda = new bancodedados.dao.VendaDAO().getVenda(scanner.nextInt());
            
            if(venda.getId()>0){
                System.out.print(Funcoes.ANSI_PURPLE + "VENDA" + Funcoes.ANSI_RESET + " nº 0" + venda.getId());
                System.out.print(" | DATA: " + Funcoes.dateToString(venda.getDataVenda()));
                System.out.print(" | CLIENTE: " + venda.getCliente().getNome());
                System.out.println(" | VENDEDOR: " + venda.getUsuario().getLogin() + "\n");

                ArrayList<String[]> ingressos = new VendaDAO().getIngressosPorTipo(venda.getId());  
                float total_geral = 0;
                for(int y=0; y < ingressos.size(); y++){                        
                    System.out.println("NOME EVENTO.............: " + ingressos.get(y)[0]);
                    System.out.println("TIPO INGRESSO...........: " + ingressos.get(y)[1]);
                    System.out.print("QUANTIDADE x VALOR UNIT.: " +ingressos.get(y)[3] + " x " + Funcoes.valorToString(Float.parseFloat(ingressos.get(y)[2])));
                    System.out.println(" (" + Funcoes.ANSI_RED + Funcoes.valorToString(Float.parseFloat(ingressos.get(y)[2]) * Float.parseFloat(ingressos.get(y)[3]))  + Funcoes.ANSI_RESET + ")");
                    total_geral += Float.parseFloat(ingressos.get(y)[2]) * Float.parseFloat(ingressos.get(y)[3]);
                    if(ingressos.size() != (y+1)){ System.out.print("\n");}
                    else{System.out.print(Funcoes.ANSI_PURPLE + "VALOR TOTAL DA COMPRA = " + Funcoes.valorToString(total_geral) + Funcoes.ANSI_RESET + "\n");}
                }
            }
            else{
                System.out.println("Venda não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Venda não encontrada.");
        }
    }
    
}
