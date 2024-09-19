import java.util.ArrayList;

public class Escalonador {
    private ArrayList<Processo> listaDeProcessos;

    public Escalonador(){
        this.listaDeProcessos = new ArrayList<Processo>();
    }

    public void addProcesso(Processo processo){
        listaDeProcessos.add(processo);
    }
}
