package be.kroma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
	private final String SQL_FIND_BY_GENRE_ID = "select id, titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen "
			+ "from voorstellingen where genreid = ?";

	private final String SQL_FIND_BY_ID = "select id, titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen "
			+ "from voorstellingen where id = ?";

	private final String SQL_FIND_BY_IDS = "select id, titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen "
			+ "from voorstellingen where id IN ( :ids )";

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
