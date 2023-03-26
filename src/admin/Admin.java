/**
 * 
 */
package admin;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import common.DataValidation;
import common.DatabaseConnection;
import common.Movie;
import common.User;
/**
 * @author 
 *
 */
public class Admin {

	/**
	 * @param args
	 */

	static Connection connection;

	public static Movie takeMovieDetails() {
		Movie movie = new Movie();
		try {
			Scanner keyboard = new Scanner(System.in);
			
    		System.out.print("\nEnter movie name: ");
    		String movieName = keyboard.nextLine();
    		
    		System.out.print("Enter synopsis: ");
    		String synopsis = keyboard.nextLine();

    		System.out.print("Enter release date: ");
    		String releaseDateString = keyboard.nextLine();

    		Double price = DataValidation.readPositiveDouble("Enter price: ");

    		movie = new Movie(movieName, synopsis, releaseDateString, price);    		
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return movie;	
	}
	
	public static void menu() throws SQLException {		
		Scanner keyboard = new Scanner(System.in);
        int number = 0;
        ArrayList<Movie> movies = Movie.listAll(connection);
       
    	while (number != 5) {
    		try {
    			System.out.println("\n            MAIN MENU            ");
    			System.out.println("---------------------------------");
    			System.out.println("1 - View all movies from list");
    			System.out.println("2 - Add movie to list");
    			System.out.println("3 - Update movie from list");
    			System.out.println("4 - Delete movie from list");
    			System.out.println("5 - Exit");
            	number = DataValidation.readPositiveInt("Please enter 1-5: ");
    			System.out.println("---------------------------------");
            	if (number == 1) {
            		movies = Movie.listAll(connection);
            		if (movies.isEmpty()) {
            			System.out.println("\nNo movies in the list. Choose 2 to add.");
            		} else {
            			System.out.println("\n           NOW SHOWING            ");
            			System.out.print(    "             *******            ");
            			for (int i=0; i<movies.size(); i++) {
            				System.out.println("\n" + movies.get(i));
            			}
            		}
            	} else if (number == 2) {            		
            		System.out.println("\n          ADD A MOVIE            ");
        			System.out.println(  "            *******            ");
            		Movie movie = takeMovieDetails();
            		Movie.insert(connection, movie);            		
            	} else if (number == 3) {
            		Movie updateMovie = movies.get(0);

            		System.out.println("\n          UPDATE A MOVIE            ");
        			System.out.println(  "             *******            ");
        			System.out.print("\nEnter movie id: ");
        			String movieIdString = keyboard.next();
        			
        			int movieId = Integer.parseInt(movieIdString);
        			for (int i=0; i<movies.size(); i++){
        				if (movies.get(i).getId() == movieId) {
        					updateMovie = movies.get(i);
        				}
        			}
        			Movie movieDetails = takeMovieDetails();
        			
        			updateMovie.setMovieName(movieDetails.getMovieName());
        			updateMovie.setSynopsis(movieDetails.getSynopsis());
        			updateMovie.setReleaseDate(movieDetails.getReleaseDate());
        			updateMovie.setPrice(movieDetails.getPrice());

        			Movie.update(connection, updateMovie);            		
            	} else if (number == 4) {
            		System.out.println("\n          DELETE A MOVIE            ");
        			System.out.println(  "              *******            ");
            		System.out.print("\nEnter movie id: ");
            		String movieIdString = keyboard.next();
            		int movieId = Integer.parseInt(movieIdString);
            		Movie.delete(connection, movieId);
            		
            	} else if (number == 5) {
            		System.out.println("\nThank you for using our program!");
        			System.out.println(  "           *******            ");
            		keyboard.close();
            		System.exit(0);
            	} else {
            		throw new Exception();
            	}
            } catch (Exception e) {
            	System.out.println("Please only enter 1-5. Try again.");
            }
    	}
       
	}
	
	public static void main(String[] args){
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			
			String username;
			String password;
			User userLogin;
			Scanner keyboard = new Scanner(System.in);
			do {
				System.out.println("Login Details");
				System.out.println("------------------");
				System.out.print("Enter Username: ");
		        username = keyboard.nextLine();
		        System.out.print("Enter Password: ");
		        password = keyboard.nextLine();
		        userLogin = User.isValidCredentials(connection, username, password);
		        
		        if(userLogin.getId() != 0) {
		        	System.out.println("\nWelcome, " + userLogin.getFirstName() +"!");
		        	menu();
		        }
		        else {
		        	System.out.println("Invalid Username/Password. Please try again\n");
		        }
			} while(userLogin.getId() == 0);
			
			keyboard.close();
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
		
	}

}
