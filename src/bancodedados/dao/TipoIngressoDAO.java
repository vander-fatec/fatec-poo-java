package bancodedados.dao;

import bancodedados.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.TipoIngresso;

public class TipoIngressoDAO {
    //Retorna todos os tipos de ingressos no banco de dados
    public ArrayList<TipoIngresso> getTiposIngresso(){
        String sql = "SELECT * FROM tipo_ingresso";
        ArrayList<TipoIngresso> tipos_ingresso = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            
            while(rs.next()){
                TipoIngresso tipo_ingresso = new TipoIngresso();
                tipo_ingresso.setId(rs.getInt(1));
                tipo_ingresso.setNome(rs.getString(2));
                
                tipos_ingresso.add(tipo_ingresso);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (TipoIngressoDAO/01 " + ex + ")");
        }
        return tipos_ingresso;
    }
    
    //Procura uma tipo_ingresso especifico no banco de dados pela id
    public TipoIngresso getTipoIngresso(int id){
        String sql = "SELECT * FROM tipo_ingresso WHERE id_tipo_ingresso = ?";
        TipoIngresso tipo_ingresso = new TipoIngresso();
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setInt(1, id);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    tipo_ingresso.setId(id);
                    tipo_ingresso.setNome(rs.getString(2));                    
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (TipoIngressoDAO/02 " + ex + ")");
        }
        return tipo_ingresso;
    }

    //Procura uma tipo_ingresso especifica no banco de dados pelo nome
    public TipoIngresso getTipoIngresso(String nome){
        String sql = "SELECT * FROM Funcao WHERE nm_tipo_ingresso=?";
        TipoIngresso tipo_ingresso = new TipoIngresso();
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setString(1, nome);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    tipo_ingresso.setId(rs.getInt(1));
                    tipo_ingresso.setNome(nome);
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (TipoIngressoDAO/03 " + ex + ")");
        }
        return tipo_ingresso;
    }
}
