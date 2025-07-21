package Data;

import java.util.List;
import java.util.ArrayList;

import General.Pokemon;

public class Player {
    
    private String player_id;
    private int current_score;
    private List<Pokemon> pokemonAttained; 
    private int totalMedals;

    // Constructor for creating a new player
    public Player(String playerId, int current_score, List<Pokemon> pokemonAttained, int totalMedals) {
        this.player_id = playerId;
        this.current_score = current_score;
        this.pokemonAttained = (pokemonAttained != null) ? pokemonAttained : new ArrayList<>();
        this.totalMedals = totalMedals;
    }
    
    // Constructor for creating a player with integer ID (if needed)
   
    public Player(int playerId, int currentScore, List<Pokemon> pokemonAttained, int totalMedals) {
        this.player_id = String.valueOf(playerId);  // Convert integer ID to string
        this.current_score = currentScore;
        this.totalMedals = 0; // Default value if not used
    }

    

	// Getter and setter methods
    public void setPlayerId(String player_id) {
        this.player_id = player_id;
    }
    
    public String getPlayerId() {
        return player_id;
    }
    
    public void setCurrentScore(int current_score) {
        this.current_score = current_score;
    }
    
    public int getCurrent_score() {
        return current_score;
    }
    
    public List<Pokemon> getPokemonAttained() {
        return pokemonAttained;
    }
    
    public void addPokemon(Pokemon pokemon) {
        pokemonAttained.add(pokemon);
    }


    public void setTotalMedals(int totalMedals) {
        this.totalMedals = totalMedals;
    }
    
    public int getTotalMedals() {
        return totalMedals;
    }
    
    
    @Override
    public String toString() {
        return "Player{" +
                "player_id='" + player_id + '\'' +
                ", current_score=" + current_score +
                ", pokemonAttained='" + pokemonAttained + '\'' +
                ", totalMedals=" + totalMedals +
                '}';
    }


}
