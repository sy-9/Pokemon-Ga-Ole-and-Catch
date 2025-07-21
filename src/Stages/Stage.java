package Stages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import General.Pokemon;

public abstract class Stage {
    protected List<String> rarePokemon = new ArrayList<>();
    private String targetType;

    public Stage(String targetType) {
        this.targetType = targetType;
        loadPokemonDetails();
    }

    
    private void loadPokemonDetails() {
        try {
            File file = new File("pokemonDetails.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");
                String name = details[1];
                String type = details[2];
                String rarity = details[4];

                if (targetType.equals(type) && "Rare".equals(rarity)) {
                    rarePokemon.add(name);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading Pokémon details.");
        }
    }


    public abstract String getPokemon();

	public abstract boolean isPokemonEligible(Pokemon pokemon);
}

