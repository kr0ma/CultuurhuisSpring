package be.kroma.web;

import org.hibernate.validator.constraints.NotBlank;

import be.kroma.entities.Klant;

class NieuweKlantForm extends Klant {
	private static final long serialVersionUID = 1L;

	@NotBlank
	String verifPaswoord;

	public String getVerifPaswoord() {
		return verifPaswoord;
	}

	public void setVerifPaswoord(String verifPaswoord) {
		this.verifPaswoord = verifPaswoord;
	}

}
