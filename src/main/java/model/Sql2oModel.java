package model;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.xml.bind.Element;
import java.util.List;

public class Sql2oModel implements Element {
    private static Sql2o sql2o;

    public Sql2oModel(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    public static List<Element> getAllElement(){
        try(Connection con = sql2o.open()){
            List<Element> list_e = con.createQuery("SELECT * FROM ELEMENT").executeAndFetch(Element.class);
            return list_e;
        }
    }
}
