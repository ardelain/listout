<#include "header.ftl"/>
<r>
    <main>
        <div class="recherche">
            <div class="sectionRecherche">
                <br>
                <form action="/listes/recherche" method="get" class="barRecherche">
                    <input type="text" name="search" placeholder="Search..">
                    <input type="submit" value="Go!">
                </form>
                <br>
            </div>
        </div>
    </main>
</r>
<add>
    <#if !liste_e_fils??>
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
            <form action="/listes/${liste_e[0].id?replace(",","")}/add" method="get">
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
                    <tr class="element" id="${e.id?replace(",","")}" onclick="SelectLigne(this)">
                        <td>
                        <td><a href=""><img src="" /></a></td>
                        <td>
                            <div class="comment" >
                                <div class="message">
                                    <div class="author">
                                        <a class="titre" href="${e.id?replace(",","")}">${e.titre}</a>
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
                            <div class="zone" title="Etat"  href="${e.id?replace(",","")}"></div>
                        </td>
                        <form action="/listes/${e.id?replace(",","")}/add" method="get">
                            <td>
                                <button class="bouton1" title="Ajouter Element" >+</button>
                            </td>
                        </form>
                        <form action="/listes/${e.id?replace(",","")}/sup" method="get">
                            <td>
                                <button class="bouton2" title="Supprimer Element">-</button>
                            </td>
                        </form>
                            <form action="/listes/${e.id?replace(",","")}/modif" method="get">
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
        <table class="table_liste_fils">
            <tbody>
            <#if liste_e_fils??>
                <#list liste_e_fils as e>
                    <tr class="element" id="${e.id?replace(",","")}" onclick="SelectLigne(this)">
                    <td>
                    <td><a href=""><img src="" /></a></td>
                    <td>
                    <div class="comment">
                    <div class="message">
                    <div class="author">
                    <a class="titre" href="${e.id?replace(",","")}">${e.titre}</a>
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
                <form action="/listes/${e.id?replace(",","")}/add" method="get">
                        <td>
                            <button class="bouton1" title="Ajouter Element" >+</button>
                        </td>
                    </form>
                <form action="/listes/${e.id?replace(",","")}/sup" method="get">
                        <td>
                            <button class="bouton2" title="Supprimer Element">-</button>
                        </td>
                    </form>
                <form action="/listes/${e.id?replace(",","")}/modif" method="get">
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
    function SelectLigne(obj)
    {
        var idLigne=obj.id;
        location.replace("/listes/"+idLigne);
    }
</script>
</body>
</html>
