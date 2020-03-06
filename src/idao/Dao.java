/**
 * 
 */
package idao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Etudiant;
import metier.Utilisateur;

/**
 * 
 * @author zeuhias
 *
 */
public class Dao {

	public static String password = "root";

	// information de la base de donnee

	static String url = "jdbc:mysql://localhost/gestionensup";
	static String login = "root";

	static Connection cn = null;
	static Statement st = null;
	static ResultSet rs = null;

	/**
	 * liste des �tudiants
	 */
	public static void lireEtudiant() {

		try {
			// etape1 chargement du driver

			Class.forName("com.mysql.jdbc.Driver");

			// etape2 recupertion de la connnexion

			cn = DriverManager.getConnection(url, login, password);

			// etape 3 creation du statement
			st = cn.createStatement();
			String sql = "select * from personne";

			// etape 4 executer la requette
			System.out.println("Liste des etudiants ");
			rs = st.executeQuery(sql);

			// etape5 parcours du resultSet
			while (rs.next()) {
				System.out.println(rs.getInt("idPersonne"));
				System.out.println(" ");
				System.out.print(rs.getString("nom"));
				System.out.print(" ");
				System.out.println(rs.getString("prenom"));
				System.out.print(" ");
				System.out.println(rs.getString("email"));
				System.out.print(" ");
				System.out.println(rs.getString("adresse"));
				System.out.print(" ");
				System.out.println(rs.getString("telephone"));
				System.out.print(" ");
				System.out.println(rs.getString("dateNaissance"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {

			// etape 5 liberer les ressources
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param utilisateur les informations sur l'utilisateur
	 */
	public static void creerUtilisateur(Utilisateur utilisateur) {
		// information de la base de donnee

		try {
			// etape1 chargement du driver

			Class.forName("com.mysql.jdbc.Driver");

			// etape2 recupertion de la connnexion

			cn = DriverManager.getConnection(url, login, password);

			// etape 3 creation du statement
			st = cn.createStatement();
			String sql = "INSERT INTO `utilisateur` (`login`,`password`, `profil`) " + "VALUES ('"
					+ utilisateur.getLogin() + "','" + utilisateur.getPassword() + "', '" + utilisateur.getProfil()
					+ "')";

			// etape 4 executer la requette
			st.executeUpdate(sql);
			System.out.println("Utilisateur enr�gistr� avec succ�s !!!! ");

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {

			// etape 5 liberer les ressources
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param utilisateur
	 * @return
	 */
	public static Utilisateur selectionUtilisateur(Utilisateur utilisateur) {

		Utilisateur user = null;
		try {
			// etape1 chargement du driver

			Class.forName("com.mysql.jdbc.Driver");

			// etape2 recupertion de la connnexion

			cn = DriverManager.getConnection(url, login, password);

			// etape 3 creation du statement
			st = cn.createStatement();
			String sql = "select * from utilisateur where `login` =  '" + utilisateur.getLogin() + "' and "
					+ "`password` = '" + utilisateur.getPassword() + "'";

			// etape 4 executer la requette
			rs = st.executeQuery(sql);

			// etape5 parcours du resultSet
			while (rs.next()) {
				user = new Utilisateur(rs.getInt("idUser"), rs.getString("login"), rs.getString("password"),
						rs.getString("profil"));
			}

		} catch (SQLException e) {
			System.out.println("L'utilisateur n'existe pas");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {

			// etape 5 liberer les ressources
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return user;
	}
	
	/**
	 * 
	 * @param etudiant
	 */
	public static void creerEtudiant(Etudiant etudiant) {

		try {
			// etape1 chargement du driver

			Class.forName("com.mysql.jdbc.Driver");

			// etape2 recupertion de la connnexion

			cn = DriverManager.getConnection(url, login, password);

			// etape 3 creation du statement
			st = cn.createStatement();
			String sql = "INSERT INTO `personne` (`nom`, `prenom`,`email`, "
					+ "`adresse`,`telephone`) "
					+ "VALUES ('" + etudiant.getNom() + "','"+ etudiant.getPrenom() + "', '"
					+ etudiant.getEmail()+"', '"+etudiant.getAdresse()+"', '"+etudiant.getTelephone() +"')";

			// etape 4 executer la requette
			st.executeUpdate(sql);
			System.out.println("Etudiant enr�gistr� avec succ�s !!!! ");

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {

			// etape 5 liberer les ressources
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}