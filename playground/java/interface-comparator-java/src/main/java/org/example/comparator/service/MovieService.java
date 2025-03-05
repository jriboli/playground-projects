package org.example.comparator.service;

import org.example.comparator.entity.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MovieService {
    public List<Movie> movies = new ArrayList<>();
    public void populateMovies() {
        movies.add(new Movie("The Departed", "Martin Scorsese", 2006, 8.5));
        movies.add(new Movie("Good Will Hunting", "Gus Van Sant", 1997, 8.3));
        movies.add(new Movie("Inception", "Christopher Nolan", 2010, 8.8));
        movies.add(new Movie("Intersellar", "Christopher Nolan", 2014, 8.7));
        movies.add(new Movie("The Dark Knight Rises", "Christopher Nolan", 2012, 8.4));
        movies.add(new Movie("The Dark Knight", "Christopher Nolan", 2008, 9.0));
        movies.add(new Movie("The Sting", "George Roy Hill", 1973, 8.3));
        movies.add(new Movie("La Grande Vadrouille", "Gerard Oury", 1966, 7.9));
        movies.add(new Movie("Lost in Translation", "Sofia Coppola", 2003, 7.7));
        movies.add(new Movie("Notting Hill", "Roger Michell", 1999, 7.2));
        movies.add(new Movie("Pulp Fiction", "Quentin Tarantino", 1994, 8.9));
        movies.add(new Movie("Gran Torino", "Clint Eastwood", 2008, 8.1));
        movies.add(new Movie("The Benchwarmers", "Dennis Dugan", 2006, 5.5));
    }

    public void FilterNSort(String director, double minRating) {
        System.out.println("-- FILTER BY AUTHOR AND MINRATING - ADD SORT BY TITLE -------------------------------------------");
        var results = movies.stream()
                .filter(m -> Objects.equals(m.getDirector(), director))
                .filter(m -> m.getRating() > minRating)
                .sorted();

        results.forEach(r -> System.out.println(r));

        System.out.println("-- FILTER BY AUTHOR AND MINRATING - ADD SORT BY RATING -------------------------------------------");
        Comparator<Movie> com = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getRating() > o2.getRating())
                    return 1;
                else
                    return -1;
            }
        };

        var results2 = movies.stream()
                .filter(m -> Objects.equals(m.getDirector(), director))
                .filter(m -> m.getRating() > minRating)
                .sorted(com);

        results2.forEach(r -> System.out.println(r));
    }

    public void FilterNComplexSort(int minReleaseYear) {
        System.out.println("-- FILTER BY RELEASE YEAR - ADD SORT BY RELEASE YEAR THEN RATING -------------------------------------------");
        Comparator<Movie> com = Comparator
                .comparing((Movie m) -> m.getReleaseYear())
                .reversed()
                .thenComparing(m -> m.getRating());

        var results = movies.stream()
                .filter(m -> m.getReleaseYear() > minReleaseYear)
                .sorted(com);

        results.forEach(r -> System.out.println(r));
    }
}
