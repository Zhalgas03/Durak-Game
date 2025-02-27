import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Game52 {

    private static JFrame frame;
    private static JPanel panel;
    private static boolean imageClicked = false;
    public static int clickedImageIndex = -1;
    public static Deck deck = new Deck(52);
    private static int Turn = 1;
    private static boolean autoClickPerformed = false;

    public static void restartGame() {
        Variables.getBot().clear();
        Variables.getList().clear();
        Variables.dList().clear();

        Game52.deck = new Deck(52);

        Game52.Game start = new Game52.Game();
        start.startGame(deck, Variables.getList(), Variables.getBot(),Variables.dList(), Variables.getTrump());
        Variables.getList().sort(new Bot.CardComparator());
        Variables.getBot().sort(new Bot.CardComparator());
        Variables.dList().sort(new Bot.CardComparator());
        updateImages();
        GameMenu.playSound(Variables.getGameSoundPath(), true);
    }

    private static void onImageClick(int index) {//checks whether the image was clicked and make move accordingly to turn
        //instances of player and bot
        GameParticipant<Integer> player = new Player52();
        GameParticipant<Integer> bot = new Bot52();
        GameParticipant<Integer> durak = new Opponent();
        if (!imageClicked) {
            clickedImageIndex = index;
            imageClicked = true;
            switch (Turn) {
                case 1 -> {
                    player.playCard(Turn);
                    Functions.refresh(1);
                }
                case 2 -> {

                    bot.playCard(Turn);
                    Functions.refresh(1);
                    autoClickPerformed = false;
                }

                case 3 -> {
                    durak.playCard(Turn);
                    Functions.refresh(1);
                }
            }

        }
    }

    public static void who(int playerWon) {//show who won
        GameMenu.stopSound();//stops background music
        if (playerWon==1) {//chooses sound depending on who won
            GameMenu.playSound(Variables.getWinnerSoundPath());
        } else {
            GameMenu.playSound(Variables.getLoserSoundPath());
        }
    }

    public static void setTurn(int turn) {
        Turn = turn;
    }
    public static int getTurn() {
        return Turn;
    }

    private static abstract class start<zxc> {// Abstract method to start the game, to be implemented by subclasses
        public abstract void startGame(Deck deck, List<zxc> list, List<zxc> bot, List<zxc> durak, String trump);
    }

    private static class Game extends start<String> {
        // Implementation of the abstract method to start the game
        // Draw 6 cards from the deck for each player
        public void startGame(Deck deck, List<String> list, List<String> bot, List<String> durak, String trump) {
            for (int i = 0; i < 6; i++) {
                Card card = deck.drawCard();
                list.add(String.valueOf(card));
            }

            for (int i = 0; i < 6; i++) {
                Card card = deck.drawCard();
                bot.add(String.valueOf(card));
            }

            for (int i = 0; i < 6; i++) {
                Card card = deck.drawCard();
                durak.add(String.valueOf(card));
            }

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
            updateImages();
        });
        Game start = new Game();//Create an instance of the Game class
        start.startGame(deck, Variables.getList(), Variables.getBot(), Variables.dList(),Variables.getTrump());// Start the game with the provided deck, player list, bot list, and trump card

        //sorts cards
        Variables.getList().sort(new Functions.CardComparator());
        Variables.getBot().sort(new Functions.CardComparator());
        Variables.dList().sort(new Functions.CardComparator());

        GameMenu.playSound(Variables.getGameSoundPath(),true);//plays background music
    }
    public class AutoClicker {//clicker to make bots turns
        public static void performAutoClick(Component component) {
            try {
                Robot robot = new Robot();
                Point location = component.getLocationOnScreen();
                int x = location.x + component.getWidth() / 2;
                int y = location.y + component.getHeight() / 2;

                robot.mouseMove(x, y);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static void createAndShowGUI() {
        //interface
        frame = new JFrame("Durak");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1800, 1000));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //adding panels
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel botPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel durakPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel trump2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

        //set color
        panel.setBackground(Variables.getPanelColor());
        botPanel.setBackground(Variables.getPanelColor());
        trump2.setBackground(Variables.getPanelColor());
        info.setBackground(Variables.getPanelColor());
        durakPanel.setBackground(Variables.getPanelColor());
        frame.getContentPane().setBackground(Variables.getPanelColor());

        //adding components
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(botPanel, BorderLayout.NORTH);
        frame.add(durakPanel, BorderLayout.EAST);

        frame.add(trump2, BorderLayout.CENTER);
        frame.add(info, BorderLayout.WEST);

        //set margin
        trump2.setBorder(BorderFactory.createEmptyBorder(200, 75, 200, 200));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        botPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        info.setBorder(BorderFactory.createEmptyBorder(210, 20, 0, 0));



        frame.setVisible(true);
    }

    @NotNull
    private static JLabel getjLabel() {//set image of trump
        ImageIcon trumpik = switch (Variables.getTrump()) {
            case "HEARTS" -> new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\ACE HEARTS.png");
            case "DIAMONDS" -> new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\ACE DIAMONDS.png");
            case "SPADES" -> new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\ACE SPADES.png");
            case "CLUBS" -> new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\ACE CLUBS.png");
            default -> new ImageIcon();
        };
        return new JLabel(trumpik);
    }

    public static void updateImages() {//update images
        panel.removeAll();//removes all elements in panel

        //adding images connected to player list
        List<String> list = Variables.getList();
        ImageIcon[] icons = new ImageIcon[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String a = list.get(i);
            icons[i] = new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\" + a + ".png");
        }

        //creation of labels and adding click events
        for (int i = 0; i < icons.length; i++) {
            JLabel label = new JLabel(icons[i]);
            int index = i;
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onImageClick(index);
                    GameMenu.playSound(Variables.getClickSoundPath());
                }
            });
            panel.add(label);
        }



        JPanel info = (JPanel) frame.getContentPane().getComponent(4); // adds panel of deck size info
        info.removeAll();

        //creation and customization of labels
        JLabel deckCountLabel = new JLabel("Deck size:");
        JLabel deckCountLabel2 = new JLabel(String.valueOf(deck.size()));
        JLabel tlabel = getjLabel();

        deckCountLabel.setFont(new Font("Arial", Font.BOLD, 24));
        deckCountLabel.setForeground(Color.WHITE);
        deckCountLabel2.setFont(new Font("Arial", Font.BOLD, 48));
        deckCountLabel2.setForeground(Color.WHITE);
        tlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deckCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deckCountLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.add(tlabel);
        info.add(deckCountLabel);
        info.add(deckCountLabel2);


        JPanel trump2 = (JPanel) frame.getContentPane().getComponent(3); //panel in center
        trump2.removeAll();
        String m = Variables.getBot().get(0);//first element of bot list
        String d = Variables.dList().get(0);
        //image of the center card
        ImageIcon midIcon = new ImageIcon();
        if(Turn==1){
            midIcon= new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\BACK.png");
        }
        else if(Turn==2){
            midIcon= new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\" + m + ".png");
        }
        else if(Turn==3){
            midIcon= new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\" + d + ".png");
        }

        //label and click event
        JLabel midLabel = new JLabel(midIcon);
        trump2.add(midLabel);
        midLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameMenu.playSound(Variables.getClickSoundPath());

            }
        });




        JPanel botPanel = (JPanel) frame.getContentPane().getComponent(1);//top panel
        botPanel.removeAll();

        //the same as in player list
        List<String> botList = Variables.getBot();
        ImageIcon[] botIcons = new ImageIcon[botList.size()];
        for (int i = 0; i < botList.size(); i++) {
            botIcons[i] = new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\BACK.png");
        }
        for (ImageIcon botIcon : botIcons) {
            JLabel label = new JLabel(botIcon);
            botPanel.add(label);
        }


        //the same as in player list
        List<String> durakList = Variables.dList();
        ImageIcon[] dIcons = new ImageIcon[durakList.size()];
        ImageIcon separate=new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\separate.png");
        JLabel slabel = new JLabel(separate);
        botPanel.add(slabel);
        for (int i = 0; i < durakList.size(); i++) {
            String a = durakList.get(i);
            dIcons[i] = new ImageIcon("C:\\Users\\falco\\Desktop\\debil2\\cards\\BACK.png");
        }
        for (ImageIcon dIcon : dIcons) {
            JLabel label = new JLabel(dIcon);
            botPanel.add(label);
        }

        if (Turn != 1 && !autoClickPerformed) {
            SwingUtilities.invokeLater(() -> {
                if (panel.getComponentCount() > 0) {
                    Component componentToClick = panel.getComponent(0);
                    AutoClicker.performAutoClick(componentToClick);
                    autoClickPerformed = true;
                }
            });
        }


        frame.revalidate();// Recalculate the layout to include the new button
        frame.repaint(); // Refresh the display to show the new button
    }

    public static void waitForNextClick() {//makes imageclicked false to wait for opponent's move
        imageClicked = false;
        clickedImageIndex = -1;
    }
}
