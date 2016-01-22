package be.kroma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.kroma.entities.Reservatie;

@Repository
class ReservatieDAOImpl implements ReservatieDAO {

	private final SimpleJdbcInsert simpleJdbcInsert;
	@SuppressWarnings("unused")
	private final ReservatieRowMapper reservatieRowMapper = new ReservatieRowMapper();

	// autowired beans
	@SuppressWarnings("unused")
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	ReservatieDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("reservaties");
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
	}

	@Override
	public void create(Reservatie reservatie) {
		Map<String, Object> kolomwaarden = new HashMap<>();
		kolomwaarden.put("klantid", reservatie.getKlantid());
		kolomwaarden.put("voorstellingsid", reservatie.getVoorstellingsid());
		kolomwaarden.put("plaatsen", reservatie.getPlaatsen());
		Number id = simpleJdbcInsert.executeAndReturnKey(kolomwaarden);
		reservatie.setId(id.intValue());
	}

	public static class ReservatieRowMapper implements RowMapper<Reservatie> {

		@Override
		public Reservatie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Reservatie(resultSet.getInt("id"), resultSet.getInt("klantid"),
					resultSet.getInt("voorstellingsid"), resultSet.getInt("plaatsen"));
		}

	}

}
