package Game;

import General.Pokemon;
import Data.Player;
import Data.UserPokemon;
import Data.OpponentPokemon;
import Data.Database;
import Data.PlayerRecord;
import Stages.Stage;
import Stages.forest;
import Stages.land;
import Stages.ocean;
import Stages.sky;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class GameController {
    private static final String DATABASE_FILE = "player_database.txt";
    private static final String POKEMON_DETAILS = "pokemonDetails.txt";
    private static List<Pokemon> pokemonList = new ArrayList<>();
    private String player_id;

    public void startGame() {
        // Create Scanner for user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================================");
        System.out.println("Welcome to Pokemon Ga Ole Battle and Catch Console Edition!");
        System.out.println("==================================================================");
        System.out.print("Press ENTER to start the game!");
        scanner.nextLine();

        System.out.print("Enter PlayerID >> ");
        player_id = scanner.nextLine();

        Player player = getPlayerData(player_id);
        if (player == null) {
            player = new Player(player_id, 0, new ArrayList<>(), 0);
            savePlayerData(player);
        } else {
            System.out.println("Welcome back, " + player_id + "!");
        }

        System.out.println("\n====================");
        System.out.println("= Welcome " + player_id + "!    =");
        System.out.println("====================");

        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n—------------------------------------------------------------------------------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("—------------------------------------------------------------------------------------------------------------------");
            System.out.println("[1] View Current Top Score");
            System.out.println("[2] Start Game");
            System.out.print("Enter Option Number >> ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    displayTopScores();
                    break;
                case 2:
                    playGame(scanner, player);
                    continueMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("\nGame Over! Thank you for playing.");
        scanner.close();
    }

    private void playGame(Scanner scanner, Player player) {
        System.out.println("==================================================================");
        System.out.println("GAME START - CHOOSE STAGE");
        System.out.println("==================================================================");

        Stage[] stages = {new land(), new ocean(), new forest(), new sky()};
        for (int i = 0; i < stages.length; i++) {
            System.out.printf("[%d] %s STAGE%n", (i + 1), stages[i].getClass().getSimpleName().toUpperCase());
            System.out.printf("Pokemon that may appear: %s%n", stages[i].getPokemon());
            System.out.println();
        }

        System.out.print("Enter your desired stage number >> ");
        int stageOption = scanner.nextInt();
        scanner.nextLine();

        if (stageOption < 1 || stageOption > stages.length) {
            System.out.println("Invalid option. Please try again.");
            return;
        }

        Stage selectedStage = stages[stageOption - 1];
        System.out.println("Selected stage: " + selectedStage.getClass().getSimpleName().toUpperCase());

        List<String[]> pokemonDetails = loadPokemonDetails(POKEMON_DETAILS);
        Database db = new Database();
        db.loadDB();

        PlayerRecord playerRecord = db.searchPlayer(player_id);
        if (playerRecord == null) {
            playerRecord = new PlayerRecord();
            playerRecord.setPlayer_id(player_id);
            playerRecord.setScore(0);
            db.addPlayer(playerRecord);
            System.out.println("New player record created.");
        } else {
            System.out.println("Player record found.");
        }

        CatchTime catchTime = new CatchTime(pokemonDetails);
        String chosenPokemon = catchTime.promptPlayerToChoosePokemon();

        playerRecord.addPokemon(chosenPokemon);
        db.storeDB();

        System.out.println("Player's record updated with " + chosenPokemon);

        WildPokemonGenerator generator = new WildPokemonGenerator(pokemonDetails);
        List<String[]> wildPokemons = generator.generateWildPokemon(stageOption);

        try {
            System.out.println("————————————————————————————————————————————————————————————————————");
            System.out.println("2 wild Pokemons appeared!");
            System.out.println("Opponent 1:\n" + generator.formatPokemonDetails(wildPokemons.get(0)));
            System.out.println("Opponent 2:\n" + generator.formatPokemonDetails(wildPokemons.get(1)));
            System.out.println("————————————————————————————————————————————————————————————————————");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Your available Pokemons:");
        List<String[]> playerPokemons = playerRecord.getPokemons_attained().stream()
                .map(pokemonName -> pokemonDetails.stream()
                        .filter(p -> p[1].equalsIgnoreCase(pokemonName))
                        .findFirst()
                        .orElse(new String[0]))
                .collect(Collectors.toList());

        for (int i = 0; i < playerPokemons.size(); i++) {
            String[] details = playerPokemons.get(i);
            System.out.printf("[%d] %s\nPokemonCode: %s\nHP: %s\nType: %s\nRarity: %s\nMove: %s\nDMG: %s\nDEF: %s\n",
                    (i + 1), details[1], details[0], details[3], details[2], details[4], details[5], details[6], details[7]);
            System.out.println();
        }

        int firstPokemonChoice;
        int secondPokemonChoice;

        do {
            System.out.print("Choose your 1st pokemon: ");
            firstPokemonChoice = scanner.nextInt() - 1;
            scanner.nextLine();
        } while (firstPokemonChoice < 0 || firstPokemonChoice >= playerPokemons.size());

        do {
            System.out.print("Choose your 2nd pokemon: ");
            secondPokemonChoice = scanner.nextInt() - 1;
            scanner.nextLine();
            if (secondPokemonChoice == firstPokemonChoice) {
                System.out.println("The second Pokémon cannot be the same as the first. Please choose a different one.");
            }
        } while (secondPokemonChoice < 0 || secondPokemonChoice >= playerPokemons.size() || secondPokemonChoice == firstPokemonChoice);

        List<String[]> chosenPokemons = Arrays.asList(playerPokemons.get(firstPokemonChoice), playerPokemons.get(secondPokemonChoice));

        String[] firstPokemon = chosenPokemons.get(0);
        String[] secondPokemon = chosenPokemons.get(1);

        System.out.printf("You have chosen %s and %s!\n", firstPokemon[1], secondPokemon[1]);

        System.out.println("---< Battle Start! >---");

        UserPokemon playerPokemon1 = new UserPokemon(Integer.parseInt(firstPokemon[0]), firstPokemon[1],
                firstPokemon[2], Integer.parseInt(firstPokemon[3]), firstPokemon[4], firstPokemon[5], Integer.parseInt(firstPokemon[6]), Integer.parseInt(firstPokemon[7]));
        UserPokemon playerPokemon2 = new UserPokemon(Integer.parseInt(secondPokemon[0]), secondPokemon[1],
        		secondPokemon[2], Integer.parseInt(secondPokemon[3]), secondPokemon[4], secondPokemon[5], Integer.parseInt(secondPokemon[6]), Integer.parseInt(secondPokemon[7]));

        String[] opponent1 = wildPokemons.get(0);
        String[] opponent2 = wildPokemons.get(1);

        OpponentPokemon enemyPokemon1 = new OpponentPokemon(Integer.parseInt(opponent1[0]), opponent1[1],
        		opponent1[2], Integer.parseInt(opponent1[3]), opponent1[4], opponent1[5], Integer.parseInt(opponent1[6]), Integer.parseInt(opponent1[7]));
        OpponentPokemon enemyPokemon2 = new OpponentPokemon(Integer.parseInt(opponent2[0]), opponent2[1],
        		opponent2[2], Integer.parseInt(opponent2[3]), opponent2[4], opponent2[5], Integer.parseInt(opponent2[6]), Integer.parseInt(opponent2[7]));
        
        Battle battle = new Battle(playerPokemon1, playerPokemon2, enemyPokemon1, enemyPokemon2);
        int start_battle = battle.gameStatus();

        if (start_battle == 0) {
            System.out.println("---< You Lose! >---");
            System.out.println("---< GAME OVER! >---");
        } else if (start_battle == 1) {
            System.out.println("---< You Win! >---");
            System.out.println("---< Catch Chance! >---");
            battle.CatchChance();
        }

        battle.calculateScoreMedals(player.getPlayerId());

        savePlayerData(player);
        saveTopScores(player);
        saveTotalMedals(player);
        
    }

    private static void saveTotalMedals(Player player) {
        String medalData = player.getPlayerId() + "," + player.getTotalMedals()+ "\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("top_scores.txt", true))) {
            writer.write(medalData);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the top_scores.txt file.");
            e.printStackTrace();
        }
    }
    
    private static void saveTopScores(Player player) {
        String scoreData = player.getPlayerId() + "," + player.getCurrent_score() + "\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("top_scores.txt", true))) {
            writer.write(scoreData);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the top_scores.txt file.");
            e.printStackTrace();
        }
    }

	private Player getPlayerData(String playerId) {
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the database file.");
            e.printStackTrace();
            return null;
        }

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3 && parts[0].equalsIgnoreCase(playerId)) {
                int score = Integer.parseInt(parts[1].trim());
                int medals = Integer.parseInt(parts[2].trim());
                return new Player(playerId, score, new ArrayList<>(), medals);
            }
        }
        return null;
    }

    private void savePlayerData(Player player) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE, true))) {
            writer.write(player.getPlayerId() + "," + player.getCurrent_score() + "," + player.getTotalMedals() + "\n");
            System.out.println("Player data saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving player data.");
            e.printStackTrace();
        }

        displayTopScores();
    }

    private void displayTopScores() {
        try {
            File file = new File("top_scores.txt");
            if (!file.exists()) {
                System.out.println("No top scores available.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> topScores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                topScores.add(line.trim());
            }
            reader.close();

            System.out.println("\n-----< Top Scores: >-----");
            for (String scoreEntry : topScores) {
                String[] parts = scoreEntry.split(",");
                if (parts.length == 3) {
                    String playerId = parts[0].trim();
                    String score = parts[1].trim();
                    String medals = parts[2].trim();
                    System.out.println("Player ID: " + playerId + " | Score: " + score + " | Medals: " + medals);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> loadPokemonDetails(String fileName) {
        List<String[]> pokemonDetails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                pokemonDetails.add(data);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading Pokémon details.");
            e.printStackTrace();
        }
        return pokemonDetails;
    }

    static void loadPokemonData() {
        List<String[]> pokemonDetails = loadPokemonDetails(POKEMON_DETAILS); // Load data from file

        for (String[] data : pokemonDetails) {
            if (data.length >= 8) {
                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String type = data[2].trim();
                    int hp = Integer.parseInt(data[3].trim());
                    String rarity = data[4].trim();
                    String moveName = data[5].trim();
                    int DMG = Integer.parseInt(data[6].trim());
                    int DEF = Integer.parseInt(data[7].trim());

                    Pokemon pokemon = new Pokemon(id, name, type, hp, rarity, moveName, DMG, DEF);
                    pokemonList.add(pokemon);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing data: " + Arrays.toString(data));
                    e.printStackTrace();
                }
            } else {
                System.out.println("Skipping invalid data: " + Arrays.toString(data));
            }
        }
    }

    private static List<Pokemon> parsePokemonList(String pokemonData) {
        List<Pokemon> pokemonList = new ArrayList<>();
        if (pokemonData != null && !pokemonData.isEmpty()) {
            String[] pokemonNames = pokemonData.split(",");
            for (String name : pokemonNames) {
                String trimmedName = name.trim();
                if (!trimmedName.isEmpty()) {
                    Pokemon pokemon = getPokemonByName(trimmedName);
                    if (pokemon != null) {
                        pokemonList.add(pokemon);
                    } else {
                        System.out.println("Pokemon not found: " + trimmedName);
                    }
                }
            }
        }
        return pokemonList;
    }
    
    private static Pokemon getPokemonByName(String name) {
        if (name == null) {
            return null;
        }
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName() != null && pokemon.getName().equalsIgnoreCase(name)) {
                return pokemon;
            }
        }
        return null;
    }

    private static List<Pokemon> getRandomPokemonForStage(Stage stage) {
        List<Pokemon> stagePokemon = new ArrayList<>();
        for (Pokemon pokemon : pokemonList) {
            if (stage.isPokemonEligible(pokemon)) {
                stagePokemon.add(pokemon);
            }
        }
        Collections.shuffle(stagePokemon);
        return stagePokemon.subList(0, Math.min(4, stagePokemon.size())); // Select up to 4 Pokémon
    }
}

