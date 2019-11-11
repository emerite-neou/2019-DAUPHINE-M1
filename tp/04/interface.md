# De meilleurs if

Beaucoup de if/else sont utilisés pour affecter une valeur.
Cela pose un problème au niveau de la mutabilité et/ou de la lisibilité.

Exemple:
```java
String hi;

if (lang == FR) {
  hi = "bonjour";
} else if (lang == DE) {
  hi = "guttentag";
} else if (lang == ES) {
  hi = "hola";
} else {
  hi = "hi";
}
```

Une solution (que certains langages utilisent) est d'avoir un if qui soit une expression (et non un statement):
```java
String hi = if (lang == FR) {
  "bonjour";
} else if (lang == DE) {
  "guttentag";
} else if (lang == ES) {
  hi = "hola";
} else {
  hi = "hi";
}
```

Le but de cet excercice est d'essayer de trouver une solution à ce problème en imitant ce comportement.

### Création de la class Expression
1. Créer une class Expression contenant une method expIf. 
La method expIf prend en argument le test et les valeurs à renvoyer.

Exemple:
```
String hi = new Expression().expIf(lang == FR, "bonjour", "hi");
```

doit être équivalent à:
```java
String hi;

if (lang == FR) {
  hi = "bonjour";
} else {
  hi = "hi";
}
```

2. Pour des raisons de lisibilités (et d'habitude de lecture), on aimerait que le else soit précisé.
Ajoutez une méthod expElse. Que doit devenir la signature de expIf si on veut pouvoir exécuter un expElse après un appel à expIf.

Exemple:
```
String hi = new Expression()
  .expIf(lang == FR, "bonjour")
  .expElse("hi");
```

3. Que dois faire le code si l'utilisateur essaie d'appeler la method expElse avant la method expIf?

4. Ajouter la method expElseIf. Assurez-vous que l'utilisateur soit obligé d'utiliser votre code manière cohérente.

Exemple:
```
String hi = new Expression()
  .expIf(lang == FR, "bonjour")
  .expElseIf(lang == DE, "guttentag")
  .expElseIf(lang == ES, "hola")
  .expElse("hi");
```

### Optimissation
Vous avez surement ajouté des flags (permettant de savoir où en est l'utilisateur dans les if/elseif/else).
Ce n'est pas optimal. 
On va exploser la class Expression en plusieurs class tel que chaque class indique dans quel état est l'expression.

1. Combien de class avez-vous besoin? Que deviennent les methods if, elseIf et else (dans quelles class vont elles, est ce que la signature change). Implémentez votre solution. Assurez-vous que modificateurs de visibilités soient les plus restrictifs possibles.

### Un peu de philosophie

Votre code a peut être les problème suivant:
. Votre code est difficile à prendre en main 
(chaque fonction renvoie une class différente, il faut aller dans chaque class pour voir ceque fait chaque method).
. Ou votre class mere n'est jamais instancié (ie elle n'est pas concrete ie ca ne devrait pas être declarer comme une class),
. Ou vous n'avez pas la relation "Est un" entre la fille et la mere (une canette longue est une canette),
. Ou le nom de vos class ne sont pas cohérent avec ce que font vos class.

Une solution, pour les éviter, est de d'utiliser une interface (pas encore parfaite).

1. Créer une interface IfExpression avec les methods expIf, expElseIf et expElse.
2. Créer une class BeforeIf qui implémente IfExpression, comment implémenter les methods elseIf et else?
3. Créer une class AfterIf qui implémente IfExpression, comment implémenter la method if?
4. Certaines de vos méthodes ne lancent qu'une exception, cela complexifie la lecture de votre code et créer de la répétition.
Ajoutez des default methods à votre interface IfExpression.
5. Assurez-vous que modificateurs de visibilités soient les plus restrictifs possibles.

# Arbre d'expression

Un arbre d'expression est un arbre binaire tel que les noeuds contiennent:
- une opération mathématique (+, -, etc...) si le noeud n'est pas une feuille 
- un nombre si le noeud est une feuille
Elles permettent de représenter des calculs.

Exemple :
```
(1+2)*3

peut être représenté par par l'arbre d'expression
   *
 +  \
/ \  3
1 2  
```

Le but de cet excercice est de créer une App utilisant ces objets.

1. Créer la class ValOrOp permettant de représenter les noeuds.
Notez qu'un noeud ne peut pas avoir une operéation mathématique ET un nombre en même temps. 
Il est donc possible de différenciers une valeur d'une opération mathématique par la présence ou l'absence de valeur.
2. Durant l'instanciation de votre class (ie `new ValOrOp(...)`) un champ sera toujours vide. 
Quelle pratique peut-on utiliser pour éviter ce problème? Implémentez la.
3. Arrangez-vous pour que si on passe un ValOrOp à un println, alors le calcul s'affiche.
   - *Pensez à ajouter des paranthèses*
4. Créer la méthod eval() qui renvoie la valeur du calcul représenté par le ValOrOp.
5. Écrire une méthode parse qui prend un String en entrée et crée l'arbre d'expression correspondant sachant que l'arbre sera donné au scanner en utilisant la notation polonaise inverse. Par exemple `(1+2)*3` devient `* + 1 2 3`.
La méthode parse est naturellement récursive: si l’expression contient encore des symboles (et qu'elle est bien formée) alors:
. soit le prochain symbole est un entier et il suffit d'en faire une feuille de l’arbre d'expression,
. soit le prochain symbole est un opérateur et il faut appeler parse() 2 fois pour obtenir le fils gauche et le fils droit et les combiner avec l'opérateur pour faire une nouvelle expression.

Votre code contient pas mal de if/else pour faire la différence entre une valeur et une opération.
De plus vos instances ne sont pas optimissé, certains champs ne sont jamais utilisés (selon le type de votre noeud).
On améliorer ca.

1. Comment doivent être déclarer vos class Add, Mult et Val pour que le code suivant fonctionne?
```java
ValorOp val1 = new Val(1);
ValorOp val2 = new Val(2);
ValorOp val3 = new Val(3);

ValorOp add = new Add(val1, val2);
ValorOp mult = new Mult(add, val3);
```
2. Pourquoi l'héritage n'est pas la bonne solution?
3. Implémentez les class Add, Mult et Val. Dans quelle classe doit-on mettre la méthode parse ? Modifier celle-ci pour qu'elle marche avec les nouvelles classes.


1. Vos class Add et Mult on beaucoup de code en commun. Dans quel type de class doit on mettre le code en commun? Refactorisez votre code!
2. On désire ajouter des opération unaires (sqrt, factoriel, sum). Implémentez les classes nécessaires pour avoir les opérations factorielles (reprenté par un ! dans le parser) et sommes (représenter par un S). 


# Jeu des allumettes

On se propose de coder le jeux des allumettes. 
Il se joue a deux joueurs, 
chacun leur tour les joueurs vont retirer 1, 2 ou 3 allumettes d'un paquet.
Le but est d'obliger son adversaire à retirer la dernière allumette.

Vous devez codez un programme qui permet de jouer à ce jeux (avec un affichage sur terminale).
De plus on vous demande de créer deux IA (une IA qui tire au hasard un nombre et une seconde qui suit la stratégie gagnante)
Votre programme prendra 3 arguments:
Le premier correspond au nombre de départ d'allumettes, 
le second et le troisième le type du joueur (humain, ia1 ou ia2)

- *On considere que le programme est toujours lancé correctement (pas besoin de faire des vérifications sur les arguments)*
- *La stratégie gagnante consite a laisser à l'adversaire un nombre n d'allumettes tel que (n-1)%4==0* 
