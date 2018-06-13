package bancodedados.dao;

import bancodedados.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Ingresso;

import negocio.Usuario;

public class UsuarioDAO {
    //Adiciona um usuario banco de dados
    public void adiciona(Usuario usuario) {
        String sql = "INSERT INTO usuario (nm_login_usuario, cd_senha_usuario, nm_usuario, id_funcao) VALUES (?,?,?,?)";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ){
            stmt.setString(1, usuario.getLogin() );           
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());            
            stmt.setInt(4, usuario.getFuncao().getId());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (UsuarioDAO/01 " + ex + ")");
        }
    }
    
    //Retorna a quantidade de usuario com o login
    public int getUsuarioCount(){
        String sql = "SELECT COUNT(*) FROM usuario";
        int count = 0;
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            while(rs.next()){
                count = rs.getInt(1);
            }      
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (UsuarioDAO/02 " + ex + ")");
        }
        return count;
    }
     
    //Retorna a quantidade de usuario com o login
    public int getUsuarioNomeUsuarioCount(String login){
        String sql = "SELECT COUNT(*) FROM usuario WHERE nm_login_usuario LIKE  ?";
        int count = 0;
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setString(1, login);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    count += rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (UsuarioDAO/03 " + ex + ")");
        }
        return count;
    }
    
    //Procura se existe um usuario com senha no banco de dados
    public int autentica(Usuario usuario){
        String sql = "SELECT COUNT(*) FROM Usuario WHERE nm_login_usuario=? AND cd_senha_usuario=?";
        int status = 0;
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    status = rs.getInt(1);
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (UsuarioDAO/04 " + ex + ")");
        }
        return status;
    } 
       
    //Retorna todos os usuarios no banco de dados
    public ArrayList<Usuario> getUsuarios(){
        String sql = "SELECT * FROM Usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString(1));
                usuario.setLogin(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                       
                usuarios.add(usuario);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (UsuarioDAO/05 " + ex + ")");
        }
        return usuarios;
    }
    
    //Procura um usuario especifico no banco de dados
    public Usuario getUsuario(String login){
        String sql = "SELECT * FROM Usuario WHERE nm_login_usuario=?";
        Usuario usuario = new Usuario();
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setString(1, login);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    usuario.setLogin(rs.getString(1));
                    usuario.setSenha(rs.getString(2));
                    usuario.setNome(rs.getString(3));
                    usuario.setFuncao(new FuncaoDAO().getFuncao(rs.getInt(4)));
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (UsuarioDAO/06 " + ex + ")");
        }
        return usuario;
    }
    
    //Altera o usuario no banco de dados
    public void altera(Usuario usuario){
        String sql = "UPDATE usuario set cd_senha_usuario=?, nm_usuario=?, id_funcao=? WHERE nm_login_usuario=?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){            
            stmt.setString(1, usuario.getSenha());
            stmt.setString(2, usuario.getNome());
            stmt.setInt(3, usuario.getFuncao().getId());           
            stmt.setString(4, usuario.getLogin());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (UsuarioDAO/07 " + ex + ")");
        }
    }
    
    //Retorna os ingressos de uma venda no banco de dados
    public ArrayList<Ingresso> getIngressosVenda(int id){
        String sql = "SELECT * FROM ingresso WHERE id_venda = ?";
        ArrayList<Ingresso> ingressos = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            stmt.setInt(1, id);
            while(rs.next()){
                Ingresso ingresso = new Ingresso();
                ingresso.setId(rs.getInt(1));
                ingresso.setVenda(new VendaDAO().getVenda(rs.getInt(2)));
                ingresso.setEvento(new EventoDAO().getEvento(rs.getInt(3)));
                ingresso.setTipoIngresso(new TipoIngressoDAO().getTipoIngresso(rs.getInt(4)));
                ingresso.setValor(rs.getFloat(5));
                       
                ingressos.add(ingresso);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (UsuarioDAO/05 " + ex + ")");
        }
        return ingressos;
    }
}
