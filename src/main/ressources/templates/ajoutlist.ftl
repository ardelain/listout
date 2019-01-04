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
        <#if liste_e??>
            <table class="table_addliste" >
            <tr >
            <td> <label for="titre">Titre</label></td>
            <td><input id="titre" name="titre" value="${liste_e[0].titre}"/></input></td><br>
        <tr>
        </tr>
        <td><label for="description">Description</label></td>
            <td><textarea id="description" name="description" name="message" rows="10" cols="30">${liste_e[0].description}</textarea></td>
            <td><br></td>
            </tr>
            </table>
        <#else >
            <table class="table_addliste" >
            <tr >
            <td> <label for="titre">Titre</label></td>
            <td><input id="titre" name="titre" "/></input></td><br>
            <tr>
            </tr>
            <td><label for="description">Description</label></td>
            <td><textarea id="description" name="description" name="message" rows="10" cols="30"></textarea></td>
            <td><br></td>
            </tr>
            </table>
        </#if>

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
