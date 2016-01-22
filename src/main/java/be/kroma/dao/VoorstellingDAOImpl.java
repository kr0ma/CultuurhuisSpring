package be.kroma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import be.kroma.entities.Voorstelling;

@Repository
class VoorstellingDAOImpl implements VoorstellingDAO {
	// autowired beans
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// constructor injection
	@Autowired
	VoorstellingDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	// rowmapper instance
	private final VoorstellingRowMapper voorstellingRowMapper = new VoorstellingRowMapper();

	// SQL statements
	private static final String SQL_FIND_BY_GENRE_ID = "select id, titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen "
			+ "from voorstellingen where genreid = ?";

	private static final String SQL_FIND_BY_ID = "select id, titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen "
			+ "from voorstellingen where id = ?";

	private static final String SQL_FIND_BY_IDS = "select id, titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen "
			+ "from voorstellingen where id IN ( :ids )";
	
	private static final String UPDATE_PLAATSEN_VAN_VOORSTELLING = "UPDATE voorstellingen SET vrijeplaatsen= vrijeplaatsen - :plaatsen WHERE id= :id and vrijeplaatsen >= :plaatsen";

	@Override
	public Iterable<Voorstelling> findVoorstellingenByGenreId(int genreID) {
		return jdbcTemplate.query(SQL_FIND_BY_GENRE_ID, voorstellingRowMapper, genreID);
	}

	@Override
	public Voorstelling findVoorstellingById(int id) {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, voorstellingRowMapper, id);
	}

	@Override
	public Iterable<Voorstelling> findVoorstellingenByIDS(Set<Integer> voorstellingIDS) {
		return namedParameterJdbcTemplate.query(SQL_FIND_BY_IDS, Collections.singletonMap("ids", voorstellingIDS),
				voorstellingRowMapper);
	}

	@Transactional(readOnly = false)
	@Override
	public int updateVoorstellingPlaatsen(int id, int plaatsen) {
		Map<String, Object> kolomwaarden = new HashMap<>();
		kolomwaarden.put("id", id);
		kolomwaarden.put("plaatsen", plaatsen);
		return namedParameterJdbcTemplate.update(UPDATE_PLAATSEN_VAN_VOORSTELLING, kolomwaarden);
	}
	
	// rowmapper class
	private static class VoorstellingRowMapper implements RowMapper<Voorstelling> {

		@Override
		public Voorstelling mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Voorstelling(resultSet.getInt("id"), resultSet.getInt("genreid"),
					resultSet.getInt("vrijeplaatsen"), resultSet.getString("titel"), resultSet.getString("uitvoerders"),
					resultSet.getDate("datum"), resultSet.getBigDecimal("prijs"));
		}

	}
	
}
