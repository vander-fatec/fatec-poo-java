package negocio;

public class TipoIngresso {
    private int id;
    private String nome;
    
    public TipoIngresso(){
        
    }
        
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
