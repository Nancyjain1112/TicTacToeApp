package tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {

    JButton[][] buttons = new JButton[3][3];
    char currentPlayer = 'X';
    JLabel statusLabel;
    JPanel boardPanel;
    JButton restartButton;

    String player1Name;
    String player2Name;

    TicTacToe(String player1, String player2) {


        this.player1Name = player1;
        this.player2Name = player2;



        getContentPane().setBackground(new Color(240,250,255));

        setTitle("Tic Tac Toe");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel;

        titleLabel = new JLabel("Tic Tac Toe",SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial",Font.BOLD,32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));

        statusLabel = new JLabel(player1Name+" 's Turn(X)", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2,1));

        topPanel.add(titleLabel);
        topPanel.add(statusLabel);

        boardPanel = new JPanel();
        boardPanel.setBackground(new Color(240,250,255));
        boardPanel.setLayout(new GridLayout(3, 3,5,5));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(e -> {
                    JButton btn = (JButton) e.getSource();

                    if (btn.getText().equals("")) {
                        btn.setText(String.valueOf(currentPlayer));
                        if(currentPlayer == 'X')
                        {
                            btn.setForeground(Color.BLUE);
                        }
                        else {
                            btn.setForeground(Color.RED);
                        }
                        if (checkWinner()) {

                            String winnername = (currentPlayer =='X')?player1Name:player2Name;

                            JOptionPane.showMessageDialog(this,
                                    winnername + " Wins!");

                            statusLabel.setText(winnername+ " Wins!");
                            disableBoard();

                            return;
                        }
                        if (checkDraw()) {
                            JOptionPane.showMessageDialog(this,
                                    "Match Draw!");

                            statusLabel.setText("Match Draw!");
                            disableBoard();

                            return;
                        }
                        if (currentPlayer == 'X') {
                            currentPlayer = 'O';
                            statusLabel.setText(player2Name + "'s Turn(O)");
                        } else {
                            currentPlayer = 'X';
                            statusLabel.setText(player1Name + "'s Turn(X)");
                        }
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }
        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);

        restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial",Font.BOLD,18));
        restartButton.setBackground(new Color(76,175,80));
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        add(restartButton,BorderLayout.SOUTH);

        //add action listener

        restartButton.addActionListener(e -> resetGame());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    //winner detection method
    public boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (!buttons[0][i].getText().equals("") &&
                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }
        }

        if (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }

        if (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()))
        {
            return true;
        }
        return false;
    }
     //Disable board method
    private void disableBoard()
    {
        for(int i = 0;i<3;i++)
        {
            for(int j = 0;j<3;j++)
            {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    //checkdraw method
    private boolean checkDraw()
    {
        for (int i = 0;i<3;i++)
        {
            for(int j = 0;j<3;j++)
            {
                if(buttons[i][j].getText().isEmpty())
                {
                    return false;
                }
            }
        }
        return true;
    }

    //reset game method
   private void  resetGame()
   {
       for(int i = 0;i<3;i++)
       {
           for(int j = 0;j<3;j++)
           {
               buttons[i][j].setText("");
               buttons[i][j].setEnabled(true);
           }
           currentPlayer = 'X';

           statusLabel.setText(player1Name+"'s Turn(X)");
       }
   }


}
