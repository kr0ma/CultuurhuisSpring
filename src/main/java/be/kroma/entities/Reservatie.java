package be.kroma.entities;

import java.io.Serializable;

public class Reservatie implements Serializable {	
	private static final long serialVersionUID = 1L;
	private int id, klantid, voorstellingsid, plaatsen;
	
	public Reservatie(int klantID,int voorstellingsid, int plaatsen){
		this(voorstellingsid, plaatsen);
		this.klantid = klantID;		
	}
	
	public Reservatie(int id, int klantid, int voorstellingsid, int plaatsen) {
		this(klantid, voorstellingsid, plaatsen);
		this.id = id;		
	}

	public Reservatie(int voorstellingsid, int plaatsen) {
		this.voorstellingsid = voorstellingsid;
		this.plaatsen = plaatsen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKlantid() {
		return klantid;
	}

	public void setKlantid(int klantid) {
		this.klantid = klantid;
	}

	public int getVoorstellingsid() {
		return voorstellingsid;
	}

	public int getPlaatsen() {
		return plaatsen;
	}

}
