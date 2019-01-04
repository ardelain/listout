package controleur;

import model.*;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static spark.Spark.get;

public class MainBDD {
    public static void main(String[] args) throws Exception {

        //String driverClassName = "org.h2.Driver";
        String url = "jdbc:h2:./listout";
        JdbcDataSource datasource = new JdbcDataSource();
        datasource.setURL(url);

        DataSource ds = datasource;

        //Sql2o sql2o = new Sql2o("jdbc:h2:./listout");
        Sql2oModel model = new Sql2oModel(ds);

        model.dropTable("LISTE");
        model.dropTable("ELEMENT");

        model.createTableListe();
        model.createTableElement();

        model.insertTableListe(1, "La liste", "Notre première liste", null);
        model.insertTableListe(2, "La liste2", "Notre deuxième liste", null);
        model.insertTableListe(3, "La liste3", "Notre troisième liste", null);

        int id = model.insertTableElement(1, 2, "2018-12-15", "2018-12-16", "toto au berceau", "toto essai1");
        int id2 = model.insertTableElement(2, 2, "2018-12-19", "2018-12-20", "toto au berceaux", "toto essai2");


        ListeComposite l = model.getListeComposite(1);
        Liste l2 = model.getListe(2);
        ListeComposite l3 = model.getListeComposite(3);

        List<Element> list_e = model.getAllElement();

        Element el = model.getElement(1);
        System.out.println("----- " + el + " -----");

        //ListeComposite lc = new ListeComposite();
        l.add(l3);
        l3.add(l2);

        //----- Affichage de tous les éléments
        final String[] vals = {""};
        list_e.forEach(e -> {
            System.out.println(e);
            vals[0] += e;
        });

        String finalVals = vals[0];
        //get("/hello", (req, res) -> finalVals);

        //----- Affichage d'une liste en particulier
        final String[] vals2 = {""};
        //System.out.println(l2);
        vals2[0] += l;

        String finalVals2 = vals2[0];
        //get("/hello", (req, res) -> finalVals2);

        String s = "2018-12-29";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(s);
        el.setDateDerModif(d);
        model.updateElement(1, el.getIdListe(),sdf.format(el.getDateCreation()), sdf.format(el.getDateDerModif()), el.getTitre(), el.getDescription());
        list_e = model.getAllElement();

        /*for (Iterator i = l.getChildren(); i.hasNext(); ) {
            Object objet = i.next();
            ListeComposite composant = (ListeComposite) objet;
            //composant.setListElement(list_e);
            //System.out.println(composant.getId());
            while (objet != null) {
                objet = composant.getChildren().next();
                if (objet == ListeComposite.class) {
                    if (composant.getId() == el.getIdListe()) {
                        composant.setListElement(model.getListeComposite(composant.getId()).getListElement());
                        //System.out.println(composant);
                    }
                    composant = (ListeComposite) objet;
                } else {
                    //System.out.println(o);
                    Liste composant2 = (Liste) objet;
                    if (composant2.getId() == el.getIdListe()) {
                        composant2.setListElement(model.getListe(composant2.getId()).getListElement());
                        //System.out.println(composant2);
                    }
                    objet = null;
                }
            }
        }
        System.out.println(l);*/

        Iterator i = l.getChildren();
        Object o = i.next();
        ListeComposite liste = (ListeComposite) o;
        while (o != null){
            //System.out.println(o);
            o = liste.getChildren().next();
            if(o == ListeComposite.class){
                if(liste.getId() == el.getIdListe()) {
                    liste.setListElement(model.getListe(liste.getId()).getListElement());
                    System.out.println(liste);
                }
                liste = (ListeComposite) o;
            }
            else{
                //System.out.println(o);
                Liste liste2 = (Liste) o;
                if(liste2.getId() == el.getIdListe()) {
                    liste2.setListElement(model.getListe(liste2.getId()).getListElement());
                    System.out.println(liste2);
                }
                o = null;
            }
            /*ListeComposite liste = (ListeComposite) o;
            System.out.println(liste.getChildren().next());*/

        }
        //l.setListElement(list_e);
        //l2.setListElement(list_e);
        //l3.setListElement(list_e);
        //model.updateListe();

        //----- Affichage d'une sous-liste
        final String[] vals3 = {""};
        //l.getChildren().forEachRemaining(o -> {
        //    System.out.println(o);
        //    vals3[0] += o;
        //});
        //System.out.println(l.getTitre());
        //System.out.println(l);
        vals3[0] += l;

        String finalVals3 = vals3[0];
        //get("/hello", (req, res) -> finalVals3);

        try{
            MainControleur main = new MainControleur(model,l,list_e);
            main.main(args);
        }catch (Exception e){
            System.err.println("ERREUR INIT SERVEUR");
        }
    }
}