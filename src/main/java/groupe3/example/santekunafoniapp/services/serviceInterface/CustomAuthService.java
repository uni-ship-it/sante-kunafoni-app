package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.DTO.CustomLoginResponse;

public interface CustomAuthService {
    CustomLoginResponse verifierAuthentification(
            String telephone,
            String motpass
    );
}
