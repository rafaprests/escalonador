public class Processo {

    private String nome;

    // Surto = tempo de CPU utilizado antes de uma operação de entrada e saída
    private int surto;
    private int maxSurto;

    // TempoES = tempo que um processo fica bloqueado por conta de uma operação E/S
    private int tempoES;
    private int maxTempoES;

    // TempoTotal = tempo total de surto que um processo tem que ter
    private int tempoTotal;
    private int maxTempoTotal;

    // Quanto maior o valor, maior a prioridade, mais tempo o processo pode executar
    private int prioridade;

    // Inicializada com o mesmo valor da prioridade, a diferença é que essa é decrementada
    private int credito;

    private int tempoFinal;

    private Estado estado;

    public Processo(String nome, int surto, int tempoES, int tempoTotal, int prioridade) {
        // As variáveis devem ser passadas ao ser criado
        this.nome = nome;
        this.surto = surto;
        this.maxSurto = surto;
        this.tempoES = tempoES;
        this.maxTempoES = tempoES;
        this.tempoTotal = 0;
        this.maxTempoTotal = tempoTotal;

        this.tempoFinal = 0;

        // A prioridade é definida na criação
        this.prioridade = prioridade;
        this.credito = prioridade; // Inicialmente, créditos são iguais à prioridade
        this.estado = Estado.READY; // O estado será READY quando o processo for criado
    }

    // Setter pode mudar apenas o crédito e o estado
    public void setCredito(int credito) {
        this.credito = credito;
    }

    public void incTempoTotal(){
        this.tempoTotal+=1;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setTempoFinal(int timer){
        this.tempoFinal = timer;
    }

    // Método interno para reduzir o surto
    public void reduzSurto() {
        this.surto -= 1;
    }

    public void resetSurto() {
        this.surto = maxSurto;
    }

    // Método interno para reduzir o tempo de E/S
    public void reduzTempoES() {
        if (this.tempoES > 0) {
            this.tempoES -= 1;
        }
    }

    public void resetTempoES() {
        this.tempoES = maxTempoES;
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

    public int getMaxSurto() {
        return maxSurto;
    }

    public int getMaxTempoES() {
        return maxTempoES;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    public int getMaxTempoTotal() {
        return maxTempoTotal;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public int getCredito() {
        return credito;
    }

    public int getTempoFinal(){
        return tempoFinal;
    }

    public Estado getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Processo [nome=" + nome + ", surto=" + surto + ", tempoES=" + tempoES + ", tempoTotal=" + tempoTotal
                + ", prioridade=" + prioridade + ", credito=" + credito + ", estado=" + estado + "]";
    }
}
