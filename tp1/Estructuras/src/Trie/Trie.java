package Trie;

import java.util.HashMap;

public class Trie {
    private NodoTrie raiz;

    public Trie(){
        raiz = new NodoTrie();
    }

    public void insert(String palabra) {
        int length = palabra.length();
        HashMap<Character, NodoTrie> hijos = raiz.getHijos();
        for (int i = 0; i < length; i++) {
            char c = palabra.charAt(i);
            NodoTrie nodo;
            if (hijos.containsKey(c)) {
                nodo = hijos.get(c);
            } else {
                nodo = new NodoTrie(c);
                hijos.put(c, nodo);
            }
            hijos = nodo.getHijos();

            if (i == length - 1) {
                nodo.setHoja(true);
            }
        }
    }

    private NodoTrie buscarNodo(String palabra) {
        HashMap<Character, NodoTrie> hijos = raiz.getHijos();
        int length = palabra.length();
        boolean sigue = true;

        NodoTrie nodo = null;
        int i = 0;

        while(i < length && sigue) {
            char c = palabra.charAt(i);
            if (hijos.containsKey(c)) {
                nodo = hijos.get(c);
                hijos = nodo.getHijos();
                i++;
            } else {
                nodo = null;
                sigue = false;
            }
        }

        return nodo;
    }

    public boolean search(String palabra) {
        NodoTrie nodo = buscarNodo(palabra);
        return (nodo != null && nodo.esHoja());
    }

    public boolean addSinonimo(String palabra, String sinonimo) {
        NodoTrie nodo = buscarNodo(palabra);

        boolean exito = (nodo != null && nodo.esHoja());
        nodo.addSinonimo(sinonimo);

        return exito;
    }

    public Lista listarSinonimos(String palabra) {
        NodoTrie nodo = buscarNodo(palabra);
        if (nodo != null) {
            return nodo.listarSinonimos();
        }
        return null;
    }
}
