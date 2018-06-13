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
        Funcoes.cabecalho("Todos eventos");
        try{
            ArrayList<Venda> vendas = new bancodedados.dao.VendaDAO().getVendas();
            ArrayList<Ingresso> ingressos;
            System.out.print("");
            if(vendas.size() > 0){
                for(int x = 0; x < vendas.size(); x++){
                    System.out.print(Funcoes.ANSI_PURPLE + "VENDA" + Funcoes.ANSI_RESET);
                    System.out.println(" nº 0" + vendas.get(x).getId());
                    System.out.println("DATA: " + Funcoes.dateToString(vendas.get(x).getDataVenda()));
                    System.out.println("CLIENTE: " + vendas.get(x).getCliente().getNome());
                    System.out.println("VENDEDOR: " + vendas.get(x).getUsuario().getLogin());
                    System.out.println(Funcoes.ANSI_PURPLE + "INGRESSOS" + Funcoes.ANSI_RESET);
                    ingressos = new VendaDAO().getIngressosVenda(vendas.get(x).getId());
                    
                    System.out.println("EVENTO: " + ingressos.get(1).getEvento().getNome());
                    System.out.println("TIPO INGRESSO: " + ingressos.get(1).getTipoIngresso().getNome());
                    System.out.println("VALOR UNITÁRIO: " + ingressos.get(1).getValor());
                    System.out.println("QUANTIDADE: " + ingressos.size());
                                     
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
        Funcoes.subCabecalho("Evento por id");
        Scanner scanner = new Scanner(System.in);
        System.out.print("BUSCAR ID: ");
        try{
            ArrayList<Venda> vendas = new bancodedados.dao.VendaDAO().getVendas();
            ArrayList<Ingresso> ingressos;
            int x = scanner.nextInt()-1;
            if(vendas.size() > 0){
                
                    System.out.print(Funcoes.ANSI_PURPLE + "VENDA" + Funcoes.ANSI_RESET);
                    System.out.println(" nº 0" + vendas.get(x).getId());
                    System.out.println("DATA: " + Funcoes.dateToString(vendas.get(x).getDataVenda()));
                    System.out.println("CLIENTE: " + vendas.get(x).getCliente().getNome());
                    System.out.println("VENDEDOR: " + vendas.get(x).getUsuario().getLogin());
                    System.out.println(Funcoes.ANSI_PURPLE + "INGRESSOS" + Funcoes.ANSI_RESET);
                    
                    ingressos = new VendaDAO().getIngressosVenda(vendas.get(x).getId());                    
                    System.out.println("EVENTO: " + ingressos.get(1).getEvento().getNome());
                    System.out.println("TIPO INGRESSO: " + ingressos.get(1).getTipoIngresso().getNome());
                    System.out.println("VALOR UNITÁRIO: " + ingressos.get(1).getValor());
                    System.out.println("QUANTIDADE: " + ingressos.size());
                                     
                    if(vendas.size() != (x+1)) System.out.print("\n");
                
            }
            else{
                System.out.println("Não há eventos cadastrados");
            }
        } catch (Exception e) {
            System.out.println("Busca inconsistente");
        }
    }
}
