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
}
