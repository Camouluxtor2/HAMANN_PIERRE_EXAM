package metier;

import dao.CompteDAO;
import dao.DAOFactory;

public class CompteSansDecouvert extends Compte{
	
	public CompteSansDecouvert(String numeroCompte) {
		super(numeroCompte);
	}
	
	public CompteSansDecouvert(String numeroCompte, double solde) {
		super(numeroCompte, solde);
	}

	public void debiter(double montant) {
		if(this.solde-montant > 0) {
			this.solde-=montant;
			CompteDAO dao = DAOFactory.getCompteDAO();
			dao.update(this.numeroCompte, this.solde);
		}
	}

}
