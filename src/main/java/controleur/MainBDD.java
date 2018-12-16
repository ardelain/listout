package controleur;

import model.Element;
import model.Sql2oModel;
import org.sql2o.Sql2o;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static spark.Spark.get;

public class MainBDD {
    public static void main(String[] args) throws Exception {
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.getConnection("jdbc:h2:D:/Cours/L3/Techno Web Serveur/projet/listout/listout");

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

        /*stmt.executeUpdate(sql);
        sql = "INSERT INTO ELEMENT " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";

        stmt.executeUpdate(sql);
        sql = "INSERT INTO ELEMENT " + "VALUES (102, 'Zaid', 'Khan', 30)";

        stmt.executeUpdate(sql);
        sql = "INSERT INTO ELEMENT " + "VALUES(103, 'Sumit', 'Mittal', 28)";*/

        stmt.executeUpdate(sql);

        sql = "SELECT id, dateCreation, dateDerModif, titre, description FROM ELEMENT";
        ResultSet rs = stmt.executeQuery(sql);

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

            String val ="ID: " + id +
                         "</br>DateCreation: " + dateCreation +
                         "</br>DateDerModif: " + dateDerModif +
                         "</br>Titre: " + titre +
                         "</br>Description: " + description +
                         "</br></br>";
            get("/hello", (req, res) -> val);
        }

        /*Sql2o sql2o = new Sql2o("jdbc:h2://");

        Sql2oModel model = new Sql2oModel(sql2o);*/

        stmt.close();
        conn.close();
    }
}
