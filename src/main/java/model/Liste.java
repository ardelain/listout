package model;

import java.util.List;

public class Liste {
    private String titre;
    private String description;
    private List<Element> listElement;

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public List<Element> getListElement() {
        return listElement;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setListElement(List<Element> listElement) {
        this.listElement = listElement;
    }

    @Override
    public String toString(){
        String vals = "</br>Titre: " + this.titre +
                "</br>Description: " + this.description +
                "</br>Liste d'éléments: " + this.listElement +
                "</br>";
        return vals;
    }
}
