package Garage.Vehicules;


public abstract class Engin {
    
    // Vitesse du véhicule 
    protected int vitesse;

    // Taille du réservoir en essence 
    protected final int capaciteReservoir;

    // Donne le nombre d'essence consommé pour 1 kilomètre ( 1ue de distance)
    protected final int consommation;

    // Donne le niveau actuel d'essence stocké dans le réservoir 
    protected int niveauEssence;

    // Donne le nombre de kilomètres avant que l'engin de tombe en panne 
    protected int prochaineReparation;

    // Donne le nombre total de kilomètres faits par l'engin
    protected int compteurKilomètres;

    // Couleur de l'engin
    protected String couleur;

    // Marque de l'engin
    protected final Marques marque;

    // Nom de la voiture 
    protected String nom;

    // Indique si l'engin est en action
    protected boolean estEnMarche;

    // Indique si l'engin est en capacité de fonctionner 
    protected boolean estEnPanne;

    
    public int getVitesse(){
        return this.vitesse;
    }

    public int getConsommation()
    {
        return this.consommation;
    }

    public String getNom(){
        return this.nom;
    }

    public int getTailleResservoir(){
        return this.capaciteReservoir;
    }

    public int getCompteurKilometres(){
        return this.compteurKilomètres;
    } 

    public int getProchaineReparation(){
        return this.prochaineReparation;
    }

    public int getNiveauEssence(){
        return this.niveauEssence;
    }
    public String getCouleur(){
        return this.couleur;
    }

    public Marques getMarque(){
        return this.marque;
    }

    public void setVitesse(int nv_v)
    {
        this.vitesse = nv_v;
    }

        public void renommer(String nvNom){
        this.nom = nvNom;
    }

    public void consommer(int km)
    {
        this.niveauEssence = this.niveauEssence - km;
        if(this.niveauEssence <= 0){
            this.niveauEssence = 0;
        }
    }

    public boolean verifEssence()
    {
        if (this.niveauEssence == 0)
        {
            return false;
        }
        return true;
    }

    public boolean verifEssence(int km, int conso)
    {
        if ( (this.niveauEssence - (km*conso)) < 0)
        {
            return false;
        }

        return true;
    }

    public void remplirResservoir(int EssenceEnPLus){
        if (EssenceEnPLus > capaciteReservoir)
        {
            EssenceEnPLus = capaciteReservoir;
        }

        this.niveauEssence = this.niveauEssence + EssenceEnPLus;
    }

    public void faireLePlein()
    {
        this.niveauEssence = this.capaciteReservoir;
    }


    public void peindre(String NouvellesCouleur){
        this.couleur = NouvellesCouleur;
    }

    // L'attribut marque est lui aussi final 
    // public void setMarque(Marques nv_m)
    // {
    //     this.marque = nv_m;
    // }

    public void augmenterCompteur(int km)
    {
        this.compteurKilomètres = this.compteurKilomètres + km;
    }

    public void setProchaineReparation(int km)
    {
        this.prochaineReparation = this.compteurKilomètres + km;
    }

    public void VerifCompteur()
    {
        if (this.compteurKilomètres >= this.prochaineReparation)
        {
            this.seCasser();
        }
    }
    

    // Inutile en fait puisque c'est un final 
    // public void setCapaciteReservoir(int nv_c)
    // {
    //     this.capaciteReservoir = nv_c;
    // }

    public boolean getEstEnPanne(){
        return this.estEnPanne;
    }

    public boolean getEstEnMarche(){
        return this.estEnMarche;
    }

    public void seCasser(){

        if (this.estEnPanne == true)
        {
            System.out.println(" [INFOS] : " + this.nom + " est déjà cassée.");
            return;
        }

        if (this.estEnMarche == true)
        {
            System.out.println(" [INFOS] : " + this.nom + " s'arrete car il y a un soucis technique.");
            this.estEnMarche = false;
            this.estEnPanne = true;
            return;
        }

        System.out.println(" [INFOS] : " + this.nom + " se casse.");
        this.estEnPanne = true;
    }

    public void seReparer(){

        if (this.estEnPanne == false)
        {
            System.out.println(" [INFOS] :  Il n'y a rien a réparé chez " + this.nom + ".");
            return;
        }
        System.out.println(" [INFOS] : " + this.nom + " est réparée.");
        this.prochaineReparation = this.compteurKilomètres + 400;
        this.estEnPanne = false;
    }

    public void seReparer(int prochainePanne)
    {
        if (this.estEnPanne == false)
        {
            System.out.println(" [INFOS] :  Il n'y a rien a réparé chez " + this.nom + ".");
            return;
        }
        System.out.println(" [INFOS] : " + this.nom + " est réparée.");
        this.estEnPanne = false;
        this.prochaineReparation = this.compteurKilomètres + prochainePanne;
    }

    public boolean seDemarrer(int km){
        this.VerifCompteur();
        
        if (this.verifEssence() == false)
        {
            System.out.println(" [INFOS] : " + this.nom + " a le reservoir vide.");
            return false;
        }

        if (this.verifEssence(km, this.getConsommation()) == false)
        {
            
            int val = this.niveauEssence - (km * this.getConsommation());
            if (val < 0 ) {
                val = val * (-1);
            }

            System.out.println(" [INFOS] : " + this.getNom() + " n'a pas assez d'essence pour faire " + km + "km, il lui manque " + val + "L d'essence.");
            return false;
        } 

        if (this.estEnPanne == true)
        {
            System.out.println(" [INFOS] : " + this.nom + " ne peux pas avancer car elle est cassée.");
            return false;
        }

        if (this.estEnMarche == true)
        {
            System.out.println(" [INFOS] : " + this.nom + " est déjà en marche.");
            return true;
        }
        this.estEnMarche = true;
        System.out.println(" [INFOS] : " + this.nom + " démarre.");
        return true;
    }

    public void seCouper(){
        if (this.estEnMarche == false)
        {
            System.out.println(" [INFOS] : " + this.nom + " est déjà coupée.\n");
            return;
        }
        this.estEnMarche = false;
        System.out.println(" [INFOS] : " + this.nom + " se coupe.\n");
    }

    public abstract void avancer(int km);

    public void affichage(){
        System.out.println("\t\t - Vitesse : " + this.vitesse);
        System.out.println("\t\t - Consommation : " + this.consommation);
        System.out.println("\t\t - Capacite reservoir : " + this.capaciteReservoir);
        System.out.println("\t\t - Essence restante : " + this.niveauEssence);
        System.out.println("\t\t - Compteur kilometres : " + this.compteurKilomètres);
        System.out.println("\t\t - Prochaine reparation dans : " + this.prochaineReparation);
        System.out.print("\t\t - Est en marche : ");

        if(this.estEnMarche == true)
        {
            System.out.println("oui");
        }
        else
        {
            System.out.println("non");
        }

        System.out.print("\t\t - Est en panne : ");

        if(this.estEnPanne == true)
        {
            System.out.println("oui");
        }
        else
        {
            System.out.println("non");
        }
    }

    public Engin()
    {
        this.estEnMarche = false;
        this.estEnPanne = false;

        this.capaciteReservoir = 300;
        this.niveauEssence = 0;
        this.consommation = 2;
        this.prochaineReparation = 400;
        this.vitesse = 400;
        this.compteurKilomètres = 0;

        this.marque = Marques.LandRover;
        this.couleur = "Bleu";
        this.nom = "Engin sans nom";
    }

    public Engin(int reservoir, int conso, int vitesse, Marques m, String couleur, String nom) {

        this.estEnMarche = false;
        this.estEnPanne = false;

        this.capaciteReservoir = reservoir;
        this.niveauEssence = 0;
        this.consommation = conso;
        this.prochaineReparation = 400;
        this.vitesse = vitesse;
        this.compteurKilomètres = 0;

        this.marque = m;
        this.couleur = couleur;
        this.nom = nom;
    }
}
