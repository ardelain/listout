package model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.converters.Converter;
import org.sql2o.data.Row;
import org.sql2o.data.Table;
import org.sql2o.quirks.NoQuirks;

import javax.sql.DataSource;
import javax.xml.bind.Element;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Sql2oModel implements Element {
    private static Sql2o sql2o;

    public Sql2oModel(DataSource ds){
        this.sql2o = new Sql2o(ds, new NoQuirks(new HashMap<Class, Converter>()));
        //this.sql2o = sql2o;
    }

    public static void dropTable(String table){
        try(Connection con = sql2o.open()){
            con.createQuery("DROP TABLE "+ table).executeUpdate();
        }
    }

    public static void createTableElement(){
        try(Connection con = sql2o.open()){
            con.createQuery("CREATE TABLE ELEMENT " +
                    "(id INTEGER not NULL, " +
                    "dateCreation DATE, " +
                    "dateDerModif DATE, " +
                    "titre VARCHAR(255), " +
                    "description VARCHAR(255), " +
                    "PRIMARY KEY ( id ));").executeUpdate();
        }
    }

    public static void insertTableElement(int id, String dateCreation, String dateDerModif, String titre, String description){
        try(Connection con = sql2o.open()){
            con.createQuery("INSERT INTO ELEMENT(id, dateCreation, dateDerModif, titre, description) VALUES (:id, :dateCreation, :dateDerModif, :titre, :description)")
                    .addParameter("id", id)
                    .addParameter("dateCreation", dateCreation)
                    .addParameter("dateDerModif", dateDerModif)
                    .addParameter("titre", titre)
                    .addParameter("description", description)
                    .executeUpdate();
        }
    }

    public static List<model.Element> getAllElement(){
        try(Connection con = sql2o.open()){
            Table table = con.createQuery("SELECT * FROM ELEMENT").executeAndFetchTable();
            List<model.Element> list_e = new LinkedList<>();

            for (Row row : table.rows()) {
                model.Element element = new model.Element();
                element.setTitre((String) row.getObject("titre"));
                element.setDescription((String) row.getObject("description"));
                element.setDateCreation((Date) row.getObject("datecreation"));
                element.setDateDerModif((Date) row.getObject("datedermodif"));
                list_e.add(element);
            }
            return list_e;
        }
    }
}
