package metier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Exception.ExceptionClientInexistant;
import Exception.ExceptionCompteInexistant;

public class Banque {
	
	private HashMap<Client,ArrayList<Compte>> clients;
	
	public HashMap<Client, ArrayList<Compte>> getClients() {
		return clients;
	}

	public Banque() {
		this.clients = new HashMap<Client,ArrayList<Compte>>();
	}
		
	public void retrait(String num_compte, String num_client, double montant) throws ExceptionCompteInexistant, ExceptionClientInexistant {
		this.trouverCompte(this.trouverClient(num_client), num_compte).debiter(montant);
	}
	
	public void depot(String num_compte, String num_client, double montant) throws ExceptionCompteInexistant, ExceptionClientInexistant {
		this.trouverCompte(this.trouverClient(num_client), num_compte).crediter(montant);
	}
	
	public void ajouterClient(Client c) {
		if(!this.clients.containsKey(c)) {
			this.clients.put(c, new ArrayList<Compte>());
		}
	}
	
	public void changerInfoClient(String num_client, String nom, String adresse) throws ExceptionClientInexistant {
		this.trouverClient(num_client).setNomEtAdresse(nom, adresse);;
	}
	
	public Compte trouverCompte(Client client, String num_compte) throws ExceptionCompteInexistant {
		ArrayList<Compte> comptes = this.clients.get(client);
		for(int i=0;i<comptes.size();i++) {
			if(comptes.get(0).numeroCompte.equals(num_compte)) {
				return comptes.get(0);
			}
		}
		throw new ExceptionCompteInexistant("Compte non trouvé");
	}
	
	
	public Client trouverClient(String num_client) throws ExceptionClientInexistant {
		Set cles = this.clients.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()){
			Client client = (Client) it.next();
			if(client.getNumeroClient().equals(num_client)) {
				return client;
			}
		}
		throw new ExceptionClientInexistant("Client non trouvé");
	}
	
	private boolean compteExisteDeja(String num_compte) {
		Set cles = this.clients.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()){
			Client client = (Client) it.next();
			ArrayList<Compte> comptes = this.clients.get(client);
		    for(int i=0;i<comptes.size();i++) {
		    	if(comptes.get(i).numeroCompte.equals(num_compte)) {
		    		return true;
				}
		    }
		}
		return false;
	}
	
	public void ouverture_compte(String num_client, Compte compte) {
		if(!this.compteExisteDeja(compte.getNumeroCompte())) {
			try {
				Client client = this.trouverClient(num_client);
				this.clients.get(client).add(compte);
			}catch (ExceptionClientInexistant e){
				Client c = new Client(num_client);
				this.clients.put(c, new ArrayList<Compte>());
				this.clients.get(c).add(compte);
			}
		}
	}
	
	public String consultation(String num_compte) {
		Set cles = this.clients.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()){
		   Client client = (Client) it.next();
		   ArrayList<Compte> comptes = this.clients.get(client);
		   for(int i=0;i<comptes.size();i++) {
			   if(comptes.get(i).numeroCompte.equals(num_compte)) {
				   return comptes.get(i).toString();
			   }
		   }
		}
		return null;
	}
	
	public double conversionFromEuro(double montant) {
	
		return montant;
	}
	
	public double conversionToEuro(double montant) {
		
		return montant;
	}
	
	

}
