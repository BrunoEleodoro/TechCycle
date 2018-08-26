package me.brunoeleodoro.techcycle.main;

import android.content.Context;

public interface MainPresenter extends MainInteractor, MainView{
    void setView(MainView view);
    Context getContext();
}
/*
[{
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
}]
 */