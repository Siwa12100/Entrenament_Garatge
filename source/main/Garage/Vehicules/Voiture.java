package Garage.Vehicules;

public class Voiture extends Engin{

    public void avancer(int km)
    {
        if (this.seDemarrer(km))
        { 
            System.out.println(" [INFOS] : La voiture " + this.nom + " roule sur la route pendant " + km + " km et consomme " + this.consommation * km  +"L d'essence.");
            this.niveauEssence = this.niveauEssence - this.consommation * km;
            this.augmenterCompteur(km);
        }
    }

    private  int nbRoues;
    private int tailleCoffre;
    private String son;
    public Engins typeEngin;

    public int getNbRoues(){
        return this.nbRoues;
    }

    public void setNbRoues(int i) {
        this.nbRoues = i;
    }

    public void setTailleCoffre(int i) {
        this.tailleCoffre = i;
    }

    public void setTypeEngin(Engins e) {
        this.typeEngin = e;
    }

    public int getTailleCoffre(){
        return this.tailleCoffre;
    }

    public Engins getTypeEngin() {
        return this.typeEngin;
    }

    public String getSon()
    {
        return this.son;
    }

    public void setSon(String nvSon)
    {
        if (nvSon != null)
        {
            this.son = nvSon;
        }
    }

    public void klaxonner(int nbfois)
    {
        if (nbfois <= 0)
        {
            return;
        }

        for (int i = 0; i < nbfois; i++)
        {
            System.out.println(" ~~~ " + son + " ~~~ ");
        }
        System.out.println("\n");
    }

    public void affichage()
    {
        System.out.println(" --- Voiture : " + this.nom + " --- ");
        System.out.println("\t\t - Taille du coffre : " + this.getTailleCoffre());
        System.out.println("\t\t - Klaxon : " + this.getSon());
        super.affichage();
    }

    public Voiture()
    {
        this.tailleCoffre = 30;
        this.nbRoues = 4;
        this.son = "tuuuu";
        this.typeEngin = Engins.Voiture;
    }

    public Voiture(int coffre){
        this.tailleCoffre = coffre;
        this.nbRoues = 4;
        this.son = "tuuuu";
        this.typeEngin = Engins.Voiture;
    }

    public Voiture(int coffre, int reservoir, int conso, int vitesse, Marques m, String couleur, String nom)
    {
        super(reservoir, conso, vitesse, m, couleur, nom);
        this.tailleCoffre = coffre;
        this.nbRoues = 4;
        this.son = "tuuuu";
        this.typeEngin = Engins.Voiture;
    }

}
