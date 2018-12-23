package controleur;

import model.Element;
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

        model.dropTable("ELEMENT");

        model.createTableElement();

        model.insertTableElement(100, "2018-12-15", "2018-12-16", "toto au berceau", "toto essai1");
        model.insertTableElement(101, "2018-12-19", "2018-12-20", "toto au berceaux", "toto essai2");

        final String[] vals = {""};

        List<Element> list_e = model.getAllElement();
        list_e.forEach(e -> {
            System.out.println(e.getTitre());
            System.out.println(e.getDescription());
            System.out.println(e.getDateCreation());
            System.out.println(e.getDateDerModif());
            vals[0] += "</br>DateCreation: " + e.getDateCreation() +
                    "</br>DateDerModif: " + e.getDateDerModif() +
                    "</br>Titre: " + e.getTitre() +
                    "</br>Description: " + e.getDescription() +
                    "</br></br>";
        });

        String finalVals = vals[0];
        get("/hello", (req, res) -> finalVals);
    }
}
