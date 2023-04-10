package tn.esprit.gestionzoo.main;
import java.util.Scanner;
import tn.esprit.gestionzoo.exceptions.*;
import tn.esprit.gestionzoo.entities.Animal;
import tn.esprit.gestionzoo.entities.Lion;
import tn.esprit.gestionzoo.entities.Perroquet;
import tn.esprit.gestionzoo.entities.Zoo;
import tn.esprit.gestionzoo.entities.Singe;
import java.util.TreeMap;
import java.util.TreeSet;


public class ZooManagement {

	
		public static void main(String[] args){
			Zoo myZoo = new Zoo(0, null, null, 0);
			myZoo.addCage(1);
			myZoo.addCage(2);
			System.out.println("Total number of cages: " + myZoo.getNbrCage());

			Animal lion = new Lion("Felidae", "Simba1", 3, true, 4);
			Animal lion1 = new Lion("Felidae", "Simba2", 3, true, 4);
			Animal lion2 = new Lion("Felidae", "Simba3", 3, true, 4);

			
	
			myZoo.affectAnimalToCage(1, lion);
			myZoo.affectAnimalToCage(1, lion1);
			myZoo.affectAnimalToCage(2, lion2);
	
			System.out.println("Non-free animals: " + myZoo.getNonFreeAnimals());
			System.out.println("Free animals: " + myZoo.getFreeAnimals());
		}
	
	

}
