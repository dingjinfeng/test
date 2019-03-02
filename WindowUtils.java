package ding.Game;

import java.awt.*;

public class WindowUtils {
    private static Dimension screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
    public static int getScreenHeight(){
        return (int)screensize.getHeight();
    }
    public static int getScreenWidth(){
        return (int)screensize.getWidth();
    }
}
