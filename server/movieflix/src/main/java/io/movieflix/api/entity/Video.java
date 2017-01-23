package io.movieflix.api.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This is the Video class defining it's attributes and getter/setters
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Video.findByTitle", query = "SELECT v FROM Video v WHERE UPPER(v.title) LIKE UPPER(:vtitle)"),
	@NamedQuery(name = "Video.selectTopRated", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype ORDER BY v.imdbRating DESC"),
	
	@NamedQuery(name = "Video.selectAllSortByYear", query = "SELECT v FROM Video v ORDER BY v.year DESC"),
	@NamedQuery(name = "Video.selectByTypeSortByYear", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype ORDER BY v.year DESC"),
	@NamedQuery(name = "Video.selectByYearSortByYear", query = "SELECT v FROM Video v WHERE v.year = :vyear ORDER BY v.year DESC"),
	@NamedQuery(name = "Video.selectByGenreSortByYear", query = "SELECT v FROM Video v WHERE v.genre LIKE :vgenre ORDER BY v.year DESC"),
	@NamedQuery(name = "Video.selectByTypeGenreSortByYear", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.genre LIKE :vgenre ORDER BY v.year DESC"),
	@NamedQuery(name = "Video.selectByTypeYearSortByYear", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.year = :vyear ORDER BY v.year DESC"),
	@NamedQuery(name = "Video.selectByYearGenreSortByYear", query = "SELECT v FROM Video v WHERE v.year = :vyear AND v.genre LIKE :vgenre ORDER BY v.year DESC"),
	@NamedQuery(name = "Video.selectByTypeYearGenreSortByYear", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.year = :vyear AND v.genre LIKE :vgenre ORDER BY v.year DESC"),

	@NamedQuery(name = "Video.selectAllSortByRating", query = "SELECT v FROM Video v ORDER BY v.imdbRating DESC"),
	@NamedQuery(name = "Video.selectByTypeSortByRating", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype ORDER BY v.imdbRating DESC"),
	@NamedQuery(name = "Video.selectByYearSortByRating", query = "SELECT v FROM Video v WHERE v.year = :vyear ORDER BY v.imdbRating DESC"),
	@NamedQuery(name = "Video.selectByGenreSortByRating", query = "SELECT v FROM Video v WHERE v.genre LIKE :vgenre ORDER BY v.imdbRating DESC"),
	@NamedQuery(name = "Video.selectByTypeGenreSortByRating", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.genre LIKE :vgenre ORDER BY v.imdbRating DESC"),
	@NamedQuery(name = "Video.selectByTypeYearSortByRating", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.year = :vyear ORDER BY v.imdbRating DESC"),
	@NamedQuery(name = "Video.selectByYearGenreSortByRating", query = "SELECT v FROM Video v WHERE v.year = :vyear AND v.genre LIKE :vgenre ORDER BY v.imdbRating DESC"),
	@NamedQuery(name = "Video.selectByTypeYearGenreSortByRating", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.year = :vyear AND v.genre LIKE :vgenre ORDER BY v.imdbRating DESC"),

	@NamedQuery(name = "Video.selectAllSortByVotes", query = "SELECT v FROM Video v ORDER BY v.imdbVotes DESC"),
	@NamedQuery(name = "Video.selectByTypeSortByVotes", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype ORDER BY v.imdbVotes DESC"),
	@NamedQuery(name = "Video.selectByYearSortByVotes", query = "SELECT v FROM Video v WHERE v.year = :vyear ORDER BY v.imdbVotes DESC"),
	@NamedQuery(name = "Video.selectByGenreSortByVotes", query = "SELECT v FROM Video v WHERE v.genre LIKE :vgenre ORDER BY v.imdbVotes DESC"),
	@NamedQuery(name = "Video.selectByTypeGenreSortByVotes", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.genre LIKE :vgenre ORDER BY v.imdbVotes DESC"),
	@NamedQuery(name = "Video.selectByTypeYearSortByVotes", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.year = :vyear ORDER BY v.imdbVotes DESC"),
	@NamedQuery(name = "Video.selectByYearGenreSortByVotes", query = "SELECT v FROM Video v WHERE v.year = :vyear AND v.genre LIKE :vgenre ORDER BY v.imdbVotes DESC"),
	@NamedQuery(name = "Video.selectByTypeYearGenreSortByVotes", query = "SELECT v FROM Video v WHERE v.type LIKE :vtype AND v.year = :vyear AND v.genre LIKE :vgenre ORDER BY v.imdbVotes DESC")

})
public class Video {

	@Id
	private String videoId;
	@Column(unique=true)
	private String title;
	private int year;
	private String rated;
	private String released;
	private String runtime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	private String plot;
	private String language;
	private String country;
	private String awards;
	private String poster;
	private int metaScore;
	private double imdbRating;
	private int imdbVotes;
	private String imdbId;
	private String type;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getMetaScore() {
		return metaScore;
	}

	public void setMetaScore(int metaScore) {
		this.metaScore = metaScore;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public int getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(int imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Video() {
		this.videoId = UUID.randomUUID().toString();
	}
	
	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released="
				+ released + ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", writer="
				+ writer + ", actors=" + actors + ", plot=" + plot + ", language=" + language + ", country=" + country
				+ ", awards=" + awards + ", poster=" + poster + ", metaScore=" + metaScore + ", imdbRating="
				+ imdbRating + ", imdbVotes=" + imdbVotes + ", imdbId=" + imdbId + ", type=" + type + "]";
	}

}
