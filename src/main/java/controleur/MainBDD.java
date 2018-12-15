package controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static spark.Spark.get;

public class MainBDD {
    public static void main(String[] args) throws Exception {
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.getConnection("jdbc:h2:D:/Cours/L3/Techno Web Serveur/projet/listout/listout");

        Statement stmt = conn.createStatement();

        String sql = "DROP TABLE REGISTRATION;";

        stmt.executeUpdate(sql);

        sql = "CREATE TABLE   REGISTRATION " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";

        stmt.executeUpdate(sql);

        sql = "INSERT INTO Registration " + "VALUES (100, 'Zara', 'Ali', 18)";

        stmt.executeUpdate(sql);
        sql = "INSERT INTO Registration " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";

        stmt.executeUpdate(sql);
        sql = "INSERT INTO Registration " + "VALUES (102, 'Zaid', 'Khan', 30)";

        stmt.executeUpdate(sql);
        sql = "INSERT INTO Registration " + "VALUES(103, 'Sumit', 'Mittal', 28)";

        stmt.executeUpdate(sql);

        sql = "SELECT id, first, last, age FROM Registration";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            // Retrieve by column name
            int id = rs.getInt("id");
            int age = rs.getInt("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            // Display values
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);

            String val ="ID: " + id +
                         "</br>Age: " + age +
                         "</br>First: " + first +
                         "</br>Last: " + last +
                         "</br></br>";
            get("/hello", (req, res) -> val);
        }

        stmt.close();
        conn.close();
    }
}
