package model.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LaListe extends AListe{
    private List<AListe> children = new ArrayList<>();

    public void add(AListe composant){
        children.add(composant);
    }

    public void remove(AListe composant){
        children.remove(composant);
    }

    public List<AListe> getListe() {
        return children;
    }

    public void setListe(List<AListe> liste) {
        this.children = liste;
    }

    @Override
    public String toString(){
        StringBuffer result = new StringBuffer();
        String vals = "\n" + "</br>Titre: " + super.getTitre() + "\n" +
                "</br>Description: " + super.getDescription() + "\n" +
                //"</br>Liste d'éléments: " + listElement + "\n" +
                "\t" + "</br>Est composée: </br>" + children +
                "</br>";
        return vals;
    }
}
