package Trie;

public class App {
    public static void main(String[] args) {
        Trie trie = new Trie();

        String[] palabras = {"alas", "alan", "alce", "al"};

        for (int i = 0; i < palabras.length; i++) {
            trie.insert(palabras[i]);
        }

        for (int i = 0; i < palabras.length; i++) {
            System.out.println("Buscando "+palabras[i]+"... ---- RESULTADO: "+trie.search(palabras[i]));
        }

        System.out.println("Buscando alioli... ---- RESULTADO: "+trie.search("alioli"));
        System.out.println("Buscando ala... ---- RESULTADO: "+trie.search("ala"));

        // sinonimos

        trie.addSinonimo("ala", "aleta");
        trie.addSinonimo("ala", "helice");
        trie.addSinonimo("alce", "ciervo");

        System.out.println("listar sinonimos de ala: " + trie.listarSinonimos("ala").toString());
    }
}
