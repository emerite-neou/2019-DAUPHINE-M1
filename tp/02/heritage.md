# Boiler Plate Log
Pour debuger du code, c'est sympa d'avoir des println un peu partout.
Le soucis avec les println c'est que quand on n'en a pas besoin c'est de la pollution.
On souhaite donc écrire une class permettant de faire des affichages configurable 
(qu'on peut activer ou désactiver sans recompiler le code).
On décide de crée un class contenant une méthode log qui permet de faire des affichages (si activé).

1. Créer une class Logger avec un champ indiquant si la log doit être active ou non. Votre méthode log sera static pour le moment.
Initialisé ce champs n'est pas le point important de l'exo, donc vous avez deux choix :
   1. Lire un fichier (avec un nom fixé) indiquant si les log sont actives ou pas, c'est la meilleur solution car il suffit de modifier le fichier pour activer/désactiver les log (pas besoin de recompiler votre code). 
   2. Lui donner une valeur a la main `this.isActive = true`. Cela implique que durant vos tests vous allez devoir modifier cette valeur puis recompiler.
   
Toutes les class des projets doivent pouvoir utiliser la méthode log, on aimerait ajouter le nom de la class appelante à chaque log.
Une solution possible serait de jouer avec l'argument de la méthode log : `Logger.log("MyClass ceci est une log")`, 
mais cela demande une action de l'utilisateur et MyClass est une valeur "magique". 
1. Que fait la méthode getClass de la class Object?
2. Modifiez votre méthode log pour prendre un Object et un String, 
la méthode doit afficher la class (durant l'execution) de l'objet suivit du String. exemple d'utilisation:
```java
public class MyClass {
...
   Logger.log(this, "ceci est une log")
...
}
```

Vous avez toujours le problème que c'est l'utilisateur qui doit travailler (il doit toujours ajouter this comme argument!)
1. Comment faire pour qu'une class ait la methode log en temps que class interne
   - *indice: quelle est le nom du tp?*
2. Implementez et testez votre solution


# Carte du Monde
On veut créer une carte numérique. Vous êtes chargé de la gestion des villes.

1. Créer une class Ville composé d'un nom et de coordonées GPS. L'affichage de la ville `Ville v = new Ville("PARIS", 48.85, 2.34)` doit être `[ PARIS, (48.85 N, 2.34 O)]`
2. Tester votre code en créant une liste de villes et en affichant chaque ville. 
3. Toutes les villes ne sont pas intéressante, 
créer la class VilleInteressante qui contient les même champs que la class Ville 
mais ajoute aussi le champs meilleurPointdInteret.
L'affichage de `Ville v = new Ville("PARIS", 48.85, 2.34, "TOUR EIFFEL")` doit être `[ PARIS, (48.85 N, 2.34 O), TOUR EIFFEL]`

Votre code actuel a deux problèmes: Il y a beaucoup de code qui se ressemble 
et il n'est pas possible d'avoir une liste qui mélange les objets Ville et les VillesInteressante.
1. Modifier votre code pour VilleInteressante hérite de Ville. Les affichages doivent fonctionner correctement.

Nouveaux problèmes: vos fonctions toString sont toujours très semblable et ___on n'utilise pas l'héritage pour éviter la duplication de code !___. L'héritage doit être utiliser pour spécifié un comportement (dans notre cas les affichages).
1. Ajouter à la class Ville et VilleInteressante la fonction getMeilleurPointdInteret.
2. Modifier la manière dont l'affichage est fait pour utiliser cette fonction. Que devient l'affichage dans VilleInteressante?

# Distributeur de Canette
Vous êtes le développeur d'une entreprise qui vend/loue des distributeurs de Canette. Vous êtes chargé de créer un outil gérant le stock d'un distributeur.

### Début de l'App

1. Créer une class Canette identifiée par son nom, une classe Bac qui permet de stocker 10 Canettes au maximum et une classe Distributeur constituée de deux bacs.
On doit pouvoir ajouter une canette à un distributeur via une méthode add en choisissant le bac.
   - *pas de valeur magique svp*
   - *Attention à comment vous gérez le nombre max pour la class Bac, chaque instance de Bac doivent prendre le moins de place possible* 
2. Créer toutes les méthodes toString permettant d'afficher le contenu d'un distributeur. 

### Format de Canette
Mauvaise nouvelle, un nouveau format de canette vient d'apparaitre sur le marché: des canettes longues (à la différence des larges). 

1. Ajouter un champ à votre class Canette et Bac permettant de d'indiquer le format de canette. 
Modifier la fonction add de Bac pour qu'elle ne puisse ajouter que le format de canette spécifié. 
Adapter vos distributeurs pour qu'il est un bac pour chaque type de canette.
L'affichage des canettes doit indiquer son format.
   - *pas de valeur magique svp*
   - *vous pouvez faire un getter*
   - *On pourra considérer que le premier bac est large et le second long*   
2. La création d'un Bac et d'une Canette est répétitif (il faut toujours préciser le format de la canette), réglez ce problème.

### La R&D s'en mêle
1. Les ingénieurs en R&D viennent de faire une découverte. Il est possible de mettre une canette longue dans un emplacement de canette large!!! Faites les modifications nécessaire
2. Durant une réunion, un stagiaire a soulevé un point important: pourquoi utiliser les bacs pour canette longue si les bacs pour canette large sont universels. On va devoir abandonner les bac de canette longues? Réponse de la R&D: non, car on peut optimisser les bacs de canettes longues pour contenir 15 canettes. Faites les modifications nécessaires.

### Vous vs un collegue (ie votre expertise en Java)

Votre collegue vous fait remarquer que le code actuel n'est pas optimissé: 
chaque instance de Canette à un champs pour le format alors qu'il est possible d'englober cette info dans la class.

1. Créer les class CanetteLarge et CanetteLongue avec les methodes add et toString correspondantes

A l'état actuel il n'est pas possible d'ajouter une CanetteLarge ni une CanetteLongue à un Bac ni à un Distributeur. 

2. Comment faire pour que le code suivant __compile__:
```java
Canette c = new CanetteLarge("cola");
...
distributeur.add(0, c);
```
Faites les modifications.
   - *n'oubliez pas qu'il est possible de mettre une canette longue dans un bac de canette large*
3. Votre code actuel __compile__ mais __ne marche pas__. En effet le code suivant compile et s'éxecute sans erreur (il devrait nous donner une exception).
```java
Canette c = new CanetteLarge("cola");
...
distributeur.add(1, c);
```   
faites les modifications nécessairent.
   - *pensez à isIntanceOf*
   - *le mieux serez de parametré le type des Canettes, si vous n'avez pas compris cette phrase utilisez la même solution que la fonction précédente*
4. Assurez vous que toutes les features fonctionnent et votre code soit le moins redondant possible.

### Vous vs votre tech lead (ie votre expertise en Java)

Vous avez 3 class de canettes, chacune a une méthode toString et ces 3 méthodes sont très similaire.
Il y a surement une meilleur(?) solution!

1. Implementer une solution avec un seul toString dans la classe Canette.
   - *votre fonction toString peut appeler une autre méthode*
