package Game;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

import Data.UserPokemon;
import General.Pokeball;
import General.Pokemon;
//import Data.List;
import Data.OpponentPokemon;
import Data.TypeEffectiveness;



public class Battle {
	  public static boolean simulateBattle(Pokemon playerPokemon, OpponentPokemon opponentPokemon) {
	        // Add logic for simulating the battle between playerPokemon and opponentPokemon
	        // Return true if the player wins, false otherwise
	        return true; // Placeholder return statement
	    }
	
	  	private UserPokemon playerpokemon1;
	    private UserPokemon playerpokemon2;
	    private OpponentPokemon enemypokemon1;
	    private OpponentPokemon enemypokemon2;
    
    private String battlemode = "On";
    
    Scanner input = new Scanner(System.in);
    Random random = new Random();
        
    private String playermove;
    private int player_turn;
    private int opponent_turn;
    private int enemy_attackingpokemon;
    private List<OpponentPokemon> enemyPokemons;
    
    private TypeEffectiveness typeeffectiveness = new TypeEffectiveness();
    
    private boolean playerpokemon1Defending = false;
    private boolean playerpokemon2Defending = false;
    
    
    public Battle() {
        playerpokemon1 = new UserPokemon(playerpokemon1.getId(), playerpokemon1.getName(), playerpokemon1.getPokemontype(), playerpokemon1.getHp(), playerpokemon1.getRarity(), playerpokemon1.getMoveType(),playerpokemon1.getDmg(), playerpokemon1.getDef()); // Uses no-argument constructor
        playerpokemon2 = new UserPokemon(playerpokemon2.getId(), playerpokemon2.getName(), playerpokemon2.getPokemontype(), playerpokemon2.getHp(), playerpokemon2.getRarity(), playerpokemon2.getMoveType(),playerpokemon2.getDmg(), playerpokemon2.getDef()); // Uses no-argument constructor
        enemypokemon1 = new OpponentPokemon(enemypokemon1.getId(), enemypokemon1.getName(), enemypokemon1.getPokemontype(), enemypokemon1.getHp(), enemypokemon1.getRarity(), enemypokemon1.getMoveType(),enemypokemon1.getDmg(), enemypokemon1.getDef());
        enemypokemon2 = new OpponentPokemon(enemypokemon1.getId(), enemypokemon2.getName(), enemypokemon2.getPokemontype(), enemypokemon2.getHp(), enemypokemon2.getRarity(), enemypokemon2.getMoveType(),enemypokemon2.getDmg(), enemypokemon2.getDef());
    }
    
    public Battle(UserPokemon p1, UserPokemon p2, OpponentPokemon e1, OpponentPokemon e2) {
        playerpokemon1 = new UserPokemon(p1.getId(), p1.getName(), p1.getPokemontype(), p1.getHp(), p1.getRarity(), p1.getMoveType(),p1.getDmg(), p1.getDef()); // Uses copy constructor
        playerpokemon2 = new UserPokemon(p2.getId(), p2.getName(), p2.getPokemontype(), p2.getHp(), p2.getRarity(), p2.getMoveType(),p2.getDmg(), p2.getDef()); // Uses copy constructor
        enemypokemon1 = new OpponentPokemon(e1.getId(), e1.getName(), e1.getPokemontype(), e1.getHp(), e1.getRarity(), e1.getMoveType(),e1.getDmg(), e1.getDef());
        enemypokemon2 = new OpponentPokemon(e2.getId(), e2.getName(), e2.getPokemontype(), e2.getHp(), e2.getRarity(), e2.getMoveType(),e2.getDmg(), e2.getDef());
    }
    
    public int gameStatus() {
        showhpStatus();
        int gamestatus = 0;
            
        while (battlemode.equals("On")) {
            turnDecider();
            
            //Player wins his turn
            if (player_turn == 1) {
                String attacking_pokemon = pokemonSelector();
                boolean isDefending = pokemonBehavior(attacking_pokemon);
                
                //Player's first Pokemon turn
                if (attacking_pokemon.equals(playerpokemon1.getName())) {
                    playerpokemon1Defending = isDefending;
                    if (playerpokemon1Defending) {
                        playerpokemon1.setHp(playerpokemon1.getHp() + playerpokemon1.getDef());
                        System.out.println("\n" + playerpokemon1.getName() + " is defending. +"+ playerpokemon1.getDef() + " DEF -> HP | HP: " + playerpokemon1.getHp());
                        showhpStatus();
                    } else {
                        List<String> targetpokemons = targetSelector();
                        pokemon1Combat(attacking_pokemon, targetpokemons);
                    }
                }
            
                //Player's second Pokemon turn
                if (attacking_pokemon.equals(playerpokemon2.getName())) {
                    playerpokemon2Defending = isDefending;
                    if (playerpokemon2Defending) {
                        playerpokemon2.setHp(playerpokemon2.getHp() + playerpokemon2.getDef());
                        System.out.println("\n" + playerpokemon2.getName()  + " is defending. +"+ playerpokemon2.getDef() + " DEF -> HP | HP: " + playerpokemon2.getHp());
                        showhpStatus();
                    } else {
                        List<String> targetpokemons = targetSelector();
                        pokemon2Combat(attacking_pokemon, targetpokemons);
                    }
                }
                player_turn = 0;
            }
        
            //Opponent gets his turn
            if (opponent_turn == 1) {
                opponentTurn();
                
                //Opponent's first Pokemon attacks both player's pokemons
                if (enemy_attackingpokemon == 1 && enemypokemon1.getHp() > 0) {
                    System.out.println("\nIt is the enemy's turn");
                    System.out.println("Opponent chose " + enemypokemon1.getName() + " to battle");
                    
                    System.out.println(enemypokemon1.getName() + " used " + enemypokemon1.getMoveType());
                    int dmg = enemypokemon1.getDmg();
                    
                    int dmgdealt = (int) calculateDamageDealt(dmg, enemypokemon1.getPokemontype(), playerpokemon1.getPokemontype(), playerpokemon2.getPokemontype());
               
                        playerpokemon1.setHp(Math.max(playerpokemon1.getHp() - dmgdealt, 0));
                    
    
                        playerpokemon2.setHp(Math.max(playerpokemon2.getHp() - dmgdealt, 0));
                    
                    
                    showhpStatus();
                }
            
                //Opponent's second Pokemon attacks both player's Pokemon        
                if (enemy_attackingpokemon == 2 && enemypokemon2.getHp() > 0) {
                    System.out.println("\nIt is the enemy's turn");
                    System.out.println("Opponent chose " + enemypokemon2.getName() + " to battle");    
                    
                    System.out.println(enemypokemon2.getName() + " used " + enemypokemon2.getMoveType());
                    int dmg = enemypokemon2.getDmg();
                    
                    int dmgdealt = (int) calculateDamageDealt(dmg, enemypokemon2.getPokemontype(), playerpokemon1.getPokemontype(), playerpokemon2.getPokemontype());
                   
                        playerpokemon1.setHp(Math.max(playerpokemon1.getHp() - dmgdealt, 0));
                    
                    
                        playerpokemon2.setHp(Math.max(playerpokemon2.getHp() - dmgdealt, 0));
                    
                
                    showhpStatus();
                } 
                opponent_turn = 0;
            }
            
            //Checks if either side of Pokemons are both defeated, then conclude the game by deciding who Wins and Lose.
            if (enemypokemon1.getHp() == 0 && enemypokemon2.getHp() == 0) {
                gamestatus = 1;
                battlemode = "Off";
                System.out.println("\nBoth of the opponent's Pokemons are defeated! Entering Catch Mode!");
            } else if (playerpokemon1.getHp() == 0 && playerpokemon2.getHp() == 0) {
                gamestatus = 2;
                battlemode = "Off";
                System.out.println("\nBoth of your Pokemons are defeated!");
            }
        }
        return gamestatus;
    }
    
    //Determines whether the Player or the opponent gets to attack
    public void turnDecider() {
        String[] rps = {"Rock", "Paper", "Scissors"};
        String opponentmove;
        
        while (true) {
            int i = 1;
            System.out.println("\nWin games of Rock, Paper, or Scissors to attack: ");
            System.out.print("► ");
            playermove = input.nextLine().trim();
    
            while (!playermove.equalsIgnoreCase("Rock") && !playermove.equalsIgnoreCase("Paper") && !playermove.equalsIgnoreCase("Scissors")) {
                System.out.println("\nInvalid Move. Select Rock, Paper, or Scissors to attack: ");
                System.out.print("► ");
                playermove = input.nextLine().trim();
                i++;
            }
    
            int opponentChoice = random.nextInt(3);
            opponentmove = rps[opponentChoice];
            System.out.println("You have chosen: " + playermove);
            System.out.println("Opponent has chosen: " + opponentmove);
            
            if (playermove.equalsIgnoreCase(opponentmove)) {
                System.out.println("It's a tie! Please select again");
                continue;
            }
            
            if (playermove.equalsIgnoreCase("Rock")) {
                if (opponentmove.equalsIgnoreCase("Scissors")) {
                    player_turn = 1;
                    return;
                } else {
                    opponent_turn = 1;
                    return;
                }
            } else if (playermove.equalsIgnoreCase("Paper")) {
                if (opponentmove.equalsIgnoreCase("Rock")) {
                    player_turn = 1;
                    return;
                } else {
                    opponent_turn = 1;
                    return;
                }
            } else if (playermove.equalsIgnoreCase("Scissors")) {
                if (opponentmove.equalsIgnoreCase("Paper")) {
                    player_turn = 1;
                    return;
                } else {
                    opponent_turn = 1;
                    return;
                }
            }
        }
    }
    
    //Players will select one of their Pokemons to attack
    private String pokemonSelector() {
        String name = " ";
        System.out.println("\nIt's your turn to attack!");
        System.out.println("Select a Pokemon to use: ");
        if (playerpokemon1.getHp() > 0) {
            System.out.println("[1] " + playerpokemon1.getName());
        }
        if (playerpokemon2.getHp() > 0) {
            System.out.println("[2] " + playerpokemon2.getName());
        }
        
        int round_counter = 1;
        while (round_counter == 1) {
            System.out.print("► ");
            String attacking_pokemon = input.next();
            
            if (attacking_pokemon.equals("1") && playerpokemon1.getHp() > 0) {
                round_counter++;
                name = playerpokemon1.getName();
            } else if (attacking_pokemon.equals("2") && playerpokemon2.getHp() > 0) {
                round_counter++;
                name = playerpokemon2.getName();
            } else {
                System.out.println("Invalid Selection. Select a Pokemon to use: ");
                if (playerpokemon1.getHp() > 0) {
                    System.out.println("[1] " + playerpokemon1.getName());
                }
                if (playerpokemon2.getHp() > 0) {
                    System.out.println("[2] " + playerpokemon2.getName());
                }
            }
        }
        input.nextLine();
        return name;
    }
    
    // Put both enemy's Pokemons into a list to be targeted together
    private List<String> targetSelector() {
        List<String> targets = new ArrayList<>();
        
        // While checking if the enemy's Pokemons are alive, Adds the enemy Pokemons as targets into the list
        if (enemypokemon1.getHp() > 0) {
            targets.add(enemypokemon1.getName());
        }
        if (enemypokemon2.getHp() > 0) {
            targets.add(enemypokemon2.getName());
        }

        return targets;
    }
    
    //Choose whether Pokemon will attack or defend
    private boolean pokemonBehavior(String name) {
        boolean isDefending = false;
        
        if ((name.equals(playerpokemon1.getName()) && playerpokemon1.getHp() == 0) || (name.equals(playerpokemon2.getName()) && playerpokemon2.getHp() == 0)) {
                System.out.println(name + " has no HP and cannot perform any actions.");
                return false;
             }
        
        System.out.println("Select a move for " + name + " : ");
        if (name.equals(playerpokemon1.getName())) {
        	System.out.println("[1] Attack | " + playerpokemon1.getMoveType());
            System.out.println("[2] Defend | DEF: " + playerpokemon1.getDef());
        }
        
        if (name.equals(playerpokemon2.getName())) {
        	System.out.println("[1] Attack | " + playerpokemon2.getMoveType());
            System.out.println("[2] Defend | DEF: " + playerpokemon2.getDef());
        }
        
        int round_counter = 1;
        while (round_counter == 1) {
            System.out.print("► ");
            String pokemonstate = input.next();
            
            if (pokemonstate.equals("1")) {
                round_counter++;
                isDefending = false;
            } else if (pokemonstate.equals("2")) {
                round_counter++;
                isDefending = true;
            } else {
             System.out.println("Select a move for " + name + " : ");
            	 if (name.equals(playerpokemon1.getName())) {
                 	System.out.println("[1] Attack | " + playerpokemon1.getMoveType());
                     System.out.println("[2] Defend | DEF: " + playerpokemon1.getDef());
                 }
                 
                 if (name.equals(playerpokemon2.getName())) {
                 	System.out.println("[1] Attack | " + playerpokemon2.getMoveType());
                     System.out.println("[2] Defend | DEF: " + playerpokemon2.getDef());
                 }
            }
        }
        input.nextLine();
        return isDefending;
    }
    
    //Player's first Pokemon attacks
    private void pokemon1Combat(String attacking_pokemon, List<String> targetpokemons) {
        if (attacking_pokemon.equals(playerpokemon1.getName())) {
            System.out.println("\n" + playerpokemon1.getName() + " used " + playerpokemon1.getMoveType());
            int dmg = playerpokemon1.getDmg();
            int dmgdealt = (int) calculateDamageDealt(dmg, playerpokemon1.getPokemontype(), enemypokemon1.getPokemontype(), enemypokemon2.getPokemontype());
            enemypokemon1.setHp(Math.max(enemypokemon1.getHp() - dmgdealt, 0));
            enemypokemon2.setHp(Math.max(enemypokemon2.getHp() - dmgdealt, 0));
            showhpStatus();
        }
    }
    
    //Player's second Pokemon attacks
    private void pokemon2Combat(String attacking_pokemon, List<String> targetpokemons) {
        if (attacking_pokemon.equals(playerpokemon2.getName())) {
            System.out.println("\n" + playerpokemon2.getName() + " used " + playerpokemon2.getMoveType());
            int dmg = playerpokemon2.getDmg();
            int dmgdealt = (int) calculateDamageDealt(dmg, playerpokemon2.getPokemontype(), enemypokemon1.getPokemontype(), enemypokemon2.getPokemontype());
            enemypokemon1.setHp(Math.max(enemypokemon1.getHp() - dmgdealt, 0));
            enemypokemon2.setHp(Math.max(enemypokemon2.getHp() - dmgdealt, 0));
            showhpStatus();
        }
    }
    
    //Calculate the Damage Dealt while comparing the Type Effectiveness
    private double calculateDamageDealt(int dmgdealt, String attackertype, String defender1type, String defender2type) {
        dmgdealt = (int) typeeffectiveness.type_comparision(dmgdealt, attackertype, defender1type, defender2type);
        return dmgdealt;
    }
    
    //Opponent will choose their Pokemon to attack
    private void opponentTurn() {
    	List<Integer> aliveEnemyIndexes = new ArrayList<>();
        if (enemypokemon1.getHp() > 0) aliveEnemyIndexes.add(1);
        if (enemypokemon2.getHp() > 0) aliveEnemyIndexes.add(2);

        // If no enemy Pokémon is alive, return early
        if (aliveEnemyIndexes.isEmpty()) return;

        //Opponent will choose one of their Pokemons to attack
        int randomIndex = random.nextInt(aliveEnemyIndexes.size()); //Opponent can only choose Pokemons that are alive to fight
        enemy_attackingpokemon = aliveEnemyIndexes.get(randomIndex);
    }
    
    //Displays the HP status of all Pokemons in battle
    private void showhpStatus() {
        System.out.println("\n" + playerpokemon1.getName() + " | HP: " + playerpokemon1.getHp());
        System.out.println(playerpokemon2.getName() + " | HP: " + playerpokemon2.getHp());
        System.out.println("|---------------------< VS >---------------------------|");
        System.out.println(enemypokemon1.getName() + " | HP: " + enemypokemon1.getHp());
        System.out.println(enemypokemon2.getName() + " | HP: " + enemypokemon2.getHp());
        
        if (playerpokemon1.getHp() == 0) {
            System.out.println("\n" + playerpokemon1.getName() + " is defeated.");
        }
        if (playerpokemon2.getHp() == 0) {
            System.out.println("\n" + playerpokemon2.getName() + " is defeated.");
        }
        if (enemypokemon1.getHp() == 0) {
            System.out.println("\n" + enemypokemon1.getName() + " is defeated.");
        }
        if (enemypokemon2.getHp() == 0) {
            System.out.println("\n" + enemypokemon2.getName() + " is defeated.");
        }
    }
    
    
private double catchChance;
	
public void CatchChance() {
    Scanner scanner = new Scanner(System.in);
    String chosenPokemonName = "";
    try {
        System.out.println("[1] " + enemypokemon1.getName() + "\n"
                + "[2] " + enemypokemon2.getName() + "\n");
        System.out.println("Select the wild pokemon you want to catch: ");
        System.out.print("► ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            chosenPokemonName = enemypokemon1.getName();
            System.out.println("You have chosen " + chosenPokemonName + ".");
        } else if (choice == 2) {
            chosenPokemonName = enemypokemon2.getName();
            System.out.println("You have chosen " + chosenPokemonName + ".");
        } else {
            System.out.println("Invalid choice! Please select a valid Pokemon.");
            return;
        }
    } catch (InputMismatchException e) {
        System.out.println("That is not an option! Choose another Pokemon.");
        scanner.nextLine(); // Clear the invalid input
        return;
    }

    int p1 = playerpokemon1.getHp();
    int p2 = playerpokemon2.getHp();

    Pokeball pokeball = new Pokeball();
    System.out.println(pokeball.toString());

    // Calculate the catch chance based on the remaining HP of the player's Pokemons
    if (p1 > 0 || p2 > 0) {
        if (p1 <= 25 || p2 <= 25) {
            catchChance = pokeball.getCatchRate() * 0.25;
        } else if (p1 <= 50 || p2 <= 50) {
            catchChance = pokeball.getCatchRate() * 0.5;
        } else if (p1 <= 75 || p2 <= 75) {
            catchChance = pokeball.getCatchRate() * 0.75;
        } else if (p1 <= 100 || p2 <= 100) {
            catchChance = pokeball.getCatchRate() * 1.0;
        }
    } else { // if both HP == 0
        catchChance = pokeball.getCatchRate() * 0.1;
    }

    // Randomize catch result
    int randomCatchValue = random.nextInt(1000) + 1; // Random value between 1 and 1000

    // Determine catch result based on calculated catch chance
    if (randomCatchValue <= catchChance) {
        System.out.println("Congratulations, you have caught " + chosenPokemonName + "!");
    } else {
        System.out.println("Oh no! The wild Pokemon has escaped!");
    }
}


	
	public void calculateScoreMedals(String playerId) {
	    int score;
	    int medals = 0;

	    int combinedHp = playerpokemon1.getHp() + playerpokemon2.getHp();

	    if (combinedHp > 100) {
	        score = 2000;
	    } else if (combinedHp > 75) {
	        score = 1500;
	    } else if (combinedHp > 50) {
	        score = 1000;
	    } else if (combinedHp > 25) {
	        score = 500;
	    } else {
	        score = 0;
	    }

	    if (score >= 2000) {
	        medals = 5;
	    } else if (score >= 1750) {
	        medals = 4;
	    } else if (score >= 1500) {
	        medals = 3;
	    } else if (score >= 1200) {
	        medals = 2;
	    } else if (score >= 1000) {
	        medals = 1;
	    } else {
	        medals = 0;
	    }

	    System.out.println("Total Score: " + score);
	    System.out.println("Total Medals: " + medals);
	    
	    saveTopScore(playerId,score,medals);
	    //saveTotalMedals(playerId,medals);
	
	}
	
	private void saveTopScore(String playerId, int score, int medals) {
	    try {
	        File file = new File("top_scores.txt");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        List<String> topScores = new ArrayList<>();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.trim().isEmpty()) {
	                continue; // Skip empty lines
	            }
	            String[] parts = line.split(",");
	            if (parts.length == 3) {
	                try {
	                    Integer.parseInt(parts[1].trim());
	                    topScores.add(line.trim());
	                } catch (NumberFormatException e) {
	                    System.out.println("Skipping invalid score entry: " + line);
	                }
	            }
	        }
	        reader.close();
	        topScores.add(playerId + "," + score + "," + medals );
	        topScores.sort((a, b) -> {
	            int scoreA = Integer.parseInt(a.split(",")[1].trim());
	            int scoreB = Integer.parseInt(b.split(",")[1].trim());
	            return Integer.compare(scoreB, scoreA);
	        });
	        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	        for (int i = 0; i < Math.min(5, topScores.size()); i++) {
	            writer.write(topScores.get(i) + "\n");
	        }
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}