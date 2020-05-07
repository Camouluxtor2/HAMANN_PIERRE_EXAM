package metier;

import dao.CompteDAO;
import dao.DAOFactory;

public abstract class Compte {
	
	protected double solde;
	protected String numeroCompte;
	
	public Compte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
		this.solde = 0;
	}
	
	public Compte(String numeroCompte, double solde) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
	}
	
	public abstract void debiter(double montant);
	
	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public void crediter(double montant) {
		this.solde+=montant;
		CompteDAO dao = DAOFactory.getCompteDAO();
		dao.update(this.numeroCompte, this.solde);
	}
	
	public String toString() {
		return "Numero de compte : "+this.numeroCompte+"\nSolde : "+this.solde;
	}

}
