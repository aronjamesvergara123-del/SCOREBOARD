/*
- Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
- Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ROUND;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ROUND {

    static int leftScore = 0;
    static int rightScore = 0;
    static int seconds = 60;
    static boolean running = false;
    static Timer timer;

    static int foulLeftCount = 0;
    static int foulRightCount = 0;
    static int disarmLeftCount = 0;
    static int disarmRightCount = 0;
    
    // Tracking advantage points and who scored first
    static int advLeftCount = 0;
    static int advRightCount = 0;
    static String firstAdvantageTeam = null;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Lemsehi VS Payapa - Scoreboard");
        frame.setSize(1100, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // BACKGROUND PANELS
        JPanel bluePanel = new JPanel();
        bluePanel.setLayout(null);
        bluePanel.setBounds(0, 0, 450, 680);
        bluePanel.setBackground(new Color(0, 145, 210));

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBounds(450, 0, 200, 680);
        middlePanel.setBackground(new Color(230, 240, 245));

        JPanel orangePanel = new JPanel();
        orangePanel.setLayout(null);
        orangePanel.setBounds(650, 0, 450, 680);
        orangePanel.setBackground(new Color(235, 75, 30));

        frame.add(bluePanel);
        frame.add(middlePanel);
        frame.add(orangePanel);

        // TEAM NAMES
        JTextField textFieldLname = new JTextField("Lemsehi");
        textFieldLname.setHorizontalAlignment(JTextField.CENTER);
        textFieldLname.setFont(new Font("Arial", Font.BOLD, 52));
        textFieldLname.setForeground(Color.WHITE);
        textFieldLname.setBackground(new Color(0, 145, 210));
        textFieldLname.setCaretColor(Color.WHITE);
        textFieldLname.setBorder(null);
        textFieldLname.setBounds(5, 15, 440, 130);
        bluePanel.add(textFieldLname);

        JTextField textFieldRname = new JTextField("Payapa");
        textFieldRname.setHorizontalAlignment(JTextField.CENTER);
        textFieldRname.setFont(new Font("Arial", Font.BOLD, 52));
        textFieldRname.setForeground(Color.WHITE);
        textFieldRname.setBackground(new Color(235, 75, 30));
        textFieldRname.setCaretColor(Color.WHITE);
        textFieldRname.setBorder(null);
        textFieldRname.setBounds(5, 15, 440, 130);
        orangePanel.add(textFieldRname);

        // MAS MALAKING TIMER SA TAAS (FONT SIZE 80)
        JLabel timerDisplay = new JLabel("60", SwingConstants.CENTER);
        timerDisplay.setFont(new Font("Arial", Font.BOLD, 80));
        timerDisplay.setForeground(Color.BLACK);
        timerDisplay.setOpaque(true);
        timerDisplay.setBackground(Color.YELLOW);
        timerDisplay.setBounds(0, 0, 200, 155);
        middlePanel.add(timerDisplay);

        // SCORES
        JLabel leftNumber = new JLabel("0", SwingConstants.CENTER);
        leftNumber.setFont(new Font("Arial", Font.BOLD, 190));
        leftNumber.setForeground(Color.WHITE);
        leftNumber.setBounds(20, 170, 410, 320);
        bluePanel.add(leftNumber);

        JLabel rightNumber = new JLabel("0", SwingConstants.CENTER);
        rightNumber.setFont(new Font("Arial", Font.BOLD, 190));
        rightNumber.setForeground(Color.WHITE);
        rightNumber.setBounds(20, 170, 410, 320);
        orangePanel.add(rightNumber);

        // LEFT SCORE BUTTONS
        JButton leftPlus = new JButton("+");
        leftPlus.setBounds(65, 535, 130, 65);
        leftPlus.setFont(new Font("Arial", Font.BOLD, 35));
        bluePanel.add(leftPlus);

        JButton leftMinus = new JButton("-");
        leftMinus.setBounds(255, 535, 130, 65);
        leftMinus.setFont(new Font("Arial", Font.BOLD, 35));
        bluePanel.add(leftMinus);

        // RIGHT SCORE BUTTONS
        JButton rightPlus = new JButton("+");
        rightPlus.setBounds(65, 535, 130, 65);
        rightPlus.setFont(new Font("Arial", Font.BOLD, 35));
        orangePanel.add(rightPlus);

        JButton rightMinus = new JButton("-");
        rightMinus.setBounds(255, 535, 130, 65);
        rightMinus.setFont(new Font("Arial", Font.BOLD, 35));
        orangePanel.add(rightMinus);

        // FONT FOR CHECKBOXES
        Font cbFont = new Font("Arial", Font.BOLD, 13);

        // ROUND
        JLabel roundLabel = new JLabel("ROUND (2)", SwingConstants.CENTER);
        roundLabel.setBounds(0, 158, 200, 20);
        roundLabel.setOpaque(true);
        roundLabel.setBackground(new Color(0, 105, 150));
        roundLabel.setForeground(Color.WHITE);
        roundLabel.setFont(new Font("Arial", Font.BOLD, 11));
        middlePanel.add(roundLabel);

        JCheckBox roundLeft1 = new JCheckBox("1");
        roundLeft1.setBounds(15, 180, 80, 24);
        roundLeft1.setFont(cbFont);
        JCheckBox roundRight1 = new JCheckBox("1");
        roundRight1.setBounds(105, 180, 80, 24);
        roundRight1.setFont(cbFont);

        JCheckBox roundLeft2 = new JCheckBox("2");
        roundLeft2.setBounds(15, 205, 80, 24);
        roundLeft2.setFont(cbFont);
        JCheckBox roundRight2 = new JCheckBox("2");
        roundRight2.setBounds(105, 205, 80, 24);
        roundRight2.setFont(cbFont);

        middlePanel.add(roundLeft1);
        middlePanel.add(roundRight1);
        middlePanel.add(roundLeft2);
        middlePanel.add(roundRight2);

        // ADVANTAGE POINTS (Max 2 Checkboxes Each Team)
        JLabel advLabel = new JLabel("ADVANTAGE (Max 2)", SwingConstants.CENTER);
        advLabel.setBounds(0, 232, 200, 20);
        advLabel.setOpaque(true);
        advLabel.setBackground(new Color(0, 105, 150));
        advLabel.setForeground(Color.WHITE);
        advLabel.setFont(new Font("Arial", Font.BOLD, 11));
        middlePanel.add(advLabel);

        JCheckBox advLeft1 = new JCheckBox("1");
        advLeft1.setBounds(15, 254, 80, 22);
        advLeft1.setFont(cbFont);
        JCheckBox advRight1 = new JCheckBox("1");
        advRight1.setBounds(105, 254, 80, 22);
        advRight1.setFont(cbFont);

        JCheckBox advLeft2 = new JCheckBox("2");
        advLeft2.setBounds(15, 277, 80, 22);
        advLeft2.setFont(cbFont);
        JCheckBox advRight2 = new JCheckBox("2");
        advRight2.setBounds(105, 277, 80, 22);
        advRight2.setFont(cbFont);

        middlePanel.add(advLeft1); middlePanel.add(advRight1);
        middlePanel.add(advLeft2); middlePanel.add(advRight2);

        // FOUL
        JLabel foulLabel = new JLabel("FOUL (Max 3)", SwingConstants.CENTER);
        foulLabel.setBounds(0, 302, 200, 20);
        foulLabel.setOpaque(true);
        foulLabel.setBackground(new Color(0, 105, 150));
        foulLabel.setForeground(Color.WHITE);
        foulLabel.setFont(new Font("Arial", Font.BOLD, 11));
        middlePanel.add(foulLabel);

        JCheckBox foulLeft1 = new JCheckBox("1");
        foulLeft1.setBounds(15, 324, 80, 22);
        foulLeft1.setFont(cbFont);
        JCheckBox foulRight1 = new JCheckBox("1");
        foulRight1.setBounds(105, 324, 80, 22);
        foulRight1.setFont(cbFont);

        JCheckBox foulLeft2 = new JCheckBox("2");
        foulLeft2.setBounds(15, 347, 80, 22);
        foulLeft2.setFont(cbFont);
        JCheckBox foulRight2 = new JCheckBox("2");
        foulRight2.setBounds(105, 347, 80, 22);
        foulRight2.setFont(cbFont);

        JCheckBox foulLeft3 = new JCheckBox("3");
        foulLeft3.setBounds(15, 370, 80, 22);
        foulLeft3.setFont(cbFont);
        JCheckBox foulRight3 = new JCheckBox("3");
        foulRight3.setBounds(105, 370, 80, 22);
        foulRight3.setFont(cbFont);

        middlePanel.add(foulLeft1); middlePanel.add(foulRight1);
        middlePanel.add(foulLeft2); middlePanel.add(foulRight2);
        middlePanel.add(foulLeft3); middlePanel.add(foulRight3);

        // DISARM
        JLabel disarmLabel = new JLabel("DISARM (Max 2)", SwingConstants.CENTER);
        disarmLabel.setBounds(0, 395, 200, 20);
        disarmLabel.setOpaque(true);
        disarmLabel.setBackground(new Color(0, 105, 150));
        disarmLabel.setForeground(Color.WHITE);
        disarmLabel.setFont(new Font("Arial", Font.BOLD, 11));
        middlePanel.add(disarmLabel);

        JCheckBox disarmLeft1 = new JCheckBox("1");
        disarmLeft1.setBounds(15, 417, 80, 22);
        disarmLeft1.setFont(cbFont);
        JCheckBox disarmRight1 = new JCheckBox("1");
        disarmRight1.setBounds(105, 417, 80, 22);
        disarmRight1.setFont(cbFont);

        JCheckBox disarmLeft2 = new JCheckBox("2");
        disarmLeft2.setBounds(15, 440, 80, 22);
        disarmLeft2.setFont(cbFont);
        JCheckBox disarmRight2 = new JCheckBox("2");
        disarmRight2.setBounds(105, 440, 80, 22);
        disarmRight2.setFont(cbFont);

        middlePanel.add(disarmLeft1); middlePanel.add(disarmRight1);
        middlePanel.add(disarmLeft2); middlePanel.add(disarmRight2);

        // WINNER POP-UP
        class WinChecker {
            public void showWinner(String teamName, Color themeColor) {
                if (timer != null) {
                    timer.stop();
                }
                running = false;

                JLabel notification = new JLabel("TEAM " + teamName.toUpperCase() + " WINS THE ROUND!", SwingConstants.CENTER);
                notification.setOpaque(true);
                notification.setBackground(themeColor);
                notification.setPreferredSize(new Dimension(450, 120));
                notification.setForeground(Color.WHITE);
                notification.setFont(new Font("Arial", Font.BOLD, 20));

                JOptionPane.showMessageDialog(frame, notification, "GAME OVER", JOptionPane.PLAIN_MESSAGE);
            }
            
            public void showCustomWinner(String message, Color themeColor) {
                if (timer != null) {
                    timer.stop();
                }
                running = false;

                JLabel notification = new JLabel(message, SwingConstants.CENTER);
                notification.setOpaque(true);
                notification.setBackground(themeColor);
                notification.setPreferredSize(new Dimension(450, 120));
                notification.setForeground(Color.WHITE);
                notification.setFont(new Font("Arial", Font.BOLD, 18));

                JOptionPane.showMessageDialog(frame, notification, "ROUND ENDED", JOptionPane.PLAIN_MESSAGE);
            }
        }
        WinChecker checker = new WinChecker();

        // SCORE ACTIONS (With Automatic First Advantage Assignment)
        leftPlus.addActionListener(e -> {
            leftScore++;
            leftNumber.setText("" + leftScore);

            if (firstAdvantageTeam == null) {
                firstAdvantageTeam = textFieldLname.getText();
                if (!advLeft1.isSelected()) {
                    advLeft1.setSelected(true);
                    advLeftCount++;
                }
            }
        });

        leftMinus.addActionListener(e -> {
            if (leftScore > 0) {
                leftScore--;
                leftNumber.setText("" + leftScore);
            }
        });

        rightPlus.addActionListener(e -> {
            rightScore++;
            rightNumber.setText("" + rightScore);

            if (firstAdvantageTeam == null) {
                firstAdvantageTeam = textFieldRname.getText();
                if (!advRight1.isSelected()) {
                    advRight1.setSelected(true);
                    advRightCount++;
                }
            }
        });

        rightMinus.addActionListener(e -> {
            if (rightScore > 0) {
                rightScore--;
                rightNumber.setText("" + rightScore);
            }
        });

        // ADVANTAGE LISTENERS
        ActionListener advListenerLeft = e -> {
            JCheckBox source = (JCheckBox) e.getSource();
            if (source.isSelected()) {
                advLeftCount++;
                if (firstAdvantageTeam == null) {
                    firstAdvantageTeam = textFieldLname.getText();
                }
            } else {
                advLeftCount--;
                if (advLeftCount == 0 && firstAdvantageTeam != null && firstAdvantageTeam.equals(textFieldLname.getText())) {
                    firstAdvantageTeam = advRightCount > 0 ? textFieldRname.getText() : null;
                }
            }
        };

        ActionListener advListenerRight = e -> {
            JCheckBox source = (JCheckBox) e.getSource();
            if (source.isSelected()) {
                advRightCount++;
                if (firstAdvantageTeam == null) {
                    firstAdvantageTeam = textFieldRname.getText();
                }
            } else {
                advRightCount--;
                if (advRightCount == 0 && firstAdvantageTeam != null && firstAdvantageTeam.equals(textFieldRname.getText())) {
                    firstAdvantageTeam = advLeftCount > 0 ? textFieldLname.getText() : null;
                }
            }
        };

        advLeft1.addActionListener(advListenerLeft);
        advLeft2.addActionListener(advListenerLeft);

        advRight1.addActionListener(advListenerRight);
        advRight2.addActionListener(advListenerRight);

        // FOUL LISTENERS
        foulLeft1.addActionListener(e -> { if(foulLeft1.isSelected()) foulLeftCount++; else foulLeftCount--; });
        foulLeft2.addActionListener(e -> { if(foulLeft2.isSelected()) foulLeftCount++; else foulLeftCount--; });
        foulLeft3.addActionListener(e -> { 
            if(foulLeft3.isSelected()) {
                foulLeftCount++;
                checker.showWinner(textFieldRname.getText(), new Color(235, 75, 30));
            } else {
                foulLeftCount--;
            }
        });

        foulRight1.addActionListener(e -> { if(foulRight1.isSelected()) foulRightCount++; else foulRightCount--; });
        foulRight2.addActionListener(e -> { if(foulRight2.isSelected()) foulRightCount++; else foulRightCount--; });
        foulRight3.addActionListener(e -> { 
            if(foulRight3.isSelected()) {
                foulRightCount++;
                checker.showWinner(textFieldLname.getText(), new Color(0, 145, 210));
            } else {
                foulRightCount--;
            }
        });

        // DISARM LISTENERS
        disarmLeft1.addActionListener(e -> { if(disarmLeft1.isSelected()) disarmLeftCount++; else disarmLeftCount--; });
        disarmLeft2.addActionListener(e -> { 
            if(disarmLeft2.isSelected()) {
                disarmLeftCount++;
                checker.showWinner(textFieldRname.getText(), new Color(235, 75, 30));
            } else {
                disarmLeftCount--;
            }
        });

        disarmRight1.addActionListener(e -> { if(disarmRight1.isSelected()) disarmRightCount++; else disarmRightCount--; });
        disarmRight2.addActionListener(e -> { 
            if(disarmRight2.isSelected()) {
                disarmRightCount++;
                checker.showWinner(textFieldLname.getText(), new Color(0, 145, 210));
            } else {
                disarmRightCount--;
            }
        });

        // VS LABEL SA BABA (MAY YELLOW BACKGROUND DIN)
        JLabel vs = new JLabel("VS", SwingConstants.CENTER);
        vs.setFont(new Font("Arial", Font.BOLD, 55));
        vs.setForeground(Color.BLACK);
        vs.setOpaque(true);
        vs.setBackground(Color.YELLOW);
        vs.setBounds(0, 468, 200, 60);
        middlePanel.add(vs);

        // TIMER BUTTONS
        JButton start = new JButton("START");
        start.setBounds(5, 536, 90, 38);

        JButton pause = new JButton("PAUSE");
        pause.setBounds(102, 536, 93, 38);

        JButton reset = new JButton("RESET");
        reset.setBounds(35, 580, 130, 42);

        middlePanel.add(start);
        middlePanel.add(pause);
        middlePanel.add(reset);

        // TIMER LOGIC
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seconds--;
                
                if (seconds <= 0) {
                    timer.stop();
                    running = false;
                    pause.setText("PAUSE");
                    timerDisplay.setFont(new Font("Arial", Font.BOLD, 18));
                    timerDisplay.setText("TIME'S UP!");

                    if (leftScore > rightScore) {
                        checker.showWinner(textFieldLname.getText(), new Color(0, 145, 210));
                    } else if (rightScore > leftScore) {
                        checker.showWinner(textFieldRname.getText(), new Color(235, 75, 30));
                    } else {
                        if (advLeftCount > advRightCount) {
                            checker.showCustomWinner("TIE-BREAKER: " + textFieldLname.getText().toUpperCase() + " WINS VIA ADVANTAGE!", new Color(0, 145, 210));
                        } else if (advRightCount > advLeftCount) {
                            checker.showCustomWinner("TIE-BREAKER: " + textFieldRname.getText().toUpperCase() + " WINS VIA ADVANTAGE!", new Color(235, 75, 30));
                        } else if (firstAdvantageTeam != null) {
                            Color theme = firstAdvantageTeam.equals(textFieldLname.getText()) ? new Color(0, 145, 210) : new Color(235, 75, 30);
                            checker.showCustomWinner("TIE-BREAKER: " + firstAdvantageTeam.toUpperCase() + " WINS (FIRST ADVANTAGE)!", theme);
                        } else {
                            checker.showCustomWinner("SUDDEN DEATH: NO ADVANTAGE RECORDED!", new Color(100, 100, 100));
                        }
                    }
                } else {
                    timerDisplay.setText("" + seconds);
                }
            }
        });

        // START BUTTON
        start.addActionListener(e -> {
            if (!running && seconds > 0) {
                timer.start();
                running = true;
                pause.setText("PAUSE");
            }
        });

        // PAUSE / RESUME BUTTON
        pause.addActionListener(e -> {
            if (running) {
                timer.stop();
                running = false;
                pause.setText("RESUME");
            } else if (seconds > 0) {
                timer.start();
                running = true;
                pause.setText("PAUSE");
            }
        });

        // RESET BUTTON
        reset.addActionListener(e -> {
            timer.stop();
            running = false;

            seconds = 60;
            leftScore = 0;
            rightScore = 0;
            foulLeftCount = 0;
            foulRightCount = 0;
            disarmLeftCount = 0;
            disarmRightCount = 0;
            advLeftCount = 0;
            advRightCount = 0;
            firstAdvantageTeam = null;

            leftNumber.setText("0");
            rightNumber.setText("0");
            
            timerDisplay.setFont(new Font("Arial", Font.BOLD, 150));
            timerDisplay.setText("60");
            pause.setText("PAUSE");
            
            // Uncheck all checkboxes
            roundLeft1.setSelected(false); roundRight1.setSelected(false);
            roundLeft2.setSelected(false); roundRight2.setSelected(false);
            advLeft1.setSelected(false); advRight1.setSelected(false);
            advLeft2.setSelected(false); advRight2.setSelected(false);
            foulLeft1.setSelected(false); foulRight1.setSelected(false);
            foulLeft2.setSelected(false); foulRight2.setSelected(false);
            foulLeft3.setSelected(false); foulRight3.setSelected(false);
            disarmLeft1.setSelected(false); disarmRight1.setSelected(false);
            disarmLeft2.setSelected(false); disarmRight2.setSelected(false);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}