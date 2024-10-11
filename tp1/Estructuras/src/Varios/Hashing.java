package Varios;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Hashing {
    public static void main(String[] args) {
        HashMap<String, Integer> mapeo = new HashMap<>();
        String texto = "";
        
        Path path = Path.of("recursos/texto.txt");

        try {
            texto = Files.readString(path, StandardCharsets.ISO_8859_1);
        } catch (Exception e) {
            System.out.println(e);
        }
        String[] palabras = texto.split("[\\s.,]+");

        for (int i = 0; i < palabras.length; i++) {
            if (mapeo.containsKey(palabras[i])) {
                mapeo.put(palabras[i], mapeo.get(palabras[i])+1);
            } else {
                mapeo.put(palabras[i], 1);
            }
        }

        for (String key : mapeo.keySet()) {
            System.out.println("Cant. ocurrencias de "+key+":" + mapeo.get(key));
        }
    }
}
