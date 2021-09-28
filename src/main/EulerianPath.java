package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class EulerianPath {
    // Numero dei vertici del grafo impostati nel metodo costruttore
    int V;
    // Lista delle adiacenze
    ArrayList<ArrayList<Integer>> adj;
    // RIsultato del cammino euleriano
    ArrayList<Integer> percorso = new ArrayList<>();

    public EulerianPath(int V) {
        this.V = V;
        adj = new ArrayList<>();
        // Inizializzo la lista delle adiacenze
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<Integer>());
        }
    }
    // Funzione per popolare la lista delle adiacenze
    void addEdge(int u, int v) {
        // Aggiungo il collegamento bidirezionale (da u a v e da v a u)
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    // Rimuovo il collegamento sostituendo il valore di adiacenza con -1
    void rmvEdge(int u, int v) {
        int iv = find(adj.get(u), v);
        adj.get(u).set(iv, -1);
        int iu = find(adj.get(v), u);
        adj.get(v).set(iu, -1);
    }
    // Trovo la posizione del valore da sostituire
    int find(ArrayList<Integer> al, int v) {
        for(int i=0; i<al.size(); i++) {
            if(al.get(i) == v) {
                return i;
            }
        }
        return -1;
    }
    // Dopo aver trovato un vertice di grado pari stampo il percorso partendo da quel vertice
    void printEulerTour() {
        int u = 0;
        for (int i = 0; i < V; i++) {
            if (adj.get(i).size() % 2 == 1) {
                u = i;
                break;
            }
        }
        printEulerUtil(u);
    }
    // Funzione ricorsiva che stampa il cammino euleriano partendo dal vertice u
    void printEulerUtil(int u) {
        percorso.add(u);
        int dim = adj.get(u).size();
        // Verifico per ogni vertice adiacente se è valido
        for (int i = 0; i<dim; ++i) {
            int v = adj.get(u).get(i);
            // In tal caso stampo il collegamento e passo al vertice successivo
            if (v != -1 && isValidNextEdge(u, v)) {
                rmvEdge(u, v);
                printEulerUtil(v);
            }
        }
    }
    // Conta i vertici raggiungibili da v
    int DFSCount(int v, boolean[] visited) {
        // Mark the current node as visited
        visited[v] = true;
        int count = 1;
        // Recur for all vertices adjacent to this vertex
        for (int i = 0; i<adj.get(v).size(); ++i) {
            int u = adj.get(v).get(i);

            if (u != -1 && !visited[u]) {
                count += DFSCount(u, visited);
            }
        }
        return count;
    }
    // Funzione che valida il collegamento u v
    boolean isValidNextEdge(int u, int v) {
        // Il collegamento u v è valido se:
        // 1) v è l'unico vertice adiacente a u
        int count = 0;
        for (int i = 0; i<adj.get(u).size(); ++i)
            if (adj.get(u).get(i) != -1)
                count++;
        if (count == 1)
            return true;
        // 2) se ce ne sono di più
        // 2.a) conto i vertici raggiungibili da u
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        int count1 = DFSCount(u, visited);
        // 2.b) rimuovo il collegamento u v e riconto i vertici raggiungibili
        rmvEdge(u, v);
        Arrays.fill(visited, false);
        int count2 = DFSCount(u, visited);
        // 2.c) riaggiungo il collegamento
        addEdge(u, v);
        // 2.d) se il primo conteggio è maggiore del secondo u v è un ponte valido
        return count1 <= count2;
    }

    public boolean isEulerianPath() {
        for (ArrayList<Integer> vert : adj) {
            for (Integer integer : vert) {
                if (integer != -1) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isEulerianCycle() {
        return Objects.equals(percorso.get(0), percorso.get(percorso.size()-1));
    }
}
