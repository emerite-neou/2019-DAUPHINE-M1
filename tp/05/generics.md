# MultiSet



Un multiset est un ensemble d'éléments qui peuvent se répéter.
Il est différent d'un set car les éléments peuvent apparaître plusieurs fois
et d'une list car il n'y a pas de notion d'ordre.

1. Créez la class MultiSet représentant un multi set de String. 
Votre class doit permettre d'ajouter (add), de retirer (remove), tester la présence d'un élément (contains)  
et d'obtenir le nombre de fois qu'un élément est dans le MultiSet (get).
Votre implémentation doit être la plus efficace possible.

2. On veut maintenant pouvoir gérer un MultiSet de n'importe quel class. Rendez votre class précédente generic.

3. Quelle est la signature de la method contains d'une List? Pourquoi (quelle method est appelé dans contains)?
Assurez que vos methods contains et get suivent la même logique.

# Arbre binaire de recherche

Un arbre binaire de recherche est une structure d'ensemble.
Chaque noeud de l'arbre réprésente un élément de l'ensemble.
Il a de plus la propriété que la racine est plus grand que tous les élèments dans son fils gauche
et plus petit que tous les élèments de son fils droit. 

### Création d'un arbre.

1. Créez la class BinarySeachTree sur des entiers.
Votre class doit permettre d'ajouter (add).
La propriété du dessu est assuré dans le add:
- si la racine est vide, ajouter le nombre dans cette racine,
- si le nombre est plus petit ou égal que la racine, l'ajouter dans le fils gauche,
- si le nombre est plus grand que la racine, l'ajouter dans le fils doit.

2. Essayez de rendre la class generic. Quelle est le problème.

3. Quelle interface de java permet d'indiquer qu'une class peut être comparer?
Indiquer que les types generics de la class peuvent être comparé et modifié votre class en conséquance.

4. Créez les method static printInc et printDesc qui prend un BinarySearchTree et qui affiche les élèments en ordre croissant et décroissant respectivement. Que doit-être la signature de ces méthodes?



# Validator

On veut écrire une class parametré qui permet de valider un Object du type parametre.

1. Créez une class parametre qui contient un objet du type parametre
```java
Validator<Integer> validator = new Validator(10);
```

2. Quelle functional interface permet un test (prend quelque chose et renvoie un bollean)? 
Ajoutez une method should qui permet d'ajouter une condition sur cet objet, on veut pouvoir chainer ces appels.
```java
Validator<Integer> validator = new Validator(10)
  .should(someCondition)
  .should(anotherCondition);
```

3. Quelle est la meilleur façon de créer someCondition et anotherCondition?

4. Ajoutez une method isValidate qui renvoie un boolean si tous les conditions sont remplies.
```java
boolean res = new Validator(10)
  .should(someCondition)
  .should(anotherCondition)
  .isValidate();
```

5. Il faudrait avoir la liste des conditions qui ont échouées. 
Ajoutez une method getFailedCondtions qui renvoie la liste des conditions échouées.
La method should pourra avoir une second argument précisant le nom du test.

Si on doit valider plusieurs objets avec les même conditions, la création de l'objet risque de peser.

1. Modifiez votre code pour pouvoir l'utiliser de la manière suivante:
```java
Validator<Integer> intValidator = new ValidatorFactory<>
  .should(someCondition)
  .should(anotherCondition)
  .createValidator();
  
boolean res1 = intValidator.validate(10);  
boolean res2 = intValidator.validate(100);  
```

2. L'instancier un ValidatorFactory n'est pas élégant.
Créez une method static qui renvoie un Validator, cette method prendra en argument les conditions.
```java
Validator<Integer> intValidator =  ValidatorFactory.<Integer>CreateValidator(someCondition, anotherCondition, anotherCondition2)
Validator<Integer> intValidator2 =  ValidatorFactory.<Integer>CreateValidator(
  someCondition, 
  anotherCondition, 
  anotherCondition2, 
  anotherCondition3
)

boolean res1 = intValidator.validate(10);  
boolean res2 = intValidator2.validate(100);  
```

