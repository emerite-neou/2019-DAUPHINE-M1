# jUnit2

1. Commencez pas créer une private static method dans la class Test qui prend un preminer nombre et le multiplie par un second.

On souhaite maintenant faire le jUnit associé. Problème la method est private (et elle doit le rester)

1. Que fais la method Class.getDeclaredMethod?
2. Récupérer votre method précédement créer. Comment l'exécuter? Testez. Quelle est le problème
3. Comment changer le visibilité de la method? Changez la puis écrivez vos tests.

# toJSon

*copier/coller d'un tp de UPEMLV http://www-igm.univ-mlv.fr/ens/Master/M1/2018-2019/JavaAvance/td13.php*

On souhaite écrire un code qui permet d'afficher un objet au format JSON, par exemple pour la classe Person:
'''java
public static String toJSON(Person person) {
    return
        "{\n" +
        "  \"firstName\": \"" + person.getFirstName() + "\"\n" +
        "  \"lastName\": \"" + person.getLastName() + "\"\n" +
        "}\n";
}
'''
Mais supposons que nous ayons envie d'écrire un méthode toJSON qui prend en paramètre un Alien et qui fait la même chose, 
nous allons dupliquer du code.
Pour éviter l'hécatombe, on se propose d'écrire une seule méthode toJSON prenant un Object en paramètre et utilisant la réflexion (reflection en anglais) pour trouver les propriétés à écrire au format JSON.

1. Ecrire une méthode toJSON qui prend en paramètre un Object, 
utilise la réflexion pour accéder à l'ensemble des méthodes publiques de la classe de l'objet (java.lang.Class.getMethods), 
sélectionne les getters, puis affiche les couples nom de propriété, valeur associée.

2. Testez votre method avec une class Person, qu'elle est le problème? 
Est ce que problème se reproduit si on utilise votre method avec une instance de Alien?
Implémentez une solution.

Pour éviter ce genre de problème, on va plutôt marquer les méthodes qui feront partie de l'affichage JSON. 
À cette fin, on se propose d'utiliser une annotation.

1. Déclarez l'annotation JSONProperty visible à l'exécution et permettant d'annoter des méthodes.
2. Modifiez le code de toJSON pour n'utiliser que les propriétés issues de méthodes marquées par l'annotation JSONProperty.
3. En fait, une propriété JSON peut contenir des caractères comme le '-' qui ne sont pas des caractères valides dans un nom de méthode Java.
Faîtes en sorte que l'on puisse utiliser l'annotation JSONProperty sans rien, et dans ce cas le nom de la méthode sera utilisée, mais que l'on puisse aussi utiliser l'annotation JSONProperty avec un nom. Ce nom sera alors utilisé au lieu du nom de la méthode.


