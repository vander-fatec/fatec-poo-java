package bancodedados.dao;

import bancodedados.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Evento;

public class EventoDAO {
    //Adiciona um evento no banco de dados
    public void adiciona(Evento evento) {
        String sql = "INSERT INTO evento"+
                "(nm_evento, dt_inicio_evento, hr_inicio_evento, nm_endereco_evento,"
                + "qt_ingresso_evento, vl_ingresso_evento)"
                + "VALUES (?,?,?,?,?,?)";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ){
            
            stmt.setString(1, evento.getNome());
            stmt.setDate(2, new java.sql.Date(evento.getDataInicio().getTime()));
            stmt.setTime(3, evento.getHoraInicio());
            stmt.setString(4, evento.getEndereco());
            stmt.setInt(5, evento.getQuantidadeIngresso());
            stmt.setFloat(6, evento.getValorIngresso());
                        
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (EventoDAO/01 " + ex + ")");

        }
    }
    
    //Retorna todos os eventos que têm ingresso para venda no banco de dados
    public ArrayList<Evento> getEventosDisponiveis(){
        String sql = "SELECT * FROM Evento WHERE qt_ingresso_evento > 0";
        ArrayList<Evento> eventos = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            
            while(rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getInt(1));
                evento.setNome(rs.getString(2));
                evento.setDataInicio(rs.getDate(3));
                evento.setHoraInicio(rs.getTime(4));
                evento.setEndereco(rs.getString(5));
                evento.setQuantidadeIngresso(rs.getInt(6));
                evento.setValorIngresso(rs.getFloat(7));
                eventos.add(evento);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (EventoDAO/02 " + ex + ")");
        }
        return eventos;
    }
    
    //Retorna todos os eventos no banco de dados
    public ArrayList<Evento> getEventos(){
        String sql = "SELECT * FROM Evento";
        ArrayList<Evento> eventos = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            
            while(rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getInt(1));
                evento.setNome(rs.getString(2));
                evento.setDataInicio(rs.getDate(3));
                evento.setHoraInicio(rs.getTime(4));
                evento.setEndereco(rs.getString(5));
                evento.setQuantidadeIngresso(rs.getInt(6));
                evento.setValorIngresso(rs.getFloat(7));
                eventos.add(evento);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (EventoDAO/03 " + ex + ")");
        }
        return eventos;
    }
    
    //Procura uma funcao especifica no banco de dados
    public Evento getEvento(int id){
        String sql = "SELECT * FROM evento WHERE id_evento=?";
        Evento evento = new Evento();
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setInt(1, id);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    evento.setId(id);
                    evento.setNome(rs.getString(2));
                    evento.setDataInicio(rs.getDate(3));
                    evento.setHoraInicio(rs.getTime(4));
                    evento.setEndereco(rs.getString(5));
                    evento.setQuantidadeIngresso(rs.getInt(6));
                    evento.setValorIngresso(rs.getFloat(7));
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (EventoDAO/04 " + ex + ")");
        }
        return evento;
    }
    
    //Altera o evento no banco de dados
    public void altera(Evento evento){
        String sql = "UPDATE evento set nm_evento=?, dt_inicio_evento=?, hr_inicio_evento=?, nm_endereco_evento=?,"
                + "qt_ingresso_evento=?, vl_ingresso_evento=?" + 
                "WHERE id_evento=?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){
            stmt.setString(1, evento.getNome());
            stmt.setDate(2, new java.sql.Date(evento.getDataInicio().getTime()));
            stmt.setTime(3, evento.getHoraInicio());
            stmt.setString(4, evento.getEndereco());
            stmt.setInt(5, evento.getQuantidadeIngresso());
            stmt.setFloat(6, evento.getValorIngresso());
            stmt.setInt(7, evento.getId());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (EventoDAO/05 " + ex + ")");
        }
    }
    
    //Remove um evento no banco de dados
    public void removerEvento(Evento evento) {
        String sql = "DELETE FROM evento WHERE "
                + "id_evento = ?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){
            stmt.setInt(1, evento.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (EventoDAO/06 " + ex + ")");
        }
    }
    
    //Altera a quantidade de um evento no banco de dados
    public void aumentarQuantidadeIngresso(int id_evento, int quantidade){
        String sql = "UPDATE evento set qt_ingresso_evento= (qt_ingresso_evento + ?) WHERE id_evento=?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){            
            stmt.setInt(1, quantidade);
            stmt.setInt(2, id_evento);
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (EventoDAO/07 " + ex + ")");
        }
    }
    
}
