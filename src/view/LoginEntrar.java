package view;

import bancodedados.dao.UsuarioDAO;
import java.util.Scanner;
import negocio.Usuario;
import uteis.Funcoes;

public class LoginEntrar {
    
    public LoginEntrar(){
        inicializar();
    }
    
    private void inicializar(){
        Scanner scanner = new Scanner(System.in);
        Funcoes.cabecalho("Login");
        Usuario usuario = new Usuario();        
        
        try{
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            while(true){
                System.out.print("LOGIN: ");
                usuario.setLogin(scanner.nextLine());
                System.out.print("SENHA: ");
                usuario.setSenha(negocio.Login.gerarHash(scanner.nextLine()));
                
                if(usuarioDAO.autentica(usuario) == 1){
                    usuario = usuarioDAO.getUsuario(usuario.getLogin());
                    new negocio.Login().setLogado(true);
                    new negocio.Login().setUsuario(usuario); 

                    //verifica se está com a senha padrão
                    if(!usuario.getSenha().equals(negocio.Login.gerarHash(negocio.Login.getSenhaPadrao()))){
                        System.out.println("\nBem-vindo " + Funcoes.ANSI_YELLOW + usuario.getNome() + Funcoes.ANSI_RESET);
                        new Menu();
                        break;
                    }else{
                        System.out.println("Você ainda está com a senha padrão, troque-a para sua segurança!");
                        new AlterarSenha();                        
                        new Menu();
                    }

                }else{
                    System.out.println("Usuário e senha não correspondem.\n");
                }
            }
        }catch(RuntimeException ex){
            System.out.println("Não foi possivel se conectar ao banco de dados. ( LoginEntrar/01 " + ex + ")");
        }
    }
    
    
    
    
}
