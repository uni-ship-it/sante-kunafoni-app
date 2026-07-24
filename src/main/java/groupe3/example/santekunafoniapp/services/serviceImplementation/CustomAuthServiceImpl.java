package groupe3.example.santekunafoniapp.services.serviceImplementation;


import groupe3.example.santekunafoniapp.DTO.CustomLoginResponse;
import groupe3.example.santekunafoniapp.Entity.AgentSante;
import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.Entity.Administrateur;
import groupe3.example.santekunafoniapp.Repository.AdministrateurRepository;
import groupe3.example.santekunafoniapp.Repository.AgentSanteRepository;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.CustomAuthService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomAuthServiceImpl implements CustomAuthService {


    private final PatientRepository patientRepository;
    private final AgentSanteRepository agentSanteRepository;
    private final AdministrateurRepository administrateurRepository;
    private final PasswordEncoder passwordEncoder;


    public CustomAuthServiceImpl(
            PatientRepository patientRepository,
            AgentSanteRepository agentSanteRepository,
            AdministrateurRepository administrateurRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.patientRepository = patientRepository;
        this.agentSanteRepository = agentSanteRepository;
        this.administrateurRepository = administrateurRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public CustomLoginResponse verifierAuthentification(
            String tel,
            String motpass
    ) {


        // =========================
        // Recherche patient
        // =========================

        Optional<Patient> patientOpt =
                patientRepository.findByTel(tel);


        if(patientOpt.isPresent()) {

            Patient patient = patientOpt.get();


            if(passwordEncoder.matches(
                    motpass,
                    patient.getMotpass()
            )) {


                return new CustomLoginResponse(
                        patient.getIdUtilisateur(),
                        patient.getTel(),
                        patient.getNom(),
                        "PATIENT"
                );

            }
        }



        // =========================
        // Recherche agent santé
        // =========================

        // =========================
// Recherche agent santé
// =========================

        Optional<AgentSante> agentOpt = agentSanteRepository.findByTel(tel);

        if(agentOpt.isPresent()) {
            AgentSante agent = agentOpt.get();

            // --- LIGNES DE TEST ---
            System.out.println("=== TEST CONNEXION AGENT ===");
            System.out.println("Téléphone cherché : " + tel);
            System.out.println("Mot de passe saisi (clair) : " + motpass);
            System.out.println("Hash récupéré en BDD : " + agent.getMotpass());

            boolean match = passwordEncoder.matches(motpass, agent.getMotpass());
            System.out.println("Résultat du match : " + match);
            // -----------------------

            if(match) {
                return new CustomLoginResponse(
                        agent.getIdUtilisateur(),
                        agent.getTel(),
                        agent.getNom(),
                        "AGENT_SANTE"
                );
            }
        }



        // =========================
        // Recherche administrateur
        // =========================

        Optional<Administrateur> adminOpt =
                administrateurRepository.findByTel(tel);


        if(adminOpt.isPresent()) {

            Administrateur admin = adminOpt.get();


            if(passwordEncoder.matches(
                    motpass,
                    admin.getMotpass()
            )) {


                return new CustomLoginResponse(
                        admin.getIdUtilisateur(),
                        admin.getTel(),
                        admin.getNom(),
                        "ADMIN"
                );

            }
        }



        throw new RuntimeException(
                "Téléphone ou mot de passe incorrect"
        );

    }

}