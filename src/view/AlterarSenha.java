package view;

import bancodedados.dao.UsuarioDAO;
import java.util.Scanner;
import negocio.Usuario;
import negocio.Login;
import uteis.Funcoes;

public class AlterarSenha {
    Scanner scanner;
    public AlterarSenha(){
        scanner = new Scanner(System.in);
        inicializar();
    }
    
    private void inicializar(){
        Funcoes.cabecalho("ALTERAR SENHA");
        Usuario usuario = new Login().getUsuario();        
        
        while(true){
            System.out.print("NOVA SENHA: ");
            usuario.setSenha(Login.gerarHash(scanner.nextLine()));
            if(!usuario.getSenha().equals(Login.gerarHash(Login.SENHAPADRAO))){            
                new UsuarioDAO().altera(usuario);
                new Login().setUsuario(usuario);
                System.out.println("Senha alterada com sucesso.");  
                break;
            }
            else{
                System.out.println("Não é permitido alterar a senha para a padrão do sistema.\n");
            }
        }
    }    
}
