package metier;

public class CompteAvecDecouvert extends Compte{
	
	private double decouvertAutorise;
	
	public CompteAvecDecouvert(String numeroCompte, double decouvert) {
		super(numeroCompte);
		this.decouvertAutorise = decouvert;
	}
	
	public void debiter(double montant) {
		if(this.solde-montant < 0) {
			if(Math.abs(this.solde-montant) < decouvertAutorise) {
				this.solde-=montant;
			}
		}else {
			this.solde-=montant;
		}
	}
	

}
