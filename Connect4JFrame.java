import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The code for the entire connect4 game. (MODIFY SO NOT ALL ONE CLASS)
 * 
 * @author (Alex Hoecht) 
 * @version (version 1)
 */
public class Connect4JFrame extends JFrame implements ActionListener
{
    // static variables
    public static final int BLANK = 0;
    public static final int GREEN = 1;
    public static final int MAGENTA = 2;
    public static final int MAXinROW = 6;
    public static final int MAXinCOL = 7;
    public static final String SPACE = "                  "; //18 spaces
    // instance variables
    private Button b1,b2,b3,b4,b5,b6,b7;
    private Label labelSpacer;
    private MenuItem newMI, exitMI, greenMI, magentaMI;
    private int[][] list;
    private int activeColour = GREEN;
    private boolean end = false;
    private boolean gameStart;
    

    /**
     * Constructor for objects of class Connect4JFrame
     */
    public Connect4JFrame()
    {
        // initialise the game
        
        //Title of the game
        setTitle("Connect 4");
        //Create a menu for the game
        MenuBar mbar = new MenuBar();
        //File Tab
        Menu fileMenu = new Menu("File");
        newMI = new MenuItem("New");        //New Game
        newMI.addActionListener(this);
        fileMenu.add(newMI);
        exitMI = new MenuItem("Exit");      //Exit Game
        exitMI.addActionListener(this);
        fileMenu.add(exitMI);
        mbar.add(fileMenu);
        //Settings Tab
        Menu optMenu = new Menu("Settings");
        greenMI = new MenuItem("Green starts");      //Red will start next game
        greenMI.addActionListener(this);
        optMenu.add(greenMI);
        magentaMI = new MenuItem("Purple starts");       //Yellow will start the next game
        magentaMI.addActionListener(this);
        optMenu.add(magentaMI);
        mbar.add(optMenu);
        setMenuBar(mbar);
        
        //Initialise a control panel
        Panel panel = new Panel();
        //Button 1
        b1 = new Button("1");
        b1.addActionListener(this);
        panel.add(b1);
        labelSpacer = new Label(SPACE);
        panel.add(labelSpacer);
        //Button 2
        b2 = new Button("2");
        b2.addActionListener(this);
        panel.add(b2);
        labelSpacer = new Label(SPACE);
        panel.add(labelSpacer);
        //Button 3
        b3 = new Button("3");
        b3.addActionListener(this);
        panel.add(b3);
        labelSpacer = new Label(SPACE);
        panel.add(labelSpacer);
        //Button 4
        b4 = new Button("4");
        b4.addActionListener(this);
        panel.add(b4);
        labelSpacer = new Label(SPACE);
        panel.add(labelSpacer);
        //Button 5
        b5 = new Button("5");
        b5.addActionListener(this);
        panel.add(b5);
        labelSpacer = new Label(SPACE);
        panel.add(labelSpacer);
        //Button 6
        b6 = new Button("6");
        b6.addActionListener(this);
        panel.add(b6);
        labelSpacer = new Label(SPACE);
        panel.add(labelSpacer);
        //Button 7
        b7 = new Button("7");
        b7.addActionListener(this);
        panel.add(b7);
        labelSpacer = new Label(SPACE);
        panel.add(labelSpacer);
        
        add(panel,BorderLayout.NORTH);
        initialize();
        
        setSize(1080,800);
    }

    /**
     * Makes all locations in the game BLANK.
     *  i   Rows on the game board
     *  j   Coloumns on the game board
     */
    public void initialize()
    {
        list = new int[MAXinROW][MAXinCOL];
        for(int i = 0; i < MAXinROW; i++)
        {
            for(int j = 0; j < MAXinCOL; j++)
            {
                list[i][j] = BLANK;
            }
        }
        gameStart = false;
    }
    
    /**
     * Paints the entire gameboard blue.
     * If a location on the board is equal to a color in INT form, it colors 
     * that location that color.
     */
    public void paint(Graphics g)
    {
        //Paint the game board blue
        g.setColor(Color.BLACK);
        g.fillRect(110, 40, 120+100*MAXinCOL, 120+100*MAXinROW);
        for(int i = 0; i < MAXinROW; i++)
        {
            for(int j = 0; j < MAXinCOL; j++)
            {
                if(list[i][j]==BLANK)
                {
                    g.setColor(Color.WHITE);
                }
                if(list[i][j]==GREEN)
                {
                    g.setColor(Color.GREEN);
                }
                if(list[i][j]==MAGENTA)
                {
                    g.setColor(Color.MAGENTA);
                }
                g.fillOval(160+100*j, 150+100*i, 100, 100);
            }
        }
        check4(g);
    }
    
    /**
     * Puts a disk in the target coloumn.
     * Once a disk is placed, it is the other person's turn.
     * If the game is over, nothing will happen.
     * 
     * @param   n   The target coloumn
     */
    public void putDisk(int n)
    {
        //If game is over
        if(end)
        {
            return;
        }
        gameStart = true;
        int i;
        n--;
        for(i = 0; i < MAXinROW; i++)
        {
            if(list[i][n]>0)
            {
                break;
            }
        }
        if(i>0)
        {
            list[--i][n] = activeColour;
            if(activeColour == GREEN)
            {
                activeColour = MAGENTA;
            }
            else
            {
                activeColour = GREEN;
            }
            repaint();
        }
    }
    
    /**
     * When 4 in a row have been found, a winner is displayed.
     */
    public void displayWinner(Graphics g, int n)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("Courier", Font.BOLD, 150));
        if(n==GREEN)
        {
            g.drawString("Green wins!", 100, 400);
        }
        else
        {
            g.drawString("Magenta wins!", 100 ,400);
        }
        end = true;
    }
    
    /**
     * Checks for 4 in a row
     */
    public void check4(Graphics g)
    {
        //Checking horizontal rows
        for(int i = 0; i < MAXinROW; i++)
        {
            for(int j = 0; j < MAXinCOL-3; j++)
            {
                int curr = list[i][j];
                if(curr > 0  &&  curr == list[i][j+1]  &&  curr == list[i][j+2]
                                                       && curr == list[i][j+3])
                {
                    displayWinner(g, list[i][j]);
                }
            }
        }
        //Checking vertical rows
        for(int i = 0; i < MAXinROW-3; i++)
        {
            for(int j = 0; j < MAXinCOL; j++)
            {
                int curr = list[i][j];
                if(curr > 0  &&  curr == list[i+1][j]  &&  curr == list[i+2][j]
                                                       &&  curr == list[i+3][j])
                {
                    displayWinner(g, list[i][j]);
                }
            }
        }
        //Checking bottom left to upper right
        for(int i = 0; i < MAXinROW-3; i++)
        {
            for(int j = 0; j < MAXinCOL-3; j++)
            {
                int curr = list[i][j];
                if(curr>0  &&  curr == list[i+1][j+1]  &&  curr == list[i+2][j+2]
                                                       &&  curr == list[i+3][j+3])
                {
                    displayWinner(g, list[i][j]);
                }
            }
        }
        //Checking upper left to bottom right
        for(int i = MAXinROW-1; i >= 3; i--)
        {
            for(int j = 0; j < MAXinCOL-3; j++)
            {
                int curr = list[i][j];
                if(curr>0  &&  curr == list[i-1][j+1]  &&  curr == list[i-2][j+2]
                                                       &&  curr == list[i-3][j+3])
                {
                    displayWinner(g, list[i][j]);
                }
            }
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            putDisk(1);
        }
        else if(e.getSource() == b2)
        {
            putDisk(2);
        }
        else if(e.getSource() == b3)
        {
            putDisk(3);
        }
        else if(e.getSource() == b4)
        {
            putDisk(4);
        }
        else if(e.getSource() == b5)
        {
            putDisk(5);
        }
        else if(e.getSource() == b6)
        {
            putDisk(6);
        }
        else if(e.getSource() == b7)
        {
            putDisk(7);
        }
        else if(e.getSource() == newMI)
        {
            end = false;
            initialize();
            repaint();
        }
        else if(e.getSource() == exitMI)
        {
            System.exit(0);
        }
        //Dont Change colour in middle of game
        else if(e.getSource() == greenMI)
        {
            if(!gameStart) activeColour = GREEN;
        }
        else if(e.getSource() == magentaMI)
        {
            if(!gameStart) activeColour = MAGENTA;
        }
    }
}
