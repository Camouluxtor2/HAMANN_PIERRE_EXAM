package metier;

public abstract class Compte {
	
	protected double solde;
	protected String numeroCompte;
	
	public Compte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
		this.solde = 0;
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
	}
	
	public String toString() {
		return "Numero de compte : "+this.numeroCompte+" Solde : "+this.solde;
	}

}
