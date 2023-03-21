package br.com.buzatto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapCodigosPorExame {

    private static final Map<String, List<String>> codigosPorExame = new HashMap<>();

    static {
        codigosPorExame.put("Hemoglobina Glicada (Fração A1c) - Pesquisa E/Ou Dosagem", List.of("40302075", "40302733"));
        codigosPorExame.put("Hemoglobina Glicosilada (HbA1c)", List.of("40302075", "40302733"));
    }

    public static Map<String, List<String>> getCodigosPorExame() {
        return codigosPorExame;
    }
}
