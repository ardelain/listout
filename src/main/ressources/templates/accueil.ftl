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
				<a href="">Acceuil</a>

			</li>
			<li>
				<a href="">Listes</a>
			</li>
		
			<li>
				<a href="">Info</a>
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
	<listes>
		<table class="table_liste">
			<tbody>
					<tr class="element">
						<td>
							<td><a href=""><img src=""  /></a></td>
							<td>
								<div class="comment">
									<div class="message">
										<div class="author">
											<a class="titre" href="">Le titre</a>
											<span class="date">- 01/12/2017</span>
										</div>
										<p class="content">
											...text...
										</p>
									</div>
								</div>
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
					</tr>
			</tbody>
		</table>
	</listes>
		<br>
		<br>
		<br>
		<footer>
			<p><br>
				<br>
				Arthur DELAIN & Floran CHAZELAS
				<br>
				Nous suivre :</p>
		</footer>
</body>
</html>
