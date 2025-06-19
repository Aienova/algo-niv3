package Intro;


import java.util.ArrayList;
import methods.Functions;

public class ArrayListDemo {


    public static void main(String[] args) {

   ArrayList<String> stagiaires = new ArrayList<>();

   stagiaires.add("Benjamin");
   stagiaires.add("Léa"); 
   stagiaires.add("Jérémie"); 
   stagiaires.add("Mamadou"); 
   stagiaires.add("Iliyana"); 

   Functions.ListeDesNoms(stagiaires);
   System.err.println(Functions.TrouverUnNoms(stagiaires,"Léa"));


    }
}
