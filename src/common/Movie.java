/**
 * 
 */
package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Movie {
	
	/*----------- Fields ----------- */
	private int id;
	private String movieName;
	private String synopsis;
	private String releaseDate;
	private int duration;
	
	/*----------- Getter Setter ----------- */
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
  
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
  
	/**
	 * @return the movieName
	 */
	public String getMovieName() {
		return movieName;
	}
  
	/**
	 * @param movieName the movieName to set
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
  
	/**
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}
  
	/**
	 * @param synopsis the synopsis to set
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
  
	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
  
	/**
	 * @param releaseDate the releaseDate to set
	 */
  
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/*----------- Constructors ----------- */
	/**
	 * Default constructor
	 */
	public Movie() {
		this.movieName = "";
		this.synopsis = "";
		this.releaseDate = "";
		
	}
	
	/**
	 * Constructor with full fields
	 * @param id
	 * @param movieName
	 * @param synopsis
	 * @param releaseDate
	 */
	public Movie(int id, String movieName, String synopsis, String releaseDate) {
		this.id = id;
		this.movieName = movieName;
		this.synopsis = synopsis;
		this.releaseDate = releaseDate;
	}
	
	public Movie(String movieName, String synopsis, String releaseDate) {
		this.movieName = movieName;
		this.synopsis = synopsis;
		this.releaseDate = releaseDate;
	}
	public Movie(int id, String movieName, String synopsis, String releaseDate, int duration) {
		this.id = id;
		this.movieName = movieName;
		this.synopsis = synopsis;
		this.releaseDate = releaseDate;
		this.duration = duration;
	}
	
	public Movie(int id) {
		this.id = id;
	}
	
	public String toString() {
		return ("ID:\t\t" + id + "\n"
				+ "Movie name:\t" + movieName + "\n"
				+ "Synopsis:\t" + synopsis + "\n"
				+ "Release date:\t" + releaseDate
				 );
	}
	
	public static void insert(Connection connection, Movie movie) throws SQLException {
		String query = "INSERT INTO movies (movie_name, synopsis, release_date) VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, movie.getMovieName());
		statement.setString(2, movie.getSynopsis());
		statement.setString(3, movie.getReleaseDate());
		statement.executeUpdate();
		System.out.println("Movie inserted successfully");
	}

	public static void update(Connection connection, Movie movie) throws SQLException {
		String query = "UPDATE movies SET movie_name = ?, synopsis = ?, release_date = ? WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, movie.getMovieName());
		statement.setString(2, movie.getSynopsis());
		statement.setString(3, movie.getReleaseDate());
		statement.setInt(4, movie.getId());
		statement.executeUpdate();
		System.out.println("Movie updated successfully");
	}

	public static void delete(Connection connection, int movie_id) throws SQLException {
		String query = "DELETE FROM movies WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, movie_id);
		statement.executeUpdate();
		System.out.println("Movie deleted successfully");
	}

	public static ArrayList<Movie> listAll(Connection connection) throws SQLException {
		String query = "SELECT * FROM movies";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		ArrayList<Movie> movies = new ArrayList<>();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("movie_name");
			String synopsis = resultSet.getString("synopsis");
			String release_date = resultSet.getString("release_date");
			Movie mv = new Movie(id, name, synopsis, release_date);
			movies.add(mv);
		}
		return movies;
	}
	
	public static ArrayList<Movie> listMovieDetails(Connection connection, int movie_id) throws SQLException {
		String query = "SELECT * FROM movies WHERE id =?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, movie_id);
		ResultSet resultSet = statement.executeQuery();
		ArrayList<Movie> movies = new ArrayList<>();
		while(resultSet.next()) {
			String name = resultSet.getString("movie_name");
			String synopsis = resultSet.getString("synopsis");
			String release_date = resultSet.getString("release_date");
			Movie mv = new Movie(movie_id, name, synopsis, release_date);
			movies.add(mv);
		}
		
		return movies;
	}
	
	public static ArrayList<Movie> selectedSeats(Connection connection, int movie_id) throws SQLException {
		String query = "SELECT * FROM seatselection WHERE movie_id =?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, movie_id);
		ResultSet resultSet = statement.executeQuery();
		ArrayList<Movie> movies = new ArrayList<>();
		while(resultSet.next()) {
			String name = resultSet.getString("movie_name");
			String synopsis = resultSet.getString("synopsis");
			String release_date = resultSet.getString("release_date");
			Movie mv = new Movie(movie_id, name, synopsis, release_date);
			movies.add(mv);
		}
		
		return movies;
	}
	
	public static Movie getByID(Connection connection, int movieId) throws SQLException {
		String query = "SELECT * FROM movies WHERE id =? ";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		Movie mv = new Movie();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("movie_name");
			String synopsis = resultSet.getString("synopsis");
			String release_date = resultSet.getString("release_date");
			mv = new Movie(id, name, synopsis, release_date);
		}
		return mv;
	}
	public static boolean checkMovieExist(ArrayList<Movie> movies, int selectedMovie) {
		for (Movie movie : movies) {
			if(movie.getId() == selectedMovie) {
				return true;
			}
		}
		return false;
	}

}
