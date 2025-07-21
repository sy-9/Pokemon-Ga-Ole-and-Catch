package Data;
import java.util.ArrayList;
import java.util.List;

public class PlayerRecord {
    private String player_id; //change to String
    private int score;
    private List<String> pokemons_attained;
    private int medals;

    public PlayerRecord() {
        this.pokemons_attained = new ArrayList<>();
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getPokemons_attained() {
        return pokemons_attained;
    }

    public void setPokemons_attained(List<String> pokemons_attained) {
        this.pokemons_attained = pokemons_attained;
    }

    public void addPokemon(String pokemon) {
        this.pokemons_attained.add(pokemon);
    }
    
    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    @Override
    public String toString() {
        return player_id + "," + score + ",[" + String.join("; ", pokemons_attained) + "]," + medals;
    }

    public static PlayerRecord fromString(String record) {
        String[] parts = record.split(",", 4);
        PlayerRecord pr = new PlayerRecord();
        pr.setPlayer_id(parts[0]);
        pr.setScore(Integer.parseInt(parts[1]));
        if (parts.length > 2) {
            String[] pokemons = parts[2].replace("[", "").replace("]", "").split(";");
            for (String pokemon : pokemons) {
                pr.addPokemon(pokemon.trim());
            }
        }
        if (parts.length > 3) {
            pr.setMedals(Integer.parseInt(parts[3]));
        }
        return pr;
    }
}