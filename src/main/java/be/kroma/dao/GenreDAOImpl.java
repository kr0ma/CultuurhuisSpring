package be.kroma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import be.kroma.entities.Genre;

@Repository
class GenreDAOImpl implements GenreDAO {

	// autowired beans
	private final JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unused")
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// constructor injection
	@Autowired
	GenreDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	// rowmapper instance
	private final GenreRowMapper genreRowMapper = new GenreRowMapper();

	// sql statements
	private static final String SQL_FIND_ALL = "select id, naam from genres";

	@Override
	public Iterable<Genre> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, genreRowMapper);
	}

	// rowmapper class
	private static class GenreRowMapper implements RowMapper<Genre> {

		@Override
		public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Genre(resultSet.getInt("id"), resultSet.getString("naam"));
		}

	}

}
