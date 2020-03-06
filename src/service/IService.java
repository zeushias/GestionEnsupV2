/**
 * Interface fonctionnelle pour associer un �tudiant � un cours
 */
package service;

import metier.Cours;
import metier.Etudiant;

/**
 * 
 * @author KODZO
 * @author YOEKO
 * @author LOIK
 *
 */
@FunctionalInterface
public interface IService {

	void associerCoursEtudiant(Etudiant etudiant, Cours cours);
}
