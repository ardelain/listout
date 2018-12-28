package controleur;

import model.*;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
        ListeComposite l = model.getListeComposite(1);
        Liste l2 = model.getListe(2);
        ListeComposite l3 = model.getListeComposite(3);

        int id = model.insertTableElement(1, 1, "2018-12-15", "2018-12-16", "toto au berceau", "toto essai1");

        int id2 = model.insertTableElement(2, 1, "2018-12-19", "2018-12-20", "toto au berceaux", "toto essai2");
        List<Element> list_e = model.getAllElement();
        l.setListElement(list_e);
        l2.setListElement(list_e);

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
        System.out.println(l2);
        vals2[0] += l;

        String finalVals2 = vals2[0];
        //get("/hello", (req, res) -> finalVals2);

        /*el.setDateDerModif();
        System.out.println(el.getDateDerModif());*/
        model.updateElement(1, 1, "2018-12-15", "2018-12-28", "toto au berceau", "toto essai1");

        //----- Affichage d'une sous-liste
        final String[] vals3 = {""};
        /*l.getChildren().forEachRemaining(o -> {
            System.out.println(o);
            vals3[0] += o;
        });*/
        System.out.println(l.getTitre());
        vals3[0] += l;

        String finalVals3 = vals3[0];
        get("/hello", (req, res) -> finalVals3);
    }
}
