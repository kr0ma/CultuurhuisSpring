package be.kroma.web;

import java.util.List;

import javax.validation.constraints.NotNull;

class VerwijderVoorstellingForm {
	@NotNull
	private List<Integer> voorstellingIDs;

	public List<Integer> getVoorstellingIDs() {
		return voorstellingIDs;
	}

	public void setVoorstellingIDs(List<Integer> voorstellingIDs) {
		this.voorstellingIDs = voorstellingIDs;
	}
	
}
