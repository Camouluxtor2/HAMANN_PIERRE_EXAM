package metier;

public class Client {
	
	private String numeroClient, adresse, nom;
	
	public Client(String numeroClient) {
		this.numeroClient = numeroClient;
	}
	
	public Client(String numeroClient,String nom, String adresse) {
		this.numeroClient = numeroClient;
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public void setNomEtAdresse(String nom, String adresse) {
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public String getNumeroClient() {
		return numeroClient;
	}

	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	

}
