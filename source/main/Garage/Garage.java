package Garage;
import java.io.IOException;
import java.util.Scanner;

//import javax.swing.plaf.synth.SynthEditorPaneUI;

public class Garage {
    
    private static String nomGarage;
    private static int os;
    //IDataManager dtmanager;

    public static void main(String args[])
    {
        ClearTerminal();
        System.out.println("=======================\n");
        System.out.println("Entrée dans le Garage : \n");
        System.out.println("=======================\n\n");

        if (args.length == 1) {
            if (args[0].equals("linux")) {
                os = 0;
            }
            else
            {
                os = 1;
            }
        }

        if (args.length == 2) {
            nomGarage = args[1];
            if (args[0].equals("linux")) {
                os = 0;
            }
            else
            {
                os = 1;
            }
        }

        System.out.println("Nom du garage : " + nomGarage + "\n\n");
        Test t1 = new Test();
        t1.TestPrincipal();

        // Annuaire a1 = new Annuaire();
        // a1.menuAnnuaire();


        System.out.println("\n");
    }

    public static void effacement() {
        for(int i = 0 ; i < 100; i++) {
            System.out.println();
        }
    }

    public static void ClearTerminal() {
            
            switch(os){

                case 0 : // Linux (bash)
                    try {
                            ProcessBuilder processBuilder = new ProcessBuilder("clear");
                            processBuilder.inheritIO();
                            Process process = processBuilder.start();
                            int exitCode = process.waitFor();
                            if (exitCode != 0){
                                System.out.println("Une erreur s'est produite lors de l'exécution de la commande clear.");
                            }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                
                case 1 : // Pour Windows mais pas encore fonctionnel...
                    //System.out.println(" -> Pas encore de version pour Windows...\n\n");
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        public static void Pause()
        {
            Scanner s = new Scanner(System.in);
            String temp;

            System.out.println("\n\n\n\n -> Appuyer sur une touche pour continuer....");
            temp = s.nextLine();
        }

    public Garage()
    {
        //dtmanager = new Serialiser("./sauvegarde/donnees.txt", "./sauvegarde/annuaire.txt");
        os = 0;
    }
}

