<#include "header.ftl"/>

<div class="liste">
    <br>
    <br>
    <#if liste_e??>
        <#if liste_e_pere??>
            <label >Nouvel element : </label>
        <#else>
            <label >Modifier Liste/Element :</label>
        </#if>
    <#else>
        <#if liste_e_pere??>
            <label >Nouvel element : </label>
        <#else>
            <label >Nouvelle Liste : </label>
        </#if>
    </#if>
    <br>
    <br>
    <form id="liste" method="post">
        <#if liste_e?? && !liste_e_pere??>

            <table class="table_addliste" >
            <tr >
            <td> <label for="titre">Titre</label></td>
            <td><input id="titre" name="titre" required=true value="${liste_e[0].titre}"/></input></td><br>
        <tr>
        </tr>
        <td><label for="description">Description</label></td>
            <td><textarea id="description" name="description" required=true name="message" rows="10" cols="30">${liste_e[0].description}</textarea></td>
            <td><br></td>
            </tr>
            </table>
        <#else >
            <#if liste_e_pere??>
                <input style="display:none;" id="idd" name="idd" value="${liste_e_pere[0].id}"/></input>
            </#if>
                <table class="table_addliste" >
                <tr >
                <td> <label for="titre">Titre</label></td>
                <td><input id="titre" name="titre" required=true /></input></td><br>
                <tr>
                </tr>
                <td><label for="description">Description</label></td>
                <td><textarea id="description" required=true  name="description" name="message" rows="10" cols="30"></textarea></td>
                <td><br></td>
                </tr>
                </table>
        </#if>
        <#if liste_e?? && !liste_e_pere??>
            <input type="submit" value="Modifier"/>
        <#else>
            <input type="submit" value="Ajouter"/>
        </#if>
    </form>

</div>
<br>
<br>
<br>
<#include "footer.ftl"/>
</body>
</html>
