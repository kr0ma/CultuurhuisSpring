package be.kroma.entities;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Klant implements Serializable {	
	private static final long serialVersionUID = 1L;
	private int id;
		
	@NotBlank	
	private String voornaam;
	@NotBlank
	private String familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord;
	
	// final
	private static final int rolid = 1;
	private static final boolean actief = true;
	
	public Klant(){		
	}
	
	public Klant(int id, String voornaam, String familienaam, String straat,
			String huisnr, String postcode, String gemeente,
			String gebruikersnaam, String paswoord) {
		this(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord);
		this.id = id;		
	}
	
	public Klant(String voornaam, String familienaam, String straat,
			String huisnr, String postcode, String gemeente,
			String gebruikersnaam, String paswoord) {		
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.straat = straat;
		this.huisnr = huisnr;
		this.postcode = postcode;
		this.gemeente = gemeente;
		this.gebruikersnaam = gebruikersnaam;
		this.paswoord = paswoord;
	}
	
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisnr() {
		return huisnr;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public String getPaswoord() {
		return paswoord;
	}	
	
	public Klant getKlant(){
		return this;
	}
	
	@Override
	public String toString() {		
		return voornaam + " " + familienaam + " " + straat + " " + huisnr + " " + postcode + " " + gemeente;
	}

	public static int getRolid() {
		return rolid;
	}

	public static boolean isActief() {
		return actief;
	}		
}
