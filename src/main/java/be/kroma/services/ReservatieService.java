package be.kroma.services;

import java.util.List;

import be.kroma.entities.Reservatie;

public interface ReservatieService {
	void createReservaties(List<Reservatie> reservaties, String name);
}
