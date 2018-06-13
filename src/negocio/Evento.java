package negocio;

import java.sql.Time;
import java.util.Date;

public class Evento {
    private int id;
    private String nome;
    private Date data_inicio;
    private Time hora_inicio;
    private String endereco;
    private int quantidade_ingresso;
    private float valor_ingresso;
    
    public Evento(){
        
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return data_inicio;
    }

    public void setDataInicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Time getHoraInicio() {
        return hora_inicio;
    }

    public void setHoraInicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public int getQuantidadeIngresso() {
        return quantidade_ingresso;
    }

    public void setQuantidadeIngresso(int quantidade_ingresso) {
        this.quantidade_ingresso = quantidade_ingresso;
    }

    public float getValorIngresso() {
        return valor_ingresso;
    }

    public void setValorIngresso(float valor_ingresso) {
        this.valor_ingresso = valor_ingresso;
    }
    
    public boolean equals(Evento evento) {
        return (
            this.nome.equals(evento.getNome())
            && this.data_inicio.compareTo(evento.getDataInicio()) == 0
            && this.hora_inicio.compareTo(evento.getHoraInicio()) == 0
            && this.endereco.equals(evento.getEndereco())
        ) ;
    }    
    
}
