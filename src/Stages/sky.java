package Stages;

import java.util.Random;

import General.Pokemon;

public class sky extends Stage {

    public sky() {
        super("Fire");
    }

    @Override
    public String getPokemon() {
        Random rand = new Random();
        if (!rarePokemon.isEmpty()) {
            return rarePokemon.get(rand.nextInt(rarePokemon.size()));
        }
        return "No rare Fire-type Pokémon available";
    }

	@Override
	public boolean isPokemonEligible(Pokemon pokemon) {
		// TODO Auto-generated method stub
		return false;
	}
}
