package Game;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CatchTime {

	 private List<String[]> allPokemonDetails;

	    public CatchTime(List<String[]> pokemonDetails) {
	        this.allPokemonDetails = pokemonDetails;
	    }

	    public String promptPlayerToChoosePokemon() {
	        Random rand = new Random();
	        List<String[]> selectedPokemon = new ArrayList<>();

	        // Randomly select 3 Pokémon
	        for (int i = 0; i < 3; i++) {
	            selectedPokemon.add(allPokemonDetails.get(rand.nextInt(allPokemonDetails.size())));
	        }

	        // Display the Pokémon details and prompt user to choose
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("==================================================================");
	        System.out.println("CATCH TIME!");
	        System.out.println("==================================================================");
	        for (int i = 0; i < selectedPokemon.size(); i++) {
	            String[] details = selectedPokemon.get(i);
	            System.out.printf("\n[%d] %s\nPokemonCode: %s\nHP: %s\nGrade: %s\nDMG: %s\nMove Type: %s\n",
	                    (i + 1), details[1], details[0], details[3], details[4], details[6], details[2]);
	        }

	        System.out.print("Choose 1 Pokemon to bring into battle: ");
	        int choice = scanner.nextInt() - 1;

	        String[] chosenPokemonDetails = selectedPokemon.get(choice);
	        System.out.println("You have Chosen " + chosenPokemonDetails[1]);

	        return chosenPokemonDetails[1]; // Return the Pokémon name
	    }
	}





