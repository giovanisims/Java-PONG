import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerData {

    ArrayList<Player> getTop5() {

        String file = "Data.txt";
        List<Player> playerDataList = new ArrayList<>();

        try (FileInputStream input = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(input)) {
            while (true) {
                try {
                    playerDataList.add((Player) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        playerDataList.sort(Comparator.comparingInt(p -> -p.highscore));

        int playerDataListSize = playerDataList.size();

        if (playerDataListSize > 5) {
            return new ArrayList<Player>(playerDataList.subList(0, 5));
        } else {
            return new ArrayList<Player>(playerDataList.subList(0, playerDataListSize));
        }

    }

    void savePlayerData(Player player) {
        String file = "Data.txt";
        List<Player> playerDataList = new ArrayList<>();

        try (FileInputStream input = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(input)) {
            while (true) {
                try {
                    playerDataList.add((Player) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        playerDataList.add(player);

        try (FileOutputStream output = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(output)) {
            for (Player playerData : playerDataList) {
                oos.writeObject(playerData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}