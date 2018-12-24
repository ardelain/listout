package controleur;

import model.Element;
import model.Liste;
import model.Sql2oModel;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
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
        Liste l = model.getListe(1);

        int id = model.insertTableElement(100, 1, "2018-12-15", "2018-12-16", "toto au berceau", "toto essai1");

        int id2 = model.insertTableElement(101, 1, "2018-12-19", "2018-12-20", "toto au berceaux", "toto essai2");
        List<Element> list_e = model.getAllElement();
        l.setListElement(list_e);

        final String[] vals = {""};
        list_e.forEach(e -> {
            System.out.println(e);
            vals[0] += e;
        });

        String finalVals = vals[0];
        //get("/hello", (req, res) -> finalVals);

        final String[] vals2 = {""};
        System.out.println(l);
        vals2[0] += l;

        String finalVals2 = vals2[0];
        get("/hello", (req, res) -> finalVals2);
    }
}
