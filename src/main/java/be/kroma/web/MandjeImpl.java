package be.kroma.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
class MandjeImpl implements Mandje, Serializable{
	private static final long serialVersionUID = 1L;
	private Map<Integer, Integer> voorstellingen = new HashMap<>();
	
	@Override
	public void addVoorstelling(int voorstellingID, int aantal) {
		voorstellingen.put(voorstellingID, aantal);			
	}	

	@Override
	public int getVoorstellingAantal(int voorstellingID) {
		return voorstellingen.get(voorstellingID);
	}

	@Override
	public boolean isVoorstellingAanwezig(int voorstellingID) {
		return voorstellingen.containsKey(voorstellingID);
	}

	@Override
	public boolean isLeeg() {
		return voorstellingen.isEmpty();
	}

	@Override
	public Map<Integer, Integer> getVoorstellingen() {
		return voorstellingen;
	}

	@Override
	public void verwijderVoorstellingen(Iterable<Integer> ids) {
		for (Integer id : ids){
			voorstellingen.remove(id);
		}
	}	

}
