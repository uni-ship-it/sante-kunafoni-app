package groupe3.example.santekunafoniapp.security;

import groupe3.example.santekunafoniapp.Entity.Utilisateur;
import groupe3.example.santekunafoniapp.Repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository repository;

    @Override
    public UserDetails loadUserByUsername(String tel) throws UsernameNotFoundException {

        Utilisateur user = repository.findByTel(tel)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        return new CustomUserDetails(user);
    }
}