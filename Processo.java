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
    private Estado estado;

    public Processo(String nome, int surto, int tempoES, int tempoTotal, int prioridade){
        //acredito que essas variaveis devem ser passadas ao ser criado
        this.nome = nome;
        this.surto = surto;
        this.tempoES = tempoES;
        this.tempoTotal = tempoTotal;
        
        //acho que a prioridade eh definida por algum metodo

        //o estado vai ser ready quando ele for criado
        this.estado = Estado.READY;
    }

    //de getter e setter acredito que so se pode mudar a prioridade e o estado, o resto faz parte do que o processo eh e nao pode ser mudado
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
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

    public Estado getEstado() {
        return estado;
    }
}