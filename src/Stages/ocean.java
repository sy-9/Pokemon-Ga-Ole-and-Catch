package Stages;

import java.util.Random;

import General.Pokemon;

public class ocean extends Stage {

    public ocean() {
        super("Water");
    }

    @Override
    public String getPokemon() {
        Random rand = new Random();
        if (!rarePokemon.isEmpty()) {
            return rarePokemon.get(rand.nextInt(rarePokemon.size()));
        }
        return "No rare Water-type Pokémon available";
    }

	@Override
	public boolean isPokemonEligible(Pokemon pokemon) {
		// TODO Auto-generated method stub
		return false;
	}
}
