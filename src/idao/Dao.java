/**
 * 
 */
package idao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Cours;
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

	static String url = "jdbc:mysql://localhost/ges_ecole_ensup";
	static String login = "root";

	static Connection cn = null;
	static Statement st = null;
	static ResultSet rs = null;

	/**
	 * liste des étudiants
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
				System.out.print(rs.getInt("idPersonne"));
				System.out.print(" ");
				System.out.print(rs.getString("nom"));
				System.out.print(" ");
				System.out.print(rs.getString("prenom"));
				System.out.print(" ");
				System.out.print(rs.getString("email"));
				System.out.print(" ");
				System.out.print(rs.getString("adresse"));
				System.out.print(" ");
				System.out.print(rs.getString("telephone"));
				System.out.print(" ");
				System.out.print(rs.getString("date_naissance"));
				System.out.println(" ");
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
			System.out.println("Utilisateur enrégistré avec succès !!!! ");

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
			System.out.println("L'utilisateur n'existe pas");
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
					+ "`adresse`,`telephone`, `date_naissance`) " + "VALUES ('" + etudiant.getNom() + "','"
					+ etudiant.getPrenom() + "', '" + etudiant.getEmail() + "', '" + etudiant.getAdresse() + "', '"
					+ etudiant.getTelephone() + "', '" + etudiant.getDateNaissanceEtudiant() + "')";

			// etape 4 executer la requette
			st.executeUpdate(sql);
			System.out.println("Etudiant enrégistré avec succès !!!! ");

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
	 * Lire les informations d'un etudiant
	 */
	public static Etudiant lireUnEtudiant(int idEtudiant) {
		Etudiant etudiant = null;
		try {
			// etape1 chargement du driver

			Class.forName("com.mysql.jdbc.Driver");

			// etape2 recupertion de la connnexion

			cn = DriverManager.getConnection(url, login, password);

			// etape 3 creation du statement
			st = cn.createStatement();
			String sql = "select * from personne where  `idPersonne` =  '" + idEtudiant + "'";

			// etape 4 executer la requette

			rs = st.executeQuery(sql);

			// etape5 parcours du resultSet
			while (rs.next()) {
				etudiant = new Etudiant(rs.getInt("idPersonne"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("adresse"), rs.getString("telephone"),
						rs.getString("date_naissance"));
			}

		} catch (SQLException e) {
			System.out.println("L'étudiant n'existe pas");
		} catch (ClassNotFoundException e) {
			System.out.println("L'étudiant n'existe pas");
		} finally {

			// etape 5 liberer les ressources
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return etudiant;
	}

	/**
	 * Lire les informations d'un etudiant
	 */
	public static Cours selectionnerUnCours(String cours) {
		Cours c = null;
		try {
			// etape1 chargement du driver

			Class.forName("com.mysql.jdbc.Driver");

			// etape2 recupertion de la connnexion

			cn = DriverManager.getConnection(url, login, password);

			// etape 3 creation du statement
			st = cn.createStatement();
			String sql = "select * from cours where  `theme` =  '" + cours + "'";

			// etape 4 executer la requette

			rs = st.executeQuery(sql);

			// etape5 parcours du resultSet
			while (rs.next()) {
				c = new Cours(rs.getInt("idCours"), rs.getString("theme"), rs.getInt("nombreHeure"));
			}

		} catch (SQLException e) {
			System.out.println("Le cours n'existe pas");
		} catch (ClassNotFoundException e) {
			System.out.println("Le cours n'existe pas");
		} finally {

			// etape 5 liberer les ressources
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return c;
	}

	/**
	 * delete etudiant
	 * 
	 * @param id
	 */
	public void supprimerEtudiant(int id) {

		// Information d'acc�s � la base de donn�es

		int resultat;

		Connection connection = null;
		Statement statement = null;

		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : R�cup�ration de la connexion
			connection = DriverManager.getConnection(url, login, password);

			// Etape 3 : Creation d'un statement
			statement = connection.createStatement();

			String sql = "delete from personne where idPersonne ='" + id + "'";

			// Etape 4 : Execution requete
			resultat = statement.executeUpdate(sql);

			if (resultat == 0) {
				System.out.println("Aucun etudiant ne possède cet id \n");
			} else {
				System.out.println("L'etudiant a bien été supprimé \n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// Etape 5 : Liberer ressources de la memoire
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * update etudiant
	 * 
	 * @param id
	 * @param email
	 */

	public void modifierEtudiant(int id, String email) {

		// Information d'acces a la base de donnees

		int resultat;

		Connection connection = null;
		Statement statement = null;

		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : Recuperation de la connexion
			connection = DriverManager.getConnection(url, login, password);

			// Etape 3 : Creation d'un statement
			statement = connection.createStatement();

			String sql = "Update personne Set email= '" + email + "'where idPersonne ='" + id + "'";

			// Etape 4 : Execution requete
			resultat = statement.executeUpdate(sql);

			if (resultat == 0) {
				System.out.println("Aucun etudiant ne possède cet id \n");
			} else {
				System.out.println("Nouvelle email mise a jour \n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// Etape 5 : Liberer ressources de la memoire
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Integer verifierCoursEtudiant(int idEtudiant) {
		Integer id = 0;
		try {
			// etape1 chargement du driver

			Class.forName("com.mysql.jdbc.Driver");

			// etape2 recupertion de la connnexion

			cn = DriverManager.getConnection(url, login, password);

			// etape 3 creation du statement
			st = cn.createStatement();
			String sql = "select * from personne_cours where  `idPersonne` =  '" + idEtudiant + "'";

			// etape 4 executer la requette

			rs = st.executeQuery(sql);

			// etape5 parcours du resultSet
			while (rs.next()) {
				id = rs.getInt("idPersonne");
			}

		} catch (SQLException e) {
			System.out.println("Le cours n'existe pas");
		} catch (ClassNotFoundException e) {
			System.out.println("Le cours n'existe pas");
		} finally {

			// etape 5 liberer les ressources
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return id;
	}

}
