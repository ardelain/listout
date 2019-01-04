package model;

import java.util.Date;

public class Element {
<<<<<<< HEAD
    private int id;
    private int idListe;
=======
    private int id = -1;
>>>>>>> e2cb9ea7926854b34efd9d901ba618a4e0e65ad5
    private Date dateCreation;
    private Date dateDerModif;
    private String titre;
    private String description;

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateDerModif() {
        return dateDerModif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateDerModif(Date dateDerModif) {
        this.dateDerModif = dateDerModif;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        String vals = "\n" + "</br>DateCreation: " + dateCreation + "\n" +
                "</br>DateDerModif: " + dateDerModif + "\n" +
                "</br>Titre: " + titre + "\n" +
                "</br>Description: " + description + "\n" +
                "</br>";
        return vals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }
}
