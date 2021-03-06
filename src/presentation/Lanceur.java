/**
 * Class main pour lancer notre application de gestion d'�cole
 * 
 */
package presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import idao.CoursPersonneDao;
import idao.Dao;
import metier.Cours;
import metier.Etudiant;
import metier.Utilisateur;
import service.IService;

/**
 * Class main pour lancer notre application de gestion de l'�cole
 * 
 * @author KODZO
 * @author YOEKO
 * @author LOIK
 *
 */
public class Lanceur {
	static Dao dao = new Dao();
	static String statut = "";

	public static void main(String[] args) {

		String reponse = "";
		// utilisateur
		System.out.println("--------------------------------------------------------------");
		Scanner sc = new Scanner(System.in);

		while (!reponse.equals("O") && !reponse.equals("N")) {
			System.out.print("Avez vous un login et mot de passe utilisateur O / N  : ");
			reponse = sc.next().toUpperCase();

			if (!reponse.equals("O") && !reponse.equals("N")) {
				System.out.println("Mauvaise reponse !!! Reprenez !!!");
			}
		}
		//

		if (reponse.equals("O")) {
			affichageRechercheUtilisateur();
			// r�cup�ration du chiffre saisie
			int saisie = saisieOperation();
			//
			operation(saisie);
		} else {
			affichageCreationUtilisateur();
			// r�cup�ration du chiffre saisie
			int saisie = saisieOperation();
			//
			operation(saisie);
		}
	}

	/**
	 * affichage responsable
	 */
	private static void affichageResponsable() {
		Scanner scan = new Scanner(System.in);

		// affichage basique
		System.out.println("--------------------------------------------------------------");
		System.out.println("Pour ins�rer un �tudiant tapez 1");
		System.out.println("Pour consulter les informations d'un �tudiant tapez 2");
		System.out.println("Pour supprimer un �tudiant tapez 3");
		System.out.println("Pour modifier le nom d'un �tudiant tapez 4");
		System.out.println("Pour associer une �cole � un �tudiant tapez 5");
		System.out.println("--------------------------------------------------------------");
		//
		System.out.print("Saisir un chiffre : ");

	}

	/**
	 * affichage responsable
	 */
	private static void affichageDirecteur() {
		Scanner scan = new Scanner(System.in);

		// affichage basique
		System.out.println("--------------------------------------------------------------");
		System.out.println("Pour ins�rer un �tudiant tapez 1");
		System.out.println("Pour consulter les informations d'un �tudiant tapez 2");
		System.out.println("Pour supprimer un �tudiant tapez 3");
		System.out.println("Pour modifier le nom d'un �tudiant tapez 4");
		System.out.println("Pour associer une �cole � un �tudiant tapez 5");
		System.out.println("Pour lister les �tudiants tapez 6");
		System.out.println("--------------------------------------------------------------");
		//
		System.out.print("Saisir un chiffre : ");

	}

	// affichageRechercheUtilisateur
	private static void affichageRechercheUtilisateur() {

		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("Veuillez saisir votre login utilisateur : ");
		System.out.println();
		String loginUser = sc.next();
		System.out.println();
		System.out.print("Veuillez saisir votre mot de passe utilisateur : ");
		System.out.println();
		String passwordUser = sc.next();

		Utilisateur utilisateur = new Utilisateur(loginUser, passwordUser, null);
		Utilisateur u = Dao.selectionUtilisateur(utilisateur);
		if (u != null) {
			if (u.getProfil().equals("R")) {
				// affichage resonsable
				statut = "R";
				affichageResponsable();
			} else {
				// affichage directeur
				statut = "D";
				affichageDirecteur();
			}
		} else {
			// compteur++;
			System.out.println("Mauvais utilisateur reprenez");
			affichageRechercheUtilisateur();
		}

	}

	/**
	 * saisir une num�ro qui correspond � une op�ration
	 * 
	 * @return
	 */
	private static Integer saisieOperation() {
		//
		Scanner scan = new Scanner(System.in);
		int saisie = scan.nextInt();
		return saisie;
	}

	/**
	 * affichageCreationUtilisateur
	 */
	private static void affichageCreationUtilisateur() {
		String profilUser = "";
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("Cr�ation d'utilisateur : ");
		System.out.println();
		System.out.print("Veuillez saisir votre login utilisateur : ");
		System.out.println();
		String loginUser = sc.next();
		System.out.println();
		System.out.print("Veuillez saisir votre mot de passe utilisateur : ");
		System.out.println();
		String passwordUser = sc.next();
		System.out.println();

		while (!profilUser.equals("R") && !profilUser.equals("D")) {
			System.out.print("Veuillez saisir votre profil utilisateur D pour Directeur R pour Responsable �tude : ");
			System.out.println();
			profilUser = sc.next().toUpperCase();

			if (!profilUser.equals("R") && !profilUser.equals("D")) {
				System.out.println("Mauvaise reponse !!! Reprenez !!!");
			}
		}

		Utilisateur utilisateur = new Utilisateur(loginUser, passwordUser, profilUser);
		Dao.creerUtilisateur(utilisateur);
	}

	/**
	 * 
	 * @param chiffre
	 */
	private static void operation(int chiffre) {
		Scanner scan = new Scanner(System.in);
		Etudiant etudiant = null;
		int idEtudiant = 0;
		switch (chiffre) {
		case 1:
			// enr�gistrer un �tudiant

			etudiant = enregistrementEtudiant();
			Dao.creerEtudiant(etudiant);
			affichage();
			break;
		case 2:

			while (idEtudiant == 0) {
				idEtudiant = saisirIdentifiantEtudiantAconsulter();
			}
			etudiant = Dao.lireUnEtudiant(idEtudiant);
			if (etudiant != null) {
				System.out.print(etudiant.getIdPersonne());
				System.out.print(" ");
				System.out.print(etudiant.getNom());
				System.out.print(" ");
				System.out.print(etudiant.getPrenom());
				System.out.print(" ");
				System.out.print(etudiant.getEmail());
				System.out.print(" ");
				System.out.print(etudiant.getAdresse());
				System.out.print(" ");
				System.out.print(etudiant.getTelephone());
				System.out.print(" ");
				System.out.print(etudiant.getDateNaissanceEtudiant());
				System.out.println(" ");
			} else {
				// compteur++;
				System.out.println("L'�tudiant n'existe pas");
			}
			affichage();
			break;

		case 3:
			System.out.println("Saisir l'identifiant de l'etudiant ");
			int id = scan.nextInt();
			Dao etuDao = new Dao();
			int etud = etuDao.verifierCoursEtudiant(id);
			if(etud ==0) {
				etuDao.supprimerEtudiant(id);
				affichage();
			} else {
				System.out.println("Vous pouvez pas supprimer cet utlisateur");
				affichage();
			}
			
			break;
		case 4:
			System.out.println("Saisir l'identifiant de l'etudiant a modifier ");
			int idModif = scan.nextInt();
			System.out.println("Saisir l'adresse a modifier ");
			String email = scan.next();

			dao.modifierEtudiant(idModif, email);
			affichage();
			break;
		case 5:

			System.out.println("Saisir le th�me du cours");
			String cours = scan.next();

			//

			while (idEtudiant == 0) {
				idEtudiant = saisirIdentifiantEtudiantAconsulter();
			}
			etudiant = Dao.lireUnEtudiant(idEtudiant);
			Cours c = Dao.selectionnerUnCours(cours);

			if (etudiant == null) {
				System.out.println("L'�tudiant n'existe pas");
			}
			if (c == null) {
				System.out.println("Le cours n'existe pas");
			}
			if (etudiant != null && c != null) {
				// classe anonyme
				new CoursPersonneDao().associerCoursEtudiant(etudiant, c);
			}
			affichage();
			break;
		case 6:
			// lister les �tudiants
			System.out.println();
			System.out.println("La liste des �tudiants");
			Dao.lireEtudiant();
			affichage();
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @return un �tudiant
	 */
	public static Etudiant enregistrementEtudiant() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.print("entrer le nom de l'�tudiant : ");
		String nomEtudiant = scan.next();
		System.out.println();
		System.out.print("entrer le prenom de l'�tudiant : ");
		String prenomEtudiant = scan.next();
		System.out.println();
		System.out.print("entrer l'email de l'�tudiant : ");
		String emailEtudaint = scan.next();
		System.out.println();
		System.out.print("entrer l'adresse de l'�cole : ");
		String adresseEtudiant = scan.next();
		System.out.println();
		System.out.print("entrer le t�l�phone de l'�tudiant : ");
		String telephoneEtudiant = scan.next();
		System.out.println();
		System.out.print("entrer la date de naissance de l'�tudiant JJ/MM/AAAA : ");
		String dateNaissance = scan.next();
		System.out.println();

		Etudiant etudiant = new Etudiant(nomEtudiant, prenomEtudiant, emailEtudaint, adresseEtudiant, telephoneEtudiant,
				dateNaissance);
		return etudiant;
	}

	/**
	 * saisir l'Identifiant de l'Etudiant A consulter
	 * 
	 * @return un nombre saisie par l'utilisateur
	 */
	public static int saisirIdentifiantEtudiantAconsulter() {
		Scanner scan = new Scanner(System.in);
		int idEtudiant = 0;
		System.out.println("Entrer l'identifiant de l'�tudiant");
		try {
			idEtudiant = scan.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("Vous devez saisir un nombre  ");
		}
		return idEtudiant;
	}

	public static void affichage() {
		if (statut.equals("D")) {
			affichageDirecteur();
			// r�cup�ration du chiffre saisie
			int saisie = saisieOperation();
			//
			operation(saisie);
		} else {
			affichageResponsable();
			// r�cup�ration du chiffre saisie
			int saisie = saisieOperation();
			//
			operation(saisie);
		}
	}

}
