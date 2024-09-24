import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Escalonador {
    private ArrayList<Processo> listaDeProcessos;
    private LinkedList<Processo> ordem; // Use LinkedList instead of custom DoublyLinkedList
    private Queue<Processo> ordemBloq;

    public Escalonador() {
        this.listaDeProcessos = new ArrayList<>();
        this.ordem = new LinkedList<>(); // Initialize LinkedList
        this.ordemBloq = new LinkedList<>();
    }

    public void addProcesso(Processo processo) {
        processo.setEstado(Estado.READY);
        listaDeProcessos.add(processo);
        ordem.addLast(processo); // Add processo to the end of the LinkedList
    }

    public void atribuiCreditos() {
        for (Processo processo : listaDeProcessos) {
            if (processo.getEstado() != Estado.EXIT) {
                int novoCredito = (processo.getCredito() / 2) + processo.getPrioridade();
                processo.setCredito(novoCredito);
            }
        }
    }

    public void executarProcessos(int timer) {
        // Gerencia os processos bloqueados
        for (Processo processo : ordemBloq) {
            if (processo.getEstado() == Estado.BLOCKED) {
                processo.reduzTempoES();
                //System.out.println(processo.getNome() + " está bloqueado, tempo de E/S restante: " + processo.getTempoES());
            }
        }
        for (Processo processo : ordemBloq) {
            if (processo.getEstado() == Estado.BLOCKED && processo.getTempoES() <= 0) {
                processo.setEstado(Estado.READY);
                processo.resetSurto();
                processo.resetTempoES();
                ordem.addLast(processo);
                ordemBloq.remove(processo);
            }
        }
        
        Processo processoParaExecutar = ordem.peek(); // Get the first element from the LinkedList

        boolean changed = false;

        if(processoParaExecutar.getEstado() != Estado.RUNNING){
            for (Processo processo : ordem) {
                if (processo.getEstado() == Estado.READY && processo.getCredito() > processoParaExecutar.getCredito()) {
                    processoParaExecutar = processo;
                    changed = true;
                    break;

                }
            }
        }

        if (changed){
            ordem.remove(processoParaExecutar);
            ordem.addFirst(processoParaExecutar);
        }

        if (processoParaExecutar != null) {
            // Execute the selected process
            processoParaExecutar.setEstado(Estado.RUNNING);
            for (Processo processo : listaDeProcessos) {
                System.out.println(processo);
            }
            System.out.println(processoParaExecutar.getNome() + " está agora em execução.");
            processoParaExecutar.incTempoTotal();

            if (processoParaExecutar.getCredito() > 0 && processoParaExecutar.getSurto() > 0) {
                processoParaExecutar.setCredito(processoParaExecutar.getCredito() - 1); // Reduz 1 crédito
                processoParaExecutar.reduzSurto(); // Reduz 1ms de surto

                // Verifica se o surto acabou
                if (processoParaExecutar.getCredito() <= 0) {
                    processoParaExecutar.setEstado(Estado.READY);
                    System.out.println(processoParaExecutar.getNome() + " retornou ao estado READY (crédito esgotado).");
                    ordem.remove(processoParaExecutar);
                    ordem.addLast(processoParaExecutar);
                }
                if (processoParaExecutar.getSurto() <= 0 && processoParaExecutar.getMaxTempoES() > 0) {
                    processoParaExecutar.setEstado(Estado.BLOCKED);
                    processoParaExecutar.resetTempoES();
                    System.out.println(processoParaExecutar.getNome() + " foi bloqueado para E/S.");
                    ordem.remove(processoParaExecutar);
                    ordemBloq.add(processoParaExecutar);
                }
                if (processoParaExecutar.getTempoTotal() == processoParaExecutar.getMaxTempoTotal()) {
                    processoParaExecutar.setEstado(Estado.EXIT);
                    processoParaExecutar.setTempoFinal(timer+1);
                    ordem.remove(processoParaExecutar);
                    ordemBloq.remove(processoParaExecutar);
                    //System.out.println("O processo " + processoParaExecutar + " terminou!");
                }
            }
            if (processoParaExecutar.getCredito() > 0 && processoParaExecutar.getMaxSurto() == 0) {
                processoParaExecutar.setCredito(processoParaExecutar.getCredito() - 1); // Reduz 1 crédito

                if (processoParaExecutar.getCredito() <= 0) {
                    processoParaExecutar.setEstado(Estado.READY);
                    ordem.remove(processoParaExecutar);
                    ordem.addLast(processoParaExecutar);
                }
                if (processoParaExecutar.getTempoTotal() == processoParaExecutar.getMaxTempoTotal()) {
                    processoParaExecutar.setEstado(Estado.EXIT);
                    processoParaExecutar.setTempoFinal(timer+1);
                    ordem.remove(processoParaExecutar);
                }
            }
        }
        if (ordem.stream().allMatch(p -> p.getCredito() <= 0 && (p.getEstado() == Estado.READY || p.getEstado() == Estado.RUNNING))) {
            System.out.println("Créditos resetados");
            atribuiCreditos();
        }
    }

    public void Clock() {
        int timer = 1;
        while (!ordem.isEmpty()) {
            System.out.println(timer);
            long startTime = System.nanoTime(); // Get the current time in nanoseconds

            executarProcessos(timer);
            timer += 1;

            long elapsedTime = System.nanoTime() - startTime;
            long elapsedMillis = elapsedTime / 1_000_000;
            if (elapsedMillis < 1) {
                try {
                    Thread.sleep(1 - elapsedMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("=============================");
        for (Processo processo : listaDeProcessos) {
            System.out.println("Processo: " + processo.getNome() + " -> turnAround: " + processo.getTempoFinal());
        }
        System.out.println("=============================");
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < listaDeProcessos.size(); i++) {
            s.append(i).append(" da fila: ").append(listaDeProcessos.get(i).toString()).append("\n");
        }
        return s.toString();
    }
}
