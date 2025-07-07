package jalon.dwwm;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import jalon.methods.MenuList;

/*
 A l’aide des ArrayList, des méthodes, des conditions et des boucles, vous allez me créer un algorithme pour me faire un inventaire de produit alimentaire sous-forme de liste. Les produits alimentaires ont chacun un type :

TypeAliment [] = {« Viande », « Légume », « Féculant », « Fruit », « Laitage », « Poisson », « Dessert », « Pâtisserie », « Boulangerie »}

Chaque Aliment possède :
-	Nom (String)
-	Type d’Aliment ( String [] Type)  : Féculant,Fruit,Laitage,Poisson,Dessert,Patisserie,Boulangerie
-	Date de fabrication (Date)
-	Date de péremption (Date),
-	Prix de base (Double ou Float)
-	Si il est consommable (Boolean ou String)
A noter, le prix diminue si la date d’aujourd’hui est proche de 3 jours avant la date de péremption. Si la date de péremption est dépassée alors le produit n’est pas consommable.
Il faudrait qu’on est la possibilité d’afficher la liste de tous les produits, pouvoir ajouter ou supprimer, mais aussi chercher un produit dans la liste via son nom.





 */

public class ExoJalonBlanc {


    static Scanner scanner = new Scanner(System.in, "Cp852");

    public static void main(String[] args) {

        ArrayList<String> productList = new ArrayList();
        int productId = 0;
        String [] productType = {"Viande", "Légume", "Féculant", "Fruit","Laitage","Poisson","Dessert","Pâtisserie","Boulangerie"};
        String productName = "";
        String productDate = "";
        double productPrice = 0.0;
        boolean canIeat= false;
        int option = 0;

        boolean continueAdding = true;

        main:while (continueAdding) {

                MenuList.displayMenu();
                System.out.print("Choisissez une option : ");

                try{
                option = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne restante
                } catch (InputMismatchException e) {

            System.out.print("Erreur : Saisie incorrecte. Veuillez entrer un nombre.");
            scanner.nextLine(); // Pour consommer la nouvelle ligne restante

                } 

                switch (option) {
                    case 1:
                        // Ajouter un produit
                            System.out.println("Ajout d'un nouveau produit :");
                            MenuList.addProduct(productList, productName, productType, productDate, productPrice, canIeat, scanner);

                        break;
                    case 2:

                        if(productList.isEmpty()) {

                            System.out.println("La liste des produits est vide. Aucun produit à supprimer, veuiller d'abord ajouter un produit (1).");

                        }else{
                         // Supprimer un produit
                        MenuList.removeProduct(productList, scanner);
                        }
  
                        break;
                    case 3:
                        // Rechercher un produit par ID
                        if(productList.isEmpty()) {

                            System.out.println("La liste des produits est vide. Aucun produit à rechercher, veuiller d'abord ajouter un produit (1).");
                        }else{
                            MenuList.searchProductById(productList, scanner);
                        }
                        break;
                    case 4:

                            MenuList.displayAllProducts(productList, scanner);
                        
                        break;
                    case 5:
                        continueAdding = false;
                        System.out.println("Merci pour votre visite, au revoir.");
                        break main; // Sort de la boucle principale et termine le programme
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            }

        }

    



}
