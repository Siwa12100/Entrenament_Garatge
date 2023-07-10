package Garage;
import Garage.Vehicules.*;
import java.util.List;
import java.util.ArrayList;

public class Test {
    
    public void TestPrincipal()
    {
        //TestDemarrageEtEtat();
        //TestFonctionnalitesEngin();
        //TestFonctionnalitesVoiture();

        Engin v1 = new Voiture(40);
        Engin v2 = new Voiture(10, 40000, 1, 5000, Marques.Peugeot, "Verd", "VoitureKaii");
        //v2.faireLePlein();
        //TestConsommationEssence(v1);
        //TestCompteurEtReparation(v2);

        IDataManager dt = new Serialiser("./Garage/sauvegarde/voitures.txt","./Garage/sauvegarde/annuaire.txt");

        // TestSerialisationVoitures(dt);
        // dt.closeStreams();
        // TestChargementVoitures(dt);
        TestChargementEnregistrementAnnuaire(dt);
    } 

    public void TestChargementEnregistrementAnnuaire(IDataManager dt) {
        Annuaire a = new Annuaire();
        ArrayList<Integer> lentiers = new ArrayList<Integer>();
        ArrayList<String> lstrings = new ArrayList<String>();

        for (int i = 0; i < 5; i++) {
            lentiers.add(i *4);
        }

        for (int i = 0; i < 5; i++) {
            lstrings.add("Utilisateur numero " + String.valueOf((i+1)) + " ");
        }

        for (int i = 0; i < 5; i++) {
            a.ajoutRapide(lstrings.get(i), lentiers.get(i));
        }

        System.out.println("\n\n Enregistrement de l'annuaire : ");
        System.out.println(" --------------------------------\n\n");
        dt.enregistrementAnnuaire(a.getCarnet());
        dt.closeStreams();

        System.out.println("\n\n Chargement Affichage de l'annuaire : ");
        System.out.println(    " ------------------------------------\n\n");
        a.setCarnet(dt.chargementAnnuaire());
        System.out.println("\n");
        a.affichageAnnuaire();
        System.out.println("\n");

    }

    public void TestChargementVoitures(IDataManager dt) {
        List<Voiture> l = dt.chargementVoitures();

        System.out.println("\n\n Affichage de toutes les voitures chargées : ");
        System.out.println(" -------------------------------------------\n\n");

        int i = 1;
        for (Voiture v : l) {
            System.out.print("- (" + i + ") : \t");
            v.affichage();
            System.out.println();
            i++;
        }

    }
    public void TestSerialisationVoitures(IDataManager dt) {

        List<Voiture> liste = new ArrayList<Voiture>();
        for (int i = 0; i < 10; i++) {
            Voiture v = new Voiture();
            liste.add(v);
        }

        dt.enregistrerVoitures(liste);
    }

    public void TestFonctionnalitesVoiture()
    {
        Voiture e1 = new Voiture(118712);

        System.out.println("\n ---> Test des fonctionnalités voitures : ");
        System.out.println(      "-----------------------------------\n\n");

        System.out.println(" --> Set son à tut-tut");
        e1.setSon("Tut-tut");
        System.out.println(" --> Get nouveau son : " + e1.getSon() + "\n");

        System.out.println(" --> On fait klaxonner 4 fois");
        e1.klaxonner(4);

        System.out.println(" --> Taille du coffre (doit être à 118712) : " + e1.getTailleCoffre() + "\n");

        System.out.println(" --> Get nb roues (doit être à 4) : " + e1.getNbRoues() + "\n");
    }

    public void TestConsommationEssence(Engin e1)
    {
        e1.renommer("Kugge");
        System.out.println("\n ---> Tests sur la consommation d'essence : \n");
        System.out.println(        "      -------------------------------------\n");
        System.out.println(" - Consommation voiture : " + e1.getConsommation());
        System.out.println(" - Taille du réservoir : " + e1.getTailleResservoir());
        System.out.println(" - Niveau essence actuel : " + e1.getNiveauEssence() + "\n");

        System.out.println("\n -> On essaye d'avancer de 50km...\n");
        e1.avancer(50);
        System.out.println("\n -> Maintenant on fait le plein et on avance de 50\n");
        e1.faireLePlein();
        e1.avancer(50);
        e1.seCouper();


        System.out.println(" - Consommation voiture : " + e1.getConsommation());
        System.out.println(" - Taille du réservoir : " + e1.getTailleResservoir());
        System.out.println(" - Niveau essence actuel : " + e1.getNiveauEssence() + "\n");

        System.out.println("\n -> On avance de 120 km (donc pas assez essence...)\n");
        e1.avancer(120);
        e1.seCouper();

        System.out.println(" - Consommation voiture : " + e1.getConsommation());
        System.out.println(" - Taille du réservoir : " + e1.getTailleResservoir());
        System.out.println(" - Niveau essence actuel : " + e1.getNiveauEssence() + "\n");

        System.out.println("\n -> On avance de 100 km (donc pile assez d'essence)\n");
        e1.avancer(100);
        e1.seCouper();

        System.out.println(" - Consommation voiture : " + e1.getConsommation());
        System.out.println(" - Taille du réservoir : " + e1.getTailleResservoir());
        System.out.println(" - Niveau essence actuel : " + e1.getNiveauEssence() + "\n");

    }

    public void TestCompteurEtReparation(Engin e1)
    {
        e1.affichage();
        System.out.println("\n -> On avance de 200, on coupe et réavance de 220 pour passer la prochaine réparation\n");
        e1.avancer(200);
        e1.seCouper();
        e1.avancer(220);
        e1.seCouper();

        e1.affichage();
        System.out.println("\n -> On refais avancer avec une prochaine rep. depassée...\n");
        e1.avancer(100);
        e1.seCouper();

        System.out.println("\n -> On repare la voiture, on la fait avancer et on l'affiche \n");
        e1.seReparer(1200);
        e1.avancer(200);
        e1.seCouper();
        e1.affichage();
    }

    public void TestFonctionnalitesEngin()
    {
        Engin e1 = new Voiture();

        System.out.println("\n Test des getteurs : ");
        System.out.println(  " -------------------\n");

        System.out.println(" - Vitesse engin : " + e1.getVitesse());
        System.out.println(" - Capacite reservoir : " + e1.getTailleResservoir());
        System.out.println(" - Consommation : " + e1.getConsommation());
        System.out.println(" - Niveau essence : " + e1.getNiveauEssence());
        System.out.println(" - Prochaine reparation : " + e1.getProchaineReparation());
        System.out.println(" - Compteur kilometres : " + e1.getCompteurKilometres());
        System.out.println(" - Couleur engin : " + e1.getCouleur());
        System.out.println(" - Marque engin : " + e1.getMarque());
        System.out.println(" - Nom engin : " + e1.getNom());
        System.out.println(" - Est en marche : " + e1.getEstEnMarche());
        System.out.println(" - Est en panne : " + e1.getEstEnPanne() + "\n");

        System.out.println("\n Test des setteurs : ");
        System.out.println(" -------------------\n\n");

        System.out.println(" ---> MAJ de la vitesse à 118712");
        e1.setVitesse(1187112);
        System.out.println(" - Nouvelle vitesse : " + e1.getVitesse() + "\n");

        // Inutile car capacite reservoir est un final....
        // System.out.println(" ---> MAJ de la capacite reservoir à 1000");
        // e1.setCapaciteReservoir(1000);
        // System.out.println( " - Nouvelle capacite reservoir : " + e1.getTailleResservoir() + "\n");

        System.out.println(" ---> On fait le plein du reservoir");
        e1.remplirResservoir(40000);
        System.out.println( " - Nouveau niveau essence : " + e1.getNiveauEssence() + "\n");

        System.out.println(" ---> MAJ du compteur de 500");
        e1.augmenterCompteur(500);
        System.out.println( " - Nouveau compteur : " + e1.getCompteurKilometres() + "\n");

        System.out.println(" ---> MAJ de la prochaine reparation + 400");
        e1.setProchaineReparation(400);
        System.out.println( " - Nouvelle prochaine rep. : " + e1.getProchaineReparation() + "\n");
    
        System.out.println(" ---> MAJ de la couleur en azur ");
        e1.peindre("Azur");
        System.out.println( " - Nouvelle couleur : " + e1.getCouleur() + "\n");

        System.out.println(" ---> MAJ du nom en kiki");
        e1.renommer("Kiki");
        System.out.println( " - Nouveau Nom : " + e1.getNom() + "\n");

        System.out.println(" ---> MAJ marche et cassé en true pour les 2");
        e1.seDemarrer(30);
        e1.seCasser();
        System.out.println( " - Nouveaux démarrés et cassés :  " + e1.getEstEnMarche() + " ; " + e1.getEstEnPanne() + "\n");
    }

    public void TestDemarrageEtEtat()
    {
        Voiture v1 = new Voiture();
        v1.renommer("Natacha");
        System.out.println("\n--> Affichage de la voiture instanciée et renommée\n");
        v1.affichage();

        System.out.println("\n ---> On casse la voiture\n");
        v1.seCasser();
        v1.affichage();

        System.out.println("\n --> On essaye de faire avancer la voiture cassée\n");
        v1.avancer(30);

        System.out.println("\n --> On répare la voiture et on la fait avancée.\n");
        v1.seReparer();
        v1.avancer(30);
        v1.affichage();

        System.out.println("\n --> On éteint la voiture 2 fois à la suite.\n");
        v1.seCouper();
        v1.seCouper();
        v1.affichage();

        System.out.println("\n --> On allume la voiture, puis on la fait se casser 2 fois.\n");
        v1.avancer(20);
        v1.seCasser();
        v1.seCasser();
        v1.affichage();

        System.out.println("\n --> On réparer la voiture, on la casse 2 fois et on la répare 2 fois.\n");
        v1.seReparer();
        v1.seCasser();
        v1.seCasser();
        v1.seReparer();
        v1.seReparer();
        v1.affichage();
    }

    public Test()
    {
       // System.out.println("-> Instanciation de la classe de tests...\n");
    }
}
