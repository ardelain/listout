package model;

import java.util.Date;

public class Element {
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
}
