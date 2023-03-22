package br.com.buzatto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapCodigosPorExame {

    private static final Map<String, List<String>> codigosPorExame = new HashMap<>();

    static {
        codigosPorExame.put("Hemoglobina Glicada (Fração A1c) - Pesquisa E/Ou Dosagem", List.of("40302075", "40302733"));
        codigosPorExame.put("Hemoglobina Glicosilada (HbA1c)", List.of("40302075", "40302733"));
        codigosPorExame.put("Anti - Peroxidase (Anti - TPO/Anti - Microssomal)", List.of("40316157", "40306348"));
        codigosPorExame.put("Antimicrossomal - Pesquisa E/Ou Dosagem", List.of("40316157", "40306348"));
        codigosPorExame.put("Cultura", List.of("40310124", "40310132", "40310213"));
        codigosPorExame.put("Cultura Bacteriana (Em Diversos Materiais Biológicos)", List.of("40310124", "40310132", "40310213"));
        codigosPorExame.put("Cultura, Urina Com Contagem De Colônias - Tuss", List.of("40310124", "40310132", "40310213"));
        codigosPorExame.put("Antibiograma (Teste De Sensibilidade E Antibióticos E Quimioterápicos), Por Bactéria - Não Automatizado", List.of("40310418", "40310426"));
        codigosPorExame.put("Antibiograma", List.of("40310418", "40310426"));
        codigosPorExame.put("Bilirrubina Total e Frações", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
        codigosPorExame.put("Eletroforese de Proteínas", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
        codigosPorExame.put("Fosfatase Alcalina - Pesquisa E/Ou Dosagem", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
        codigosPorExame.put("Gama-Glutamil Transferase - Pesquisa E/Ou Dosagem", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
        codigosPorExame.put("GGT - Gama Glutamil Transferase", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
        codigosPorExame.put("Transaminase Pirúvica (Amino Transferase De Alanina) - Pesquisa E/Ou Dosagem", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
        codigosPorExame.put("TGP / ALT - Transaminase Pirúvica", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
        codigosPorExame.put("Transaminase Oxalacética (Amino Transferase Aspartato) - Pesquisa E/Ou Dosagem", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
        codigosPorExame.put("TGO / AST - Transaminase Oxalacética", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504"));
    }

    public static Map<String, List<String>> getCodigosPorExame() {
        return codigosPorExame;
    }
}
