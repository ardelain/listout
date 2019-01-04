<!doctype html>
<html lang="fr">
<head>
    <link  href="../css/style.css" rel="stylesheet">
    <meta charset="UTF-8"/>
    <title>Listout</title>
</head>
<body>
<nav>
    <ul>
        <li>
            <a href="/accueil">Acceuil</a>

        </li>
        <li>
            <a href="/listes">Listes</a>
        </li>

        <li>
            <a href="/info">Info</a>
        </li>
    </ul>
</nav>
<r>
    <main>
        <div class="recherche">
            <div class="sectionRecherche">
                <br>
                <form class="barRecherche">
                    <input type="text" name="search" placeholder="Search..">
                    <input type="submit" value="Go!">
                </form>
                <br>
                <a id="haut" href="modele.php"> voir un modele</a>
            </div>
        </div>
    </main>
</r>
<add>
    <div class="boutonaddl">
        <button  class="bouton" title="Ajouter Liste" onclick="add()">+</button>
    </div>
    <br>
    <br>
    <#if !liste_e??>
    <div class="boutonall">
        <button  class="bouton" title="Afficher toutes les listes" onclick="alll()">all</button>
    </div>
    </#if>
</add>
<br>
<listes>
    <table class="table_liste">
        <tbody>
        <#if liste_e??>
            <#list liste_e as e>
                <tr class="element">
                    <td>
                    <td><a href=""><img src="" /></a></td>
                    <td>
                        <div class="comment">
                            <div class="message">
                                <div class="author">
                                    <a class="titre" href="${e.id}">${e.titre}</a>
                                    <span class="date">${e.dateCreation}</span>
                                </div>
                                <p class="content">
                                         ${e.description}
                                </p>
                            </div>
                        </div>
                    </td>
                    </td>
                    <td width="30%">
                    <td>
                        <div class="zone" title="Etat"></div>
                    </td>
                    <form action="/listes/${e.id}/add" method="post">
                        <td>
                            <button class="bouton1" title="Ajouter Element" >+</button>
                        </td>
                    </form>
                    <form action="/listes/${e.id}/supp" method="post">
                        <td>
                            <button class="bouton2" title="Supprimer Element">-</button>
                        </td>
                    </form>
                        <form action="/listes/${e.id}/modif" method="post">
                        <td>
                            <button class="bouton3" title="Modifier Element">.</button>
                        </td>
                    </form>
                    </td>
                </tr>
            <#else>
            </#list>
        </#if>
        </table>
        <table class="table_liste_fils">
        <#if liste_e_fils??>
            <#list liste_e_fils as e>
                <tr class="element_fils">
                <td>
                <td><a href=""><img src="" /></a></td>
                <td>
                <div class="comment">
                <div class="message">
                <div class="author">
                <a class="titre" href="${e.id}">${e.titre}</a>
                <span class="date">${e.dateCreation}</span>
                </div>
                <p class="content">
                ${e.description}
                </p>
                </div>
                </div>
                </td>
            </td>
            <td width="30%">
            <td>
                <div class="zone" title="Etat"></div>
            </td>
            <form action="/listes/${e.id}/add" method="post">
                    <td>
                        <button class="bouton1" title="Ajouter Element" >+</button>
                    </td>
                </form>
            <form action="/listes/${e.id}/supp" method="post">
                    <td>
                        <button class="bouton2" title="Supprimer Element">-</button>
                    </td>
                </form>
            <form action="/listes/${e.id}/modif" method="post">
                <td>
                    <button class="bouton3" title="Modifier Element">.</button>
                </td>
                </form>
                </td>
                </tr>
            <#else>
            </#list>
        </#if>
        </tbody>

    </table>
</listes>
<br>
<br>
<br>
<footer>
    <p><br>
        Arthur DELAIN & Floran CHAZELAS
        <br>
        <br>
    </p>
</footer>

<script>
    function myFunction() {
        //<p id="demo"></p>
        document.getElementById("demo").innerHTML = "Hello World";
    }
    function add() {
        location.replace("/listes/add")
    }
    function alll() {
        location.replace("/listes/all")
    }
</script>
</body>
</html>
