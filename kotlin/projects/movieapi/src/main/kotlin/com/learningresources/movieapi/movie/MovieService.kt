package com.learningresources.movieapi.movie

import com.learningresources.movieapi.exception.ResourceNotFoundException
import com.learningresources.movieapi.movie.model.Movie
import com.learningresources.movieapi.movie.model.MovieCreateDTO
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieRepository: MovieRepository
) {

    fun create(dto: MovieCreateDTO) =
        movieRepository.create(dto)

    fun findAll(): List<Movie> =
        movieRepository.findAll()

    fun findById(id: Long): Movie =
        movieRepository.findById(id) ?: throw ResourceNotFoundException("Movie with id: $id not found")

    fun delete(id: Long) =
        movieRepository.delete(id)

}
