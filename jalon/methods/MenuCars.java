package jalon.methods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCars {

    public static void Menu(boolean continueAdding, String[] brand, String[][] model,String[][]colors, boolean isNew, int kilometer,
            String code, String [][]prices, Scanner scanner, int option, ArrayList<String> carsList) {

        main: while (continueAdding) {

            displayMenu(carsList);
            System.out.print("Choisissez une option : ");

            try {

                option = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne restante

                switch (option) {
                    case 1:
                        // Ajouter un produit
                        System.out.println("Ajout d'un nouvelle voiture :");
                        addCar(carsList, brand, model,colors ,prices,isNew,kilometer,code,scanner);

                        break;
                    case 2:

                        if (carsList.isEmpty()) {

                            System.out.println("Pour supprimer veuiller d'abord ajouter une voiture (1).");

                        } else {
                            // Supprimer un produit
                            removeCar(carsList, scanner);

                        }

                        break;
                    case 3:
                        // Rechercher un produit par ID
                        if (carsList.isEmpty()) {

                            System.out.println(
                                    "La liste des voitures est vide. Aucun produit à rechercher, veuiller d'abord ajouter un produit (1).");
                        } else {

                            searchCarById(carsList, scanner);

                        }
                        break;
                    case 4:

                        break;
                    case 0:
                        continueAdding = false;
                        System.out.println("Merci pour votre visite, au revoir.");
                        break main; // Sort de la boucle principale et termine le programme
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            }

            catch (InputMismatchException e) {

                System.out.println("Erreur : Saisie incorrecte. Veuillez entrer un nombre.");
                scanner.nextLine(); // Pour consommer la nouvelle ligne restante

            }

        }

    }

    public static void displayMenu(ArrayList<String> list) {

        displayAllCars(list);

        System.out.println("(1). Ajouter une voiture");
        System.out.println("(2). Supprimer une voiture");
        System.out.println("(3). Rechercher une voiture");
        System.out.println("(0). Quitter");
    }

    public static void displayAllCars(ArrayList<String> list) {
        // Implémentez la logique pour afficher tous les voitures de la liste
        // Par exemple, vous pouvez parcourir la liste et afficher les détails de chaque
        // produit
        if (list.isEmpty()) {
            System.out.println(
                    "La liste des voitures est vide. Aucune voiture à afficher, veuillez ajouter d'abord une voiture (1).");
        } else {
            System.out.println("Liste des voitures :");
            for (String car : list) {
                System.out.println(car);
            }
        }
    }

    public static void addCar(ArrayList<String> list, String[] brand, String[][] model,String[][]colors ,String[][] prices, boolean isNew, int kilometer,
            String code, Scanner clavier) {

        // Code pour ajouter un produit dans la liste

        addCar: while (true) {

            int brandid = addCarBrand(list, brand, clavier);

            String brandName = brand[brandid - 1];

            String modelName = addModel(list, brandid - 1, model, clavier);

            isNew = addCarIsNew(list, isNew, clavier);

            String newString = isNew ? "Oui" : "Non"; /* On récupère si la voiture est en occasion */

            kilometer = addkilometer(list, kilometer, isNew, clavier);

            code = generateCode(brandName, modelName);

            double price = foundCarPrice(list, modelName, isNew, prices, kilometer);

            int idColor = addColor(colors,clavier);

            double colorPrice = Double.parseDouble(colors[idColor][1]);

            String carDetails = "[ ID: " + (list.size() + 1) + "| Marque: " + brandName + "| Modele: " + modelName
                    + " | Neuf :" + newString + " | Kilometrage:" + kilometer + " km | Code :" + code + " | Couleur "+colors[idColor][0]+"| Prix : "
                    + String.format("%.2f", price+colorPrice) + " euros]";

            list.add(carDetails);
            System.out.println("Produit ajouté : " + carDetails);

           
            if(again(clavier)==true){
             
              continue addCar;
             
              }else{

              break addCar;
              
              }
             

        }

    }

    public static int addCarBrand(ArrayList<String> list, String[] brand, Scanner clavier) {
        // Implémentez la logique pour ajouter un produit avec son nom
        // Par exemple, vous pouvez créer une nouvelle entrée dans la liste avec le nom
        // du produit

        addCarBrand: while (true) {
            try {
                int choice;
                String name;
                System.out.println("Choisissez votre marque : ");
                displayTab(brand);
                choice = clavier.nextInt();
                name = brand[choice - 1];
                clavier.nextLine();
                System.out.println("Vous avez choisi la marque : " + name);
                return choice;

            } catch (Exception e) {

                Exceptioner.TxtException(e, list.size());
                clavier.nextLine();
                continue addCarBrand; // Recommencer la saisie du prix

            }

        }
    }

    public static String generateCode(String brandName, String modelName) {

        LocalDateTime now = LocalDateTime.now();

        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        // Print the current date and time

        // y: Année, m: Mois, d: Jour, h: Heure (24h), m: Minute, s: Seconde

        // Format the date-time
        String formattedDate = now.format(formatter);

        String code =

                brandName.substring(0, 1).toUpperCase() +
                        modelName.substring(0, 1).toUpperCase() +
                        formattedDate

        ;

        return code;

    }

        public static int addColor(String[][]colors,Scanner clavier){

            int color=0;
        addColor: while (true) {
            try {

            System.out.println("Quel est sa couleur ?:");

            displayTab2(colors);

            System.out.print("Choix de la couleur :");

            color = clavier.nextInt();
            
            System.out.print("Vous avez choisi la couleur :"+colors[color][0]);

            } catch (InputMismatchException e) {

                Exceptioner.TxtException(e, 0);
                clavier.nextLine();
                continue addColor; // Recommencer la saisie du solde
            }


            return color;

        }
            
            
            

            
        }

    public static double foundCarPrice(ArrayList<String> list, String modelName, boolean isNew, String[][] prices,
            int kilometer) {
        // Implémentez la logique pour ajouter un produit avec son nom
        // Par exemple, vous pouvez créer une nouvelle entrée dans la liste avec le nom
        // du produit

        double initPrice = 0;
        double finalPrice = 0;
        boolean found = false;

        System.out.println("Prix du modèle :"+modelName);

        for (int i = 0; i < prices.length; i++) {


            if (modelName == prices[i][0]) {

                initPrice = Double.parseDouble(prices[i][1]);
                found = true;

            }

        }

        if (found == false) {

            System.out.println("Ce model n'existe pas dans la liste, nous n'avons pas pu retrouver son prix");
            initPrice = 0;
        }

        if (isNew == false) {

            finalPrice = initPrice * kilometerReduction(kilometer, initPrice);

        } else {

            finalPrice = initPrice;

        }

        System.out.println("Prix dr la voiture : " + finalPrice);

        return finalPrice;

    }

    public static boolean again(Scanner clavier) {

        boolean respond;

       again: while (true) {

            try {

        System.out.println("Voulez-vous recommencer (true/false) ? :");
        respond = clavier.nextBoolean();
        clavier.nextLine();

        } catch (InputMismatchException e) {

                Exceptioner.TxtException(e, 0);
                clavier.nextLine();
                continue again; // Recommencer la saisie du solde
            }

        return respond;

       }

    }

    public static boolean addCarIsNew(ArrayList<String> list, boolean isNew, Scanner clavier) {
        // Implémentez la logique pour ajouter un produit avec son solde
        // Par exemple, vous pouvez créer une nouvelle entrée dans la liste avec le
        // solde du produit
        addCarIsNew: while (true) {

            try {
                System.out.println("Est-il neuf (true/false) : ");
                isNew = clavier.nextBoolean();
                clavier.nextLine();
                System.out.println("Est-il neuf (Oui ou Non) : " + (isNew ? "Oui" : "Non"));
                return isNew;

            } catch (InputMismatchException e) {

                Exceptioner.TxtException(e, list.size());
                clavier.nextLine();
                continue addCarIsNew; // Recommencer la saisie du solde
            }
        }

    }

    public static double kilometerReduction(int kilometer, double initPrice) {

        double reduction;

        if (kilometer >= 100000) {
            reduction = 0.75;
        }
        if (kilometer >= 200000) {
            reduction = 0.50;
        } else {
            reduction = 0.90;
        }

        return reduction;

    }

    public static int addkilometer(ArrayList<String> list, int kilometer, boolean isNew, Scanner clavier) {

        addkilometer: while (true) {
            try {
                if (isNew) {

                    System.out.println("la voiture est neuf par défaut son kilométrage vaut 6000 km");
                    kilometer = 6000;

                } else {

                    System.out.println("Veuillez renseigner son kilométrage (minimum : 6001 km)");

                    kilometer = clavier.nextInt();

                    if (kilometer < 6001) {

                        System.out
                                .println("Le kilométrage est trop bas pour une occasion, nous l'avons mis à 6001 km ");
                        kilometer = 6001;
                    } else {

                        System.out.println("Kilométrage(km) : " + kilometer + " km");

                    }

                }

                return kilometer;

            } catch (Exception e) {

                Exceptioner.TxtException(e, 0);
                clavier.nextLine();
                continue addkilometer; // Recommencer la saisie du type

            }

        }

    }

    public static String addModel(ArrayList<String> list, int brandid, String[][] model, Scanner clavier) {
        // Implémentez la logique pour ajouter un produit avec son type
        // Par exemple, vous pouvez créer une nouvelle entrée dans la liste avec le type
        // du produit
        String modelName = "";
        addProductType: while (true) {
            try {
                System.out.println("Brand id :" + brandid);
                System.out.println("Modele de la voiture :");

                for (int i = 0; i < model[brandid].length; i++) {
                    System.out.println("(" + (i + 1) + "). " + model[brandid][i]);
                }

                System.out.print("Choix du modele (entre 1 et " + model[brandid].length + "):");
                int typeChoice = clavier.nextInt();

                modelName = model[brandid][typeChoice - 1];
                System.out.println("Vous avez choisi le modèle  : " + modelName);

            } catch (Exception e) {

                Exceptioner.TxtException(e, model.length);
                clavier.nextLine();
                continue addProductType; // Recommencer la saisie du type

            }

            return modelName;
        }

    }

    public static String addProductDate(ArrayList<String> list, String date, Scanner clavier) {
        // Implémentez la logique pour ajouter une date de stockage
        // Par exemple, vous pouvez créer une nouvelle entrée dans la liste avec la date
        // du produit
        addDate: while (true) { // Boucle pour recommencer la saisie de la date en cas d'erreur
            try {

                System.out.println("Date de stockage (DD/MM/YYYY) : ");
                date = clavier.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate.parse(date, formatter); /* On test si le format est bon */
                System.out.println("Date de stockage choisie : " + date);

            } catch (Exception e) {

                Exceptioner.TxtException(e, list.size());
                continue addDate; // Recommencer la saisie de la date

            }

            return date;
        }

    }

    public static void removeCar(ArrayList<String> list, Scanner clavier) {
        // Implémentez la logique pour supprimer un produit de la liste
        // Par exemple, vous pouvez utiliser catId pour identifier le produit à
        // supprimer
        removedCar: while (true) { // Boucle pour recommencer la saisie de la date en cas d'erreur
            int carId = 0;
            try {

                if (list.isEmpty()) {
                    System.out.println(
                            "Veuillez d'abord ajouter une voiture dans votre liste (1).");
                    break removedCar; // Sort de la méthode si la liste est vide
                }

                System.out.println("Entrez l'ID du produit à supprimer : ");
                carId = clavier.nextInt();

                String removedCar = list.remove(carId - 1);
                System.out.println("Produit supprimé : " + removedCar);

                if (again(clavier) == true) {

                    continue removedCar;

                } else {
                    break removedCar;

                }

            } catch (Exception e) {

                Exceptioner.TxtException(e, list.size());

                clavier.nextLine(); // Pour consommer la nouvelle ligne restante
                continue removedCar;
            }

        }

    }

    public static void displayTab(String[] tab) {

        int i = 1;

        for (String value : tab) {

            System.out.println("("+i+")" + "-" + value);

            i++;
        }
    }


        public static void displayTab2(String[][] tab) {

        int i;

        String price;

        for (i=0; i<tab.length;i++) {

            if(Integer.parseInt(tab[i][1]) == 0){

                price = "( Gratuit )";

            }else{

                price = " ( +"+(tab[i][1])+" euros )";


            }

            System.out.println("("+i+")" + "-" + tab[i][0]+price);

        }
    }

    public static void searchCarById(ArrayList<String> list, Scanner clavier) {
        // Implémentez la logique pour rechercher un produit par son ID
        // Par exemple, vous pouvez afficher les détails du produit correspondant à l'ID
        searchCarById: while (true) { // Boucle pour recommencer la saisie de l'ID en cas d'erreur
            int catId = 0;
            try {

                System.out.println("Entrez l'ID de la voiture : ");
                catId = clavier.nextInt();

                String productDetails = list.get(catId - 1);
                System.out.println("Détails de la voiture : " + productDetails);

                if (again(clavier) == true) {

                    continue searchCarById;

                } else {

                    break searchCarById;

                }

            } catch (Exception e) {

                Exceptioner.TxtException(e, list.size());
                clavier.nextLine(); // Pour consommer la nouvelle ligne restante
                continue searchCarById;
            }

        }

    }

}
