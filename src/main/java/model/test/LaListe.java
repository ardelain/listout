package model.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LaListe extends AListe{
    private List<AListe> children = new ArrayList<>();

    public boolean add(AListe composant){
        if(children.add(composant)){
            return true;
        }else{
            return false;
        }
    }

    public boolean remove(AListe composant){
        if(children.remove(composant)){
            return true;
        }else{
            return false;
        }
    }

    public List<AListe> getListe() {
        return children;
    }

    public void setListe(List<AListe> liste) {
        this.children = liste;
    }

    public static List<AListe> rechercheFils(UnSql2oModel sql, List<AListe> liste,int id){
        List<AListe> l = new ArrayList<>();
        for(int i : sql.getAllPossede(id)){
            for (AListe a: liste) {
                if(a.getId() == i){
                    l.add(a);
                }
            }
        }
        return l;
    }

    public static List<AListe> recherchePere(UnSql2oModel sql, List<AListe> liste,int id){
        List<AListe> l = new ArrayList<>();
        for(int i : sql.getAllPossedant(id)){
            for (AListe a: liste) {
                if(a.getId() == i){
                    l.add(a);
                }
            }
            //l.add(liste.get(liste.indexOf(i)));
        }
        return l;
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
