package Ease;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
Ease inherits properties of a JPanel, which means
that it can have graphics, and be placed inside of
a JFrame, and can have mouse and key listeners
To pick up user inputs.
*/
public abstract class Ease extends JPanel implements KeyListener, MouseListener {
    public int mouseX;
    public int mouseY;
    public int height;
    public int width;
    public int refreshRate = 1000;
    private Frame frame;
    private Graphics2D g2d;
    private boolean close = false;
    public boolean autoUpdate = true;
    /*
    This constructor creates a JFrame which then holds
    the JPanel of which Ease extends from. The
    different methods acting on the frame add Ease to
    it, add ease a a mouselistener, and a keylistener,
    in order to be able to pick up keystrokes and mouse
    movement. This also sets defaults for the screen,
    constructs variables, and creates a default close
    operation.
    */
    public Ease(int width, int height) throws InterruptedException {
        JFrame frame = new JFrame("Game");
        frame.add(this);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.setVisible(true);
        frame.setSize(width, height + 22);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        this.frame = frame;
        this.width = width;
        this.height = height;
        //setting common defaults for a screen.^^^
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.err.println("EXIT.");
                System.exit(0);
            }
        });
        autoUpdate = false;
    }
    /*
    This is the constructor in the
    case that the user wants a constant
    refresh rate. This means that the speed at which
    the panel is repainted is constant and not
    interrupted or changed by user input.
    */
    public Ease(int width, int height, int refreshRate) throws InterruptedException {
        /*
        Below this is a JFrame.
        A JFrame is a simple java.swing component.
        Swing is the most commonly used method for
        creating visuals in java. Although I did
        not use them in this program, one can use
        JFrames to add option panels, menus,
        buttons and more. What I am using this one
        for, is to hold a JPanel. The JPanel is in
        fact this class, (Ease extends/inherits
        from the JPanel class).
        */
        JFrame frame = new JFrame("Game");
        frame.add(this);
        /*
        The Ease class also implements
        keylisteners and mouselisteners which
        allow and force the ease class to implement
        methods for each possible keystroke or
        mouseclick.
        */
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.setVisible(true);
        frame.setSize(width, height + 22);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocation(700,100);
        this.frame = frame;
        this.width = width;
        this.height = height;
        //setting common defaults for a screen.^^^
        this.refreshRate = refreshRate;
        //Below is a simple windowListener which
        //detects if the window is closed.
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.err.println("EXIT.");
                System.exit(0);
            }
        });
    }
    /*
    This is the paintComponents function. This
    will run every time there is a repaint call.
    This will also call the paint function as
    well as update the MouseX and Y coordinates.
    */
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D)g;
        mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX() - frame.getX();
        mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY() - frame.getY();
        paint();
        if(close) {
            System.exit(0);
        }
        /*
        This try and catch framework allows Ease
        to not only detect whether it should Auto-
        update its display, but also handles the
        Case in which it cannot happen due to an
        interruption if this would happen.
        */
        if(autoUpdate) try {
            Thread.sleep(refreshRate);//Wait for the desired number of milliseconds.
            repaint();//Iterate through the code again.
        } catch (InterruptedException e) {
            e.printStackTrace();
            repaint();
        }
    }
    //This runs every time there is a repaint() call.
    public void paint() {}

    /*
    Different methods of painting that the user can Access.
    These can only be used for drawing. Any logic that the
    User desires must be created by themselves.
    */
    public void line(int x, int y, int w, int h, Color c) {
        g2d.setColor(c);
        g2d.drawLine(x,y,w,h);
    }
    public void polygon(int[] Xpoints,int[] Ypoints,Color c) {
        g2d.setColor(c);
        g2d.drawPolygon(new Polygon(Xpoints,Ypoints,Xpoints.length));
    }
    public void ellipse(int x, int y, int x2, int y2, Color c) {
        g2d.setColor(c);
        g2d.fillOval(x,y,x2,y2);
    }
    public void ellipse(double x, double y, double x2, double y2, Color c) {
        g2d.setColor(c);
        g2d.fillOval((int)x,(int)y,(int)x2,(int)y2);
    }
    public void text(int x, int y, String text,Color c,int size) {
        g2d.setColor(c);
        g2d.setFont(new Font("Monospaced", 0, size));
        g2d.drawString(text,x,y);
    }
    public void rect(int x, int y, int w, int h, Color c) {
        g2d.setColor(c);
        g2d.fillRect(x,y,w,h);
    }
    public void pixel(int x,int y,Color c) {
        g2d.setColor(c);
        g2d.fillRect(x,y,1,1);
    }

    /*
    All of the below functions are placeholder functions
    of the Ease class. They can, and must be overridden
    by the user if the application they want to make
    Requires a User interface.
    */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}