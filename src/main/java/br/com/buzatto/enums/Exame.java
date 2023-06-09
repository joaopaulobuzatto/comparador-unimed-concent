package br.com.buzatto.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Exame {

    EXAME_100("40302075", List.of("40302075", "40302733")),
    EXAME_101("40302733", List.of("40302075", "40302733")),
    EXAME_200("40316157", List.of("40316157", "40306348", "40306437")),
    EXAME_201("40306348", List.of("40316157", "40306348", "40306437")),
    EXAME_202("40306437", List.of("40316157", "40306348", "40306437")),
    EXAME_300("40310124", List.of("40310124", "40310132", "40310213", "40310400")),
    EXAME_301("40310132", List.of("40310124", "40310132", "40310213", "40310400")),
    EXAME_302("40310213", List.of("40310124", "40310132", "40310213", "40310400")),
    EXAME_303("40310400", List.of("40310124", "40310132", "40310213", "40310400")),
    EXAME_400("40310418", List.of("40310418", "40310426")),
    EXAME_401("40310426", List.of("40310418", "40310426")),
    EXAME_500("40301397", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504", "40312151")),
    EXAME_501("40301761", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504", "40312151")),
    EXAME_502("40301885", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504", "40312151")),
    EXAME_503("40301990", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504", "40312151")),
    EXAME_504("40302512", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504", "40312151")),
    EXAME_505("40302504", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504", "40312151")),
    EXAME_506("40312151", List.of("40301397", "40301761", "40301885", "40301990", "40302512", "40302504", "40312151")),
    EXAME_600("40316424", List.of("40316424", "40305465")),
    EXAME_601("40305465", List.of("40316424", "40305465")),
    EXAME_700("40307182", List.of("40307182", "40307174")),
    EXAME_701("40307174", List.of("40307182", "40307174")),
    EXAME_800("40305627", List.of("40316521", "40316491", "40316548", "40316556", "40316467"));

    private String codigo;
    private List<String> variacoes;

    Exame(String codigo, List<String> variacoes) {
        this.codigo = codigo;
        this.variacoes = variacoes;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<String> getVariacoes() {
        return variacoes;
    }

    public static Exame valueOfByCodigo(String codigo) {
        return Arrays.stream(values())
                .filter(exame -> exame.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public static List<String> getVariacoesByCodigo(String codigo) {
        Exame exame = valueOfByCodigo(codigo);
        if (exame == null) {
            return new ArrayList<>();
        }
        return exame.getVariacoes();
    }

    @Override
    public String toString() {
        return "Exame{" +
                "codigo='" + codigo + '\'' +
                ", variacoes=" + variacoes +
                '}';
    }
}
