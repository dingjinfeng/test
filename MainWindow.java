package ding.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;

public class MainWindow extends JFrame {
    public static InfoPanel infoPanel;
    public static GamePanel gamePanel;
    private int DIRECTION_LEFT=-1;
    private int DIRECTION_RIGHT=1;
    private int DIRECTION_NONE=0;
    private static Timer timer;
    public MainWindow(){
        setSize(800,900);
        setLocation(WindowUtils.getScreenWidth()/2-200,WindowUtils.getScreenHeight()/2-400);
        setResizable(false);
        infoPanel=new InfoPanel();
        gamePanel=new GamePanel();
        timer=new Timer(500,new TimeActionListener());
        add(infoPanel,BorderLayout.WEST);
        add(gamePanel,BorderLayout.CENTER);
    }
    public static void startTimer()
    {
        timer.start();
    }
    public static void stopTimer()
    {
        timer.stop();
    }


    public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            if(e.getKeyCode()==KeyEvent.VK_DOWN){
                System.out.println("down");
                gamePanel.moveToBottom();
                infoPanel.setScore(gamePanel.checkLine());
            }else if(e.getKeyCode()== KeyEvent.VK_LEFT){
                System.out.println("left");
                gamePanel.move(DIRECTION_LEFT);
                infoPanel.setScore(gamePanel.checkLine());
            }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                System.out.println("right");
                gamePanel.move(DIRECTION_RIGHT);
                infoPanel.setScore(gamePanel.checkLine());
            }else if(e.getKeyCode()==KeyEvent.VK_UP){
                System.out.println("up");
                gamePanel.changePosition();
                infoPanel.setScore(gamePanel.checkLine());
            }

    }

    private class TimeActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.move(DIRECTION_NONE);
            infoPanel.setScore(gamePanel.checkLine());
            infoPanel.repaint();
        }
    }


}
