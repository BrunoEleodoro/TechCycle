package me.brunoeleodoro.techcycle.select_points.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Step
{
    private String tipo;
    private String nome;
    private String numero;
    private String paradas;
    private String cor;
    private List<LatLng> lats_lngs = new ArrayList<>();
    private String distance;
    private String duration;

    public Step(String tipo, String nome, String numero, String paradas, String cor, List<LatLng> lats_lngs, String distance, String duration) {
        this.tipo = tipo;
        this.nome = nome;
        this.numero = numero;
        this.paradas = paradas;
        this.cor = cor;
        this.lats_lngs = lats_lngs;
        this.distance = distance;
        this.duration = duration;
    }

    public Step() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getParadas() {
        return paradas;
    }

    public void setParadas(String paradas) {
        this.paradas = paradas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public List<LatLng> getLats_lngs() {
        return lats_lngs;
    }

    public void setLats_lngs(List<LatLng> lats_lngs) {
        this.lats_lngs = lats_lngs;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}