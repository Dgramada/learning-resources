package com.learningresources.movieapi.movie

import com.learningresources.movieapi.movie.model.Movie
import com.learningresources.movieapi.movie.model.MovieCreateDTO
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class MovieRepository(
    private val template: NamedParameterJdbcTemplate
) {

    private val movieRowMapper = { rs: ResultSet, _: Int ->
        Movie(
            id = rs.getLong("id"),
            name = rs.getString("name"),
            description = rs.getString("description"),
        )
    }

    fun create(dto: MovieCreateDTO) {
        val sql = """
            INSERT INTO movies (name, description)
            VALUES (:name, :description);
        """.trimIndent()

        val params = MapSqlParameterSource()
            .addValue("name", dto.name)
            .addValue("name", dto.description)

        template.update(sql, params)
    }

    fun findAll(): List<Movie> {
        val sql = """
            SELECT id, name, description
            FROM movies
        """.trimIndent()

        return template.query(sql, movieRowMapper)
    }

    fun findById(id: Long): Movie? {
        val sql = """
            SELECT id, name, description
            FROM movies
            WHERE id = :id
        """.trimIndent()

        val params = MapSqlParameterSource("id", id)
        return template.query(sql, params, movieRowMapper).firstOrNull()
    }

    fun delete(id: Long) {
        val sql = """
            DELETE FROM movies
            WHERE id = :id
        """.trimIndent()

        val params = MapSqlParameterSource("id", id)
        template.update(sql, params)
    }

}
