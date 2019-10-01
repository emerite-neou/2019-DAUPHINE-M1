# Créer un JUnit c'est parfois compliqué

### La class ne peut pas être testé!
Vous faites partie du projet SIP (Super Important Project).
Ce projet fait des choses très intéressantes et notament des ecritures et lectures de fichier.
Sauf que les IO se font sur un server avec une distribution Dauphine System.
Votre collègue c'est chargé de créer la class SIPIO (Super Important Project IO) qui s'occupe de toute la logique 
de lecture et d'écrire de fichier. 
Vous êtes chargé de créer le jUnit de cette class.

*Vous avez à votre disposition 
le jar testMe contient la class SIPIO à tester et toutes ces dépendances
et à la javadoc des class DauphineSystemFileSystem et SIPIO.
Vous n'avez PAS acces au code source de la class SIPIO.*

1. En utilisant la javadoc, trouvez les methods que vous devez tester et ce qu'elles doivent faire.
2. Instanciez un DauphineSystemFileSystem et essayez de créer un fichier. Pouvez -vous tester la class SIPIO?


### Créer un Mock
La class DauphineSystemFileSystem ne marche pas sur votre ordinateur 
car vous n'avez pas de Dauphine System. 
Il est hors de question de l'installer: les tests marcheront sur votre machine
mais pas sur celle de vos collègues. 
Les tests ne doivent pas être dépendant de la machine qui l'éxécute! 
Il faut donc crée une class qui similue DauphineSystemFileSystem le plus simplement possible.

1. Comment créer la class DauphineSystemFileSystemMock tel que le code suivant fonctionne:
```java
DauphineSystemFileSystem fsMock = new DauphineSystemFileSystemMock();
SIPIO io = new SIPIO(fsMock);
```
2. Quelle sont les besoins auquels DauphineSystemFileSystemMock doit répondre? 
Voici trois points pour vous aider
   - Les informations suivantes sont les plus fréquentes pour un fichier: acces permission, owner, group, size, date, name, content 
   - Parmis ces informations, laquelle permet d'identifier un fichier de manière unique? 
   - Parmis ces informations, lesquelles votre class Mock doit gérer?
3. Implémentez DauphineSystemFileSystemMock.

### La mutabilité c'est mal(?)
Vous êtes maintenant prêt à tester la class.
1. Commencez par tester la method exist. Quelles sont les différents cas à tester. Comment tester la présence d'un fichier? 
2. Testez le reste des methods. 
3. Il y a une method qui ne correspond pas à sa description. Trouvez la.
