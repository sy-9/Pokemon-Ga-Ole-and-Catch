package Data;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private ArrayList<PlayerRecord> player;
    private final String FILE_NAME = "player_records.txt";
    private static final String DEFAULT_POKEMON = "Charmander";

    public Database() {
        player = new ArrayList<>();
    }

    public void loadDB() {
        openInputFile();
    }

    public void storeDB() {
        openOutputFile();
    }

    private void openInputFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                player.add(PlayerRecord.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openOutputFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (PlayerRecord pr : player) {
                bw.write(pr.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerRecord searchPlayer(String player_id) {
        for (PlayerRecord pr : player) {
            if (pr.getPlayer_id().equals(player_id)) { //change from pr.getPlayer_id() == player_id
                return pr;
            }
        }
        return null;
    }

    public void addPlayer(PlayerRecord pr) {
        player.add(pr);
        pr.addPokemon(DEFAULT_POKEMON);
    }

    public ArrayList<PlayerRecord> getPlayers() {
        return player;
    }
    
    
    
}
