package model.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AListe {

    private int id;
    private String titre;
    private String description;
    private Date dateCreation;
    private Date dateDerModif;
    private List<AListe> children = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateDerModif() {
        return dateDerModif;
    }

    public void setDateDerModif(Date dateDerModif) {
        this.dateDerModif = dateDerModif;
    }

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
        String vals = "\n" + "</br>Titre: " + titre + "\n" +
                "</br>Description: " + description + "\n" +
                //"</br>Liste d'éléments: " + listElement + "\n" +
                "</br>";
        return vals;
    }
}
