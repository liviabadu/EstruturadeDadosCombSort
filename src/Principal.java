import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Principal {

    static ArrayList<Integer> listaOriginal = new ArrayList<>();
    static ArrayList<Integer> listaTrabalho = new ArrayList<>();

    public static void main(String[] args) {

        String opcao = "";

        while (!opcao.equals("7")) {
            opcao = JOptionPane.showInputDialog(
                    "===== MENU - COMB SORT =====\n" +
                    "1 - Preencher Lista\n" +
                    "2 - Ordenar com Comb Sort (Alg 1)\n" +
                    "3 - Ordenar com Collections.sort (Alg 2)\n" +
                    "4 - Mostrar Lista Ordenada\n" +
                    "5 - Mostrar Lista Original\n" +
                    "6 - Mostrar Tempos (ns)\n" +
                    "7 - Sair"
            );

            if (opcao == null) break;

            switch (opcao) {
                case "1":
                    preencherLista();
                    break;
                case "2":
                    ordenarCombSort();
                    break;
                case "3":
                    ordenarCollections();
                    break;
                case "4":
                    mostrar("Lista Ordenada", listaTrabalho);
                    break;
                case "5":
                    mostrar("Lista Original", listaOriginal);
                    break;
                case "6":
                    medirTempos();
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    // ================================================================
    // 1) Preencher lista
    // ================================================================
    public static void preencherLista() {
        listaOriginal.clear();

        int[] dados = {
            432,809,213,725,37,960,578,63,921,145,689,281,506,955,194,374,820,62,
            890,485,786,911,394,178,627,902,420,579,733,96,311,654,250,771,9,624,
            712,135,505,884,445,688,77,912,721,390,538,893,470,679,1,869,302,946,
            561,144,790,422,769,57,899,308,687,469,237,630,961,36,578,799,180,628,
            886,298,835,62,974,214,518,746,132,899,243,511,798,235,681,61,918,375,
            692,993,183,553,846,24,954,286,647,129
        };

        for (int v : dados) {
            listaOriginal.add(v);
        }

        listaTrabalho = new ArrayList<>(listaOriginal);

        JOptionPane.showMessageDialog(null, "Lista preenchida com " + listaOriginal.size() + " elementos!");
    }

    // ================================================================
    // 2) Ordenar com Comb Sort
    // ================================================================
    public static void ordenarCombSort() {
        if (listaOriginal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha a lista primeiro!");
            return;
        }

        listaTrabalho = new ArrayList<>(listaOriginal);

        combSort(listaTrabalho);

        JOptionPane.showMessageDialog(null, "Lista ordenada com Comb Sort!");
    }

    // ================================================================
    // 3) Ordenar com Collections.sort (baseline)
    // ================================================================
    public static void ordenarCollections() {
        if (listaOriginal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha a lista primeiro!");
            return;
        }

        listaTrabalho = new ArrayList<>(listaOriginal);

        Collections.sort(listaTrabalho);

        JOptionPane.showMessageDialog(null, "Lista ordenada com Collections.sort!");
    }

    // ================================================================
    // 4 e 5) Exibição
    // ================================================================
    public static void mostrar(String titulo, ArrayList<Integer> lista) {
        JOptionPane.showMessageDialog(null, titulo + ":\n" + lista);
        System.out.println(titulo + ": " + lista);
    }

    // ================================================================
    // 6) Tempo de execução
    // ================================================================
    public static void medirTempos() {
        if (listaOriginal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha a lista primeiro!");
            return;
        }

        ArrayList<Integer> lista1 = new ArrayList<>(listaOriginal);
        ArrayList<Integer> lista2 = new ArrayList<>(listaOriginal);

        // Comb Sort
        long t1 = System.nanoTime();
        combSort(lista1);
        long t2 = System.nanoTime();
        long tempoComb = t2 - t1;

        // Collections.sort
        long t3 = System.nanoTime();
        Collections.sort(lista2);
        long t4 = System.nanoTime();
        long tempoColl = t4 - t3;

        String msg =
                "===== TEMPOS EM NANOSEGUNDOS =====\n\n" +
                "Comb Sort:            " + tempoComb + " ns\n" +
                "Collections.sort:     " + tempoColl + " ns\n";

        JOptionPane.showMessageDialog(null, msg);
        System.out.println(msg);
    }

    // ================================================================
    // IMPLEMENTAÇÃO DO COMB SORT
    // ================================================================
    public static void combSort(ArrayList<Integer> lista) {
        int n = lista.size();
        int gap = n;
        boolean houveTroca = true;
        double fator = 1.3;

        while (gap > 1 || houveTroca) {
            gap = (int)(gap / fator);
            if (gap < 1) gap = 1;

            houveTroca = false;

            for (int i = 0; i + gap < n; i++) {
                if (lista.get(i) > lista.get(i + gap)) {
                    int temp = lista.get(i);
                    lista.set(i, lista.get(i + gap));
                    lista.set(i + gap, temp);
                    houveTroca = true;
                }
            }
        }
    }
}
