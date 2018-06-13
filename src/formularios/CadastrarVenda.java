package formularios;

import bancodedados.dao.ClienteDAO;
import bancodedados.dao.EventoDAO;
import bancodedados.dao.IngressoDAO;
import bancodedados.dao.TipoIngressoDAO;
import bancodedados.dao.UsuarioDAO;
import bancodedados.dao.VendaDAO;
import java.util.ArrayList;
import java.util.Scanner;
import negocio.Cliente;
import negocio.Evento;
import negocio.Ingresso;
import negocio.Login;
import negocio.TipoIngresso;
import negocio.Venda;
import uteis.Funcoes;

public class CadastrarVenda {
    
    public CadastrarVenda(){
        inicializar();
    }
    
    private void inicializar(){
        Scanner scanner = new Scanner(System.in);        
        Venda venda = new Venda();
        Ingresso ingresso = new Ingresso();
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        
        ArrayList<Evento> eventos = new EventoDAO().getEventosDisponiveis();
        
        if(eventos.size() > 0){
            //CLIENTE
            Cliente cliente = new Cliente();
            while(true){
                Funcoes.cabecalho("Efetuar venda");
                
                Funcoes.subCabecalho("Cliente");
                System.out.print("CPF/CNPJ e NOME NA NOTA [s/n]: ");
                
                if(Funcoes.opcSimNao()){                
                    if(new ClienteDAO().getClientes().size() > 0){                    
                        try {
                            System.out.print("DIGITE O CPF/CNPJ: ");
                            cliente = new ClienteDAO().getCliente(scanner.nextLine());
                            if(cliente.getId() != 0){
                                System.out.println("\n:: CPF/CNPJ: " + cliente.getCpfCnpj());
                                System.out.println(":: NOME: " + cliente.getNome());
                                System.out.print("\nCONFIRMAR CLIENTE [s/n]: ");
                                if(Funcoes.opcSimNao()){
                                    venda.setCliente(cliente);                            
                                    break;
                                }
                                else{
                                    System.out.println("Cliente não encontrado.");
                                    System.out.print("DESEJA CADASTRAR UM NOVO CLIENTE [s/n]: ");
                                    if(Funcoes.opcSimNao()){
                                        new CadastrarCliente();
                                    }
                                    else{
                                        cliente = new ClienteDAO().getCliente(1);
                                        venda.setCliente(cliente);
                                        System.out.println("Venda sem dados de cliente.\n");
                                        break;
                                    }
                                }
                            }
                            else{
                                System.out.println("Cliente não encontrado.");
                                System.out.print("DESEJA CADASTRAR UM NOVO CLIENTE [s/n]: ");
                                if(Funcoes.opcSimNao()){
                                    new CadastrarCliente();
                                }
                                else{
                                    cliente = new ClienteDAO().getCliente(1);
                                    venda.setCliente(cliente);
                                    System.out.println("Venda sem dados de cliente.\n");
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Nenhum cliente encontrado nessa busca.\n");
                        }
                    }            
                    else{
                        System.out.println("Não há clientes cadastrados");
                        new CadastrarCliente();
                    }
                }
                else{
                    cliente = new ClienteDAO().getCliente(1);
                    venda.setCliente(cliente);
                    System.out.println("Venda sem dados de cliente.");
                    break;
                }              
            }            

            //EVENTO        
            System.out.print("\n");
            Funcoes.subCabecalho("Evento"); 
            for(int x=0; x< eventos.size(); x++){
                System.out.println(Funcoes.ANSI_PURPLE + "[" + (x+1) + "] " + Funcoes.ANSI_RESET + "NOME: " + eventos.get(x).getNome() + "\n"
                    + "    QUANTIDADE DE INGRESSO DISPONIVEL: " + eventos.get(x).getQuantidadeIngresso() + "\n"
                    + "    INTEIRO: " + Funcoes.valorToString(eventos.get(x).getValorIngresso()) + " | "
                    + "MEIA COMUM: " + Funcoes.valorToString((eventos.get(x).getValorIngresso()/2)) + " | "
                    + "VIP: " + Funcoes.valorToString((eventos.get(x).getValorIngresso()*1.5f)) + " | "
                    + "MEIA VIP: " + Funcoes.valorToString((eventos.get(x).getValorIngresso()*1.5f)/2));
                if(eventos.size() != (x+1)) System.out.print("\n");
            }
            System.out.print("\n");
            ingresso.setEvento(eventos.get((Funcoes.escolherOpcao(1, eventos.size())-1)));
            
            //TIPO INGRESSO
            ArrayList<TipoIngresso> tipo_ingresso = new TipoIngressoDAO().getTiposIngresso();
            if(tipo_ingresso.size() > 0){
                System.out.print("\n");
                Funcoes.subCabecalho("Tipo de Ingresso");
                for(int x=0; x< tipo_ingresso.size(); x++){
                    System.out.println(Funcoes.ANSI_PURPLE + "[" + (x+1) + "] " + Funcoes.ANSI_RESET + "TIPO: " + tipo_ingresso.get(x).getNome());
                }
                ingresso.setTipoIngresso(tipo_ingresso.get((Funcoes.escolherOpcao(1, tipo_ingresso.size())-1)));
                
                //VENDA
                //QUANTIDADE DE INGRESSOS NESSA VENDA
                System.out.print("\n");
                Funcoes.subCabecalho("Quantidade de ingresso");
                System.out.print("QUANTIDADE DE COMPRA [max " + ingresso.getEvento().getQuantidadeIngresso() + "]");
                int qtd_ingresso = Funcoes.escolherOpcao(1,ingresso.getEvento().getQuantidadeIngresso());
                ingresso.getEvento().setQuantidadeIngresso(ingresso.getEvento().getQuantidadeIngresso()-qtd_ingresso);
                float valor_total = 0;
                
                for(int x=0; x< qtd_ingresso ; x++){
                    ingressos.add(ingresso);
                    valor_total += ingresso.getValor();
                }
                
                Funcoes.cabecalho("Relação de venda - nº0" + (new VendaDAO().numVenda()));
                System.out.println(Funcoes.ANSI_GREEN + "TOTAL A PAGAR " + Funcoes.valorToString(valor_total) + Funcoes.ANSI_RESET);
                
                //FINALIZAR COMPRA
                System.out.print("\n");
                Funcoes.subCabecalho("Finalização");
                System.out.print("FINALIZAR COMPRA [s/n]: ");
                
                boolean ret;
                while(true){
                    String entrada = scanner.nextLine();
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
                
                if(ret){                     
                    venda.setUsuario(new UsuarioDAO().getUsuario(new Login().getUsuario().getLogin()));
                    new EventoDAO().altera(ingresso.getEvento());
                    new VendaDAO().adiciona(venda);
                    ArrayList<Ingresso> ingressosAux = new ArrayList<>();
                    
                    for(int x=0; x < ingressos.size(); x++){                        
                        ingressos.get(x).setVenda(new VendaDAO().getVenda(new VendaDAO().numVenda()-1));
                        new IngressoDAO().adiciona(ingressos.get(x));                        
                        ingressosAux.add(new IngressoDAO().getIngresso(new IngressoDAO().numUltimoIngresso()));                        
                    }                   
                }
                else{
                    System.out.println("\nVenda cancelada.");
                }
            }
            else{
                System.out.println("Não há tipo de ingresso cadastrado.");
            }
        }
        else{
            System.out.println("Não há ingressos para venda em nenhum evento.");
        }
    }
    
}
