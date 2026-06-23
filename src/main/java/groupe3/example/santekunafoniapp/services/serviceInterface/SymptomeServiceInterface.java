package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.Entity.Symptome;

import java.util.List;

public interface SymptomeServiceInterface {
   List<Symptome>getAllSymptome();
   Symptome getSymptomeById(Long id);
   Symptome createSymptome(Symptome symptome);
   Symptome updateSymptomeById(Long id, Symptome symptome);
   void deleteSymptomeById(Long id);
}
