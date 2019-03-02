package ding.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.nio.file.OpenOption;

public class InfoPanel extends JPanel {
    private int score=0;
    private final int POSITION_X=0;
    private final int POSITION_Y=0;
    private final int WIDTH=200;
    private final int HEIGHT=600;
    private paintPanel nextPaintPanel=new paintPanel();
    private int DIRECTION_LEFT=-1;
    private int DIRECTION_RIGHT=1;
    private int DIRECTION_NONE=0;
    private JLabel displayScoreLabel=new JLabel("0");
    public InfoPanel(){
        setBounds(POSITION_X,POSITION_Y,WIDTH,HEIGHT);
        setLayout(new GridLayout(8,1));
        JLabel nextLabel=new JLabel("Next");
        nextLabel.setFont(new Font("Serif",Font.BOLD,30));
        JLabel scoreLabel =new JLabel("Score");
        scoreLabel.setFont(new Font("Serif",Font.BOLD,30));
        JButton startButton=new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.startTimer();
            }
        });
        JButton pauseButton=new JButton("pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.stopTimer();
            }
        });
        JButton restartButton=new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel.resetGamePanel();
                score=0;
                setScore(0);
                repaint();
            }
        });
        JPanel buttonPanel1=new JPanel();
        buttonPanel1.add(startButton);
        buttonPanel1.add(pauseButton);
        JPanel buttonPanel2=new JPanel();
        buttonPanel2.add(restartButton);
        add(nextLabel);
        add(nextPaintPanel);
        add(scoreLabel);
        add(displayScoreLabel);
        add(buttonPanel1);
        add(buttonPanel2);
        JPanel operation=new JPanel();
        JButton upButton=new JButton("UP");
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.gamePanel.changePosition();
                setScore(MainWindow.gamePanel.checkLine());
            }
        });
        operation.add(upButton);
        JButton downButton=new JButton("DOWN");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.gamePanel.moveToBottom();
                setScore(MainWindow.gamePanel.checkLine());
            }
        });
        operation.add(downButton);
        JButton leftButton=new JButton("LEFT");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.gamePanel.move(DIRECTION_LEFT);
                setScore(MainWindow.gamePanel.checkLine());
            }
        });
        operation.add(leftButton);
        JButton rightButton=new JButton("RIGHT");
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.gamePanel.move(DIRECTION_RIGHT);
                setScore(MainWindow.gamePanel.checkLine());
            }
        });
        operation.add(rightButton);
        add(operation);
    }
    public void setScore(int s){
        score+=s*100;
        displayScoreLabel.setText(String.valueOf(score));
    }
}
class paintPanel extends JPanel{
    private static Blocks nextBlock;
    public paintPanel(){
        nextBlock=getNextBlock();
    }
    public static Blocks getNextBlock(){
        int i=(int)(Math.random()*7);
        if(i==0){
            nextBlock=new TuBlock();
        }else if(i==1){
            nextBlock=new TianBlock();
        }else if(i==2){
            nextBlock=new QiBlock();
        }else if(i==3){
            nextBlock=new RotationQiBlock();
        }else if(i==4){
            nextBlock=new ZBlock();
        }else if(i==5){
            nextBlock=new RotationZBlock();
        }else if(i==6){
            nextBlock=new YiBlock();
        }
        return nextBlock;
    }
    private final double POINT_DISTANCE=12;
    private final double BLOCK_WIDTH=10;
    private final double BLOCK_HEIGHT=10;
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        int[][] locations=nextBlock.getLocation(4,4);
        g2.setPaint(Color.BLUE);

        for(int i=0;i<4;i++)
        {
            Rectangle2D rectangle=new Rectangle2D.Double(locations[i][0]*POINT_DISTANCE-1,locations[i][1]*POINT_DISTANCE-1,BLOCK_WIDTH,BLOCK_HEIGHT);
            g2.fill(rectangle);
        }
    }
    public static Blocks getBlock(){
        Blocks temp=nextBlock;
        nextBlock=getNextBlock();
        return temp;
    }

}
