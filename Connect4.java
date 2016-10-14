import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The main funciton for connect four. The game board is initialized as a Connect4 JFrame.
 * @author (Alex Hoecht) 
 * @version (version 1.0)
 */
public class Connect4
{
    public static void main(String[] args)
    {
        Connect4JFrame frame = new Connect4JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
