Le but de ce tp est de s'habituer au functional interface de java et les methods qui (communes à tous les languages) les utilisants, 
nommément foreach, map, filter et aggregate/reduce.

# Echauffement

1. En utilisant la method forEach d'une liste, afficher tous les élèments de cette liste.

En utilisant une boucles for :
1. Ecrivez une method static qui prend une List de String, met tous les mots en majuscules et renvoie cette liste.
2. Modifiez la method précédente pour ne garder que les mots qui commencent par un M.

# Stream
1. Ecrivez une method static qui prend une Stream de String, met tous les mots en majuscules et renvoie ce Stream. 
Vous pouvez transformer toutes les valeurs d'un stream avec la method map : cette method prend une function en argument 
et remplace chaque élèment du stream en appliquant la function à cette valeur.
2. Modifiez la method précédente pour ne garder que les mots qui commencent par un M.
Vous pouvez filtrer les valeurs d'un stream avec la method filter : elle prend une function en argument
et ne garde que les élèment du stream qui sont évaluer à true part la function précédente.
3. Affichez les élèment d'un stream avec la method forEach.


# Entrainement

### Scrabble

En partant d'un stream de String :
1. Mappez chaque mot à son nombre de caratères.

En partant d'un steam de String :
1. Mappez chaque élèment à un score (type scrabble).

On souhaite maintenant afficher chaque mot suivie de son score au scrabble.
Avec la solution précèdente on perd le mot.
Une solution serait de créer une class contenant un champs mot et score.
Mais si on veut afficher le mot, son nombre de lettre et et son score on va devoir modificer la class.
On va utiliser une Map pour contenir toutes les informations.
En partant d'un steam de String :
1. Mappez chaque string à une Map<String, Object> contenant la key "mot" mapper à la valeur du string.
2. Mappez chaque map pour y ajouter la key "score" associé à au score du mot. 
Attention vous avez une Map<String, Object>, il vous faudra donc des cast.
3. Mappez chaque map pour y ajouter la key "taille" associé à la taille du mot.
4. Ne gardez que les mots ayants un score supérieur à 10.
5. Affichez proprement chaque élèment.

On souhaite prendre en compte qu'une lettre peut valoir le double de son score
1. Mappez chaque string à une Map<String, Object> contenant la key "mot" associé à la valeur du string.
2. Mappez chaque map pour y ajouter la key "scores" associé à la list des scores possible.
3. Mappez chaque map pour y ajouter la key "score_min" associé au score minimal possible.
4. Mappez chaque map pour y ajouter la key "score_max" associé au score minimal possible.
5. Mappez chaque map pour y ajouter la key "score_avg" associé à la moyenne des scores
6. Affichez proprement chaque élèment.


### Points

#### Cacul de distances
1. Créer une liste de string de la forme "X,Y" où X et Y sont des entiers.
2. Mappez ces string à une map qui associe les keys X et Y.
3. Ajoutez la distance au point de coordonées 0,0.

#### Centroid
1. A partir d'un stream de Points, calculez le centroid de cet ensemble de point. Le centroid correspond au moyenne des coordonnées.

#### 2-means
1. Créez une fonction qui prend une liste de points et une liste de deux centres (qui sont aussi des points). 
Groupez chaque point au centre auquel il est le plus proche, puis calculer le centroid des deux groupes.
REnvoie une liste contenant les deux centroid

#### k-means
1. Même question que précédent mais avec une lsite de centre de taille non défini.


####
1. A partir du stream des distances et en utilisant la method reduce, calculez la somme des distances.
2. A partir du stream des distances et la method reduce calculez la distance moyenne. 
Vous devez ajouter une map avant de faire le reduce pour ce faire.

