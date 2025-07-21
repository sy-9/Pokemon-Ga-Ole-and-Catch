package Game;

public class PokemonGaOle {
    public static void main(String[] args) {
        // Create a new instance of GameController
        GameController controller = new GameController();
        
        // Load Pokémon data from file
        GameController.loadPokemonData();
        
        // Start the game
        controller.startGame();
    }
}


