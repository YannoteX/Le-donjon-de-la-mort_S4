# Le dongeon de la mort

## Principe

  Le jeu prend place lorsqu'un aventurier, fauché mais plein de rêve, s'engouffre dans un terrible dongeon pour sauver la princesse des griffes du redoutable dragon. Nombreux sont les periples qu'il devra affronter afin d'en venir à bout. Heureusement, vous aiderez l'aventurier à prendre les bonnes décisions afin de pouvoir mener notre héros à sa dulcinée... À moins que vous n'en décidiez autrement.
  
## Objectif

  En tant que joueur, vous devrez créer votre propre histoire pour terminer le jeu parmis les différentes fins proposées. Vous devrez, sur une succession d'évènements, choisir quelles actions réaliser parmis celles proposées. Le jeu adaptera, en fonction de vos décision, la fin la plus adaptée au style de jeu que vous avez choisis d'adopter.
  
## Mécaniques
  Le jeu consiste en une simple interface ou des cartes défileront les unes à la suite des autres aléatoirement. Elles sont accompagnés d'un texte descriptif, d'une image illustrative, et de plusieurs options, parfois accessibles sous certaines conditions (ouvrir un coffre que si clé possédée par exemple); que le joueur pourra choisir. Le joueur dispose aussi de points de vie, d'armure, d'un inventaire contenant des objets, et d'une bourse.
  Les différents evenements du jeu influerons sur ces statistiques tout le long de la partie. Des objets pourront aussi être récupérés. Les fins seront aussi sélectionnées par le jeu en fonction des choix du joueur.
 
# Ce qui suit n'est pas à inclure dans le cahier des charges
  
## Les fins

- La mort : le joueur meurt dans le donjon
- La fin heureuse : le joueur sauve la princesse du dragon
- La fin riche : Le joueur est devenu tellement fortuné qu'il n'a plus besoin de parourir le donjon
- La fin philantrope : Le joueur a acquis une réputation au sein du donjon et décide de créer une guilde
- La fin zombie : Le joueur devient une goule sous l'effet de la folie (parties trop longues)
- La fin psychopathe : Le joueur a tué beaucoup trop de survivants (il faudrait tuer la princesse après l'avoir sauvée du dragon pour la déclencher)

## Précisions techniques

Les événements sont définis dans un fichier JSON et s'enchaînent de manière semi-aléatoire.
On entend par semi-aléatoire que l'application choisira le prochain événement de manière aléatoire mais que si l'événement choisi demande que le personnage joué ai des caractéristiques qu'il n'a pas, l'événement n'est pas affiché et c'est un autre qui est pioché.
Prenons un exemple: on a un évènement "se battre contre le boss"  seulement pour cela  le personnage joué doit avoir ouvert la porte du boss : l'application ne va donc pas afficher cet événement tant que le joueur n'a pas ouvert la porte en question ( le fait que la porte soit ouverte ou non sera représenté par  une caractéristique du personnage joué). 
De la même manière on peut décider qu'au bout d'un certain nombre d'événements traversés, d'une certaine quantité d'argent ou d'un certain nombre d'ennemis tués les événements de fin apparaîtront (ces caractéristiques seraient traités de la même manière que la possession ou non d'une clé).

## User stories

- 1 : En tant que joueur, je dois pouvoir afficher le menu de démarrage du jeu lorsque celui-ci se lance.
- 2 : En tant que joueur, je dois pouvoir afficher le menu de pause à n'importe quel moment de la partie.
- 3 : En tant que joueur, je dois pouvoir recommencer une partie lorsque je le souhaite.
- 4 : En tant que joueur, je dois pouvoir identifier clairement et visuellement la situation de jeu dans laquelle je me trouve.
- 5 : En tant que joueur, je dois pouvoir sélectionner un choix afin d'avancer dans le jeu.
