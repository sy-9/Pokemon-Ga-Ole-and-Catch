package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WildPokemonGenerator {

    private List<String[]> pokemonDetails;

    public WildPokemonGenerator(List<String[]> pokemonDetails) {
        this.pokemonDetails = pokemonDetails;
    }

    public List<String[]> generateWildPokemon(int stageOption) {
        List<String[]> wildPokemons = new ArrayList<>();
        Random random = new Random();

        
        // Convert stageOption to a stage type string
        String stageType = mapStageOptionToType(stageOption);

        // Filter Pokémon by the given stage type
        List<String[]> stagePokemon = pokemonDetails.stream()
                .filter(p -> p[2].equalsIgnoreCase(stageType))
                .collect(Collectors.toList());

        // Check if stagePokemon list is empty
        if (stagePokemon.isEmpty()) {
            throw new IllegalArgumentException("No Pokémon found for the specified stage type: " + stageOption);
        }

        // Randomly pick one Pokémon of the same type as the stage
        String[] wildPokemon1 = stagePokemon.get(random.nextInt(stagePokemon.size()));

        // Randomly pick one Pokémon from the entire list
        List<String[]> allPokemonDetails = new ArrayList<>(pokemonDetails);

        // Check if allPokemonDetails list is empty
        if (allPokemonDetails.isEmpty()) {
            throw new IllegalArgumentException("No Pokémon available in the database.");
        }

        String[] wildPokemon2 = allPokemonDetails.get(random.nextInt(allPokemonDetails.size()));

        // Create a list to hold the two wild Pokémon
        wildPokemons.add(wildPokemon1);
        wildPokemons.add(wildPokemon2);

        return wildPokemons;
    }
    
    private String mapStageOptionToType(int stageOption) {
        switch (stageOption) {
            case 1:
                return "Rock";   // Map to Rock type for Land Stage
            case 2:
                return "Water";  // Map to Water type for Ocean Stage
            case 3:
                return "Grass";  // Map to Grass type for Forest Stage
            case 4:
                return "Fire";   // Map to Fire type for Sky Stage
            default:
                throw new IllegalArgumentException("Invalid stage option: " + stageOption);
        }
    }
    
    public String formatPokemonDetails(String[] pokemon) {
        return String.format(
            "Pokemon: %s\nPokemonID: %s\nHP: %s\nGrade: %s\nDMG: %s\nMove Type: %s\n",
            pokemon[1], pokemon[0], pokemon[3], pokemon[4], pokemon[6], pokemon[2]
        );
    }
}
