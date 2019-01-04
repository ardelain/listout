package controleur;

import freemarker.template.*;
import model.Composant;
import model.Element;
import model.ListeComposite;
import model.Sql2oModel;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.internalServerError;

public class MainControleur {
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_19);
    Sql2oModel model;
    ListeComposite l;
    List<Element> list_e ;

    public MainControleur(Sql2oModel model, ListeComposite l,List<Element> list_e) {
        this.model = model;
        this.list_e = list_e;//model.getAllElement();
        this.l = l;//model.getListeComposite(1);
    }


    public void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        log4jConf.log.info("This is Logger Info");

        System.out.println("**********************************************************************\n**********************************************************************");
        System.out.println("\t\t\t\t\t\tLISTOUT DEMARRAGE :");
        System.out.println("**********************************************************************\n**********************************************************************");

        /*----config---- :*/
        port(8080); // Spark will run on port 8080
        //Thread
        int maxThreads = 10;
        int minThreads = 1;
        int timeOutMillis = 30000;
        threadPool(maxThreads, minThreads, timeOutMillis);
        //Initialisation
        //awaitInitialization(); // Wait for server to be initialized
        //SSL/HTTPS
        //String keyStoreLocation = "javax.net.ssl.keyStore";//"deploy/keystore.jks";
        //String keyStorePassword = "password";
        //secure(keyStoreLocation, keyStorePassword, null, null);

        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        staticFiles.expireTime(600); // ten minutes

        /*--------------*/

        configuration = new Configuration(Configuration.VERSION_2_3_19);//new Configuration(new Version(2, 3, 0));
        configuration.setDirectoryForTemplateLoading(new File("src/main/ressources"));//MainControleur.class, "/"//new File("src/main/ressources/")
        //configuration.setClassForTemplateLoading(MainControleur.class, "src/main/ressources");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);

        externalStaticFileLocation("src/main/ressources");
        //staticFileLocation("/css");
        //staticFiles.externalLocation("/css/");
        //staticFiles.location("src/main/ressources/templates"); // css

        // Les routes :
        /*get("", (request, response) -> {
            //response.redirect("/accueil");
            return "!!";
        });*/
        //before("/*", (q, a) -> log.info("Received api call"));

        path("/", () -> {
            get("/accueil", (request, response) -> {
                StringWriter writer = new StringWriter();
                Map<String, Object> attributes = new HashMap<>();
                try {
                    // TODO Auto-generated method stub
                    //Template template = render("templates/accueil.ftl", null);//configuration.getTemplate("accueil.ftl");//
                    Template template = configuration.getTemplate("templates/accueil.ftl");
                    //template.process(document, writer);
                    template.process(null, writer);
                    //template.dump(writer);
                    System.out.println(writer);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                return writer;
            });
            get("/connexion", (request, response) -> {
                StringWriter writer = new StringWriter();
                try {

                    Map<String, Integer> params = new HashMap<>();
                    List<Element> le = model.getAllElement();
                    params.put("isIncription", 0);

                    Template template = configuration.getTemplate("templates/connexion.ftl");
                    template.process(params, writer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return writer;
            });
            post("/connexion", (request, response) -> {
                String email = request.queryParams("email");
                String mdp = request.queryParams("mdp");
                String isInscription = request.queryParams("isIncription");
                if(email != null || mdp != null){
                    if(Integer.parseInt(isInscription) == 0){
                        //inscription
                        //model.insertTableElement(l.getListElement().size()+1,)
                    }else{
                        //connection
                    }
                }else{
                    response.redirect("/listes/add");
                }
                response.redirect("/listes/all");
                return "!";
            });
               /*return new ModelAndView(attributes, "src/public/accueil.ftl");
                }, new FreeMarkerEngine());*/
                /*get("/:name", (request, response) -> {

                    //return render("accueil.ftl", model);
                    //return "Test 1 Page: " + request.params(":name") + " inexistante.";
                });*/


            path("/listes", () -> {
                get("", (request, response) -> {
                    StringWriter writer = new StringWriter();
                    try {
                        Template template = configuration.getTemplate("templates/listes.ftl");//render("accueil.ftl", model);
                        //template.dump(writer);
                        template.process(null, writer);
                        System.out.println(writer);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    return writer;
                    //return "Liste : " + request.params(":name") + " inexistante.";
                });
                get("/add", (request, response) -> {
                    StringWriter writer = new StringWriter();
                    try {
                        Template template = configuration.getTemplate("templates/ajoutlist.ftl");//render("accueil.ftl", model);
                        template.process(null, writer);
                        System.out.println(writer);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    return writer;
                });
                post("/add", (request, response) -> {
                    String titre = request.queryParams("titre");
                    String description = request.queryParams("description");
                    String id = request.queryParams("id");
                    if(titre != null || description != null){
                        if(id == null){
                            //ajout
                            //model.insertTableElement(l.getListElement().size()+1,)
                        }else{
                            //modification
                        }
                    }else{
                        response.redirect("/listes/add");
                    }
                    response.redirect("/listes");
                    return "!";
                });
                get("/all", (request, response) -> {
                    StringWriter writer = new StringWriter();
                    Map<String, List<Element>> params = new HashMap<>();
                    List<Element> le = model.getAllElement();
                    params.put("liste_e", le);
                    try {
                        Template template = configuration.getTemplate("templates/listes.ftl");//render("accueil.ftl", model);
                        template.process(params, writer);
                        System.out.println(writer);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    return writer;
                });

                //..........................................probleme css/ chargement page -> la regardeger (affiche sans le css ...?)
                path("/:name", () -> {
                    get("", (request, response) -> {
                        StringWriter writer = new StringWriter();
                        int i = -3;i = Integer.parseInt(request.params(":name"));//request.params(":name")
                        Element ee;ee = model.getElement(i);
                        Map<String, List<Element>> params = new HashMap<>();
                        List<Element> le = new ArrayList<>();le.add(ee);
                        params.put("liste_e", le);
                        try {
                            Template template = configuration.getTemplate("templates/listes.ftl");//render("accueil.ftl", model);
                            template.process(params, writer);
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                        return writer;
                    });
                    path("/modif", () -> {
                        get("", (request, response) -> {
                            //response.type("text/html");
                            StringWriter writer = new StringWriter();
                            int i = -3;i = Integer.parseInt(request.params(":name"));//request.splat()[0]
                            System.out.println("iii "+i);
                            Element ee;ee = model.getElement(i);

                            Map<String, List<Element>> params = new HashMap<>();
                            List<Element> le = new ArrayList<>();le.add(ee);
                            params.put("liste_e", le);
                            try {
                                Template template = configuration.getTemplate("templates/ajoutlist.ftl");//render("accueil.ftl", model);
                                template.process(params, writer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return writer;
                        });
                    });

                    get("/supp", (request, response) -> {
                        //request.params(":name")
                        return "Liste supp: " + request.params(":name") + " inexistante.";
                    });
                    get("/:name", (request, response) -> {
                        //request.params(":name")
                        return "Liste name: " + request.params(":name") + " inexistante.";
                    });
                });

            });
            get("/info", (request, response) -> {
                StringWriter writer = new StringWriter();
                try {
                    Template template = configuration.getTemplate("templates/info.ftl");//render("accueil.ftl", model);
                    template.process(null, writer);
                    System.out.println(writer);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                return writer;
                //return "info";
            });
            model.getListeComposite(1);
            final String[] vals3 = {""};
            vals3[0] += l;
            String finalVals3 = vals3[0];
            get("/all", (req, res) -> finalVals3);

            //?.????....................................................................utile?
            get("/:name", (request, response) -> {
                return "Page: " + request.params(":name") + " inexistante.";
            });

        });

        get("/:name", (request, response) -> {
            //request.params(":name")
            return "dada : " + request.params(":name") + " inexistante.";
        });

        // a integrer apres le choix de template
        /*get("/:name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "path-to-template")
            );
        });*/

        //faire page erreur ......................................................................!!
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