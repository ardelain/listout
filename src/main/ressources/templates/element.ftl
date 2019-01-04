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
<div>
    td><a href=""><img src=""  /></a></td>
    <td>
        <#if liste_e??>
                <tr class="element">
                <td>
                <td><a href=""><img src="" /></a></td>
                <td>
                <div class="comment">
                <div class="message">
                <div class="author">
                <a class="titre" href="${liste_e[0].id}">${liste_e[0].titre}</a>
                <span class="date">${liste_e[0].dateCreation}</span>
                </div>
                <p class="content">
                ${liste_e[0].description}
                </p>
                </div>
                </div>
                </td>
            </td>
            <td width="30%">
            <td>
                <div class="zone" title="Etat"></div>
            </td>
            <form action="/listes/${liste_e[0].id}/add" method="post">
                    <td>
                        <button class="bouton1" title="Ajouter Element" >+</button>
                    </td>
                </form>
            <form action="/listes/${liste_e[0].id}/supp" method="post">
                    <td>
                        <button class="bouton2" title="Supprimer Element">-</button>
                    </td>
                </form>
            <form action="/listes/${liste_e[0].id}/modif" method="post">
                <td>
                    <button class="bouton3" title="Modifier Element">.</button>
                </td>
                </form>
                </td>
                </tr>
        </#if>
    </td>
    </td>
    <td width="30%">
    <td>
        <div class="zone" title="Etat"></div>
    </td>
    <td>
        <button class="bouton1" title="Ajouter Element">+</button>
    </td>
    <td>
        <button class="bouton2" title="Supprimer Element">-</button>
    </td>
    <td>
        <button class="bouton3" title="Modifier Element">.</button>
    </td>
    </td>
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
