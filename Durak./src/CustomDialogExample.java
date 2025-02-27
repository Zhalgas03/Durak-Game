import javax.swing.*;

import static java.lang.System.exit;

public class CustomDialogExample {


    public CustomDialogExample(int a) {
        String[] options = {"Replay", "Exit"};

        int result = JOptionPane.showOptionDialog(null, "Game Over. Would you like to play again?", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch (result) {
            case 0 -> {
                if (a == 1) {
                    MainBeta2.restartGame();
                } else if (a == 2) {
                    Game52.restartGame();
                }
            }
            case 1 -> exit(0);
            default -> JOptionPane.showMessageDialog(null, "Nothing was selected.");
        }

    }
}
