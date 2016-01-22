package be.kroma.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

class ReserverenForm {	
	@Min(1)
	@NotNull
	private Integer aantal;

	public Integer getAantal() {
		return aantal;
	}

	public void setAantal(Integer aantal) {
		this.aantal = aantal;
	}
	
}
