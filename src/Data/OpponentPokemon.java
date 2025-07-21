package Data;

import General.Pokemon;
import Data.UserPokemon;
import Data.OpponentPokemon;


public class OpponentPokemon extends Pokemon {
	
	public OpponentPokemon(int id, String name, String pokemontype, int hp, String rarity, String movetype, int dmg, int def) {
		super(id, name, pokemontype, hp, rarity, movetype, dmg, def);
	}
//	private String name;
//    private int pokemonid;
//    private int hp;
//    private int dmg;
//    private String rarity;
//    private String pokemontype;
//    private String movetype;
//
//    // Default constructor
//    public OpponentPokemon(int id, String name, String pokemontype, int hp, String rarity, String movetype, int dmg, int def) {
//        super(id, name, pokemontype, hp, rarity, movetype, dmg, def); // Adjusted to match the Pokemon constructor
//    }
//
//    // Copy constructor
//    public OpponentPokemon(OpponentPokemon enemy) {
//        super(enemy.getId(), enemy.getName(), enemy.getPokemontype(), enemy.getHp(), enemy.getRarity(), enemy.getMoveType(), enemy.getDmg(), enemy.getDef());
//    }
//
//    // Constructor to initialize from a Pokemon object
//    public OpponentPokemon(Pokemon opponentPokemon) {
//        super(opponentPokemon.getId(), opponentPokemon.getName(), opponentPokemon.getPokemontype(), opponentPokemon.getHp(), opponentPokemon.getRarity(), opponentPokemon.getMoveType(), opponentPokemon.getDmg(), opponentPokemon.getDef());
//    }
//
//    // Getters and setters
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getPokemonid() {
//        return pokemonid;
//    }
//
//    public void setPokemonid(int pokemonid) {
//        this.pokemonid = pokemonid;
//    }
//
//    public int getHp() {
//        return hp;
//    }
//
//    public void setHp(int hp) {
//        this.hp = hp;
//    }
//
//    public int getDmg() {
//        return dmg;
//    }
//
//    public void setDmg(int dmg) {
//        this.dmg = dmg;
//    }
//
//    public String getRarity() {
//        return rarity;
//    }
//
//    public void setRarity(String rarity) {
//        this.rarity = rarity;
//    }
//
//    public String getPokemontype() {
//        return pokemontype;
//    }
//
//    public void setPokemontype(String pokemontype) {
//        this.pokemontype = pokemontype;
//    }
//
//    public String getMovetype() {
//        return movetype;
//    }
//
//    public void setMovetype(String movetype) {
//        this.movetype = movetype;
//    }
//    
//    @Override
//    public String toString() {
//        return String.format(
//            "OpponentPokemon [name=%s, pokemonid=%s, hp=%s, dmg=%s, rarity=%s, pokemontype=%s, movetype=%s]", 
//            name, pokemonid, hp, dmg, rarity, pokemontype, movetype
//        );
//    }
//    
//    // Placeholder method for getLevel
//    public String getLevel() {
//        return null;
//    }
}

