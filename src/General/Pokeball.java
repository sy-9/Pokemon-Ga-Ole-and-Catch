package General;
import java.util.Random;

public class Pokeball {
	private int randomPokeball;
	private String pokeballType; 
	private double catchRate;
	
	Random random = new Random();

	public Pokeball() {
		randomPokeball = random.nextInt(99)+ 1;
		
		if ((randomPokeball >= 0) && (randomPokeball <=100)) {
			if (randomPokeball <= 30) {
				pokeballType = "Normal PokeBall";
				catchRate = 100;
			}
			else if (randomPokeball <= 60) {
				pokeballType = "GreatBall";
				catchRate = 250;
			}
			else if (randomPokeball <= 80) {
				pokeballType = "UltraBall";
				catchRate = 550;
			}
			else if (randomPokeball <= 100) {
				pokeballType = "MasterBall";
				catchRate = 1000;
			}
		}	
	}
	
	public String getPokeballType() {
		return pokeballType;
	}
	
	public double getCatchRate() {
		return catchRate;
	}
	
	@Override
	public String toString() {
		return String.format("You have been given a %s to catch the pokemon!", pokeballType);
	}

}


