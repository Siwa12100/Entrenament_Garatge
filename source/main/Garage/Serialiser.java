package Garage;
import Garage.Vehicules.*;
import java.io.*;
import java.util.*;

public class Serialiser implements IDataManager {

    private BufferedReader reader;
    private BufferedWriter writer;
    private FileReader freader;
    private FileWriter fwriter;

    private String cheminFichier;
    File fichier;


    public void enregistrementVoitures(List<Voiture> l) {

        for (Voiture v : l) {
            try {
                writer.write(v.getNom());
                writer.write(v.getCouleur());
                writer.write(v.getMarque().toString());
                writer.write(Boolean.toString(v.getEstEnMarche()));
                writer.write(Boolean.toString(v.getEstEnPanne()));
                writer.write(v.getCompteurKilometres());
                writer.write(v.getProchaineReparation());
                writer.write(v.getTailleResservoir());
                writer.write(v.getConsommation());
                writer.write(v.getVitesse());
                writer.write(v.getNiveauEssence());
                writer.write(v.getNbRoues());
                writer.write(v.getTailleCoffre());
                writer.write(v.getSon());
                writer.write(v.getTypeEngin().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void closeStreams() {
        try {
            if (writer != null) {
                writer.close();
            }
            if (fwriter != null) {
                fwriter.close();
            }
        } catch (IOException e) {
            // Gérer les exceptions appropriées
            System.out.println("[Erreur] : Soucis dans la fermeture des flux d'écriture.");
        }
    }
    

    public Serialiser(String chemin) {

        this.cheminFichier = chemin;
        fichier = new File(this.cheminFichier);
        
        try {
            if(!fichier.exists()) {
                fichier.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("[Erreur] : Soucis dans la création du fichier de sauvegarde.");
        }

        try {
            this.freader = new FileReader(this.cheminFichier);
            this.fwriter = new FileWriter(this.cheminFichier);
            
        } catch(FileNotFoundException f) {
            System.out.println("[Erreur] : Impossible d'accéder au fichier renseigné.");
        } catch( IOException e) {
            System.out.println("[Erreur] : Impossible d'accéder au fichier renseigné.");
        }

        this.reader = new BufferedReader(this.freader);
        this.writer = new BufferedWriter(this.fwriter);
    }
    
}
