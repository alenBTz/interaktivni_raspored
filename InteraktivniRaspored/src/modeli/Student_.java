package modeli;

public class Student_ {
	int sifStudent;
	String imeStudent;
	String prezStudent;
	String jmbgStudent;
	String brIndexa;
	int sifGrupa;
	int sifSemestra;
	int sifUsmjerenja;
	
	public Student_() {
		this.sifStudent = -1;
		this.imeStudent = "";
		this.prezStudent = "";
		this.jmbgStudent = "";
		this.brIndexa = "";
		this.sifGrupa = -1;
		this.sifSemestra = -1;
		this.sifUsmjerenja = -1;
	}

	public int getSifStudent() {
		return sifStudent;
	}

	public String getImeStudent() {
		return imeStudent;
	}

	public String getPrezStudent() {
		return prezStudent;
	}

	public String getJmbgStudent() {
		return jmbgStudent;
	}

	public String getBrIndexa() {
		return brIndexa;
	}

	public int getSifGrupa() {
		return sifGrupa;
	}
	
	public int getSifUsmjerenja() {
		return sifUsmjerenja;
	}
	
	public int getSifSemestra() {
		return sifSemestra;
	}

	public void setSifStudent(int sifStudent) {
		this.sifStudent = sifStudent;
	}

	public void setImeStudent(String imeStudent) {
		this.imeStudent = imeStudent;
	}

	public void setPrezStudent(String prezStudent) {
		this.prezStudent = prezStudent;
	}

	public void setJmbgStudent(String jmbgStudent) {
		this.jmbgStudent = jmbgStudent;
	}

	public void setBrIndexa(String brIndexa) {
		this.brIndexa = brIndexa;
	}

	public void setSifGrupa(int sifGrupa) {
		this.sifGrupa = sifGrupa;
	}
	
	public void setSifUsmjerenja(int sifUsmjerenja) {
		this.sifUsmjerenja = sifUsmjerenja;
	}
	
	public void setSifSemestra(int sifSemestra) {
		this.sifSemestra = sifSemestra;
	}
	
	
}
