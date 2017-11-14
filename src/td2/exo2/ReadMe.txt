La gestion des clients exploite deux types de mécanisme:

1 - Mécanisme utilisant les énumérations. Celui qui est actuellement implémenté

2 - Mécanisme utilisanr le design paterrn Strategy. Présenté à titre d'exemple.
Cette option est intéressante si les fonctionnalités différent beaucoup entre les
différents type de clients privilégiés. Dans le cas présent, il n'y a que des attributs
qui sont modifiés (pas de calcul, où d'autres fonctions), la méthode des énumérations
est donc recommandé.