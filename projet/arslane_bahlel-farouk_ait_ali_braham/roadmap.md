# Vérification et Anonymisation des données - Roadmap

## Ce qui est demandé

Création du socle d'un projet qui vérifie et anonymise des données.

L'outil  pourra lire et écrire des fichiers au format CSV. 

L'application devra lire et écrire d'autres format de données que CSV, et doit pouvoir être éxecuté de deux manières différentes, l'une pour vérifier les données et l'autre pour les anonymiser.


## [Énoncé](https://github.com/emerite-neou/2019-DAUPHINE-M1/blob/master/projet/sujet.md)

## Cas d’utilisations

### Cas d’utilisation « UC1 - Vérification des données »
En entrée quatre arguments sont donnés : 

1. Le fichier CSV à vérifier 
2. Un fichier décrivant le fichier CSV
3. Un fichier décrivant les données à vérifier
4. Le nom du fichier CSV en Output

L'exécution de ce cas d'utilisation vérifiera automatiquement que les données correspondent au type attendu, et à l'issue de cette exécution, on aura en sortie :

1. Un fichier CSV portant le nom donné dans l'argument d'entrée « 4 » contenant les données du fichier CSV vérifiés.
### Cas d’utilisation « UC2 - Anonymisation des données »

En entrée quatre arguments sont donnés : 

1. Le fichier CSV à anonymiser 
2. Un fichier décrivant le fichier CSV
3. Un fichier décrivant les données à anonymiser
4. Le nom du fichier CSV en Output

L'exécution de ce cas d'utilisation anonymisera les données grâce au fichier donné l'argument d'entrée « 3 », et à l'issue de cette exécution, on aura en sortie :

1. Un fichier CSV portant le nom donné dans l'argument d'entrée « 4 » contenant les données du fichier CSV anonymisé.

## Fonctionnalités détaillées
 
### Features 
Feature| Ordre de développement| Cas d'utilisation|Fonctionnalité |
|:--------:|:----|:----|:------------|
| F1 | 1 |  UC1 & UC2 | Lire un fichier CSV à vérifier/anonymiser  | 
| F2 | 2 |  UC1 & UC2 | Lire un fichier JSON décrivant les colonnes du fichier CSV | 
| F3 | 10 |  UC1 & UC2 | Donner un nom au fichier CSV en output|
| F4 | 3 |  UC1 | Lire un fichier JSON contenant la base de règles de vérification|
| F5 | 4 |  UC1 | Vérifier automatiquement que les données du fichier CSV sont bien au format décrit|
| F6 | 5 |  UC1 | Vérifier que les données du fichier CSV respectent les règles de vérification|
| F7 | 6 |  UC1 | Écrire les données vérifiées dans un fichier dont le nom est donné en entrée |
| F8 | 7 |  UC2 | Lire un fichier JSON contenant la base de règles d'anonymisation|
| F9 | 8 |  UC2 | Anonymiser les données |
| F10 | 9 |  UC2 | Écrire les données anonymisées dans un fichier dont le nom est donné en entrée |
