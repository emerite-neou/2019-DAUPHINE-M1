Le but de ce TP est de ce familiariser avec les functional interfaces de java les plus courante et de voir quelques design les utilisants.

# Boilerplate - withTime

On veut tester le temps d'éxecution de la method sort de la class Collections.

1. Créez une static method qui rempli une List d'entiers aléatoires puis tri la liste. 
La list et le nombre d'élèment et la seed doivent pouvoir être changer.
2. Chronometrez le tri d'une ArrayList avec beaucoup d'élèments.

On souhaite maintenant comparer le temps d'éxecution avec d'autres implementations de List (LinkedList par exemple).

1. Expliquez l'intérêt d'avoir mis la seed en argument?
2. A quoi sert l'interface Runnable? Ecrivez une method withTime qui prend un Runnable en argument et affiche le temps d'éxecution. 
3. Pourquoi cette solution est préférable qu'une method prennant une List en parametre et qui utilise la method précédente en ajoutant un chorno?
4. Utilisez cette method pour tester sortList. Pour vous entrainez utilisez la notation de class anonyme et des lambdas.

Pour tester plusieurs implémentations de list et/ou avec des nombres d'élèments différents, vous devez faire du copier/coller.
On peut améliorer cela.

1. Ecrivez une method qui renvoie un Runnable faissant le tri. 
La list, le nombre d'élèment et la seed doivent pouvoir être changer.

Le code actuel chronometre le tri et la création de la list, il faut les séparer.


1. Il faudrait modifier la method withTime pour qu'elle prenne en argument une method  qui renvoie un objet
et une autre method qui l'utilise. Trouvez ces deux functional interfaces.
(https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
2. Modifier votre la method withTime en conséquence. 
3. Testez le tri d'une linkedList et d'un ArrayList. Votre code doit avoir le moins de copier/coller possible.

(Bonus)

1. A partir d'un certains nombre d'entiers, en lancant deux fois le même test, les temps d'exécution auront une différence significative, pourquoi? Réglez ce problème.

2. Séparez l'initilisation de la création de la List.

# Boilerplate - withResources

Le but de cet excercice est de tester des designs de code utilisant des ressource (une DB, un fichier, une connection à un socket, etc...).

### 1. Version simple

1. Ecrivez une method static qui prend un nom de fichier, qui parse un nombre contenu dans ce fichier et le renvoie. Que se passe-t-il si le fichier n'existe pas? Si le fichier le contient pas un nombre? La ressource doit toujours être fermé à la fin de l'exécution de votre code (avec ou sans erreur).

### 2. Version pour FileReader 

Pour éviter du copier/coller de code si on veut lire des fichiers d'une autre manière, on va séparer l'ouverture/fermeture du flux de l'éxecution

1. Créez une method static qui prend un flux de fichier et fait la même chose que method de la question 1.1
2. Créez une method qui puisse prendre un nom de fichier et la method précédent et l'éxecute.
3. Comment gérer le cas si la method ne renvoie rien? Implémentez votre solution.
4. Réfléchissez à la solution où la method de la question 2.2 prend comme premier argument une method qui renvoie un flux de fichier.
Quelles sont les avantages et désavantages? Implémentez votre solution.

### 3. Version generic 

Votre code suffit à la lecture d'un fichier mais il existe d'autres types de flux.

1 En reprennant la method de la question 2.4 et rendant le type de flux parametrable, rendez la method generic.
Comment fermer le flux (si il faut le fermer)?
2 Quelles sont les avantages/inconvéniants des method des questions 1.1, 2.2, 2.4 et 3.1?
