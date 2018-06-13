package negocio;

public class Cliente {
    private int id;
    private String cpf_cnpj;
    private String nome;
    
    public Cliente(){
        this.id = 0;
        this.cpf_cnpj = "0";
        this.nome = "Não nominal";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCpfCnpj() {
        return cpf_cnpj;
    }

    public void setCpfCnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean equals(Cliente cliente) {
        return (
            this.nome.equals(cliente.getCpfCnpj())
        ) ;
    } 
}
