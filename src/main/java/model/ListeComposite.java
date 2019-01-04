package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ListeComposite implements Composant {
    private int id = -1;
    private Collection children = new ArrayList();
    private String titre;
    private String description;
    private List<Element> listElement;

    /*public ListeComposite() {
        children = new ArrayList();
    }*/

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
        assert null != children;

        StringBuffer result = new StringBuffer();
        result.append(titre);
        result.append(" : (");

        for (Iterator i = children.iterator(); i.hasNext(); ) {
            Object objet = i.next();

            assert null != objet : "Un objet null a été trouvé dans la liste des composantes";
            assert objet instanceof Composant : "Une \"non liste\" a été trouvé dans la liste des composantes";

            Composant composant = (Composant) objet;

            result.append(composant.getTitre());
            if (i.hasNext()) { // on ajoute une virgule pour séparer les titres
                result.append(", ");
            }
        }

        result.append(" )");
        return result.toString();
    }

    @Override
    public String getDescription() {
        assert null != children;

        StringBuffer result = new StringBuffer();
        result.append(description);
        result.append(" : (");

        for (Iterator i = children.iterator(); i.hasNext(); ) {
            Object objet = i.next();

            assert null != objet : "Un objet null a été trouvé dans la liste des composantes";
            assert objet instanceof Composant : "Une \"non liste\" a été trouvé dans la liste des composantes";

            Composant composant = (Composant) objet;

            result.append(composant.getDescription());
            if (i.hasNext()) { // on ajoute une virgule pour séparer les descriptions
                result.append(", ");
            }
        }

        result.append(" )");
        return result.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public void setListElement(List<Element> listElement) {
        this.listElement = listElement;
    }

    @Override
    public String toString(){
        StringBuffer result = new StringBuffer();
        String vals = "\n" + "</br>Titre: " + titre + "\n" +
                "</br>Description: " + description + "\n" +
                "</br>Liste d'éléments: " + listElement + "\n" +
                "\t" + "</br>Sous-liste: " + children +
                "</br>";
        return vals;
    }
}
