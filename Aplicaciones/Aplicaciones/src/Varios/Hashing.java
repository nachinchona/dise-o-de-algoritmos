package Varios;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

public class Hashing {
    public static void main(String[] args) {
        File file = new File("texto.txt");
        System.out.println(file);
        HashMap<String, Integer> mapeo = new HashMap<>();
        String texto = "Hola a todos los que estén leyendo este texto. Este texto es utilizado para el algoritmo de Hashing en la carpeta Varios. Lee a todas las palabras repetidas y toma la cantidad de ocurrencias de cada una en un HashMap.";
    
        try {
            texto = Files.readString(file.toPath(), StandardCharsets.ISO_8859_1);
        } catch (Exception e) {
            System.out.println(e);
        }
        String[] palabras = texto.split("(?=[,.])|\\s+");

        for (int i = 0; i < palabras.length; i++) {
            System.out.println(palabras[i]);
        }

        for (int i = 0; i < palabras.length; i++) {
            if (mapeo.containsKey(palabras[i])) {
                mapeo.put(palabras[i], mapeo.get(palabras[i])+1);
            } else {
                mapeo.put(palabras[i], 1);
            }
        }

        System.out.println("cant de 'de':" + mapeo.get("de"));
    }
}


