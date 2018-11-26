package com.company;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import spark.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Scanner;
import static spark.Spark.*;

import junit.framework.Assert;
import spark.ModelAndView;
import freemarker.*;

import static spark.Spark.get;

public class Main { //ya des problemes avec spark sur des version de intelij, j'ai galere ^^ enfin bon j'ai ajouter les librairies a la main au final
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here
        System.out.println("**********************************************************************");
        System.out.println("**********************************************************************");
        System.out.println("\t\t\t\t\t\tLISTOUT DEMARRAGE :");
        System.out.println("**********************************************************************");
        System.out.println("**********************************************************************");


        /*----config---- :*/
        port(8080); // Spark will run on port 8080

        //Thread
        int maxThreads = 8;
        int minThreads = 2;
        int timeOutMillis = 30000;
        threadPool(maxThreads, minThreads, timeOutMillis);

        awaitInitialization(); // Wait for server to be initialized

        //SSL/HTTPS
        String keyStoreLocation = "deploy/keystore.jks";
        String keyStorePassword = "password";
        secure(keyStoreLocation, keyStorePassword, null, null);

        /*--------------*/
        get("/hello", (req, res) -> "Hello World");


        path("/test", () -> {
            //before("/*", (q, a) -> log.info("Received api call"));
            path("/1", () -> {
                get("", (request, response) -> {
                    return "Test 1 ";
                });
                get("/:name", (request, response) -> {
                    return "Test 1 Page: " + request.params(":name") + " inexistante.";
                });
            });
            path("/2", () -> {
                get("", (request, response) -> {
                    return "Test 2 ";
                });
                get("/:name", (request, response) -> {
                    return "Test 2 Page: " + request.params(":name") + " inexistante.";
                });
            });
            path("/username", () -> {
                //post("/add",       );
                //put("/change",     );
            });
        });


        get("/say/*/to/*", (request, response) -> {
            return "Number of splat parameters: " + request.splat().length;
        });


        get("/:name", (request, response) -> {
            return "Page: " + request.params(":name") + " inexistante.";
        });

        // redirect a POST to "/fromPath" to "/toPath", with status 303
        //redirect.post("/fromPath", "/toPath", Redirect.Status.SEE_OTHER);

        /*//If you use staticFiles.location()
        if (localhost) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }
        */
    }

    // declare this in a util-class
    /*
    // Create a data-model
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Product latest = new Product();
        latest.setUrl("products/greenmouse.html");
        latest.setName("green mouse");
        root.put("latestProduct", latest);
    */
    public static Template render( String templateName, String root) {//root = data model -> faire une classe data modele
        try{
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);

            // Specify the source where the template files come from. Here I set a
            // plain directory for it, but non-file-system sources are possible too:
            cfg.setDirectoryForTemplateLoading(new File("/Vue"));

            // Set the preferred charset template files are stored in. UTF-8 is
            // a good choice in most applications:
            cfg.setDefaultEncoding("UTF-8");

            // Sets how errors will appear.
            // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
            cfg.setLogTemplateExceptions(false);

            // Wrap unchecked exceptions thrown during template processing into TemplateException-s.
            cfg.setWrapUncheckedExceptions(true);

            Template temp = cfg.getTemplate(templateName);//"test.ftlh"

            if(root != null){//si il y a des données a ajouter
                Writer out = new OutputStreamWriter(System.out);
                temp.process(root, out);
            }
            return temp;
        }catch(Exception e){//IOException e,TemplateException te
            return null;
        }
    }

}
