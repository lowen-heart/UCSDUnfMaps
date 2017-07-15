package module6;

public class Button {

	private int x;
	private int y;
	private float width;
	private float height;
	private boolean clicked;
	private String text;
	private int[] color = {255,255,255};
	
	public Button(int x, int y, String text, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.clicked = false;
		this.text = text;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
		setColor();
	}
	
	private void setColor(){
		if(isClicked()){
			color[0] = 0;
			color[1] = 255;
			color[2] = 0;
		}else{
			color[0] = 255;
			color[1] = 255;
			color[2] = 255;
		}
	}
	
	public int[] getColor(){
		return color;
	}

	public String getText() {
		return text;
	}

	public boolean isInside(int mouseX,int mouseY){
		
		if(mouseX > x & mouseX < (x + width) & mouseY > y & mouseY < (y + height)){
			return true;
		}
		return false;
	}

	public float getWidth() {
		return width;
	}
	
	public float getHeight(){
		return height;
	}
}
