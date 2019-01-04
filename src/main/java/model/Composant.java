package model;

import java.util.List;

public interface Composant {
    public int getId();
    public String getTitre();
    public String getDescription();
    public List<Element> getListElement();
    public void setId(int id);
    public void setTitre(String titre);
    public void setDescription(String description);
    public void setListElement(List<Element> listElement);
}
