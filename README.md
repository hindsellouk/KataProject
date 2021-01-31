# KataProject
Bonjour,
Il s'agit d'une application java desktop en Spring Boot.

Pour lancer l'application, veuillez suivre les étapes suivantes:
1- git clone https://github.com/hindsellouk/KataProject.git
2- ouvrir le projet maven
3- lancer un "maven clean install"
4- executer la classe: OrderBillingApplication:

      --> Vous aurez l'affichage suivant:
                Products Data:
                Product id:0 Product Label:livre
                Product id:1 Product Label:CD musical
                Product id:2 Product Label:barre de chocolat
                Product id:3 Product Label:flacon de parfum
                Product id:4 Product Label:boite de pilule contre la migraine
         Il s'agit des données qui concernent les produits( id, libellé), afin de permettre à l'utilisateur de savoir quel id  de produit entrer pour le calcul de sa facture.
      
      --> l'application vous demandera d'entrer le nombre de produits que vous voulez ajouter à la commande:
                Enter the number of products
      
      --> l'application vous demandera d'entrer produit par produit selon le format suivant : (Product_id:Price:Quantity:Isimported)
                Enter input in the following format (Product_id:Price:Quantity:Isimported)
                exemple: 0:12.49:2:False


/****Exemples d'execution:****/

Products Data:
Product id:0 Product Label:livre
Product id:1 Product Label:CD musical
Product id:2 Product Label:barre de chocolat
Product id:3 Product Label:flacon de parfum
Product id:4 Product Label:boite de pilule contre la migraine



Enter the number of products
3
Enter input in the following format (Product_id:Price:Quantity:Isimported)
0:12.49:2:False
Enter input in the following format (Product_id:Price:Quantity:Isimported)
1:14.99:1:False
Enter input in the following format (Product_id:Price:Quantity:Isimported)
2:0.85:3:False
2021-01-31 19:19:48.537  INFO 10756 --- [           main] t.c.services.impl.OrderBillingImpl       : Calculating prices
2 livre   12.49: 27.5
1 CD musical   14.99: 18.0
3 barre de chocolat   0.85: 2.55
Montant des taxes: 5.5
Total: 48.05



Enter the number of products
2
Enter input in the following format (Product_id:Price:Quantity:Isimported)
2:10:2:True
Enter input in the following format (Product_id:Price:Quantity:Isimported)
3:47.50:3:True
2021-01-31 19:20:09.704  INFO 10756 --- [           main] t.c.services.impl.OrderBillingImpl       : Calculating prices
2 barre de chocolat importé 10.0: 21.0
3 flacon de parfum importé 47.5: 178.15
Montant des taxes: 36.65
Total: 199.15



Enter the number of products
4
Enter input in the following format (Product_id:Price:Quantity:Isimported)
3:27.99:2:True
Enter input in the following format (Product_id:Price:Quantity:Isimported)
3:18.99:1:False
Enter input in the following format (Product_id:Price:Quantity:Isimported)
4:9.75:3:False
Enter input in the following format (Product_id:Price:Quantity:Isimported)
2:11.25:2:True
2021-01-31 19:23:10.001  INFO 10756 --- [           main] t.c.services.impl.OrderBillingImpl       : Calculating prices
2 flacon de parfum importé 27.99: 70.0
1 flacon de parfum   18.99: 22.8
3 boite de pilule contre la migraine   9.75: 29.25
2 barre de chocolat importé 11.25: 23.65
Montant des taxes: 18.95
Total: 145.7
