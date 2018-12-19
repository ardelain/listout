package controleur;

import model.Sql2oModel;
import org.h2.jdbcx.JdbcDataSource;
import org.sql2o.Sql2o;
import org.sql2o.converters.Converter;
import org.sql2o.converters.joda.DateTimeConverter;
import org.sql2o.quirks.NoQuirks;

import javax.sql.DataSource;
import javax.xml.bind.Element;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

public class MainBDD {
    public static void main(String[] args) throws Exception {
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.getConnection("jdbc:h2:./listout");

        Statement stmt = conn.createStatement();

        String sql = "DROP TABLE ELEMENT;";

        stmt.executeUpdate(sql);

        sql = "CREATE TABLE ELEMENT " +
                "(id INTEGER not NULL, " +
                " dateCreation DATE, " +
                " dateDerModif DATE, " +
                " titre VARCHAR(255), " +
                " description VARCHAR(255), " +
                " PRIMARY KEY ( id ))";

        stmt.executeUpdate(sql);

        sql = "INSERT INTO ELEMENT " + "VALUES (100, '2018-12-15', '2018-12-16', 'toto au berceau', 'toto essai')";

        stmt.executeUpdate(sql);
        sql = "INSERT INTO ELEMENT " + "VALUES (101, '2018-12-20', '2018-12-21', 'toto au berceaux', 'toto essai2')";

        stmt.executeUpdate(sql);
        /*sql = "INSERT INTO ELEMENT " + "VALUES (102, 'Zaid', 'Khan', 30)";

        stmt.executeUpdate(sql);
        sql = "INSERT INTO ELEMENT " + "VALUES(103, 'Sumit', 'Mittal', 28)";

        stmt.executeUpdate(sql);*/

        sql = "SELECT id, dateCreation, dateDerModif, titre, description FROM ELEMENT";
        ResultSet rs = stmt.executeQuery(sql);

        String vals = "";

        while (rs.next()) {
            // Retrieve by column name
            int id = rs.getInt("id");
            String dateCreation = rs.getString("dateCreation");
            String dateDerModif = rs.getString("dateDerModif");
            String titre = rs.getString("titre");
            String description = rs.getString("description");

            // Display values
            System.out.print("ID: " + id);
            System.out.print(", DateCreation: " + dateCreation);
            System.out.print(", DateDerModif: " + dateDerModif);
            System.out.print(", Titre: " + titre);
            System.out.println(", Description: " + description);

            vals +="ID: " + id +
                         "</br>DateCreation: " + dateCreation +
                         "</br>DateDerModif: " + dateDerModif +
                         "</br>Titre: " + titre +
                         "</br>Description: " + description +
                         "</br></br>";
            //get("/hello", (req, res) -> val);
        }

        String finalVals = vals;
        get("/hello", (req, res) -> finalVals);

        /*String driverClassName = "org.h2.Driver";
        String url = "jdbc:h2:./listout";
        JdbcDataSource datasource = new JdbcDataSource();
        datasource.setURL(url);

        DataSource ds = datasource;

        Sql2o sql2o = new Sql2o(ds, new NoQuirks(new HashMap<Class, Converter>()));



        //Sql2o sql2o = new Sql2o("jdbc:h2:./listout");

        Sql2oModel model = new Sql2oModel(sql2o);

        List<Element> list = model.getAllElement();*/

        stmt.close();
        conn.close();
    }
}
