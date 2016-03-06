package pomocneF;

import java.sql.Time;

public class RaspKoord_ {
	String dan;
	Time vrijemePoc;
	Time vrijemeKraj;
	int x;
	int y;
	int height;
	
	public RaspKoord_() {
		this.dan = "";
		this.vrijemePoc =  Time.valueOf("00:00:00");
		this.vrijemeKraj =  Time.valueOf("00:00:00");
		this.x = -1;
		this.y = -1;
		this.height = -1;
	}

	public String getDan() {
		return dan;
	}

	public Time getVrijemePoc() {
		return vrijemePoc;
	}

	public Time getVrijemeKraj() {
		return vrijemeKraj;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public void setDan(String dan) {
		this.dan = dan;
	}

	public void setVrijemePoc(Time vrijemePoc) {
		this.vrijemePoc = vrijemePoc;
	}

	public void setVrijemeKraj(Time vrijemeKraj) {
		this.vrijemeKraj = vrijemeKraj;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
