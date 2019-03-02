package ding.Game;

import jdk.nashorn.internal.ir.Block;

public class Blocks {
    protected int currentMethod=0;
    public int[][] getLocation(int x,int y){
        return new int[4][2];
    }
    public void changePosition(){
        currentMethod++;
        if(currentMethod>3){
            currentMethod=0;
        }
    }
}
class TuBlock extends Blocks{ //土
    public int[][] getLocation(int x,int y){
        int[][] points=new int[4][2];
        points[0]=new int[]{x,y};
        if(currentMethod==0){
            points[1]=new int[]{x-1,y};
            points[2]=new int[]{x+1,y};
            points[3]=new int[]{x,y-1};
        }else if(currentMethod==1){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x,y-2};
            points[3]=new int[]{x-1,y-1};
        }else if(currentMethod==2){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x-1,y-1};
            points[3]=new int[]{x+1,y-1};
        }else if(currentMethod==3){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x,y-2};
            points[3]=new int[]{x+1,y-1};
        }
        return points;
    }
}
class TianBlock extends Blocks{ //田
    public int[][] getLocation(int x,int y){
        int[][] points=new int[4][2];
        points[0]=new int[]{x,y};
        points[1]=new int[]{x+1,y};
        points[2]=new int[]{x+1,y-1};
        points[3]=new int[]{x,y-1};
        return points;
    }
}
class QiBlock extends Blocks{  //7
    public int[][] getLocation(int x,int y){
        int[][] points=new int[4][2];
        points[0]=new int[]{x,y};
        if(currentMethod==0){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x,y-2};
            points[3]=new int[]{x+1,y-2};
        }else if(currentMethod==1){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x-1,y-1};
            points[3]=new int[]{x-2,y-1};
        }else if(currentMethod==2){
            points[1]=new int[]{x-1,y};
            points[2]=new int[]{x,y-1};
            points[3]=new int[]{x,y-2};
        }else if(currentMethod==3){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x+1,y};
            points[3]=new int[]{x+2,y};
        }
        return points;
    }

}
class RotationQiBlock extends Blocks{
    public int[][] getLocation(int x,int y)
    {
        int[][] points=new int[4][2];
        points[0]=new int[]{x,y};
        if(currentMethod==0)
        {
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x,y-2};
            points[3]=new int[]{x-1,y-2};
        }
        else if(currentMethod==1)
        {
            points[1]=new int[]{x-1,y};
            points[2]=new int[]{x+1,y};
            points[3]=new int[]{x+1,y-1};
        }
        else if(currentMethod==2)
        {
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x,y-2};
            points[3]=new int[]{x+1,y};
        }
        else if(currentMethod==3)
        {
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x+1,y-1};
            points[3]=new int[]{x+2,y-1};
        }
        return points;
    }
}
class ZBlock extends Blocks{//Z
    public int[][] getLocation(int x,int y){
        int[][] points=new int[4][2];
        points[0]=new int[]{x,y};
        if(currentMethod==0||currentMethod==2){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x-1,y-1};
            points[3]=new int[]{x-1,y-2};
        }else if(currentMethod==1||currentMethod==3){
            points[1]=new int[]{x-1,y};
            points[2]=new int[]{x,y-1};
            points[3]=new int[]{x+1,y-1};
        }
        return points;
    }

}
class RotationZBlock extends Blocks{//5
    public int[][] getLocation(int x,int y){
        int[][] points=new int[4][2];
        points[0]=new int[]{x,y};
        if(currentMethod==0||currentMethod==2){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x+1,y-1};
            points[3]=new int[]{x+1,y-2};
        }else if(currentMethod==1||currentMethod==3){
            points[1]=new int[]{x+1,y};
            points[2]=new int[]{x,y-1};
            points[3]=new int[]{x-1,y-1};
        }
        return points;
    }
}
class YiBlock extends Blocks{//-
    public int[][] getLocation(int x,int y){
        int[][] points=new int[4][2];
        points[0]=new int[]{x,y};
        if(currentMethod==0||currentMethod==2){
            points[1]=new int[]{x,y-1};
            points[2]=new int[]{x,y-2};
            points[3]=new int[]{x,y-3};
        }else if(currentMethod==1||currentMethod==3){
            points[1]=new int[]{x+1,y};
            points[2]=new int[]{x+2,y};
            points[3]=new int[]{x-1,y};
        }
        return points;
    }

}