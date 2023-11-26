package fractal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A program that displays one of a selection of fractals, and allows the user
 * to select which fractal is displayed.
 * 
 * @author Cara Tang @ Domonic Senesi
 * @version 2017.08.12
 *
 * 2017.08.12 - Version Changes
 * - added Circle, Mandelbrot, Sierpinksi constants to allFractals String array
 * - createContents - Created circle, mandelbrot, and sierpinski panels in a scroll pane
 * - createContents - Created new instance of cardLayout
 *  - createContents - Created new instance of fractalCards panel
 *  - createContents - Added all fractal panes with constants  to fractalCards
 *  - createContents - Created fractalChooser combo box
 *  - createContents - Added a ComboListener to the fractalChooser combo box
 *  - createContents - Added the fractalChooser combo box at NORTH
 *  - createContents - Added fractalCards panel at CENTER
 *  - createContents - Removed un-used line of code
 *  - ComboListener - actionPerformed - Now gets the String of the selected card from the combo box
 *  - ComboListener - actionPerformed - Adjusted the card layout to show the selected card
 */

public class FractalDriver
{
    private static final int WIDTH = 350;
    private static final int HEIGHT = 300;
    private static final String CANTOR = "Cantor";
    private static final String CIRCLE = "Circle";
    private static final String MANDELBROT = "Mandelbrot";
    private static final String SIERPINSKI = "Sierpinski";
    //
    private static final String[] allFractals = {CANTOR,CIRCLE, MANDELBROT, SIERPINSKI};


    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel fractalCards;
    private JComboBox<String> fractalChooser;

    /**
     * Create a FractalDriver
     */
    public FractalDriver()
    {
        makeFrame();
    }

    /**
     * Create the FractalDriver GUI
     */
    private void makeFrame()
    {
        frame = new JFrame("Fractals!");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createContents();
        frame.setVisible(true);
    }

    /**
     * Create the contents of the main window
     * A combo box at the top controls selection of which fractal panel is displayed
     */
    private void createContents() 
    {
        JScrollPane cantorPane = new JScrollPane(new CantorPanel(6));
        //
        JScrollPane circlePane = new JScrollPane(new CirclesPanel(8));
        JScrollPane mandelbrotPane = new JScrollPane(new MandelbrotPanel(10));
        JScrollPane sierpinskiPane = new JScrollPane(new SierpinskiPanel(12));

        //
        cardLayout = new CardLayout();
        fractalCards = new JPanel(cardLayout);

        //
        fractalCards.add(cantorPane, CANTOR);
        fractalCards.add(circlePane, CIRCLE);
        fractalCards.add(mandelbrotPane, MANDELBROT);
        fractalCards.add(sierpinskiPane, SIERPINSKI);


        //
        JComboBox fractalChooser = new JComboBox<>(allFractals);

        //
        fractalChooser.addActionListener(new ComboListener());


        //
        frame.add(fractalChooser, BorderLayout.NORTH);
        frame.add(fractalCards, BorderLayout.CENTER);

    }

    /**
     * Listen to the combo box and switch the displayed fractal when the selection changes
     */
    private class ComboListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //
            JComboBox fractalChooser = (JComboBox)e.getSource();
            String ChoosenName = (String)fractalChooser.getSelectedItem();

            //
            cardLayout.show(fractalCards,ChoosenName);

        }
    }

    public static void main(String[] args)
    {
        new FractalDriver();
    }

}
