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
<div class="liste">
    <br>
    <br>
    <label >Nouvelle Liste : </label>
    <br>
    <br>
    <form id="liste" method="post">
        <table class="table_addliste" >
            <tr >
                <td> <label for="titre">Titre</label></td>
                <td><input id="titre" name="titre" value="<#if liste_e??>${liste_e[0].titre}</#if>"/></input></td><br>
            <tr>
            </tr>
                <td><label for="description">Description</label></td>
                <td><textarea id="description" name="description" name="message" rows="10" cols="30"><#if liste_e??>${liste_e[0].description}</#if></textarea></td>
                <td><br></td>
            </tr>
        </table>
        <input type="submit" value="Ajouter"/>
    </form>

</div>
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
</body>
</html>
