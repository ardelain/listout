package model;

import java.util.List;

public class Liste implements Composant {
    private String titre;
    private String description;
    private List<Element> listElement;

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Element> getListElement() {
        return listElement;
    }

    @Override
    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
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
