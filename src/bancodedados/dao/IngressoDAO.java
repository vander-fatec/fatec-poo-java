package bancodedados.dao;

import bancodedados.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Ingresso;

public class IngressoDAO {
    //Adiciona um  ingresso no banco de dados
    public void adiciona(Ingresso ingresso) {
        String sql = "INSERT INTO ingresso (id_venda, id_evento, id_tipo_ingresso, vl_ingresso) VALUES (?,?,?,?)";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ){                      
            stmt.setInt(1, ingresso.getVenda().getId());
            stmt.setInt(2, ingresso.getEvento().getId());
            stmt.setInt(3, ingresso.getTipoIngresso().getId());           
            stmt.setFloat(4, ingresso.getValor()); 
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (IngressoDAO/01 " + ex + ")");
        }
    }
    
    //Retorna todos as funcoes de lancamento no banco de dados
    public ArrayList<Ingresso> getIngressos(){
        String sql = "SELECT * FROM ingresso";
        ArrayList<Ingresso> ingressos = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){            
            while(rs.next()){
                Ingresso ingresso = new Ingresso();
                ingresso.setId(rs.getInt(1));
                ingresso.setEvento(new EventoDAO().getEvento(rs.getInt(2)));
                ingresso.setTipoIngresso(new TipoIngressoDAO().getTipoIngresso(rs.getInt(3)));
                ingresso.setValor(rs.getFloat(4));
                
                ingressos.add(ingresso);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (IngressoDAO/02 " + ex + ")");
        }
        return ingressos;
    }
    
    //Procura uma ingresso especifica no banco de dados
    public Ingresso getIngresso(int id){
        String sql = "SELECT * FROM ingresso WHERE id_ingresso = ?";
        Ingresso ingresso = new Ingresso();
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setInt(1, id);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    ingresso.setId(rs.getInt(1));
                    ingresso.setEvento(new EventoDAO().getEvento(rs.getInt(2)));
                    ingresso.setTipoIngresso(new TipoIngressoDAO().getTipoIngresso(rs.getInt(3)));
                    ingresso.setValor(rs.getFloat(4));                   
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (IngressoDAO/03 " + ex + ")");
        }
        return ingresso;
    }
    
    //Altera o ingresso no banco de dados
    public void altera(Ingresso ingresso){
        String sql = "UPDATE ingresso set id_evento=?, id_tipo_ingresso=?, vl_ingresso=?"
                + " WHERE id_ingresso=?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){
            stmt.setInt(1, ingresso.getEvento().getId());
            stmt.setInt(2, ingresso.getTipoIngresso().getId());
            stmt.setFloat(3, ingresso.getValor());
            stmt.setInt(7, ingresso.getId());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (IngressoDAO/04 " + ex + ")");
        }
    }
    
    //Remove um ingresso no banco de dados
    public void removerIngresso(Ingresso ingresso) {
        String sql = "DELETE FROM ingresso WHERE "
                + "id_evento = ?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){
            stmt.setInt(1, ingresso.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (IngressoDAO/05 " + ex + ")");
        }
    }
    
    //Retorna a ultima id de ingresso inserida no banco
    public int numUltimoIngresso() {
        String sql = "SELECT MAX(id_ingresso) FROM ingresso";
        int max = 1;
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            while(rs.next()){
                max = rs.getInt(1);
            }      
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (IngressoDAO/06 " + ex + ")");
        }
        return max;
    }
        
}
