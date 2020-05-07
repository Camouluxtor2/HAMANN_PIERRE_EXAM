package metier;

public class CompteSansDecouvert extends Compte{
	
	public CompteSansDecouvert(String numeroCompte) {
		super(numeroCompte);
	}

	public void debiter(double montant) {
		if(this.solde-montant > 0) {
			this.solde-=montant;
		}
	}

}
