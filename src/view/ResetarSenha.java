package view;

import bancodedados.dao.UsuarioDAO;
import java.util.Scanner;
import negocio.Login;
import negocio.Usuario;
import uteis.Funcoes;

public class ResetarSenha {
    
    public ResetarSenha(){
        Funcoes.cabecalho("Resetar senha de usuario");
        inicializar();
    }
    
    private void inicializar(){
        Scanner scanner = new Scanner(System.in); 
        System.out.print("LOGIN DO USUARIO: ");
        try{
            String login = scanner.nextLine();
            Usuario usuario = new bancodedados.dao.UsuarioDAO().getUsuario(login);            
            usuario.setSenha(Login.gerarHash(Login.SENHAPADRAO));
            
            new UsuarioDAO().altera(usuario);
            System.out.println("Senha resetada com sucesso.");    
            
            System.out.print("\n");
            System.out.println(":: LOGIN: " + usuario.getLogin());
            System.out.println(":: NOME : " + usuario.getNome());
            System.out.println(":: SENHA: " + Login.SENHAPADRAO);
            System.out.println(":: TIPO : " + usuario.getFuncao().getNome());
                
        } catch (Exception e) {
            System.out.println("Usuario não encontrado.");
        }
    }   
    
}
