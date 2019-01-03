package controleur;

import freemarker.template.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.internalServerError;

public class MainControleur {
    static Configuration configuration = new Configuration(Configuration.VERSION_2_3_19);

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        log4jConf.log.info("This is Logger Info");

        System.out.println("**********************************************************************\n**********************************************************************");
        System.out.println("\t\t\t\t\t\tLISTOUT DEMARRAGE :");
        System.out.println("**********************************************************************\n**********************************************************************");

        /*----config---- :*/
        port(8080); // Spark will run on port 8080
        //Thread
        int maxThreads = 2;
        int minThreads = 1;
        int timeOutMillis = 30000;
        //threadPool(maxThreads, minThreads, timeOutMillis);
        //Initialisation
//        awaitInitialization(); // Wait for server to be initialized
        //SSL/HTTPS
        //String keyStoreLocation = "deploy/keystore.jks";
        //String keyStorePassword = "password";
        //secure(keyStoreLocation, keyStorePassword, null, null);
        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        //staticFiles.expireTime(600); // ten minutes

        /*--------------*/

        configuration = new Configuration(Configuration.VERSION_2_3_19);//new Configuration(new Version(2, 3, 0));
        configuration.setDirectoryForTemplateLoading(new File("src/main/ressources"));//MainControleur.class, "/"//new File("src/main/ressources/")
        //configuration.setClassForTemplateLoading(MainControleur.class, "src/main/ressources");
        //configuration.setDefaultEncoding("UTF-8");
        //configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //configuration.setLogTemplateExceptions(false);

        externalStaticFileLocation("src/main/ressources");
        //staticFileLocation("/css");

        //staticFiles.externalLocation("/css/");
        //staticFiles.location("src/main/ressources/templates"); // css

        // Les routes :
        /*get("", (request, response) -> {
            //response.redirect("/accueil");
            return "!!";
        });*/
        path("/", () -> {
            get("", (request, response) -> {
                return "!";
            });
            //before("/*", (q, a) -> log.info("Received api call"));
            path("/accueil", () -> {
                Map<String, Object> model = new HashMap<>();
                get("", (request, response) -> {
                    return "accueil";
                });
                get("/test", (request, response) -> {
                    StringWriter writer = new StringWriter();
                    Map<String, Object> attributes = new HashMap<>();
                    try {
                        // TODO Auto-generated method stub
                        Template template = render("accueil.ftl", null);//configuration.getTemplate("accueil.ftl");//
                        String document = "accueil.ftl";
                        //template.process(document, writer);
                        template.dump(writer);
                        System.out.println(writer);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    return writer;
                });
                get("/hello", (request, response) -> {
                    TemplateHashModel templateHashModel;
                    StringWriter writer = new StringWriter();
                    Map<String, Object> attributes = new HashMap<>();
                    try {
                        // TODO Auto-generated method stub
                        Template helloTemplate = configuration.getTemplate("templates/accueil.ftl");//render("accueil.ftl", model);
                        String document = "accueil.ftl";
                        //helloTemplate.process(document, writer);
                        helloTemplate.dump(writer);
                        System.out.println(writer);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    return writer;

                });
                    /*return new ModelAndView(attributes, "src/public/accueil.ftl");
                }, new FreeMarkerEngine());*/
                /*get("/:name", (request, response) -> {

                    //return render("accueil.ftl", model);
                    //return "Test 1 Page: " + request.params(":name") + " inexistante.";
                });*/
            });
            path("/listes", () -> {
                get("", (request, response) -> {
                    StringWriter writer = new StringWriter();
                    try {
                        Template template = configuration.getTemplate("listes.ftl");//render("accueil.ftl", model);
                        template.dump(writer);
                        System.out.println(writer);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    return writer;
                    //return "Listes";
                });
                get("/:name", (request, response) -> {
                    return "Liste : " + request.params(":name") + " inexistante.";
                });
            });
            path("/info", () -> {
                get("", (request, response) -> {
                    StringWriter writer = new StringWriter();
                    try {
                        Template template = configuration.getTemplate("info.ftl");//render("accueil.ftl", model);
                        template.dump(writer);
                        System.out.println(writer);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    return writer;
                    //return "info";
                });
            });
            get("/:name", (request, response) -> {
                return "Page: " + request.params(":name") + " inexistante.";
            });
        });

        // a integrer apres le choix de template
        /*get("/:name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "path-to-template")
            );
        });*/

        //faire page erreur ................................................................
        // gerer l'err 404
        notFound((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Custom 404\"}";
        });

        // gerer l'err  500
        internalServerError((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Custom 500 handling\"}";
        });
    }


    //pas encore utile
    public static Template render(String templateName, Map<String, Object> root) {//root = data model -> faire une classe data modele
        try{
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_19);
            // Specify the source where the template files come from. Here I set a
            // plain directory for it, but non-file-system sources are possible too:
            cfg.setDirectoryForTemplateLoading(new File("src/public"));

            // Set the preferred charset template files are stored in. UTF-8 is
            // a good choice in most applications:
            cfg.setDefaultEncoding("UTF-8");

            // Sets how errors will appear.
            // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
            cfg.setLogTemplateExceptions(false);

            // Wrap unchecked exceptions thrown during template processing into TemplateException-s.
            //cfg.setWrapUncheckedExceptions(true);

            Template temp = cfg.getTemplate(templateName);//"test.ftlh"
            Writer out = new OutputStreamWriter(System.out);
            if(root != null){//si il y a des données a ajouter
                temp.process(root, out);
            }else{
                //temp.dump(out);
            }
            return temp;
        }catch(Exception e){//IOException e,TemplateException te
            return null;
        }
    }

}