package ding.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel {
    private int BLOCK_WIDTH=18;//方块的边长
    private int BLOCK_HEIGHT=18;
    private int POINT_DISTANCE=20;//坐标点的间距
    private int[] currentLocation=new int[]{0,0};//当前参考点的坐标
    private Blocks currentBlock;//目前正在游戏区活动的方块
    private static boolean flag=true;//为true时，表示当前没有活动的方块
    private int[][] locations;
    private static int[][] grid=new int[20][30];
    public GamePanel(){
        for(int i=0;i<20;i++){
            for(int j=0;j<30;j++){
                grid[i][j]=0;
            }
        }
        setBackground(Color.BLACK);
        currentBlock = paintPanel.getBlock();
        currentLocation[0]=9;
        currentLocation[1]=0;
        locations=currentBlock.getLocation(currentLocation[0],currentLocation[1]);
        flag=false;
    }
    public void move(int direction){
        Class<? extends Blocks> curBlock = currentBlock.getClass();
        System.out.println(curBlock.getName());
        System.out.println("direction:"+direction);
        if(flag==true){
            currentBlock=paintPanel.getBlock();


            currentLocation[0]=9;
            currentLocation[1]=0;
            locations = currentBlock.getLocation(currentLocation[0], currentLocation[1]);
            flag=false;
        }
        if(isBottom(locations)==true){
            flag=true;
            for(int i=0;i<4;i++){
                if(locations[i][1]>=0){
                    grid[locations[i][0]][locations[i][1]]=1;
                }
            }
            repaint();
            if(checkOver()==true){
                MainWindow.stopTimer();
                JOptionPane.showMessageDialog(null,"Game Over");
            }
            return;
        }
        if(isEdge(direction)==false){
            currentLocation[0]+=direction;
        }
        if(direction==0){
            currentLocation[1]++;
        }
        locations=currentBlock.getLocation(currentLocation[0],currentLocation[1]);
        repaint();
    }
    public boolean isEdge(int direction){

        for(int i=0;i<4;i++){
            if(locations[i][1]>=0) {
                if ((locations[i][0] + direction) >= 20 || (locations[i][0] + direction) < 0 || grid[locations[i][0] + direction][locations[i][1]] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkOver(){
        for(int i=0;i<4;i++){
            if(locations[i][1]<0){
                return true;
            }
        }
        return false;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setPaint(Color.BLUE);
        for(int i=0;i<20;i++){
            for(int j=0;j<30;j++){
                if(grid[i][j]==1){
                    Rectangle2D rec=new Rectangle2D.Double(i*POINT_DISTANCE-1,j*POINT_DISTANCE-1,BLOCK_WIDTH,BLOCK_HEIGHT);
                    g2.fill(rec);
                }
            }
        }
        for(int i=0;i<4;i++)
        {
            if(locations[i][1]>=0)
            {
                Rectangle2D rectangle=new Rectangle2D.Double(locations[i][0]*POINT_DISTANCE-1,locations[i][1]*POINT_DISTANCE-1,BLOCK_WIDTH,BLOCK_HEIGHT);
                g2.fill(rectangle);
            }
        }
    }

    public boolean isBottom(int[][] l){
        for(int i=0;i<4;i++){
            if(l[i][1]>=0&&l[i][0]<20){
                if(l[i][1]+1>=30||grid[l[i][0]][l[i][1]+1]==1){
                    return true;
                }
            }
        }
        return false;
    }
    public int checkLine(){
        int lines=0;
        for(int i=0;i<30;i++){
            int sum=0;
            for(int j=0;j<20;j++){
                sum+=grid[j][i];
            }
            if(sum==20){
                lines++;
                for(int k=i;k>0;k--){
                    for(int h=0;h<20;h++){
                        grid[h][k]=grid[h][k-1];
                    }
                }
                for(int k=0;k<20;k++){
                    grid[k][0]=0;
                }
                i--;
                repaint();
            }
        }
        return lines;
    }
    public static void resetGamePanel(){
        for(int i=0;i<30;i++){
            for(int j=0;j<20;j++){
                grid[j][i]=0;
            }
        }
        flag=true;
    }
    public void changePosition(){
        currentBlock.changePosition();
        int min=0;
        int max=0;
        locations=currentBlock.getLocation(currentLocation[0],currentLocation[1]);
        for(int i=0;i<4;i++){
            if(min>locations[i][0]){
                min=locations[i][0];
            }
            if(max<locations[i][0]){
                max=locations[i][0];
            }
        }
        if(min<0){
            currentLocation[0]+=(-min);
        }
        if(max>19){
            currentLocation[1]-=(max-19);
        }
        locations=currentBlock.getLocation(currentLocation[0],currentLocation[1]);
        repaint();
    }
    public void moveToBottom(){
        int i=0;
        while(isBottom(locations)==false){
            i++;
            for(int j=0;j<4;j++){
                locations[j][1]++;
            }
        }
        for(int j=0;j<4;j++){
            if(locations[j][1]>=0){
                grid[locations[j][0]][locations[j][1]]=1;
            }
        }
        flag=true;
        repaint();
    }
}
