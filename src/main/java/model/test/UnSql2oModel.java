package model.test;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UnSql2oModel {
    private static Sql2o sql2o;

    public UnSql2oModel(DataSource ds){
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
                    "FOREIGN KEY ( idListe ) REFERENCES ELEMENT ( id ));").executeUpdate();
        }
    }

    public static void createTablePossede(){
        try(Connection con = sql2o.open()){
            con.createQuery("CREATE TABLE POSSEDE " +
                    "(id INTEGER not NULL, " +
                    "idListe INTEGER not NULL, " +
                    "PRIMARY KEY ( id ), " +
                    "FOREIGN KEY ( idListe ) REFERENCES ELEMENT ( id ),"+
                    "FOREIGN KEY ( id ) REFERENCES ELEMENT ( id ));").executeUpdate();
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

    public static int insertTablePossede(int id, int idListe){
        try(Connection con = sql2o.open()){
            con.createQuery("INSERT INTO POSSEDE(id, idListe) VALUES (:id, :idListe)")
                    .addParameter("id", id)
                    .addParameter("idListe", idListe)
                    .executeUpdate();
            return idListe;
        }
    }

    public static List<AListe> getAllElement(){
        try(Connection con = sql2o.open()){
            Table table = con.createQuery("SELECT * FROM ELEMENT").executeAndFetchTable();
            List<AListe> list_e = new LinkedList<>();
            for (Row row : table.rows()) {
                AListe element = new UnElement();
                element.setId((int) row.getObject("idListe"));
                element.setTitre((String) row.getObject("titre"));
                element.setDescription((String) row.getObject("description"));
                element.setDateCreation((Date) row.getObject("datecreation"));
                element.setDateDerModif((Date) row.getObject("datedermodif"));
                list_e.add(element);
            }
            return list_e;
        }
    }

    public static List<Integer> getAllPossede(int val){
        try(Connection con = sql2o.open()){
            List<Integer> li = new ArrayList<>();
            Table table = con.createQuery("SELECT * FROM POSSEDE WHERE id = :val").addParameter("val", val).executeAndFetchTable();
            List<AListe> list_e = new LinkedList<>();
            for (Row row : table.rows()) {
                li.add((int) row.getObject("id"));
            }
            return li;
        }
    }

    public static List<Integer> getAllPossedant(int val){
        try(Connection con = sql2o.open()){
            List<Integer> li = new ArrayList<>();
            Table table = con.createQuery("SELECT * FROM POSSEDE WHERE idliste = :val").addParameter("val", val).executeAndFetchTable();
            List<AListe> list_e = new LinkedList<>();
            for (Row row : table.rows()) {
                li.add((int) row.getObject("idListe"));
            }
            return li;
        }
    }
    public static UnElement getElement(int val){
        try(Connection con = sql2o.open()){
            Table table = con.createQuery("SELECT * FROM ELEMENT where id = :val").addParameter("val", val).executeAndFetchTable();
            int v = 0;
            UnElement l = new UnElement();
            l.setId((int) table.rows().get(v).getObject("id"));
            //l.setIdListe((int) table.rows().get(v).getObject("idListe"));
            l.setTitre((String) table.rows().get(v).getObject("titre"));
            l.setDescription((String) table.rows().get(v).getObject("description"));
            l.setDateCreation((Date) table.rows().get(v).getObject("datecreation"));
            l.setDateDerModif((Date) table.rows().get(v).getObject("datedermodif"));
            //l.setId((int) table.rows().get(v).getObject("id"));
            return l;
        }
    }

    public static void updateElement(int id, int idListe, Date dateCreation, Date dateDerModif, String titre, String description){
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
                    "PRIMARY KEY ( id ));").executeUpdate();
        }
    }
    /*   "dateCreation DATE, " +
                    "dateDerModif DATE, " +*/

    public static void insertTableListe(int id, String titre, String description){//, String d, String d2
        try(Connection con = sql2o.open()){
            con.createQuery("INSERT INTO LISTE(id, titre, description) VALUES (:id, :titre, :description)")//, dateCreation, dateDerModif//, :d,:d2
                    .addParameter("id", id)
                    .addParameter("titre", titre)
                    .addParameter("description", description)
                    //.addParameter("dateCreation", d)
                    //.addParameter("dateDerModif", d2)
                    .executeUpdate();
        }
    }

    public static AListe getListe(int val){
        try(Connection con = sql2o.open()) {
            Table table = con.createQuery("SELECT * FROM LISTE").executeAndFetchTable();
            int v = val - 1;
            Date d = new Date();//...................................................................................................................................Integrer date au liste normale !!!
            AListe l = new LaListe();
            List<AListe> list_e = new LinkedList<>();

            l.setId((int) table.rows().get(v).getObject("id"));
            l.setTitre((String) table.rows().get(v).getObject("titre"));
            l.setDescription((String) table.rows().get(v).getObject("description"));
            //l.setDateCreation((Date) table.rows().get(v).getObject("datecreation"));
            //l.setDateDerModif((Date) table.rows().get(v).getObject("datedermodif"));


            Table table2 = con.createQuery("SELECT * FROM ELEMENT WHERE idliste = :val")
                    .addParameter("val", val)
                    .executeAndFetchTable();

            for (Row row : table2.rows()) {
                AListe element = new UnElement();
                element.setId((int) row.getObject("id"));
                element.setTitre((String) row.getObject("titre"));
                element.setDescription((String) row.getObject("description"));
                element.setDateCreation((Date) row.getObject("datecreation"));
                element.setDateDerModif((Date) row.getObject("datedermodif"));
                element.setId((int) row.getObject("id"));
                list_e.add(element);
            }
            ((LaListe) l).setListe(list_e);
            //l.setListElement(list_e);

            return l;
        }
    }

    public static AListe getAllList(){
        try(Connection con = sql2o.open()) {
            Table table = con.createQuery("SELECT * FROM LISTE").executeAndFetchTable();
            AListe all = new LaListe();
            AListe l = new LaListe();
            List<AListe> list_e = new LinkedList<>();
            for (Row ro : table.rows()) {
                int v = (int) ro.getObject("id");
                l.setId((int) ro.getObject("id"));
                l.setTitre((String) ro.getObject("titre"));
                l.setDescription((String) ro.getObject("description"));


                Table table2 = con.createQuery("SELECT * FROM ELEMENT WHERE idliste = :val")
                        .addParameter("val", v)
                        .executeAndFetchTable();

                for (Row row : table2.rows()) {
                    AListe element = new UnElement();
                    element.setId((int) row.getObject("id"));
                    element.setTitre((String) row.getObject("titre"));
                    element.setDescription((String) row.getObject("description"));
                    element.setDateCreation((Date) row.getObject("datecreation"));
                    element.setDateDerModif((Date) row.getObject("datedermodif"));
                    element.setId((int) row.getObject("id"));
                    list_e.add(element);
                }
                ((LaListe) l).getListe().addAll(list_e);
                ((LaListe) all).getListe().add(l);
                //l.setListElement(list_e);
            }
            return all;
        }
    }

    public static void updateListe(int id, String titre, String description, List<AListe> listElement){
        try(Connection con = sql2o.open()){
            con.createQuery("UPDATE LISTE SET titre = :titre, description = :description WHERE id = :id")
                    .addParameter("id", id)
                    .addParameter("titre", titre)
                    .addParameter("description", description)
                    .executeUpdate();
        }
    }
}
