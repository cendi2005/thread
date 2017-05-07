package acm; /**
 * Created by admin on 2017/5/7.
 */
//acm.Bounce.java

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bounce {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
                               {
                                   public void run()
                                   {
                                       JFrame frame = new BounceFrame();
                                       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                       frame.setVisible(true);
                                   }
                               }
        );

    }

}

class BounceFrame extends JFrame
{
    public BounceFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("acm.Bounce");

        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        addBall();
                    }
                }
        );

        addButton(buttonPanel, "Close", new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        System.exit(0);
                    }
                }
        );

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addButton(Container c, String title, ActionListener listener)
    {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall()
    {

        Ball b = new Ball();
        comp.add(b);
        Runnable r = new BallRunnable(b, comp);
        Thread t = new Thread(r);
        t.start();
		/*
		try
		{
			acm.Ball ball = new acm.Ball();
			comp.add(ball);

			for(int i=1; i<=STEPS; i++)
			{
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		}
		catch(Exception e)
		{}
		*/
    }

    private BallComponent comp;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;
}

class BallRunnable implements Runnable
{
    public BallRunnable(Ball aBall, Component aComponent)
    {
        ball = aBall;
        component = aComponent;
    }

    public void run()
    {
        try
        {
            for(int i=1; i<=STEPS; i++)
            {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        }
        catch(Exception e)
        {}
    }


    private Ball ball;
    private Component component;

    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    public static final int STEPS = 1000*100;
    public static final int DELAY = 5;
}