package DAO;

import autre.log4jConf;
import model.AListe;
import model.UnElement;
import org.apache.log4j.Priority;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class UnSql2oModel {//MANAGER DAO ?
    //
    private static final Logger LOGGER = Logger.getLogger(UnSql2oModel.class.getName());
    //
    private static Sql2o sql2o;

    /**
     *
     * @param ds
     */
    public UnSql2oModel(DataSource ds){
        this.sql2o = new Sql2o(ds);
        //this.sql2o = sql2o;
    }

    /**
     *
     * @param table
     */
    public static void dropTable(String table){
        try(Connection con = sql2o.open()){
            con.createQuery("DROP TABLE "+ table).executeUpdate();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
            //log4jConf.log.log(org.apache.log4j.Priority.,"PB DELETE : {0}",e);
        }
    }

    /**
     *
     */
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
                    ");").executeUpdate();//FOREIGN KEY ( idListe ) REFERENCES ELEMENT ( id )
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
        }
    }

    /**
     * table permettant de créer une relation de hierarchie entre les elements
     */
    public static void createTablePossede(){
        try(Connection con = sql2o.open()){
            con.createQuery("CREATE TABLE POSSEDE " +
                    "(id INTEGER not NULL, " +
                    "idListe INTEGER not NULL, " +
                    "PRIMARY KEY ( id ), " +
                    "FOREIGN KEY ( idListe ) REFERENCES ELEMENT ( id ),"+
                    "FOREIGN KEY ( id ) REFERENCES ELEMENT ( id ));").executeUpdate();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
        }
    }

    /**
     *
     * @param id
     * @param idListe
     * @param dateCreation
     * @param dateDerModif
     * @param titre
     * @param description
     * @return
     */
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
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
            return -1;
        }
    }

    /**
     *
     * @param id
     * @param idListe
     * @return
     */
    public static int insertTablePossede(int id, int idListe){
        try(Connection con = sql2o.open()){
            con.createQuery("INSERT INTO POSSEDE(id, idListe) VALUES (:id, :idListe)")
                    .addParameter("id", id)
                    .addParameter("idListe", idListe)
                    .executeUpdate();
            return idListe;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
            return -1;
        }
    }

    /**
     *
     * @param val
     */
    public static void deleteElement(int val){
        //recuperation des fils
        List<AListe> list_e = new LinkedList<>();
        try(Connection con = sql2o.open()){
            Table table = con.createQuery("SELECT * FROM POSSEDE WHERE ELEMENT.idliste = :val").addParameter("val", val).executeAndFetchTable();
            for (Row row : table.rows()) {
                AListe element = new UnElement();
                element.setId((int) row.getObject("id"));
            }
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
        }
        //suppression des fils
        for (AListe a : list_e) {
            try(Connection con = sql2o.open()){
                con.createQuery("DELETE FROM ELEMENT WHERE ELEMENT.id = :val").addParameter("val", a.getId()).executeUpdate();
            }
        }
        //suppression des element de la table possede (le lien avec les fils)
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM POSSEDE WHERE POSSEDE.idliste = :val or POSSEDE.id = :val").addParameter("val", val).executeUpdate();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
        }
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM ELEMENT WHERE ELEMENT.id = :val").addParameter("val", val).executeUpdate();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
        }

    }

    /**
     *
     * @return
     */
    public static List<AListe> getAllElement(){
        try(Connection con = sql2o.open()){
            Table table = con.createQuery("SELECT * FROM ELEMENT").executeAndFetchTable();
            List<AListe> list_e = new LinkedList<>();
            for (Row row : table.rows()) {
                AListe element = new UnElement();
                element.setId((int) row.getObject("id"));
                element.setTitre((String) row.getObject("titre"));
                element.setDescription((String) row.getObject("description"));
                element.setDateCreation((Date) row.getObject("datecreation"));
                element.setDateDerModif((Date) row.getObject("datedermodif"));
                list_e.add(element);
            }
            return list_e;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
            return null;
        }
    }

    /**
     *
     * @param val
     * @return
     */
    public static List<Integer> getAllPossede(int val){
        try(Connection con = sql2o.open()){
            List<Integer> li = new ArrayList<>();
            Table table = con.createQuery("SELECT * FROM POSSEDE WHERE idliste = :val").addParameter("val", val).executeAndFetchTable();
            List<AListe> list_e = new LinkedList<>();
            for (Row row : table.rows()) {
                li.add((int) row.getObject("id"));
            }
            return li;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
            return null;
        }
    }

    /**
     *
     * @param val
     * @return
     */
    public static List<Integer> getAllPossedant(int val){
        try(Connection con = sql2o.open()){
            List<Integer> li = new ArrayList<>();
            Table table = con.createQuery("SELECT * FROM POSSEDE WHERE id = :val").addParameter("val", val).executeAndFetchTable();
            List<AListe> list_e = new LinkedList<>();
            for (Row row : table.rows()) {
                li.add((int) row.getObject("idListe"));
            }
            return li;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
            return null;
        }
    }

    /**
     *
     * @param val
     * @return
     */
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
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
            return null;
        }
    }

    public static List<AListe> recherche(String val){
        try(Connection con = sql2o.open()){
            Table table = con.createQuery("SELECT * FROM ELEMENT where titre REGEXP :val or  description REGEXP :val").addParameter("val", val).executeAndFetchTable();
            List<AListe> list_e = new LinkedList<>();
            for (Row row : table.rows()) {
                AListe element = new UnElement();
                element.setId((int) row.getObject("id"));
                element.setTitre((String) row.getObject("titre"));
                element.setDescription((String) row.getObject("description"));
                element.setDateCreation((Date) row.getObject("datecreation"));
                element.setDateDerModif((Date) row.getObject("datedermodif"));
                list_e.add(element);
            }
            return list_e;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
            return null;
        }
    }

    /**
     *
     * @param id
     * @param idListe
     * @param dateCreation
     * @param dateDerModif
     * @param titre
     * @param description
     */
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
        }catch(Exception e){
            LOGGER.log(Level.SEVERE," {0}",e);
        }
    }
}
