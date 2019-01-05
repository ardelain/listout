package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AListe {

    private int id;
    private String titre;
    private String description;
    private Date dateCreation;
    private Date dateDerModif;

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

    @Override
    public String toString(){
        String vals = "\n" + "</br>Titre: " + titre + "\n" +
                "</br>Description: " + description + "\n" +
                //"</br>Liste d'éléments: " + listElement + "\n" +
                "</br>";
        return vals;
    }
}
