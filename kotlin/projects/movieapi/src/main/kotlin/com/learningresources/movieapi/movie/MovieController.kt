package com.learningresources.movieapi.movie

import com.learningresources.movieapi.movie.model.Movie
import com.learningresources.movieapi.movie.model.MovieCreateDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController("/api/v1/movies")
class MovieController(
    private val movieService: MovieService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: MovieCreateDTO) =
        movieService.create(dto)

    @GetMapping
    fun findAll(): List<Movie> =
        movieService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Movie =
        movieService.findById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) =
        movieService.delete(id)

}
