package General;

public class Pokemon {
	private String name;
    private int id;
    private int hp;
    private int def;
    private int dmg;
    private String rarity;
    private String pokemontype;
    private String movetype;
    private String moveName;

    // Updated constructor
    public Pokemon(int id, String name, String pokemontype, int hp, String rarity, String movetype, int dmg, int def) {
        this.id = id;
        this.name = name;
        this.pokemontype = pokemontype;
        this.hp = hp;
        this.rarity = rarity;
        this.movetype = movetype;
        this.dmg = dmg;
        this.def = def;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public String getPokemontype() {
        return pokemontype;
    }

    public void setPokemontype(String pokemontype) {
        this.pokemontype = pokemontype;
    }

    public String getMoveType() {
        return movetype;
    }

    public void setMoveType(String movetype) {
        this.movetype = movetype;
    }
    
    public String getRarity() {
    	return rarity;
    }
    
    public void setRarity(String rarity) {
    	this.rarity = rarity;
    }

    @Override
    public String toString() {
        return String.format("Pokemon [name=%s, id=%d, hp=%d, def=%d, dmg=%d, pokemontype=%s]", 
            name, id, hp, def, dmg, pokemontype);
    }
}
