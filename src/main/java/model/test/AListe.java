package model.test;

import java.util.Date;

public abstract class AListe {

    private int id;
    private String titre;
    private String description;
    private Date dateCreation;
    private Date dateFin;
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

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateDerModif() {
        return dateDerModif;
    }

    public void setDateDerModif(Date dateDerModif) {
        this.dateDerModif = dateDerModif;
    }

    @Override
    public String toString() {
        return "AListe{" +
                "titre='" + titre + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }
}
