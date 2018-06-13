package negocio;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
    private int id;
    private Date data_venda;
    private Cliente cliente;
    private Usuario usuario;
    
    public Venda(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return data_venda;
    }

    public void setDataVenda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
