package me.brunoeleodoro.techcycle.select_points.models;

import java.util.List;

import me.brunoeleodoro.techcycle.select_points.SelectPointsInteractorImpl;

public class Rota
{
    private String preco_total;
    private String tempo_total;
    private String tipo_rota;
    private List<Step> steps;

    public Rota(String preco_total, String tempo_total, String tipo_rota, List<Step> steps) {
        this.preco_total = preco_total;
        this.tempo_total = tempo_total;
        this.tipo_rota = tipo_rota;
        this.steps = steps;
    }

    public Rota() {
    }

    public String getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(String preco_total) {
        this.preco_total = preco_total;
    }

    public String getTempo_total() {
        return tempo_total;
    }

    public void setTempo_total(String tempo_total) {
        this.tempo_total = tempo_total;
    }

    public String getTipo_rota() {
        return tipo_rota;
    }

    public void setTipo_rota(String tipo_rota) {
        this.tipo_rota = tipo_rota;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}