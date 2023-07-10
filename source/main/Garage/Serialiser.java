package Garage;
import Garage.Vehicules.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Serialiser implements IDataManager {

    private BufferedReader reader;
    private BufferedWriter writer;
    private FileReader freader;
    private FileWriter fwriter;

    private BufferedReader reader2;
    private BufferedWriter writer2;
    private FileReader freader2;
    private FileWriter fwriter2;

    private String cheminFichierVoitures;
    private String cheminFichierAnnuaire;
    File fichierVoitures;
    File fichierAnnuaire;

    @Override
    public void enregistrementAnnuaire(Map<String, Integer> m) {
        int i = 1;
        for (Map.Entry<String, Integer> paire : m.entrySet()) {
            try {
                writer2.write(paire.getKey());
                writer2.newLine();
                writer2.write(String.valueOf(paire.getValue()));
                writer2.newLine();
                System.out.println("[INFOS] : Enregistrement d'une personne de l'annuaire ("+i+")");
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<String, Integer> chargementAnnuaire() {

        Map<String, Integer> m = new HashMap<String, Integer>();
        String line;
        int valeur = 0;
        String cle;
        int i = 1;

        try {
            while ((line = reader2.readLine()) != null) {
                cle = line;
                line = reader2.readLine();
                valeur = Integer.valueOf(valeur);
                System.out.println("[INFOS]  : Nouvelle paire enregistrée(" + i + ")");
                i ++;
                m.put(cle, valeur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public void enregistrerVoitures(List<Voiture> l) {

        int i = 1;
        for (Voiture v : l) {
            try {
                writer.write(v.getNom());
                writer.newLine();
                writer.write(v.getCouleur());
                writer.newLine();
                writer.write(v.getMarque().toString());
                writer.newLine();
                writer.write(Boolean.toString(v.getEstEnMarche()));
                writer.newLine();
                writer.write(Boolean.toString(v.getEstEnPanne()));
                writer.newLine();
                writer.write(String.valueOf(v.getCompteurKilometres()));
                writer.newLine();
                writer.write(String.valueOf(v.getProchaineReparation()));
                writer.newLine();
                writer.write(String.valueOf(v.getTailleResservoir()));
                writer.newLine();
                writer.write(String.valueOf(v.getConsommation()));
                writer.newLine();
                writer.write(String.valueOf(v.getVitesse()));
                writer.newLine();
                writer.write(String.valueOf(v.getNiveauEssence()));
                writer.newLine();
                writer.write(String.valueOf(v.getNbRoues()));
                writer.newLine();
                writer.write(String.valueOf(v.getTailleCoffre()));
                writer.newLine();
                writer.write(v.getSon());
                writer.newLine();
                writer.write(v.getTypeEngin().toString());
                writer.newLine();
                System.out.println("[INFOS] : Voiture enregistrée("+ i + ").");
                i++;

            } catch (IOException e) {
                System.out.println("[Erreur] : Echec de l'enregistrement des voitures.");
                e.printStackTrace();
            }

        }
    }

    @Override
    public List<Voiture> chargementVoitures() {
        List<Voiture> l = new ArrayList<Voiture>();
        String line;
        int i = 1;

        try {
            while ((line = reader.readLine())!= null) {
                Voiture v = new Voiture();
                v.renommer(line);

                line = reader.readLine();
                v.peindre(line);

                line = reader.readLine();
                v.setMarque(Marques.valueOf(line));

                line = reader.readLine();
                v.setMarche(Boolean.valueOf(line));

                line = reader.readLine();
                v.setPanne(Boolean.valueOf(line));

                line = reader.readLine();
                v.setCompteur(Integer.valueOf(line));

                line = reader.readLine();
                v.setReparation(Integer.valueOf(line));

                line = reader.readLine();
                v.setTailleReservoir(Integer.valueOf(line));

                line = reader.readLine();
                v.setConsommation(Integer.valueOf(line));

                line = reader.readLine();
                v.setVitesse(Integer.valueOf(line));

                line = reader.readLine();
                v.setNiveauEssence(Integer.valueOf(line));

                line = reader.readLine();
                v.setNbRoues(Integer.valueOf(line));

                line = reader.readLine();
                v.setTailleCoffre(Integer.valueOf(line));

                line = reader.readLine();
                v.setSon(line);

                line = reader.readLine();
                v.setTypeEngin(Engins.valueOf(line));

                System.out.println("[INFOS] : Voiture chargée(" + i + ")");
                l.add(v);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return l;
    }

    @Override
    public void closeStreams() {
        try {
            if (writer != null) {
                writer.close();
            }
            if (fwriter != null) {
                fwriter.close();
            }

            if (writer2 != null) {
                writer2.close();
            }
            if (fwriter2 != null) {
                fwriter2.close();
            }
        } catch (IOException e) {
            // Gérer les exceptions appropriées
            System.out.println("[Erreur] : Soucis dans la fermeture des flux d'écriture.");
        }
    }
    
    public Serialiser(String cheminVoitures, String cheminAnnuaire) {

        this.cheminFichierVoitures = cheminVoitures;
        this.cheminFichierAnnuaire = cheminAnnuaire;

        fichierVoitures = new File(this.cheminFichierVoitures);
        fichierAnnuaire = new File(this.cheminFichierAnnuaire);
        
        try {
            if(!fichierVoitures.exists()) {
                fichierVoitures.createNewFile();
            }

            if (!fichierAnnuaire.exists()) {
                fichierAnnuaire.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("[Erreur] : Soucis dans la création d'un fichier de sauvegarde.");
        }

        try {
            this.freader = new FileReader(this.cheminFichierVoitures);
            this.fwriter = new FileWriter(this.cheminFichierVoitures, StandardCharsets.UTF_8);
            this.freader2 = new FileReader(this.cheminFichierAnnuaire);
            this.fwriter2 = new FileWriter(this.cheminFichierAnnuaire, StandardCharsets.UTF_8);

        } catch(FileNotFoundException f) {
            System.out.println("[Erreur] : Impossible d'accéder à un fichier renseigné.");
        } catch( IOException e) {
            System.out.println("[Erreur] : Impossible d'accéder à un fichier renseigné.");
        }

        this.reader = new BufferedReader(this.freader);
        this.writer = new BufferedWriter(this.fwriter);
        this.writer2 = new BufferedWriter(this.fwriter2);
        this.reader2 = new BufferedReader(this.freader2);
    }
    
}
