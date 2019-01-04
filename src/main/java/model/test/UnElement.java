package model.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnElement extends AListe{

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String vals = "\n" + "</br>DateCreation: " + sdf.format(super.getDateCreation()) + "\n" +
                "</br>DateDerModif: " + sdf.format(super.getDateDerModif()) + "\n" +
                "</br>Titre: " + super.getTitre() + "\n" +
                "</br>Description: " + super.getDescription() + "\n" +
                "</br>";
        return vals;
    }
}
