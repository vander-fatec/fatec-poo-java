package bancodedados.dao;

import bancodedados.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Funcao;

public class FuncaoDAO {
    
    //Retorna todos as funcoes de lancamento no banco de dados
    public ArrayList<Funcao> getFuncoes(){
        String sql = "SELECT * FROM Funcao";
        ArrayList<Funcao> funcoes = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            
            while(rs.next()){
                Funcao funcao = new Funcao();
                funcao.setId(rs.getInt(1));
                funcao.setNome(rs.getString(2));
                
                funcoes.add(funcao);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (FuncaoDAO/01 " + ex + ")");
        }
        return funcoes;
    }
    
    //Procura uma funcao especifica no banco de dados
    public Funcao getFuncao(int id){
        String sql = "SELECT * FROM sisingressos.funcao where id_funcao = ?";
        Funcao funcao = new Funcao();
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setInt(1, id);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    funcao.setId(id);
                    funcao.setNome(rs.getString(2));
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (FuncaoDAO/02 " + ex + ")"); 
        }
        return funcao;
    }

    //Procura uma funcao especifica no banco de dados
    public Funcao getFuncao(String nome){
        String sql = "SELECT * FROM Funcao WHERE nm_funcao=?";
        Funcao funcao = new Funcao();
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setString(1, nome);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    funcao.setId(rs.getInt(1));
                    funcao.setNome(nome);
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (FuncaoDAO/03 " + ex + ")");
        }
        return funcao;
    }     
}