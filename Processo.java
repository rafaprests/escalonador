public class Processo{

    private String nome;

    //surto=tempo de cpu utilizado antes de uma operacao de entrada e saida
    private int surto;

    //tempoES=tempo que um processo fica bloqueado por conta de uma operacao E/S
    private int tempoES;

    //tempoTotal=tempo total de surto que um processo tem que ter
    private int tempoTotal;

    //quanto maior o valor, maior a prioridade, mais tempo o processo pode executar
    private int prioridade;

    //eh inicializada com o mesmo valor da prioridade, a diferenca eh que essa eh decrementada
    private int credito;
    
    private Estado estado;

    public Processo(String nome, int surto, int tempoES, int tempoTotal, int prioridade){
        //acredito que essas variaveis devem ser passadas ao ser criado
        this.nome = nome;
        this.surto = surto;
        this.tempoES = tempoES;
        this.tempoTotal = tempoTotal;
        
        //acho que a prioridade eh definida por algum metodo
        this.prioridade = prioridade;
        this.credito = prioridade;
        //o estado vai ser ready quando ele for criado
        this.estado = Estado.READY;
    }

    //de setter pode mudar apenas o credito e o estado
    public void setCredito(int credito) {
        this.credito = credito;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public String getNome() {
        return nome;
    }

    public int getSurto() {
        return surto;
    }

    public int getTempoES() {
        return tempoES;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    public int getPrioridade() {
        return prioridade;
    }
    
    public int getCredito() {
        return credito;
    }

    public Estado getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Processo [nome=" + nome + ", surto=" + surto + ", tempoES=" + tempoES + ", tempoTotal=" + tempoTotal
                + ", prioridade=" + prioridade + ", credito=" + credito + ", estado=" + estado
                + "]";
    }
}