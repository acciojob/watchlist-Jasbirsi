package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MovieRepository {


    HashMap<String , Movie> movies =new HashMap<>();
    HashMap<String , Director> directors =new HashMap<>();
    HashMap<String , List<String >> movieDirectorPair=new HashMap<>();

    public void addMovie(String name,  int durationInMinutes, double imdbRating)
    {
        Movie movie=new Movie(name, durationInMinutes,  imdbRating);
        movies.put(name,movie);

    }

    public void addDirector(String name, int numberOfMovies, double imdbRating)
    {
        Director director=new Director( name,  numberOfMovies,  imdbRating);
        directors.put(name,director);

    }

    public void addMovieDirectorPair(String directorName, String movieName)
    {
        //key is director , value is movie
        if (movieDirectorPair.containsKey(directorName))
        {
            List<String> moviesName=new ArrayList<>(movieDirectorPair.get(directorName));
            moviesName.add(movieName);
            movieDirectorPair.put(directorName,new ArrayList<>(moviesName));
        }
        else
        {
            List<String> moviesName=new ArrayList<>();
            moviesName.add(movieName);
            movieDirectorPair.put(directorName,new ArrayList<>(moviesName));
        }

    }

    public Movie getMovieByName(String name)
    {
        return movies.get(name);
    }
    public Director getDirectorByName(String name)
    {
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String name)
    {
        return movieDirectorPair.get(name);
    }

    public List<Movie> findAllMovies()
    {
        List<Movie> listOfMovies=new ArrayList<>();
        for (String movie:movies.keySet())
        {
            listOfMovies.add(movies.get(movie));
        }

        return listOfMovies;
    }

    public void deleteDirectorByName(String directorName)
    {
        List<String> moviesByDirector=movieDirectorPair.get(directorName);

        //removing all movies
        for (int i=0; i<moviesByDirector.size(); i++)
        {
            String movie=moviesByDirector.get(i);
            if (movies.containsKey(movie))
            {
                movies.remove(movie);
            }
        }

        //removig direcotor
        directors.remove(directorName);

        //at last removig movie director pair
        movieDirectorPair.remove(directorName);
    }

    public void deleteAllDirectors()
    {
        //iterating through all director linked with movies
        for (String director:movieDirectorPair.keySet())
        {
            //list of all movies by that director
            List<String> listOfMovies=movieDirectorPair.get(director);

//            iterating througn the list of all movies
            for (int i=0; i<listOfMovies.size(); i++)
            {
                //if the movie is present in the movies then remove it
                String movie=listOfMovies.get(i);
                if (movies.containsKey(movie))
                {
                    movies.remove(movie);
                }
            }

        }
        //the can be some director which are not linked to any movies
        //we also need to delete them
        directors.clear();
    }



}
