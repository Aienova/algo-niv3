package jalon.executer;

import java.util.ArrayList;
import java.util.Scanner;

import jalon.methods.MenuCars;

public class mainJalonDWWM {


    public static final String [] brand = {"Volkswagen","Audi","Porsche","Lamborghini"};

    public static final String [][] model = 
    
    {
        {"Polo" , "Tiguan" , "Golf"},
        {"A3" , "Q5" , "A4"},
        {"Macan" , "Carrera"},
        {"Huracán", "Aventador"}

    };

        public static final String [][] prices = 
    
    {
        {"Polo" , "29000"},
        {"Tiguan" , "36000"},
        {"Golf" , "23000"},
        {"A3", "34000"},
        {"Q5", "54000"},
        {"A4", "43000"},
        {"Macan", "70000"},
        {"Carrera", "120000"},
        {"Aventador", "120000"},
        {"Huracán", "260000"}
    };



    /* Couleurs Tab String : Blanc (+500€) , Noir (Gratuit) , Rouge (+1000€), Bleu (+2000€)  */

    public static final String [][] colors =

        {
            {"Blanc" , "500"},
            {"Noir" , "0"},
            {"Rouge" , "1000"},       
            {"Bleu" , "2000"}
        };

    /* Les variables du programme */
    
    public static boolean isNew;

    public static ArrayList<String> carsList = new ArrayList<String>();

    public static int kilometer;

    public static String code;

    public static int finalPrice;


    public static void main(String[] args) {

       System.out.println("Bienvenu dans l'inventaire de votre concession :");

       Scanner scanner = new Scanner(System.in, "Cp852");

       boolean continueAdding = true;

       int option=0;

        MenuCars. Menu(continueAdding, brand, model,colors, isNew, kilometer,
            code, prices, scanner, option, carsList);

        }


        
    }
    


/*
 * A l’aide des ArrayList, des méthodes, des conditions et des boucles, vous allez me créer un algorithme pour me faire, une liste de voiture à vendre, voici les informations à renseigner : 

 

Nom de la marque (un tableau String ) : [ Wolkswagen, Audi, Porsche, Lamborghini ] 

Nom du modèle en fonction de la marque (Un tableau par marque) :  

Volkswagen : Polo , Tiguan , Golf 

Audi : A3 , Q5 , A4 

Porsche : Macan , Carrera 

Lamborghini : Huracán, Aventador 

Est-il neuf ? : True or False , si il est en occasion il faut ajouter son kilométrage, une réduction de -10% ou plus selon le kilométrage. 

Kilométrage de la voiture (en int ) : Si le kilométrage atteint les 100 000 km, une réduction de 25% du prix est appliqué, une réduction de 50% si il dépasse les 200 000 km, sinon par défaut -10% 

 

Couleurs Tab String : Blanc (+500€) , Noir (Gratuit) , Rouge (+1000€), Bleu (+2000€) 

 

Code du produit : Deux première lettre de la marque en majuscule + Deux première lettre du modèle en majuscule + date et heure d’aujourd’hui à ce format : MaMaMdMd-AAAA-MM-JJ 

 

Prix initial : En fonction du modèle choisi précédemment
 */
