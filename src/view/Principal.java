package view;


import bancodedados.dao.UsuarioDAO;
import bancodedados.dao.VendaDAO;
import formularios.BuscarVenda;
import formularios.CadastrarUsuario;
import javax.swing.JOptionPane;

public class Principal {
    
    public static void main(String args[]){  

        int confirma = JOptionPane.YES_OPTION;
        while(confirma == JOptionPane.YES_OPTION){
            while(true){
                try{
                    if(new UsuarioDAO().getUsuarioCount() == 0){
                        System.out.println("Parece que é o primeiro acesso. Cadastre um usuário!");
                        new CadastrarUsuario();
                        System.out.print("\n");
                    }else{
                        System.out.println("Bem-vindo ao SisIngresso! Sistema de Venda de Ingressos.");
                        new LoginEntrar();                  
                    }                    
                    confirma = JOptionPane.NO_OPTION;
                }catch(RuntimeException ex){
                    confirma = JOptionPane.showConfirmDialog(null,"Erro ao conectar ao banco de dados. Deseja tentar novamente?" , "Erro banco de dados", JOptionPane.YES_NO_OPTION);
                }
            }
        }        
    } 
    
}