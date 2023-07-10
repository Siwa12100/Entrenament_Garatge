package Garage;
import Garage.Vehicules.*;
import java.util.List;
import java.util.Map;

public interface IDataManager {
    
    public void enregistrerVoitures(List<Voiture> l);
    public List<Voiture> chargementVehicules();

    public void enregistrementAnnuaire(Map<String, Integer> m);
    public Map<String, Integer> chargementAnnuaire();
}
