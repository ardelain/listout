package model.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LaListe extends AListe{
    private List<AListe> liste = new ArrayList<>();


    public List<AListe> getListe() {
        return liste;
    }

    public void setListe(List<AListe> liste) {
        this.liste = liste;
    }

    @Override
    public String toString() {
        return "LaListe{" +
                "liste=" + liste +
                '}';
    }
}
