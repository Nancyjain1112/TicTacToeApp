package tictactoe;

import javax.swing.*;
import java.awt.*;

public class PlayerSetup extends JFrame {

    JTextField player1Field;
    JTextField player2Field;

    JButton startButton;

    PlayerSetup()
    {
        setTitle("Tic Tac Toe Setup");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(6,1,10,10));

        JLabel title = new JLabel("Tic Tac Toe",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,26));

        player1Field = new JTextField();
        player2Field = new JTextField();

        startButton = new JButton("Start Game");

        startButton.addActionListener(e -> {
            String player1 = player1Field.getText().trim();
            String player2 = player2Field.getText().trim();

            if(player1.isEmpty()||player2.isEmpty())
            {
                JOptionPane.showMessageDialog(this,
                        "Please enter both player names..");

                return;
            }
            new TicTacToe(player1,player2);
            dispose();
        });



        add(title);
        add(new JLabel("Player 1 Name(X):"));
        add(player1Field);
        add(new JLabel("Player 2 Name(O):"));
        add(player2Field);
        add(startButton);

        setVisible(true);
    }
    public static void main(String[] args) {
        new PlayerSetup();
    }
}
