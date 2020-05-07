package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.ExceptionClientInexistant;
import exception.ExceptionCompteInexistant;
import metier.Banque;
import metier.Client;
import metier.CompteAvecDecouvert;
import metier.CompteSansDecouvert;

public class TestBanque {
	
	Banque banque;
	
	@Before
	public void setup() {
		banque = new Banque();
	}

	@Test
	public void ajouterClient() {
		banque.ajouterClient(new Client("001", "nom_test", "adresse_test"));
		assertEquals(banque.getClients().size(), 1);
	}
	
	@Test
	public void ajouterClientDejaExistant() {
		Client c = new Client("001", "nom_test", "adresse_test");
		banque.ajouterClient(c);
		banque.ajouterClient(c);
		assertEquals(banque.getClients().size(), 1);
	}
	
	@Test
	public void ouvrirCompteAvecClientExistant() throws ExceptionClientInexistant {
		banque.ajouterClient(new Client("001", "nom_test", "adresse_test"));
		banque.ouverture_compte("001", new CompteSansDecouvert("c001"));
		banque.ouverture_compte("001", new CompteSansDecouvert("c002"));
		assertEquals(banque.getClients().get(banque.trouverClient("001")).size(), 2);
	}
	
	@Test
	public void ouvrirCompteAvecClientInexistant() throws ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteSansDecouvert("c001"));
		banque.ouverture_compte("001", new CompteSansDecouvert("c002"));
		assertEquals(banque.getClients().get(banque.trouverClient("001")).size(), 2);
	}
	
	@Test
	public void ouvrirCompteNumeroDeCompteDejaExistant() throws ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteSansDecouvert("c001"));
		banque.ajouterClient(new Client("001", "nom_test", "adresse_test"));
		banque.ouverture_compte("002", new CompteSansDecouvert("c001"));
		banque.ajouterClient(new Client("002", "nom_test2", "adresse_test2"));
		assertEquals(banque.getClients().get(banque.trouverClient("002")).size(), 0);
		
	}
	
	@Test
	public void depot() throws ExceptionCompteInexistant, ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteSansDecouvert("c001"));
		banque.depot("c001", "001", 120);
		assertEquals(banque.trouverCompte(banque.trouverClient("001"), "c001").getSolde()==120, true);
	}
	
	@Test
	public void depotCompteInexistant() throws ExceptionClientInexistant {
		banque.ajouterClient(new Client("001", "nom_test", "adresse_test"));
		try {
			banque.depot("c001", "001", 120);
		} catch (ExceptionCompteInexistant e) {
			assert(e.getMessage().contains("Compte non trouvé"));
		}
	}
	
	@Test
	public void depotClientInexistant() throws ExceptionCompteInexistant {
		try {
			banque.retrait("c001", "001", 120);
		} catch (ExceptionClientInexistant e) {
			assert(e.getMessage().contains("Client non trouvé"));
		}
	}
	
	@Test
	public void consultation() throws ExceptionCompteInexistant, ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteSansDecouvert("c001"));
		banque.depot("c001", "001", 120);
		assertEquals(banque.consultation("c001").equals("Numero de compte : c001 Solde : 120.0"), true);
	}
	
	@Test
	public void consultationCompteInexistant() throws ExceptionClientInexistant {
		assertNull(banque.consultation("c001"));
	}
	
	
	@Test
	public void retraitCompteInexistant() throws ExceptionClientInexistant {
		banque.ajouterClient(new Client("001", "nom_test", "adresse_test"));
		try {
			banque.retrait("c001", "001", 120);
		} catch (ExceptionCompteInexistant e) {
			assert(e.getMessage().contains("Compte non trouvé"));
		}
	}
	
	@Test
	public void retraitClientInexistant() throws ExceptionCompteInexistant {
		try {
			banque.retrait("c001", "001", 120);
		} catch (ExceptionClientInexistant e) {
			assert(e.getMessage().contains("Client non trouvé"));
		}
	}
	

	@Test
	public void retraitSansDecouvertOK() throws ExceptionCompteInexistant, ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteSansDecouvert("c001"));
		banque.depot("c001", "001", 120);
		banque.retrait("c001", "001", 100);
		assertEquals(banque.consultation("c001").equals("Numero de compte : c001 Solde : 20.0"), true);
	}
	
	@Test
	public void retraitSansDecouvertPasOK() throws ExceptionCompteInexistant, ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteSansDecouvert("c001"));
		banque.depot("c001", "001", 120);
		banque.retrait("c001", "001", 130);
		assertEquals(banque.consultation("c001").equals("Numero de compte : c001 Solde : 120.0"), true);
	}
	
	@Test
	public void retraitDecouvertAuDessusDeZero() throws ExceptionCompteInexistant, ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteAvecDecouvert("c001", 100.0));
		banque.depot("c001", "001", 120);
		banque.retrait("c001", "001", 110);
		assertEquals(banque.consultation("c001").equals("Numero de compte : c001 Solde : 10.0"), true);
	}
	
	@Test
	public void retraitDecouvertAuDessusDeZeroDecouvertOK() throws ExceptionCompteInexistant, ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteAvecDecouvert("c001", 100.0));
		banque.depot("c001", "001", 120);
		banque.retrait("c001", "001", 140);
		assertEquals(banque.consultation("c001").equals("Numero de compte : c001 Solde : -20.0"), true);
	}
	
	@Test
	public void retraitDecouvertAuDessusDeZeroDecouvertPasOK() throws ExceptionCompteInexistant, ExceptionClientInexistant {
		banque.ouverture_compte("001", new CompteAvecDecouvert("c001", 100.0));
		banque.depot("c001", "001", 120);
		banque.retrait("c001", "001", 240);
		assertEquals(banque.consultation("c001").equals("Numero de compte : c001 Solde : 120.0"), true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
