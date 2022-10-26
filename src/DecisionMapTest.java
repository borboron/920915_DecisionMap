import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DecisionMapTest {
    JFrame myFrame;
    Container container;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, ButtonPanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton, buttonYes, buttonNo;
    JTextArea mainTextArea;

    TitleScreenHandler tsHandler = new TitleScreenHandler();


    public static void main(String[] args)throws FileNotFoundException {

        Utils u = new Utils();
        Scanner choice = new Scanner(System.in);
        DecisionMap perec;
        try {
            perec = new DecisionMap();
        } catch (FileNotFoundException fe) {
            u.console("File not found");
            return;
        }

        u.lineBreak();
        u.console("Start...");

        navigateMap(u, perec);


    }


    public DecisionMapTest() {

        myFrame = new JFrame();
        myFrame.setSize(800, 600);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setBackground(Color.BLACK);
        myFrame.setLayout(null);
        container = myFrame.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);

        titleNameLabel = new JLabel("Text Based Game");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 100, 50);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.addActionListener(tsHandler);
        startButton.setFocusable(false);


        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        container.add(titleNamePanel);
        container.add(startButtonPanel);
        myFrame.setVisible(true);


    }

    public void Gamedisplay() {

        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(220, 80, 300, 250);
        mainTextPanel.setBackground(Color.black);
        container.add(mainTextPanel);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(220, 80, 300, 250);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        ButtonPanel = new JPanel();
        ButtonPanel.setBounds(220, 360, 300, 150);
        ButtonPanel.setBackground(Color.black);
        container.add(ButtonPanel);

        buttonYes = new JButton("Yes");
        buttonYes.setBackground(Color.black);
        buttonYes.setForeground(Color.white);
        buttonYes.setFont(normalFont);
        buttonYes.setFocusable(false);
        ButtonPanel.add(buttonYes);

        buttonNo = new JButton("No");
        buttonNo.setBackground(Color.black);
        buttonNo.setForeground(Color.white);
        buttonNo.setFont(normalFont);
        buttonNo.setFocusable(false);
        ButtonPanel.add(buttonNo);
        container.setBackground(Color.BLUE);


        mainTextArea.setText("This is a test piece of text for the UI");


    }




    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            Gamedisplay();
        }
    }


    public static void navigateMap(Utils f, DecisionMap perec) {
        DecisionNode node = perec.entryPoint();

        while (node != null) {

            f.console(node.getDescription());
            f.console(node.getQuestion());

            if (node.getQuestion().equals("-")) {
                f.pressEnterToContinue();
                node = node.getYesNode();
            } else {
                try {
                    int decision = f.fromConsoleGetInt("Yes or No? (press 5 for Yes or 6 No)");
                    node = switch (decision) {
                        case 1 -> node.getYesNode();
                        case 2 -> node.getNoNode();
                        default -> throw new CustomRuntime("Not available");
                    };

                } catch (Exception e) {
                    System.out.println("Input not valid" + "\n-----------");
                }

            }
        }
    }
}

