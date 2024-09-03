package Trie;

import java.util.HashMap;

public class NodoTrie {
    private char c;
    private HashMap<Character, NodoTrie> hijos = new HashMap<>();
    private boolean esHoja;
    private Lista sinonimos = new Lista();

    public NodoTrie() {
    }

    public NodoTrie(char c) {
        this.c = c;
    }

    public HashMap<Character, NodoTrie> getHijos() {
        return hijos;
    }

    public void setHijos(HashMap<Character, NodoTrie> hijos) {
        this.hijos = hijos;
    }

    public boolean esHoja() {
        return esHoja;
    }

    public void setHoja(boolean esHoja) {
        this.esHoja = esHoja;
    }

    public void addSinonimo(String sinonimo) {
        sinonimos.agregarElem(sinonimo, sinonimos.longitud()+1);
    }

    public Lista listarSinonimos() {
        return sinonimos;
    }
}
