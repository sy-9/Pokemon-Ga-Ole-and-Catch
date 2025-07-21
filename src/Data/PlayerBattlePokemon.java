package Data;

public class PlayerBattlePokemon {

	  private String[] pokemon1;
	    private String[] pokemon2;

	    public PlayerBattlePokemon(String[] pokemon1, String[] pokemon2) {
	        this.pokemon1 = pokemon1;
	        this.pokemon2 = pokemon2;
	    }

	    public String getPokemon1Details() {
	        return formatPokemonDetails(pokemon1);
	    }

	    public String getPokemon2Details() {
	        return formatPokemonDetails(pokemon2);
	    }

	    private String formatPokemonDetails(String[] pokemon) {
	        if (pokemon == null || pokemon.length < 7) {
	            return "Details not available.";
	        }
	        return String.format(
	            "Pokemon: %s\nPokemonID: %s\nHP: %s\nGrade: %s\nDMG: %s\nMove Type: %s\n\",\r\n"
	            + pokemon[1],pokemon[0], pokemon[3], pokemon[4], pokemon[6], pokemon[2]
	        );
	    }

	    @Override
	    public String toString() {
	        return "Pokemon 1:\n" + getPokemon1Details() + "\n" +
	               "Pokemon 2:\n" + getPokemon2Details();
	    }
	}

