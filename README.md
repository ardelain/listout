# listout
"Tout pour faire des Listes."
site de liste en java (11) / spark / freemarker / h2 / sql2o sur intelliJ IDEA

</br>
<img src="/src/main/ressources/image/todoliste.png" alt="My cool logo"/>
</br>

(mais pas fini)

Licence publique générale GNU (GPL3)

listout

"Tout pour faire des Listes." site de liste en java (11) / spark / freemarker / h2 / sql2o sur intelliJ IDEA

Action : -recherche en rapport au titre ou a la description de n'importe quel élément
	 -création de liste avec un titre et une decription
	 -ajout d'element avec un titre et une decription (qui peuvent a leur tour devenir des listes)

src/main/</br>
 	&nbsp;java/</br>
		autre/</br>
			log4jConf.java : log configuration du serveur spark</br>
   controleur/</br>
			MainController.java : Gère l'ensemble des routes du serveur web et effectuer les actions necessaire en consequence (a simplifier avec Classe intermediaire pour classer les différentes actions)</br>
		DAO/</br>
			UnSql2oModele.java : Ensemble des requetes sql de création de table et d'insertion/suppression/ajout d'élement dans ces tables</br>
		main/</br>
			Main.java : Initialistation de la base de donnée (h2/Sql2oCréation), des tables, et des éléments testes 
		model/</br>
			AListe.java : Element mere du patron de conception composite</br>
			LaListe.java : Element Fille "Liste" du patron de conception composite</br>
			Tag.java : tag est un element string (qui pourra se complexifier)</br>
			UnElement.java :  Element Fille "atomique" du patron de conception composite, possedant une liste de tag</br>
 </br>     
 </br>
 ressources/ : ensemble des éléments partagés</br>
		css/</br>
			style.css : ensemble du style de toutes les pages du sites (a fractionner)</br>
		image/ </br>
			abr.png : logo du site</br>
			connexion.png : logo de connexion</br>
			todoliste.png : image d'acceuil</br>
		templates/</br>
			accueil.ftl : page d'acceuil</br>
			ajoutlist.ftl : page d'ajout/modification de Liste/Element</br>
			connexion.ftl : page de connexion (non mise en place)</br>
			footer.ftl : footer du site integre dans chaque page avec un #include</br>
			header.ftl : header du site integre dans chaque page avec un #include</br>
			info.ftl : page d'information / contact (mails,...)</br>
			listes.ftl : page de recherche, d'affichage des listes/elements</br>
</br>
   test/ : test graphique</br>
			tagsinput.css : code css de boostrap  pour les listes de tag</br>
			tagsinput.min.js : code javascript de boostrap  pour les listes de tag</br>
</br>
</br>
Base de donnée</br>
</br>
   TABLE ELEMENT -> ensemble des element liste ou "fueille" de liste</br>
                    id INTEGER not NULL -> identifiant de l'element (généré par aléatoirement)</br>
                    idListe INTEGER not NULL -> inutile (cas d'un seul element pere)</br>
                    dateCreation DATE -> Date de création de l'element</br>
                    dateDerModif DATE -> Derniere date modification de l'element</br>
                    titre VARCHAR(255) -> (taille arbitraire)</br>
                    description VARCHAR(255) -> description en elle meme (taille arbitraire)</br>
                    PRIMARY KEY ( id ) -> id unique</br>
</br>
 TABLE POSSEDE : table permettant de créer des relation de hierarchie entre les elements</br>
                    id INTEGER not NULL -> identifiant d'un ELEMENT FILS</br>
                    idListe INTEGER not NULL -> identifiant d'un ELEMENT PERE</br>
                    PRIMARY KEY ( id ) -> </br>
                    FOREIGN KEY ( idListe ) REFERENCES ELEMENT ( id ) -> en reference a ELEMENT.id</br>
                    FOREIGN KEY ( id ) REFERENCES ELEMENT ( id ) -> en reference a ELEMENT.id</br>
</br>
 TABLE TAG : table permettant d'établir une liste de tag pour un element donné</br>
                    id INTEGER not NULL -> identifiant d'un ELEMENT</br>
                    tag VARCHAR(255) -> tag en lui meme (taille arbitraire)</br>
                    FOREIGN KEY ( id ) REFERENCES ELEMENT ( id ) -> en reference a ELEMENT.id</br>



Injection dans les formulaire non géré; Element dans une autre Liste non géré;

