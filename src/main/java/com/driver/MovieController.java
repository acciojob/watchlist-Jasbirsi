package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService serviceobj;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody  Movie body)
    {
        try {
            serviceobj.addMovie(body.getName(), body.getDurationInMinutes(), body.getImdbRating());
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(""+e, HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody  Director body)
    {

        try {
            serviceobj.addDirector(body.getName(), body.getNumberOfMovies(), body.getImdbRating());
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(""+e, HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movieName, @RequestParam String  directorName)
    {

        try {
            serviceobj.addMovieDirectorPair(directorName,movieName);
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(""+e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name)
    {
        try {
            Movie movie=serviceobj.getMovieByName(name);
            return new ResponseEntity<>(movie, HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name)
    {
        try {
            Director directorObj=serviceobj.getDirectorByName(name);
            return new ResponseEntity<>(directorObj, HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director)
    {
        //we need to return list of moveies
        try {
            List<String> listOfMovies=new ArrayList<>();
            listOfMovies =  serviceobj.getMoviesByDirectorName(director);
            return new ResponseEntity<>(listOfMovies, HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies()
    {
        try {
            List<Movie> listOfMovies=new ArrayList<>();
            listOfMovies = serviceobj.findAllMovies();
            return new ResponseEntity<>(listOfMovies, HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director)
    {
        try {
            serviceobj.deleteDirectorByName(director);
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(""+e, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors()
    {
        try {
            serviceobj.deleteAllDirectors();
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(""+e, HttpStatus.BAD_REQUEST);
        }
    }

}
