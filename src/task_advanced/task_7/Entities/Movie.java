package task_advanced.task_7.Entities;

import task_advanced.task_7.DAOs.DAOException;
import task_advanced.task_7.Services.DirectorService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Movie extends Entity{
    private static int numberOfMovies = 0;
    private int id;
    private String name;
    private List<Actor> actorList = new ArrayList<>();
    private Date releaseDate;
    private String country;
    private String directorName;

    public Movie(String name, Date releaseDate, String country, String directorName, List<Actor> actorList) {
        this.id = numberOfMovies + 1;
        this.name = name;
        this.actorList = actorList;
        this.releaseDate = releaseDate;
        this.country = country;
        this.directorName = directorName;
        numberOfMovies++;
    }

    public Movie(int id, String name, Date releaseDate, String country, String directorName, List<Actor> actorList) {
        this.id = id;
        this.name = name;
        this.actorList = actorList;
        this.releaseDate = releaseDate;
        this.country = country;
        this.directorName = directorName;
    }

    public static void setNumberOfMovies(int nom) {
        numberOfMovies = nom;
        numberOfMovies += 2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public void addActor(Actor actor){
        actorList.add(actor);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public Director getDirector() {
        Director director = null;
        try {
            DirectorService directorService = new DirectorService();
            director = directorService.findDirectorsByLastName(directorName).get(0);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return director;
    }

    public String getDirectorName(){
        return directorName;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(id).append(", ").append(name).append(", ").append(releaseDate.toString())
                .append(", ").append(country).append(", ").append(directorName).append(", ").append(actorList.toString())
                .append(" }");
        return sb.toString();
    }
}
