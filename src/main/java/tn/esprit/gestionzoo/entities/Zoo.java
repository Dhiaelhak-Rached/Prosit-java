package tn.esprit.gestionzoo.entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import tn.esprit.gestionzoo.exceptions.*;
import tn.esprit.gestionzoo.interfaces.AnimalType;

public class Zoo {
	private TreeMap<Integer, TreeSet<String>> animalsCage;

	private Animal[] animals;
	private String name;
	private String city;
	private int nbrCage;
	private int numberOfAnimals;
	List<Animal> maListe = new ArrayList<Animal>();
	public Zoo(int nbrAnimals, String name, String city, int nbrCage) {
		if (nbrAnimals > 50) {
			System.err.println("zoo cant have more than 50 animals, setting to 50 animals");
			this.animals = new Animal[50];
			this.maListe = new ArrayList<Animal>();
		} else if (nbrAnimals>0){
			this.animals = new Animal[nbrAnimals];
			this.maListe = new ArrayList<Animal>();

		}else {
			this.animals = new Animal[0];
			this.maListe = new ArrayList<Animal>();

		}
		this.name = name;
		this.city = city;
		setNbrCage(nbrCage);

		animalsCage = new TreeMap<>();

        for (int i = 1; i <= 3; i++) {
            animalsCage.put(i, new TreeSet<>());
        }




	}
	
	
	// public void addCage(int cageNumber) {
    //     if (cageNumber > 0 && cageNumber <= 20) {
    //         animalsCage.put(cageNumber, new TreeSet<>());
    //     } else {
    //         System.out.println("Le nombre de cage doit être compris entre 1 et 20.");
    //     }
    // }


	public void addCage(int cageNumber) {
        if (cageNumber > 0 && cageNumber <= 20) {
            animalsCage.put(cageNumber, new TreeSet<>());
            System.out.println("Cage ajoutée avec succès. Nombre de cages actuel: " + getNbrCage());
        } else {
            System.out.println("Le nombre de cage doit être compris entre 1 et 20. Nombre de cages actuel: " + getNbrCage());
        }
    }

    public int getNbrCage() {
        return animalsCage.size();
    }


    public void affectAnimalToCage(int cageNumber, Animal animal) {
        if (animalsCage.containsKey(cageNumber)) {
            if (animalsCage.containsValue(animal.getName())) {
                animalsCage.get(cageNumber).add(animal.getName());
                System.out.println("L'animal " + animal.getName() + " a été affecté à la cage " + cageNumber + " avec succès.");
            } else {
                System.out.println("L'animal " + animal.getName() + " n'existe pas dans le zoo.");
            }
        } else {
            System.out.println("La cage " + cageNumber + " n'existe pas.");
        }
    }



	public ArrayList<String> getNonFreeAnimals() {
        ArrayList<String> nonFreeAnimals = new ArrayList<>();

        for (Map.Entry<Integer, TreeSet<String>> entry : animalsCage.entrySet()) {
            nonFreeAnimals.addAll(entry.getValue());
        }

        return nonFreeAnimals;
    }

    public ArrayList<String> getFreeAnimals() {
        ArrayList<String> freeAnimals = new ArrayList<>();

        for (Animal animal : Animal.getAllAnimals()) {
            if (!getNonFreeAnimals().contains(animal.getName())) {
                freeAnimals.add(animal.getName());
            }
        }

        return freeAnimals;
    }
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	//public int getNbrCage() {
	//	return nbrCage;
	//}



	public void setNbrCage(int nbrCage) {
		if (nbrCage < 10) {
			System.err.println("zoo cant have less than 10 cages, setting to 10 cages");
		} else if (nbrCage > 20) {
			System.err.println("zoo cant have more than 20 cages, setting to 20 cages");
		}
	}



	public void displayZoo() {
		System.out.println("name : " + this.name);
		System.out.println("city : " + this.city);
		System.out.println("number of cages : " + this.nbrCage);
		System.out.println("number of Animals : " + this.numberOfAnimals);
	}

	//public boolean addAnimal(Animal an) throws FullZooException {
		// check array full
	//	if (numberOfAnimals == animals.length) {
	//		throw new FullZooException("Zoo is full");
	//	}
	//	if (searchAnimal(an) != -1) {
	//		System.out.println("Animal exists");
	//		return false;
	//	}
	//	this.animals[numberOfAnimals] = an;
	//	this.numberOfAnimals++;
	//	return true;
	//}







	public boolean addAnimal(Animal an) throws FullZooException {
		// check array full
		if (numberOfAnimals == maListe.size()) {
			throw new FullZooException("Zoo is full");
		}
		if (searchAnimal(an) != -1) {
			System.out.println("Animal exists");
			return false;
		}
		maListe.add(an);
		this.numberOfAnimals++;
		return true;
	}















	
	//public boolean removeAnimal(Animal an) {
	//	int index = searchAnimal(an);
		//if (index == -1) {
	//		return false;
	//	}
	//	for (int i = index; i < numberOfAnimals - 1; i++) {
	//		animals[i] = animals[i + 1];
	//	}
	//	numberOfAnimals--;
	//	return true;
	//}

	public boolean removeAnimal(Animal an) {
		return maListe.remove(an);
	}

	public void displayAnimals() {
		for (int i = 0; i < this.numberOfAnimals; i++) {
			System.out.println(maListe.get(i));
		}
	}
	
	public void displayAllPredators() {
		System.out.println("Prédateurs : ");
		for (int i = 0; i < this.numberOfAnimals; i++) {
			if(((AnimalType)(maListe.get(i))).estPredateur()) {
				System.out.println(maListe.get(i));
			}
		}
	}
	public void displayAllPreys() {
		try {
			for (int i = 0; i < maListe.size(); i++) {
				Animal animal = maListe.get(i);
				if (animal instanceof AnimalType) {
					AnimalType animalType = (AnimalType) animal;
					if (!animalType.estPredateur()) {
						System.out.println(animalType.toString());
					}
				}
			}
		} catch (ClassCastException e) {
			int index = -1;
			for (int i = 0; i < maListe.size(); i++) {
				if (maListe.get(i).toString().equals(e.getMessage())) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				Animal animal = animals[index];
				System.out.println("l'animal de type : " + animal.getFamily() + " qui s'appelle " + animal.getName() + " n'est pas défini comme prédateur ou proie");
			}
		}
	}
	
	
	public int searchAnimal(Animal an) {
		return maListe.indexOf(an);
	}

	@Override
	public String toString() {
		return "Zoo [" + " name=" + name + ", city=" + city + ", nbrCage=" + nbrCage + ", numberOfAnimals="
				+ numberOfAnimals + "]";
	}

}
