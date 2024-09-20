import java.util.ArrayList;

public class Escalonador {
    //ordem dos processos sera atribuida atraves da lista de processos, com politica rotativa FIFO
    private ArrayList<Processo> listaDeProcessos;

    public Escalonador(){
        this.listaDeProcessos = new ArrayList<Processo>();
    }

    public void addProcesso(Processo processo){
        //ao criar um processo deve ser setado o estado, a prioridade e a ordem dele
        processo.setEstado(Estado.READY);
        //nao sei ainda como funciona a ordenacao
        //processo.setOrdem(0);
        
        listaDeProcessos.add(processo);
    }

    public void atribuiCreditos(){
        for(int i = 0; i < listaDeProcessos.size(); i++){
            if(listaDeProcessos.get(i).getEstado() != Estado.EXIT){
                //calcula o valor dos novos creditos
                int novoCredito = (listaDeProcessos.get(i).getCredito()/2) + listaDeProcessos.get(i).getPrioridade();

                //atribui os novos creditos ao processo
                listaDeProcessos.get(i).setCredito(novoCredito);
            }
        }
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < listaDeProcessos.size(); i++){
            s += i + " da fila: " + listaDeProcessos.get(i).toString() + "\n";
        }
        return s;
    }

}
