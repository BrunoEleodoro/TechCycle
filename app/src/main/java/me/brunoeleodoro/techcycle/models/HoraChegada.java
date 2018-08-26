package me.brunoeleodoro.techcycle.models;

public class HoraChegada {
    String text;
    String time_zone;
    String value;

    public HoraChegada(String text, String time_zone, String value) {
        this.text = text;
        this.time_zone = time_zone;
        this.value = value;
    }

    public HoraChegada() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
