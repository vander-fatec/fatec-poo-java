package bancodedados.dao;

import bancodedados.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Cliente;

public class ClienteDAO {
    
     //Adiciona um cliente banco de dados
    public void adiciona(Cliente cliente) {
        String sql = "INSERT INTO cliente (cd_cpf_cnpj_cliente, nm_cliente) VALUES (?,?)";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ){
            stmt.setString(1, cliente.getCpfCnpj() );           
            stmt.setString(2, cliente.getNome());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (ClienteDAO/01 " + ex + ")");
        }
    }
     
    //Retorna a quantidade de cliente com o cpf/cnpj
    public int getClienteCount(String cpf_cnpj_nome){
        String sql = "SELECT COUNT(*) FROM cliente WHERE cd_cpf_cnpj_cliente=?";
        int count = 0;
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);      
        ){
            stmt.setString(1, cpf_cnpj_nome);
            try(
                ResultSet rs = stmt.executeQuery()
            ){
                while(rs.next()){
                    count += rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (ClienteDAO/02 " + ex + ")");
        }
        return count;
    }
           
    //Retorna todos os clientes no banco de dados
    public ArrayList<Cliente> getClientes(){
        String sql = "SELECT * FROM Cliente WHERE cd_cpf_cnpj_cliente NOT LIKE 0";
        ArrayList<Cliente> clientes = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ){
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setCpfCnpj(rs.getString(2));
                cliente.setNome(rs.getString(3));
                
                clientes.add(cliente);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (ClienteDAO/03 " + ex + ")");
        }
        return clientes;
    }
    
    //Procura clientes no banco de dados que tenha cpf 100% igual ou nome parecido
    public ArrayList<Cliente> getClientes(String cpf_cnpj_nome){
        String sql = "SELECT * FROM cliente"
                + " WHERE cd_cpf_cnpj_cliente LIKE ? OR nm_cliente LIKE ?";
        ArrayList<Cliente> clientes = new ArrayList<>();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
        ){
            stmt.setString(1, cpf_cnpj_nome);
            stmt.setString(2, cpf_cnpj_nome+"%");
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setCpfCnpj(rs.getString(2));
                cliente.setNome(rs.getString(3));
                
                clientes.add(cliente);
            }
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (ClienteDAO/04 " + ex + ")");
        }
        return clientes;
    }
    
    //Procura um cliente especifico no banco de dados pelo cpf
    public Cliente getCliente(String cpf_cnpj){
        String sql = "SELECT * FROM cliente WHERE id_cliente != 0"
                + " AND cd_cpf_cnpj_cliente LIKE ?";
        Cliente cliente = new Cliente();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
        ){
            stmt.setString(1, cpf_cnpj);            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                cliente.setId(rs.getInt(1));
                cliente.setCpfCnpj(rs.getString(2));
                cliente.setNome(rs.getString(3));
            }
            
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (ClienteDAO/05 " + ex + ")");
        }
        return cliente;
    }
    
    //Procura um cliente especifico no banco de dados pelo id
    public Cliente getCliente(int id){
        String sql = "SELECT * FROM cliente"
                + " WHERE id_cliente LIKE ?";
        Cliente cliente = new Cliente();
       
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
        ){
            stmt.setInt(1, id);            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                cliente.setId(rs.getInt(1));
                cliente.setCpfCnpj(rs.getString(2));
                cliente.setNome(rs.getString(3));
            }
            
        }catch(SQLException ex){
            System.out.println("Erro desconhecido. Contate TI. (ClienteDAO/06 " + ex + ")");
        }
        return cliente;
    }
    
    //Altera o cliente no banco de dados
    public void altera(Cliente cliente){
        String sql = "UPDATE cliente set cd_cpf_cnpj_cliente=?, nm_cliente=? "
                + "WHERE id_cliente = ?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){            
            stmt.setString(1, cliente.getCpfCnpj());
            stmt.setString(2, cliente.getNome());
            stmt.setInt(3, cliente.getId());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (ClienteDAO/07 " + ex + ")");
        }
    }
    
    //Remove um cliente no banco de dados
    public void removerCliente(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE "
                + "cd_cpf_cnpj_cliente = ?";
        try(
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);        
        ){
            stmt.setString(1, cliente.getCpfCnpj());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro desconhecido. Contate TI. (ClienteDAO/08 " + ex + ")");
        }
    }
    
}