package groupe3.example.santekunafoniapp.services.serviceImplementation;



import groupe3.example.santekunafoniapp.DTO.TraitementDTO;

import groupe3.example.santekunafoniapp.Entity.*;

import groupe3.example.santekunafoniapp.Repository.*;


import groupe3.example.santekunafoniapp.services.serviceInterface.TraitementServiceInterface;


import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;



@Service
public class TraitementServiceImplementation
        implements TraitementServiceInterface {



    private final TraitementRepository traitementRepository;

    private final MaladieRepository maladieRepository;

    private final PatientRepository patientRepository;

    private final AgentSanteRepository agentSanteRepository;




    public TraitementServiceImplementation(
            TraitementRepository traitementRepository,
            MaladieRepository maladieRepository,
            PatientRepository patientRepository,
            AgentSanteRepository agentSanteRepository
    ){

        this.traitementRepository = traitementRepository;
        this.maladieRepository = maladieRepository;
        this.patientRepository = patientRepository;
        this.agentSanteRepository = agentSanteRepository;

    }





    @Override
    public Traitement ajouterTraitement(TraitementDTO dto){
        Traitement traitement = new Traitement();
        return remplirTraitement(traitement,dto);

    }

    @Override
    public List<Traitement> getAllTraitements(){

        return traitementRepository.findAll();

    }

    @Override
    public Optional<Traitement> getTraitementById(Long id){

        return traitementRepository.findById(id);

    }

    @Override
    public Traitement modifierTraitement(Long id,TraitementDTO dto){


        Traitement traitement =
                traitementRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException("Traitement introuvable")
                        );


        return remplirTraitement(traitement,dto);

    }

    @Override
    public void supprimerTraitement(Long id){

        traitementRepository.deleteById(id);

    }

    private Traitement remplirTraitement(
            Traitement traitement,
            TraitementDTO dto
    ){


        traitement.setNomTraitement(dto.getNomTraitement());

        traitement.setDatedebut(dto.getDatedebut());

        traitement.setDatefin(dto.getDatefin());

        traitement.setDescription(dto.getDescription());



        traitement.setMaladie(

                maladieRepository.findById(dto.getIdMaladie())

                        .orElseThrow(
                                () -> new RuntimeException("Maladie introuvable")
                        )

        );



        traitement.setPatient(

                patientRepository.findById(dto.getIdPatient())

                        .orElseThrow(
                                () -> new RuntimeException("Patient introuvable")
                        )

        );

        traitement.setAgentSante(

                agentSanteRepository.findById(dto.getIdAgentSante())

                        .orElseThrow(
                                () -> new RuntimeException("Agent santé introuvable")
                        )

        );



        return traitementRepository.save(traitement);

    }

}