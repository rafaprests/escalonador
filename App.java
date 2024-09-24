public class App {
    public static void main(String args[]){
        Processo A = new Processo("A", 2, 5, 6, 3);
        Processo B = new Processo("B", 3, 10, 6, 3);
        Processo C = new Processo("C", 0,0, 14, 3);
        Processo D = new Processo("D", 0, 0, 10, 3);

        Escalonador e = new Escalonador();
        e.addProcesso(A);
        e.addProcesso(B);
        e.addProcesso(C);
        e.addProcesso(D);
        
        e.Clock();
    }
}
