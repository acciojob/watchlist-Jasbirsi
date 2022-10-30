package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepo;

    public void addMovie(String name, int durationInMinutes, double imdbRating)
    {
        movieRepo.addMovie( name,  durationInMinutes,  imdbRating);
    }

    public void addDirector(String name, int numberOfMovies, double imdbRating)
    {
        movieRepo.addDirector( name,  numberOfMovies,  imdbRating);
    }

    public void addMovieDirectorPair(String directorName, String movieName)
    {
        movieRepo.addMovieDirectorPair(directorName, movieName);
    }

    public Movie getMovieByName(String name)
    {
         return movieRepo.getMovieByName(name);
    }

    public Director getDirectorByName(String name)
    {
        return movieRepo.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorName(String name)
    {
//        List<Movie> listOfMoviesByDirector=new ArrayList<>();
//        listOfMoviesByDirector=movieRepo.getMoviesByDirectorName(name);

        return movieRepo.getMoviesByDirectorName(name);
    }

    public List<String> findAllMovies()
    {
        return movieRepo.findAllMovies();
    }

    public void deleteDirectorByName(String name)
    {
        movieRepo.deleteDirectorByName(name);
    }

    public void deleteAllDirectors()
    {
        movieRepo.deleteAllDirectors();
    }

}
