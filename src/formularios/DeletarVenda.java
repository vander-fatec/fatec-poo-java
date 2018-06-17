package formularios;

import bancodedados.dao.EventoDAO;
import bancodedados.dao.IngressoDAO;
import bancodedados.dao.VendaDAO;
import java.util.ArrayList;
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
        float total_geral = 0; int quantidade = 0;
        System.out.print("BUSCAR ID: ");
        try {
            //removerVenda
            Venda venda = new bancodedados.dao.VendaDAO().getVenda(scanner.nextInt()); 
            if(venda.getId() > 0){
                System.out.print(Funcoes.ANSI_PURPLE + "VENDA" + Funcoes.ANSI_RESET + " nº 0" + venda.getId());
                System.out.print(" | DATA: " + Funcoes.dateToString(venda.getDataVenda()));
                System.out.print(" | CLIENTE: " + venda.getCliente().getNome());
                System.out.println(" | VENDEDOR: " + venda.getUsuario().getLogin() + "\n");
                
                ArrayList<String[]> ingressos = new VendaDAO().getIngressosPorTipo(venda.getId());  
                
                for(int y=0; y < ingressos.size(); y++){                        
                    System.out.println("NOME EVENTO.............: " + ingressos.get(y)[0]);
                    System.out.println("TIPO INGRESSO...........: " + ingressos.get(y)[1]);
                    System.out.print("QUANTIDADE x VALOR UNIT.: " +ingressos.get(y)[3] + " x " + Funcoes.valorToString(Float.parseFloat(ingressos.get(y)[2])));
                    System.out.println(" (" + Funcoes.ANSI_RED + Funcoes.valorToString(Float.parseFloat(ingressos.get(y)[2]) * Float.parseFloat(ingressos.get(y)[3]))  + Funcoes.ANSI_RESET + ")");
                    total_geral += Float.parseFloat(ingressos.get(y)[2]) * Float.parseFloat(ingressos.get(y)[3]);
                    quantidade += Float.parseFloat(ingressos.get(y)[3]);
                    if(ingressos.size() != (y+1)){ System.out.print("\n");}
                    else{System.out.print(Funcoes.ANSI_PURPLE + "VALOR TOTAL DA COMPRA = " + Funcoes.valorToString(total_geral) + Funcoes.ANSI_RESET + "\n");}
                }
                
                Funcoes.subCabecalho("Confirmação");                
                System.out.print("Deseja realmente estornar essa venda? [s/n]: ");
                if(Funcoes.opcSimNao()){                    
                    new VendaDAO().removerVenda(venda);
                    new IngressoDAO().removerIngressosVenda(venda.getId());
                    new EventoDAO().aumentarQuantidadeIngresso(venda.getId(), quantidade);
                    System.out.println("Venda estornada com exito.");
                }
                else{
                    System.out.println("Venda foi mantida cadastrada com número: " + venda.getId());
                }
            }
            else{
                System.out.println("Venda não encontrada..");
            }
        } catch (Exception e) {
            System.out.println("Entrada inconsistente");
        }
    }
    
}
