package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import dao.ClientDAO;
import dao.CompteDAO;
import dao.DAOFactory;
import metier.Banque;
import metier.Client;
import metier.Compte;
import metier.CompteAvecDecouvert;
import metier.CompteSansDecouvert;

public class main {

	public static void main(String[] args) throws SQLException {

		ClientDAO cdao = DAOFactory.getClientDAO();
		CompteDAO cdao2 = DAOFactory.getCompteDAO();

		HashMap hm = cdao2.findAll();
		ArrayList<Client> cl = cdao.findAll();

		Banque banque = new Banque();

		for (int i = 0; i < cl.size(); i++) {
			banque.ajouterClient(cl.get(i));
		}

		Set cles = hm.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()) {
			Compte compte = (Compte) it.next();
			banque.ouverture_compte((String) hm.get(compte), compte);
		}

		boolean b = true;

		while (b) {
			System.out.println("Bienvenue dans votre banque : \n" + " - pour consulter un compte tapez 1 \n"
					+ " - pour faire un depot tapez 2 \n" + " - pour faire un retrait tapez 3 \n"
					+ " - pour créer un compte tapez 4 \n" + " - pour inscrire un client tapez 5 \n"
					+ " - pour quitter tapez 6");

			Scanner sc = new Scanner(System.in);
			int i = Integer.parseInt(sc.nextLine());
			switch (i) {
			case 1: {
				System.out.println("Tapez le numéro du compte souhaite :");
				String str = sc.nextLine();
				System.out.println(banque.consultation(str));
				break;
			}
			case 2: {
				try {
					System.out.println("Tapez le numéro du compte/numéro de client/montant :");
					String[] tab = sc.nextLine().split("/");
					banque.depot(tab[0], tab[1], Double.parseDouble(tab[2]));
					System.out.println("Nouveau solde :\n" + banque.consultation(tab[0]));
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			case 3:
				try {
					System.out.println("Tapez le numéro du compte/numéro de client/montant :");
					String[] tab = sc.nextLine().split("/");
					banque.retrait(tab[0], tab[1], Double.parseDouble(tab[2]));
					System.out.println("Nouveau solde :\n" + banque.consultation(tab[0]));
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			case 4:
				try {
					System.out.println("Tapez 1 pour un compte sans découvert et 2 pour un compte avec découvert :");
					int res = Integer.parseInt(sc.nextLine());
					if(res == 1) {
						System.out.println("Tapez numéro client/numéro de compte/solde :");
						String[] tab = sc.nextLine().split("/");
						banque.ouverture_compte(tab[0], new CompteSansDecouvert(tab[1], Double.parseDouble(tab[2])));
						System.out.println("compte créé");
					}
					else if(res == 2) {
						System.out.println("Tapez numéro client/numéro de compte/solde/découvert autorisé :");
						String[] tab = sc.nextLine().split("/");
						banque.ouverture_compte(tab[0], new CompteAvecDecouvert(tab[1],Double.parseDouble(tab[3]), Double.parseDouble(tab[2])));
						System.out.println("compte créé");
					}
					else{
						System.out.println("Mauvaise commande veuillez recommancer");
					}
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			case 5:
				try {
					System.out.println("Tapez le numéro de client/adresse/nom :");
					String[] tab = sc.nextLine().split("/");
					banque.ajouterClient(new Client(tab[0],tab[1],tab[2]));
					System.out.println("Client créé");
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			case 6: {
				b = false;
				break;
			}
			default:
				System.out.println("Mauvaise commande");
			}
		}

	}

}
