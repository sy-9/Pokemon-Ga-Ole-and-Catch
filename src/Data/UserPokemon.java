package Data;

import General.Pokemon;
import Data.UserPokemon;
import Data.OpponentPokemon;


public class UserPokemon extends Pokemon {
	public UserPokemon(int id, String name, String pokemontype, int hp, String rarity, String movetype, int dmg, int def) {
		super(id, name, pokemontype, hp, rarity, movetype, dmg, def);
	}

//	    private String name;
//	    private int id;
//	    private int hp;
//	    private int def;
//	    private int dmg;
//	    private String pokemontype;
//	    private String rarity;
//	    private String movetype;

//	    public UserPokemon(int id, String name, String pokemontype, int hp, String rarity, String movetype, int dmg, int def) {
//	        super(id, name, pokemontype, hp, rarity, movetype, dmg, def);
//	    }
//
//	    // Copy constructor
//	    public UserPokemon(UserPokemon player) {
//	        super(player.getId(), player.getName(), player.getPokemontype(), player.getHp(), player.getRarity(), player.getMoveType(), player.getDmg(), player.getDef());
//	    }
//
//	    // Constructor to create UserPokemon from Pokemon object
//	    public UserPokemon(Pokemon pokemon) {
//	        super(pokemon.getId(), pokemon.getName(), pokemon.getPokemontype(), pokemon.getHp(), pokemon.getRarity(), pokemon.getMoveType(), pokemon.getDmg(), pokemon.getDef());
//	    }
//
//	    // Getters and setters
//	    public String getName() {
//	        return name;
//	    }
//
//	    public void setName(String name) {
//	        this.name = name;
//	    }
//
//	    public int getPokemonid() {
//	        return id;
//	    }
//
//	    public void setPokemonid(int pokemonid) {
//	        this.id = pokemonid;
//	    }
//
//	    public int getHp() {
//	        return hp;
//	    }
//
//	    public void setHp(int hp) {
//	        this.hp = hp;
//	    }
//
//	    public int getDef() {
//	        return def;
//	    }
//
//	    public void setDef(int def) {
//	        this.def = def;
//	    }
//
//	    public int getDmg() {
//	        return dmg;
//	    }
//
//	    public void setDmg(int dmg) {
//	        this.dmg = dmg;
//	    }
//
//	    public String getPokemonType() {
//	        return pokemontype;
//	    }
//
//	    public void setPokemonType(String pokemontype) {
//	        this.pokemontype = pokemontype;
//	    }
//
//	    public String getMoveType() {
//	        return movetype;
//	    }
//
//	    public void setMoveType(String movetype) {
//	        this.movetype = movetype;
//	    }
//
//	    @Override
//	    public String toString() {
//	        return String.format(
//	            "UserPokemon [name=%s, id=%s, hp=%s, def=%s, dmg=%s, pokemontype=%s, rarity=%s, movetype=%s]", 
//	            name, id, hp, def, dmg, pokemontype, rarity, movetype
//	        );
//	    }
}

