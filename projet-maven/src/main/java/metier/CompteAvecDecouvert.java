package metier;

import dao.CompteDAO;
import dao.DAOFactory;

public class CompteAvecDecouvert extends Compte{
	
	private double decouvertAutorise;
	
	public double getDecouvertAutorise() {
		return decouvertAutorise;
	}

	public CompteAvecDecouvert(String numeroCompte, double decouvert) {
		super(numeroCompte);
		this.decouvertAutorise = decouvert;
	}
	
	public CompteAvecDecouvert(String numeroCompte, double decouvert, double solde) {
		super(numeroCompte, solde);
		this.decouvertAutorise = decouvert;
	}
	
	public void debiter(double montant) {
		if(this.solde-montant > 0) {
			this.solde-=montant;
		}
		if(this.solde-montant < 0) {
			if(Math.abs(this.solde-montant) < decouvertAutorise) {
				this.solde-=montant;
			}
		}
		
		CompteDAO dao = DAOFactory.getCompteDAO();
		dao.update(this.numeroCompte, this.solde);
	}
	

}
