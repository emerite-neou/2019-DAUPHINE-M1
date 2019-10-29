# Mois

1. Créer une enum réprésentant les 12 mois de l'année.

2. On doit pouvoir afficher le mois à partir d'un sysout :
```java
system.out.println(Month.JANUARY) // JANUARY
```
*Indice: Les élèments de votre enum Month sont des instances de la class Month, vous pouvez donc écrire un constructeur et des methods*

3. On souhaite récupérer le numéro du mois associé. Faites les modifications nécéssaires.
```java
system.out.println(Month.JANUARY.getMM()) // 01
```


# Caculette

1. Créér un enum contitué des 4 opérations atihmétiques courantes.

2. On souhaite maintenant effectuer les calculs liés à ces opérations. 
Ecrire une method compute prenant deux int et renvoie le résultat.
```java
system.out.println(Operation.PLUS.compute(5, 6)) // 11
```
