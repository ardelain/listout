package controleur;

import static spark.Spark.*;

public class MainControleur {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }


}