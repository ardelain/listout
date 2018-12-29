package controleur;

import model.*;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        l.add(l2);
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
        model.updateElement(1, 1,sdf.format(el.getDateCreation()), sdf.format(el.getDateDerModif()), el.getTitre(), el.getDescription());
        list_e = model.getAllElement();
        //model.updateListe();

        //----- Affichage d'une sous-liste
        final String[] vals3 = {""};
        /*l.getChildren().forEachRemaining(o -> {
            System.out.println(o);
            vals3[0] += o;
        });*/
        //System.out.println(l.getTitre());
        vals3[0] += l;

        String finalVals3 = vals3[0];
        get("/hello", (req, res) -> finalVals3);
    }
}
