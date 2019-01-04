package model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import javax.sql.DataSource;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Sql2oModel {
    private static Sql2o sql2o;

    public Sql2oModel(DataSource ds){
        this.sql2o = new Sql2o(ds);
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
                    "idListe INTEGER not NULL, " +
                    "dateCreation DATE, " +
                    "dateDerModif DATE, " +
                    "titre VARCHAR(255), " +
                    "description VARCHAR(255), " +
                    "PRIMARY KEY ( id ), " +
                    "FOREIGN KEY ( idListe ) REFERENCES LISTE ( id ));").executeUpdate();
        }
    }

    public static int insertTableElement(int id, int idListe, String dateCreation, String dateDerModif, String titre, String description){
        try(Connection con = sql2o.open()){

            con.createQuery("INSERT INTO ELEMENT(id, idListe, dateCreation, dateDerModif, titre, description) VALUES (:id, :idListe, :dateCreation, :dateDerModif, :titre, :description)")
                    .addParameter("id", id)
                    .addParameter("idListe", idListe)
                    .addParameter("dateCreation", dateCreation)
                    .addParameter("dateDerModif", dateDerModif)
                    .addParameter("titre", titre)
                    .addParameter("description", description)
                    .executeUpdate();

            return idListe;
        }
    }

    public static List<model.Element> getAllElement(){
        try(Connection con = sql2o.open()){
            Table table = con.createQuery("SELECT * FROM ELEMENT").executeAndFetchTable();
            List<model.Element> list_e = new LinkedList<>();

            for (Row row : table.rows()) {
                model.Element element = new model.Element();
                element.setId((int) row.getObject("id"));
                element.setIdListe((int) row.getObject("idListe"));
                element.setTitre((String) row.getObject("titre"));
                element.setDescription((String) row.getObject("description"));
                element.setDateCreation((Date) row.getObject("datecreation"));
                element.setDateDerModif((Date) row.getObject("datedermodif"));
                element.setId((int) row.getObject("id"));
                list_e.add(element);
            }
            return list_e;
        }
    }

    public static model.Element getElement(int val){
        try(Connection con = sql2o.open()){
            Table table = con.createQuery("SELECT * FROM ELEMENT").executeAndFetchTable();
            model.Element l = new model.Element();
            int v = val - 1;

            l.setId((int) table.rows().get(v).getObject("id"));
            l.setIdListe((int) table.rows().get(v).getObject("idListe"));
            l.setTitre((String) table.rows().get(v).getObject("titre"));
            l.setDescription((String) table.rows().get(v).getObject("description"));
            l.setDateCreation((Date) table.rows().get(v).getObject("datecreation"));
            l.setDateDerModif((Date) table.rows().get(v).getObject("datedermodif"));
            l.setId((int) table.rows().get(v).getObject("id"));

            return l;
        }
    }

    public static void updateElement(int id, int idListe, String dateCreation, String dateDerModif, String titre, String description){
        try(Connection con = sql2o.open()){
            con.createQuery("UPDATE ELEMENT SET idListe = :idListe, dateCreation = :dateCreation, dateDerModif = :dateDerModif, titre = :titre, description = :description WHERE id = :id")
                    .addParameter("id", id)
                    .addParameter("idListe", idListe)
                    .addParameter("dateCreation", dateCreation)
                    .addParameter("dateDerModif", dateDerModif)
                    .addParameter("titre", titre)
                    .addParameter("description", description)
                    .executeUpdate();
        }
    }

    public static void createTableListe(){
        try(Connection con = sql2o.open()){
            con.createQuery("CREATE TABLE LISTE " +
                    "(id INTEGER not NULL, " +
                    "titre VARCHAR(255), " +
                    "description VARCHAR(255), " +
                    "listElement VARCHAR(1000), " +
                    "PRIMARY KEY ( id ));").executeUpdate();
        }
    }

    public static void insertTableListe(int id, String titre, String description, List<model.Element> listElement){
        try(Connection con = sql2o.open()){
            con.createQuery("INSERT INTO LISTE(id, titre, description, listElement) VALUES (:id, :titre, :description, :listElement)")
                    .addParameter("id", id)
                    .addParameter("titre", titre)
                    .addParameter("description", description)
                    .addParameter("listElement", listElement)
                    .executeUpdate();
        }
    }

    public static Liste getListe(int val){
        try(Connection con = sql2o.open()) {
            Liste l = new Liste();
            List<model.Element> list_e = new LinkedList<>();
            int v = val - 1;
            Table table = con.createQuery("SELECT * FROM LISTE").executeAndFetchTable();

            l.setId((int) table.rows().get(v).getObject("id"));
            l.setTitre((String) table.rows().get(v).getObject("titre"));
            l.setDescription((String) table.rows().get(v).getObject("description"));

            Table table2 = con.createQuery("SELECT * FROM ELEMENT WHERE idliste = :val")
                    .addParameter("val", val)
                    .executeAndFetchTable();

            for (Row row : table2.rows()) {
                model.Element element = new model.Element();
                element.setId((int) row.getObject("id"));
                element.setTitre((String) row.getObject("titre"));
                element.setDescription((String) row.getObject("description"));
                element.setDateCreation((Date) row.getObject("datecreation"));
                element.setDateDerModif((Date) row.getObject("datedermodif"));
                element.setId((int) row.getObject("id"));
                list_e.add(element);
            }
            l.setListElement(list_e);

            return l;
        }
    }

    public static void updateListe(int id, String titre, String description, List<model.Element> listElement){
        try(Connection con = sql2o.open()){
            con.createQuery("UPDATE LISTE SET titre = :titre, description = :description WHERE id = :id")
                    .addParameter("id", id)
                    .addParameter("titre", titre)
                    .addParameter("description", description)
                    .executeUpdate();
        }
    }

    public static ListeComposite getListeComposite(int val){
        try(Connection con = sql2o.open()) {
            ListeComposite l = new ListeComposite();
            List<model.Element> list_e = new LinkedList<>();
            int v = val - 1;
            Table table = con.createQuery("SELECT * FROM LISTE").executeAndFetchTable();

            l.setId((int) table.rows().get(v).getObject("id"));
            l.setTitre((String) table.rows().get(v).getObject("titre"));
            l.setDescription((String) table.rows().get(v).getObject("description"));

            Table table2 = con.createQuery("SELECT * FROM ELEMENT WHERE idListe = :val")
                    .addParameter("val", val)
                    .executeAndFetchTable();

            for (Row row : table2.rows()) {
                model.Element element = new model.Element();
                element.setId((int) row.getObject("id"));
                element.setTitre((String) row.getObject("titre"));
                element.setDescription((String) row.getObject("description"));
                element.setDateCreation((Date) row.getObject("datecreation"));
                element.setDateDerModif((Date) row.getObject("datedermodif"));
                element.setId((int) row.getObject("id"));
                list_e.add(element);
            }
            l.setListElement(list_e);

            return l;
        }
    }
}