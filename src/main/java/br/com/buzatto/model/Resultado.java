package br.com.buzatto.model;

import java.util.ArrayList;
import java.util.List;

public class Resultado {

    private Long totalItensUnimedProcessados = 0L;
    private Long totalItensUnimedComSucesso = 0L;
    private Long totalItensUnimedComDivergencia = 0L;
    private List<Item> itensDivergenteOrigemUnimed = new ArrayList<>();

    private Long totalItensConcentProcessados = 0L;
    private Long totalItensConcentComSucesso = 0L;
    private Long totalItensConcentComDivergencia = 0L;
    private List<Item> itensDivergenteOrigemConcent = new ArrayList<>();

    public Long getTotalItensUnimedProcessados() {
        return totalItensUnimedProcessados;
    }

    public void setTotalItensUnimedProcessados(Long totalItensUnimedProcessados) {
        this.totalItensUnimedProcessados = totalItensUnimedProcessados;
    }

    public Long getTotalItensUnimedComSucesso() {
        return totalItensUnimedComSucesso;
    }

    public void setTotalItensUnimedComSucesso(Long totalItensUnimedComSucesso) {
        this.totalItensUnimedComSucesso = totalItensUnimedComSucesso;
    }

    public Long getTotalItensUnimedComDivergencia() {
        return totalItensUnimedComDivergencia;
    }

    public void setTotalItensUnimedComDivergencia(Long totalItensUnimedComDivergencia) {
        this.totalItensUnimedComDivergencia = totalItensUnimedComDivergencia;
    }

    public List<Item> getItensDivergenteOrigemUnimed() {
        return itensDivergenteOrigemUnimed;
    }

    public void setItensDivergenteOrigemUnimed(List<Item> itensDivergenteOrigemUnimed) {
        this.itensDivergenteOrigemUnimed = itensDivergenteOrigemUnimed;
    }

    public Long getTotalItensConcentProcessados() {
        return totalItensConcentProcessados;
    }

    public void setTotalItensConcentProcessados(Long totalItensConcentProcessados) {
        this.totalItensConcentProcessados = totalItensConcentProcessados;
    }

    public Long getTotalItensConcentComSucesso() {
        return totalItensConcentComSucesso;
    }

    public void setTotalItensConcentComSucesso(Long totalItensConcentComSucesso) {
        this.totalItensConcentComSucesso = totalItensConcentComSucesso;
    }

    public Long getTotalItensConcentComDivergencia() {
        return totalItensConcentComDivergencia;
    }

    public void setTotalItensConcentComDivergencia(Long totalItensConcentComDivergencia) {
        this.totalItensConcentComDivergencia = totalItensConcentComDivergencia;
    }

    public List<Item> getItensDivergenteOrigemConcent() {
        return itensDivergenteOrigemConcent;
    }

    public void setItensDivergenteOrigemConcent(List<Item> itensDivergenteOrigemConcent) {
        this.itensDivergenteOrigemConcent = itensDivergenteOrigemConcent;
    }
}
