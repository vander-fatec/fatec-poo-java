package bancodedados.dao;

import bancodedados.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import negocio.Ingresso;
import negocio.Venda;

public class VendaDAO {   
    //Adiciona uma venda no banco de dados
    public void adiciona(Venda venda) {
        String sql = "INSERT INTO venda (id_venda, id_cliente, nm_login_usuario, dt_venda) VALUES (?,?,?, NOW())";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ){  
            int id_venda = this.numVenda();
            stmt.setInt(1, id_venda);                
            stmt.setInt(2, venda.getCliente().getId()); 
            stmt.setString(3, venda.getUsuario().getLogin());

            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/01 " + ex + ")");
        }
    }
    
    //Retorna todos os ingressos de uma venda lançada no banco de dados
    public ArrayList<Ingresso> getIngressosVenda(int id){
        String sql = "SELECT id_ingresso FROM ingresso WHERE id_venda = ?";
        ArrayList<Ingresso> ingressos = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);            
        ){   
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Ingresso ingresso = new Ingresso();
                ingresso = new IngressoDAO().getIngresso(rs.getInt(1));
                ingressos.add(ingresso);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/02 " + ex + ")");
        }
        return ingressos;
    }
        
    //Retorna todos as vendas lançadas no banco de dados
    public ArrayList<Venda> getVendas(){
        String sql = "SELECT * FROM venda";
        ArrayList<Venda> vendas = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){            
            while(rs.next()){
                Venda venda = new Venda();
                venda.setId(rs.getInt(1));
                venda.setDataVenda(rs.getDate(2));
                venda.setCliente(new ClienteDAO().getCliente(rs.getInt(3)));
                venda.setUsuario(new UsuarioDAO().getUsuario(rs.getString(4)));
               
                vendas.add(venda);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/03 " + ex + ")");
        }
        return vendas;
    }   
    
    //Retorna uma venda especifica lançadas no banco de dados
    public Venda getVenda(int id){
        String sql = "SELECT * FROM venda WHERE id_venda=?";
        Venda venda = new Venda();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);            
        ){     
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){                
                venda.setId(rs.getInt(1));
                venda.setDataVenda(rs.getDate(2));
                venda.setCliente(new ClienteDAO().getCliente(rs.getInt(3)));
                venda.setUsuario(new UsuarioDAO().getUsuario(rs.getString(4)));
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/03 " + ex + ")");
        }
        return venda;
    }
        
    //Altera uma venda no banco de dados
    public void altera(Venda venda){
        String sql = "UPDATE venda set dt_venda=?, id_cliente=?, nm_login_usuario=?"
                + " WHERE id_venda=?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){
            stmt.setDate(1, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setInt(2, venda.getCliente().getId());
            stmt.setString(3, venda.getUsuario().getLogin());
            stmt.setInt(4, venda.getId());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/04 " + ex + ")");
        }
    }
    
    //Remove um ingresso de uma venda do banco de dados
    public void removerVenda(Venda venda) {
        String sql = "DELETE FROM venda WHERE id_venda = ?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){
            stmt.setInt(1, venda.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/05 " + ex + ")");
        }
    }
    
    //Retorna o proximo numero de venda a ser inserido no banco
    public int numVenda() {
        String sql = "SELECT MAX(id_venda) FROM venda";
        int max = 0;
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            while(rs.next()){
                max = rs.getInt(1);
            }      
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/06 " + ex + ")");
        }
        return (max+1);
    }
    
    //Retorna ingressos distintos de uma venda no banco
    public ArrayList<Ingresso> getIngressoDistintosVenda(int id){
        String sql = "SELECT * FROM ingresso WHERE id_venda = ? GROUP BY  id_tipo_ingresso";
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
        ){     
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Ingresso ingresso = new Ingresso();
                ingresso.setId(rs.getInt(1));
                ingresso.setEvento(new EventoDAO().getEvento(rs.getInt(2)));
                ingresso.setTipoIngresso(new TipoIngressoDAO().getTipoIngresso(rs.getInt(3)));
                ingresso.setValor(rs.getFloat(4)); 
                ingressos.add(ingresso);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/07 " + ex + ")");
        }
        return ingressos;
    }

    //Retorna um ArrayList com um array de ingressos contendo
    // nome do evento, tipo do ingresso, valor unitario do ingresso e quantidade total de ingressos comprado
    public ArrayList<String[]> getIngressosPorTipo(int id_venda){
        String sql = "SELECT e.nm_evento, ti.nm_tipo_ingresso, i.vl_ingresso, count(*) "
                + "FROM ingresso as i "
                + "JOIN evento as e "
                + "JOIN tipo_ingresso as ti "
                + "WHERE e.id_evento = i.id_evento and i.id_tipo_ingresso = ti.id_tipo_ingresso and i.id_venda = ? "
                + "GROUP BY id_venda, i.id_tipo_ingresso, i.id_evento "
                + "ORDER BY e.nm_evento";        
        ArrayList<String[]> ingressos = new ArrayList<>();        
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
        ){     
            stmt.setInt(1, id_venda);
            ResultSet rs = stmt.executeQuery();
            int x = 0;
            while(rs.next()){
                String[] array = new String[4];
                array[0] = rs.getString(1);
                array[1] = rs.getString(2);
                array[2] = Float.toString(rs.getFloat(3));
                array[3] = Integer.toString(rs.getInt(4));
                ingressos.add(x, array);
                x++;
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/08 " + ex + ")");
        }
        
        return ingressos;
        
    }
    
    //Retorna true se o cliente existir em alguma venda, false se não estiver associado
    public boolean isClienteAssociado(int id_cliente){
        String sql = "SELECT * FROM venda WHERE id_cliente=?";
        boolean ret = false;
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);            
        ){     
            stmt.setInt(1, id_cliente);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){                
                ret = true;
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/09 " + ex + ")");
        }
        return ret;
    }
    
    //Retorna true se o evento existir em alguma venda, false se não estiver associado
    public boolean isEventoAssociado(int id_evento){
        String sql = "SELECT * FROM ingresso WHERE id_evento=?";
        boolean ret = false;
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);            
        ){     
            stmt.setInt(1, id_evento);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){                
                ret = true;
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (VendaDAO/09 " + ex + ")");
        }
        return ret;
    }
    
}
