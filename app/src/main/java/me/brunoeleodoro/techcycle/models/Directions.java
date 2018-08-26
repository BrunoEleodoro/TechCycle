package me.brunoeleodoro.techcycle.models;

import java.util.List;

public class Directions  {

    String tipo;
    String nome;
    String numero;
    String cor;
    String paradas;
    String polyline;
    HoraSaida horasSaida;
    HoraChegada horasChegada;

    public Directions() {

    }

    public Directions(String tipo, String nome, String numero, String cor, String paradas, String polyline, HoraSaida horasSaida, HoraChegada horasChegada) {
        this.tipo = tipo;
        this.nome = nome;
        this.numero = numero;
        this.cor = cor;
        this.paradas = paradas;
        this.polyline = polyline;
        this.horasSaida = horasSaida;
        this.horasChegada = horasChegada;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getParadas() {
        return paradas;
    }

    public void setParadas(String paradas) {
        this.paradas = paradas;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public HoraSaida getHorasSaida() {
        return horasSaida;
    }

    public void setHorasSaida(HoraSaida horasSaida) {
        this.horasSaida = horasSaida;
    }

    public HoraChegada getHorasChegada() {
        return horasChegada;
    }

    public void setHorasChegada(HoraChegada horasChegada) {
        this.horasChegada = horasChegada;
    }

    /*
    {
    "tipo":"SUBWAY",
    "nome":"Tucuruvi - Jabaquara",
    "numero":"METRÔ L1",
    "cor":"#01579b",
    "paradas":8,
    "polyline":{
        "points":"nnioCpqt{GoDjEyPwCaGPgP]wFIwGKA?_DHyCcB{Aj@w@l@iC[kBi@aEa@oF|DqBFsKy@aDKa@EmGyAME]CcDc@}HiAw[kEMAyOyB_KoAmBWoBQgCIcGCA?wDAeC@gCC{Dc@gEs@uKgC}^KO?ee@WyAMcAYq@Sc@Y{@m@{@y@yAiA{AWwAu@_AYUGgBUoBCmBAkBTwA\\eDjBqDzBcGlD{F~CiFxCWNu@PuFdDsAt@wC|AoB~@yBj@{AXyAKaAImFY"},"hora_saida":{"text":"8:55pm","time_zone":"America/Sao_Paulo","value":1535241325},"hora_chegada":{"text":"9:10pm","time_zone":"America/Sao_Paulo","value":1535242221}},{"tipo":"BUS","nome":"Pq. Res. Cocaia - Pça. Da Sé","numero":"5362-10","cor":"#0277bd","paradas":4,"polyline":{"points":"da{nCbst{G?SjADlEHp@D|@L`@PpAp@zBdD~BjEfAjBx@`Br@`ALNl@~@`BdCjCfEbKnO`D|FbAfC|@dBh@t@@@r@`A`AjATTZd@Xf@Jj@Cd@Kt@iCdF}@b@YNKFe@TIDgEvBKFGBKHa@VOHSLbAfBdBlCZd@Zs@^u@X]d@c@b@]^SnAg@zAq@d@S`FoBv@]l@[BCJR"
    },
    "hora_saida":{
        "text":"9:25pm",
        "time_zone":"America/Sao_Paulo",
        "value":1535243140
    },
    "hora_chegada":{
        "text":"9:34pm",
        "time_zone":"America/Sao_Paulo",
        "value":1535243640
    }
}
     */
}
