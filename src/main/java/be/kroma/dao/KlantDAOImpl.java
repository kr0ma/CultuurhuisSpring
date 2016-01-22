package be.kroma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import be.kroma.entities.Klant;

@Repository
class KlantDAOImpl implements KlantDAO {
	private final SimpleJdbcInsert simpleJdbcInsert;

	// autowired beans
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	KlantDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("klanten");
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
	}

	// rowmapper instance
	private final KlantRowMapper klantRowMapper = new KlantRowMapper();

	// (id, voornaam, familienaam, straat, huisnr, postcode, gemeente,
	// gebruikersnaam, paswoord);
	private final String SQL_FIND_BY_USERNAME = "select id, voornaam, familienaam, straat, "
												+ "huisnr, postcode, gemeente, gebruikersnaam, paswoord "
												+ "from klanten where gebruikersnaam = ?";

	@Override
	public void create(Klant klant) {
		String encryptedPassword = new BCryptPasswordEncoder().encode(klant.getPaswoord());
		Map<String, Object> kolomWaarden = new HashMap<>();
		kolomWaarden.put("voornaam", klant.getVoornaam());
		kolomWaarden.put("familienaam", klant.getFamilienaam());
		kolomWaarden.put("straat", klant.getStraat());
		kolomWaarden.put("huisnr", klant.getHuisnr());
		kolomWaarden.put("postcode", klant.getPostcode());
		kolomWaarden.put("gemeente", klant.getGemeente());
		kolomWaarden.put("gebruikersnaam", klant.getGebruikersnaam());
		kolomWaarden.put("paswoord", encryptedPassword);
		kolomWaarden.put("actief", Klant.isActief());
		kolomWaarden.put("rolid", Klant.getRolid());
		Number id = simpleJdbcInsert.executeAndReturnKey(kolomWaarden);
		klant.setId(id.intValue());
	}

	@Override
	public boolean bestaatKlant(String gebruikersnaam) {
		Boolean klantBestaat = true;
		try {
			@SuppressWarnings("unused")
			Klant klant = jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, klantRowMapper, gebruikersnaam);
		} catch (EmptyResultDataAccessException ex){
			klantBestaat = false;
		}
		return klantBestaat;
	}

	// rowmapper class
	private static class KlantRowMapper implements RowMapper<Klant> {

		@Override
		public Klant mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Klant(resultSet.getInt("id"), 
							 resultSet.getString("voornaam"), 
							 resultSet.getString("familienaam"), 
							 resultSet.getString("straat"), 
							 resultSet.getString("huisnr"), 
							 resultSet.getString("postcode"), 
							 resultSet.getString("gemeente"), 
							 resultSet.getString("gebruikersnaam"), 
							 resultSet.getString("paswoord"));
		}

	}

}
