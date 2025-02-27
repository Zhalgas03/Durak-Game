import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Variables{
    private static final ArrayList<String> bot=new ArrayList<>(); //the bot's cards
    private static final ArrayList<String> list=new ArrayList<>();//the player's cards
    private static final ArrayList<String> durak=new ArrayList<>();//durak cards

    //choosing trump suit randomly
    private static final Random random = new Random();
    private static final int r = random.nextInt(4);
    private static final String[] suit = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"};
    private static String trump=suit[r];

    //color
    private static final Color panelColor = new Color(0x496f93);


    //paths
    private static final String buttonSoundPath = "C:\\Users\\falco\\Desktop\\debil2\\cards\\button.wav";
    private static final String menuSoundPath = "C:\\Users\\falco\\Desktop\\debil2\\cards\\menu.wav";
    private static final String gameSoundPath = "C:\\Users\\falco\\Desktop\\debil2\\cards\\game.wav";
    private static final String winnerImagePath = "C:\\Users\\falco\\Desktop\\debil2\\cards\\papanya.jpg";
    private static final String loserImagePath = "C:\\Users\\falco\\Desktop\\debil2\\cards\\loser.jpg";
    private static final String clickSoundPath = "C:\\Users\\falco\\Desktop\\debil2\\cards\\click.wav";
    private static final String loserSoundPath = "C:\\Users\\falco\\Desktop\\debil2\\cards\\loser.wav";
    private static final String winnerSoundPath = "C:\\Users\\falco\\Desktop\\debil2\\cards\\winner.wav";



    //getters for variables
    public static ArrayList<String> getBot() {
        return bot;
    }
    public static ArrayList<String> getList() {
        return list;
    }
    public static ArrayList<String> dList() {
        return durak;
    }
    public static String getTrump() {
        return trump;
    }

    public static String getLoserSoundPath() {
        return loserSoundPath;
    }
    public static String getWinnerSoundPath() {
        return winnerSoundPath;
    }

    public static Color getPanelColor() {
        return panelColor;
    }

    public static String getButtonSoundPath() {
        return buttonSoundPath;
    }

    public static String getMenuSoundPath() {
        return menuSoundPath;
    }

    public static String getGameSoundPath() {
        return gameSoundPath;
    }

    public static String getWinnerImagePath() {
        return winnerImagePath;
    }

    public static String getLoserImagePath() {
        return loserImagePath;
    }

    public static String getClickSoundPath() {
        return clickSoundPath;
    }


}

