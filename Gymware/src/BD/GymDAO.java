package BD;

import model.Gimnasio;
import java.util.List;

public interface GymDAO {
    
    List<Gimnasio> getAllGyms();
    
    Gimnasio getGymById(int id);
    
    void addGym(Gimnasio gym);
    
    void updateGym(Gimnasio gym);
    
    void deleteGym(int id);
}