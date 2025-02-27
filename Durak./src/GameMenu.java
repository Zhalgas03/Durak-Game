import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameMenu extends JFrame {
    private static Clip clip;
    private JLabel logoLabel;

    public GameMenu() {
        //creation of the frame
        setTitle("Durak");
        setMinimumSize(new Dimension(1800, 1000));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //set the background color
        getContentPane().setBackground(Variables.getPanelColor());

        //set the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //set the spacing between the components
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //add the logo
        logoLabel = new JLabel("Durak");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 72));
        logoLabel.setForeground(Color.WHITE);

        //set the logo position
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(logoLabel, gbc);

        //add the buttons
        JButton startButton = new JButton("1 vs 1");
        JButton startButton2 = new JButton("1 vs 2");
        JButton exitButton = new JButton("Exit");
        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        startButton.setFont(buttonFont);
        startButton2.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        //customize the buttons and adding padding
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setVerticalAlignment(SwingConstants.CENTER);
        startButton.setMargin(new Insets(10, 10, 10, 10));


        startButton2.setHorizontalAlignment(SwingConstants.CENTER);
        startButton2.setVerticalAlignment(SwingConstants.CENTER);
        startButton2.setMargin(new Insets(10, 10, 10, 10));

        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setVerticalAlignment(SwingConstants.CENTER);
        exitButton.setMargin(new Insets(10, 10, 10, 10));

        //set the buttons position
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(startButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(startButton2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(exitButton, gbc);

        //add the action listeners
        startButton.addActionListener(e -> {
            stopSound();
            playSound(Variables.getButtonSoundPath());
            dispose();
  
            new Thread(() -> MainBeta2.main(new String[]{})).start();

        });

        startButton2.addActionListener(e -> {
            stopSound();
            playSound(Variables.getButtonSoundPath());
            dispose();

            new Thread(() -> Game52.main(new String[]{})).start();
        });

        exitButton.addActionListener(e -> {
            playSound(Variables.getButtonSoundPath());
            System.exit(0);
        });



    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GameMenu().setVisible(true));
        playSound(Variables.getMenuSoundPath(),true);

    }

    public static void playSound(String filepath,boolean loop) { //play background music
        try {//exception handling
            // Create a File object for the sound file
            File soundFile = new File(filepath);

            // Obtain an audio input stream from the sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a Clip object, which can be used to play the audio
            clip = AudioSystem.getClip();

            // Open the audio input stream
            clip.open(audioStream);

            if(loop) {
                //clip is looped
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            //clip starts playing
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopSound() {//stop background music
        try {
            //clip stops playing
            clip.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSound(String filepath) {//click sound, the difference is that the sound is not looped
        try {
            File soundFile = new File(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
