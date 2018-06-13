package formularios;

import bancodedados.dao.UsuarioDAO;
import bancodedados.dao.FuncaoDAO;
import java.util.Scanner;
import negocio.Usuario;
import negocio.Login;
import uteis.Funcoes;

public class CadastrarUsuario {
    
    public CadastrarUsuario(){
        inicializar();
    }

    private void inicializar(){
        Scanner scanner = new Scanner(System.in);
        boolean aux = true;
        while(aux){
            Funcoes.cabecalho("Cadastrar usuário");
            System.out.print("LOGIN: ");
            String login = scanner.nextLine();
            
            if(new UsuarioDAO().getUsuarioNomeUsuarioCount(login) == 0){
                Usuario usuario = new Usuario();
                usuario.setLogin(login);
                
                System.out.print("NOME: ");
                usuario.setNome(scanner.nextLine());
                
                if(new UsuarioDAO().getUsuarioCount() == 0){
                    usuario.setFuncao(new FuncaoDAO().getFuncao(1));
                }
                else{
                    String msg = "FUNCÃO [1] ADMINISTRADOR [2] VENDEDOR: ";                    
                    usuario.setFuncao(new FuncaoDAO().getFuncao(Funcoes.escolherOpcao(1, 2, msg)));
                }
                
                usuario.setSenha(Login.gerarHash(Login.SENHAPADRAO));
                new UsuarioDAO().adiciona(usuario);
                
                System.out.print("\n");
                System.out.println(":: LOGIN: " + usuario.getLogin());
                System.out.println(":: NOME : " + usuario.getNome());
                System.out.println(":: SENHA: " + Login.SENHAPADRAO);
                System.out.println(":: TIPO : " + usuario.getFuncao().getNome());
                aux = false;                
            }
            else{
                if(!Funcoes.tentarNovamente("Login existente.")){
                    aux = false;
                }
            }
        }        
    }
    
    
}
