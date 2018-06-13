package negocio;

public class Ingresso {
    private int id;
    private Venda venda;
    private Evento evento;
    private TipoIngresso tipo_ingresso;
    private float valor;

    public Ingresso() {
        
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public TipoIngresso getTipoIngresso() {
        return tipo_ingresso;
    }

    public void setTipoIngresso(TipoIngresso tipo_ingresso) {
        this.tipo_ingresso = tipo_ingresso;
    }

    public float getValor() {
        switch (this.tipo_ingresso.getId()) {
            case 1 : this.valor = evento.getValorIngresso(); break; //inteiro
            case 2 : this.valor = (evento.getValorIngresso()/2); break; //meio comum
            case 3 : this.valor = (evento.getValorIngresso()*1.5f); break; //vip 
            case 4 : this.valor = ((evento.getValorIngresso()*1.5f)/2); break; //meio vip
            default: this.valor = 0.00f; break;
        }
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public boolean equals(Ingresso ingresso) {
        return (
            this.evento.equals(ingresso.getEvento()) &&
            this.tipo_ingresso == ingresso.tipo_ingresso
        );    
    }
    
}
