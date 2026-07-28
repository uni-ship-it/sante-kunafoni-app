package groupe3.example.santekunafoniapp.config;

import groupe3.example.santekunafoniapp.Entity.Administrateur;
import groupe3.example.santekunafoniapp.Entity.Role;
import groupe3.example.santekunafoniapp.Repository.AdministrateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(AdministrateurRepository adminRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Vérifie si un admin existe déjà par son numéro de téléphone ou email
            String telAdminDefaut = "00000000"; // Mettez le numéro de votre choix

            if (adminRepository.findByTel(telAdminDefaut).isEmpty()) {
                Administrateur admin = new Administrateur();
                admin.setNom("Admin");
                admin.setPrenom("Principal");
                admin.setTel(telAdminDefaut);
                // Le mot de passe est crypté avant d'être sauvegardé en base
                admin.setMotpass(passwordEncoder.encode("admin123!"));
                admin.setRole(Role.ADMIN);;

                adminRepository.save(admin);
                System.out.println(" Administrateur par défaut créé avec succès ! ");
            }
        };
    }
}