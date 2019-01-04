package controleur;

import model.test.*;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import static spark.Spark.get;

public class Main {
    public static void main(String[] args) throws Exception {

        //String driverClassName = "org.h2.Driver";
        String url = "jdbc:h2:./listout";
        JdbcDataSource datasource = new JdbcDataSource();
        datasource.setURL(url);

        DataSource ds = datasource;

        //Sql2o sql2o = new Sql2o("jdbc:h2:./listout");
        UnSql2oModel model = new UnSql2oModel(ds);

        model.dropTable("LISTE");
        model.dropTable("ELEMENT");

        model.createTableListe();
        model.createTableElement();

        model.insertTableListe(1, "La liste", "Notre première liste", null);
        model.insertTableListe(2, "La liste2", "Notre deuxième liste", null);
        model.insertTableListe(3, "La liste3", "Notre troisième liste", null);

        int id = model.insertTableElement(1, 1, "2018-12-15", "2018-12-16", "toto au berceau", "toto essai1");
        int id2 = model.insertTableElement(2, 1, "2018-12-19", "2018-12-20", "toto au berceaux", "toto essai2");


        AListe l = model.getListe(1);
        AListe l2 = model.getListe(2);
        AListe l3 = model.getListe(3);

        List<AListe> list_e = model.getAllElement();

        UnElement el = model.getElement(1);
        System.out.println("----- " + el + " -----");

        //ListeComposite lc = new ListeComposite();
        l.add(l3);
        //l3.add(l2);

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
        model.updateElement(1, el.getId(),el.getDateCreation(), el.getDateDerModif(), el.getTitre(), el.getDescription());
        list_e = model.getAllElement();
        System.out.println("--- "+el+" ---");
        //l.setListe(list_e);

        l.getListe().iterator().forEachRemaining(liste -> {
            if (liste.getClass() == UnElement.class & liste.getId() == el.getId()){
                liste.setDateDerModif(el.getDateDerModif());
                System.out.println(liste.getDateDerModif());
            }
            else{
                System.out.println(liste.getTitre());
            }
        });

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
        get("/hello", (req, res) -> finalVals3);

        /*try{
            MainControleur main = new MainControleur(model,l,list_e);
            main.main(args);
        }catch (Exception e){
            System.err.println("ERREUR INIT SERVEUR"+e);
        }*/
    }
}
