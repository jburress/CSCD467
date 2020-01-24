import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class Jframe extends JFrame implements KeyListener
{
	
	private static final long serialVersionUID = 1L;

	private static JTextArea area;
	private Thread t1;
	private Thread t2;
	private String response = new String();
	
	public Jframe(String name)// default code for JFrame given in demo and in class 
	{
		super(name);
		area = new JTextArea(20,30);
		DefaultCaret caret = (DefaultCaret)area.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		add(new JScrollPane(area));
		setSize(500,500);
		setVisible(true);
		area.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent args)//logic for this method was provided in lecture on 6/28
	{
		
		if(args.getKeyCode() == KeyEvent.VK_ENTER)
		{
			t1 = new Thread(new DisplayThread(response));
			t1.start();
		}
		if(args.getKeyCode() != KeyEvent.VK_ENTER && t1 != null)
		{
			if(t1.isAlive())
			{
				t1.interrupt();
				try {
						t1.join();
					} catch (InterruptedException e1) {
							return;
					}
				response = new String();
			}
		}
	}
	
	public void keyReleased(KeyEvent args) 
	{
		if(response.toLowerCase().equals("exit"))
			System.exit(0);
	}

	
	public void keyTyped(KeyEvent args)
	{	
		response = response + args.getKeyChar();
	}
	
	public static void appendArea(String text)
	{
		area.append(text +"\n");
		
	}
	
	
	

}
