import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends Applet implements KeyListener {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double width = screenSize.getWidth();
	public double height = screenSize.getHeight();
	
	public boolean debug = false;
	
	public double xPos = width / 2;
	public double yPos = height / 2;
	public double view_width = 50;
	public double view_height = 30;
	public double view_change_size = 5;
	public double view_change_position = 5;
	public int color_choice = 0;
	
	public Color colors[] = {Color.RED, Color.CYAN, Color.GREEN, Color.GRAY, Color.YELLOW, Color.ORANGE, Color.BLUE, Color.MAGENTA};
	 
	public Graphics graphics;
	
	public void init(){
		this.setSize((int)width, (int)height);
		this.setBackground(Color.BLACK);
		this.addKeyListener(this);
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	
	public void paint(Graphics g){
		graphics = g;
		fillInRoof();
		fillInFloor();
		fillInLeft();
		fillInRight();
		
		if(debug == true){
			drawDebugLines();
		}
	}// End of Paint Method
	
	public void drawDebugLines(){
		graphics.setColor(colors[color_choice]);
		graphics.drawRect((int)xPos - ((int)view_width / 2), (int)yPos - ((int)view_height / 2), (int)view_width, (int)view_height);
		graphics.drawLine(0, 0, (int)xPos - ((int)view_width / 2), (int)yPos - ((int)view_height / 2));
		graphics.drawLine((int)width, 0, (int)xPos + ((int)view_width / 2), (int)yPos - ((int)view_height / 2));
		graphics.drawLine((int)width, (int)height, (int)xPos + ((int)view_width / 2), (int)yPos + ((int)view_height / 2));
		graphics.drawLine(0, (int)height, (int)xPos - ((int)view_width / 2), (int)yPos + ((int)view_height / 2));
	}
	
	public void fillInRight() {
		graphics.setColor(Color.DARK_GRAY);
		int xPoints[] = {0, (int)xPos - ((int)view_width / 2), (int)xPos - ((int)view_width / 2), 0};
		int yPoints[] = {0, (int)yPos - ((int)view_height / 2), (int)yPos + ((int)view_height / 2), (int)height};
		graphics.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	public void fillInLeft() {
		graphics.setColor(Color.DARK_GRAY);
		int xPoints[] = {(int)xPos + ((int)view_width / 2), (int)width, (int)width, (int)xPos + ((int)view_width / 2)};
		int yPoints[] = {(int)yPos - ((int)view_height / 2), 0, (int)height, (int)yPos + ((int)view_height / 2)};
		graphics.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	public void fillInRoof(){
		graphics.setColor(Color.LIGHT_GRAY);
		int xPoints[] = {0, (int)width, (int)xPos + ((int)view_width / 2), (int)xPos - ((int)view_width / 2)};
		int yPoints[] = {0, 0, (int)yPos - ((int)view_height / 2), (int)yPos - ((int)view_height / 2)};
		graphics.fillPolygon(xPoints, yPoints, xPoints.length);
	}
	
	public void fillInFloor(){
		graphics.setColor(Color.LIGHT_GRAY);
		int xPoints[] = {0, (int)width, (int)xPos + ((int)view_width / 2), (int)xPos - ((int)view_width / 2)};
		int yPoints[] = {(int)height, (int)height, (int)yPos + ((int)view_height / 2), (int)yPos + ((int)view_height / 2)};
		graphics.fillPolygon(xPoints, yPoints, xPoints.length);
	}
	
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_W:
	        	if(view_width < width && view_height < height){
	        		view_width += view_change_size;
		        	view_height += view_change_size;
	        	}
	        	break;
	        case KeyEvent.VK_S:
	        	if(view_width > 0 && view_height > 0){
	        		view_width -= view_change_size;
	        		view_height -= view_change_size;
	        	}
	            break;
	        case KeyEvent.VK_A:
	        	if((xPos - (view_width / 2)) > 0){
	        		xPos -= view_change_position;
	        	}
	        	break;
	        case KeyEvent.VK_D:
	        	if((xPos + (view_width / 2)) < width){
	        		xPos += view_change_position;
	        	}
	        	break;
	        case KeyEvent.VK_SHIFT:
	        	if((yPos - (view_height / 2)) > 0){
	        		yPos -= view_change_position;
	        	}
	        	break;
	        case KeyEvent.VK_SPACE:
	        	if((yPos + (view_height / 2)) < height){
	        		yPos += view_change_position;
	        	}
	        	break;
	        case KeyEvent.VK_C:
	        	if(color_choice < colors.length - 1){
	        		color_choice++;
	        	}else if(color_choice == colors.length - 1){
	        		color_choice = 0;
	        	}
	     }
	     repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {} 
}// End of class