package Garage;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class Annuaire {

    private Map<String, Integer> carnet;
    Scanner scanner;

    // Méthode volontairement un peu compliquée pour s'entrainer...
    public void ajouterPersonne() {
        
        System.out.print(" - Quel est le nom de la personne : ");
        String nom = scanner.nextLine();
        
        System.out.print("\n - Quel est son numéro : ");
        int num = scanner.nextInt();
        String captureRetourLigne = scanner.nextLine();
        System.out.println();

        List<Integer> CompliquePourRien = new ArrayList<Integer>();

        for(Map.Entry<String, Integer> verif : carnet.entrySet())
        {
            CompliquePourRien.add(verif.getValue());
        }

        for (int i : CompliquePourRien)
        {
            if (i == num) {
                System.out.println("[INFOS] : Ce numéro est déjà pris, échec de l'enregistrement de la personne " + nom + ".");
                return;
            }
        }

        for (String verif : carnet.keySet()) {
            if (verif.equals(nom)) {
                System.out.println("[INFOS] : La personne " + nom + " est déjà présente dans l'annuaire, échec de l'enregistrement.");
                return;
            }
        }

        carnet.put(nom, num);
        System.out.println("[INFOS] : La personne " + nom + "(" + num + ") a bien été ajoutée à l'annuaire.");
    }

    public void modifierNumero()
    {
        System.out.print(" - Quel est le nom de la personne : ");
        String nom = scanner.nextLine();
        
        System.out.print("\n - Quel est son numéro : ");
        int num = scanner.nextInt();
        String captureRetourLigne = scanner.nextLine();
        System.out.println();

        boolean verif = false;

        for (String s : carnet.keySet()) {
            if (s.equals(nom)) {
                verif = true;
            }
        }

        if (verif != true) {
            System.out.println("[INFOS] : L'utilisateur " + nom + " n'existe pas, modification impossible.");
            return;
        }

        for (int i : carnet.values()) {
            if (i == num) {
                System.out.println("[INFOS] : Le numéro " + num + " est déjà pris, modification impossible.");
                return;
            }
        }

        carnet.put(nom, num);
        System.out.println("[INFOS] : Modification de l'utilisateur " + nom + " bien réussie.");
    }

    public void supprimerPersonne()
    {
        System.out.print(" - Quel est le nom de la personne : ");
        String nom = scanner.nextLine();
        
        boolean verif = false;

        for (String s : carnet.keySet()) {
            if (s.equals(nom)) {
                verif = true;
            }
        }

        if (verif != true) {
            System.out.println("[INFOS] : L'utilisateur " + nom + " n'existe pas, suppression impossible.");
            return;
        }

        carnet.remove(nom);
        System.out.println("[INFOS] : L'utilisateur " + nom + " a bien été supprimé.");
    }

    public void menuAnnuaire() {

        int choix = 5;

        while (choix != 0)
        {
            Garage.ClearTerminal();

            System.out.println("\n===========================");
            System.out.println("   Annuaire du garage");
            System.out.println("===========================\n\n");

            System.out.println(" Affichage des personnes : ");
            System.out.println(" -------------------------");

            for (Map.Entry<String, Integer> paire : carnet.entrySet()) {
                System.out.print("|\t - " + paire.getKey() + " : ");
                System.out.println(paire.getValue());
            }

            System.out.println("\n Que souhaitez-vous faire ? ");
            System.out.println(  " -------------------------\n");
            System.out.println(" 1.) Ajouter une nouvelle personne.");
            System.out.println(" 2.) Modifier une personne existante.");
            System.out.println(" 3.) Supprimer une personne existante.");
            System.out.println(" 0.) Retourner au menu principal\n");
            System.out.print(" -> Votre choix : ");
            choix = scanner.nextInt();
            String captureRetourLigne = scanner.nextLine();
            System.out.println("\n\n");

            if (choix == 0) {
                return;
            }
            else
            {
                Garage.ClearTerminal();
                System.out.println("\n===========================");
                System.out.println("   Annuaire du garage");
                System.out.println("===========================\n\n");
            }

            switch(choix)
            {

                case 1 :
                    ajouterPersonne();
                    Garage.Pause();
                    break;
                
                case 2 : 
                    modifierNumero();
                    Garage.Pause();
                    break;

                case 3 : 
                    supprimerPersonne();
                    Garage.Pause();
                    break;
            }
        }
    }

    public Annuaire() {
        carnet = new HashMap<String, Integer>();
        scanner = new Scanner(System.in);
    }
}
