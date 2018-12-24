package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ListeComposite implements Composant {
    private Collection children;

    public void add(Composant composant){
        children.add(composant);
    }

    public void remove(Composant composant){
        children.remove(composant);
    }

    public Iterator getChildren() {
        return children.iterator();
    }

    @Override
    public String getTitre() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public List<Element> getListElement() {
        return null;
    }

    @Override
    public void setTitre(String titre) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setListElement(List<Element> listElement) {

    }
}
