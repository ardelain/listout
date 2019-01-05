<#include "header.ftl"/>
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
            </div>
        </div>
    </main>
</r>
<add>
    <#if !table_liste_fils??>
        <div class="boutonaddl">
            <x>Listes :</x>
            <form action="/listes/add" method="get">
                <button  class="bouton" title="Ajouter Liste element" ">+</button>
            </form>
        </div>
        <br>
        <br>
    <#else>
        <div class="boutonaddl">
             <x>Liste :</x>
            <form action="/listes/${liste_e[0].id}/add" method="get">
                <button  class="bouton" title="Ajouter Liste element" ">+</button>
            </form>
        </div>
        <br>
        <br>
    </#if>
    <#if !liste_e??>
    <div class="boutonall">
        <form action="/listes/all" method="get">
            <button  class="bouton" title="Afficher toutes les listes" ">toutes</button>
        </form>
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
                    <form action="/listes/${e.id}/add" method="get">
                        <td>
                            <button class="bouton1" title="Ajouter Element" >+</button>
                        </td>
                    </form>
                    <form action="/listes/${e.id}/supp" method="get">
                        <td>
                            <button class="bouton2" title="Supprimer Element">-</button>
                        </td>
                    </form>
                        <form action="/listes/${e.id}/modif" method="get">
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
            <form action="/listes/${e.id}/add" method="get">
                    <td>
                        <button class="bouton1" title="Ajouter Element" >+</button>
                    </td>
                </form>
            <form action="/listes/${e.id}/supp" method="get">
                    <td>
                        <button class="bouton2" title="Supprimer Element">-</button>
                    </td>
                </form>
            <form action="/listes/${e.id}/modif" method="get">
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
<#include "footer.ftl"/>
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
