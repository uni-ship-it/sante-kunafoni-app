package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.StatistiqueMaladieDTOs.*;
import groupe3.example.santekunafoniapp.services.serviceImplementation.ResumePatientService;
import groupe3.example.santekunafoniapp.services.serviceImplementation.StatistiqueMaladieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistiques")
@CrossOrigin(origins = "http://localhost:4200")
public class StatistiqueMaladieController {

    private final StatistiqueMaladieService statistiqueMaladieService;
    private final ResumePatientService resumePatientService;

    public StatistiqueMaladieController(StatistiqueMaladieService statistiqueMaladieService,
                                        ResumePatientService resumePatientService) {
        this.statistiqueMaladieService = statistiqueMaladieService;
        this.resumePatientService = resumePatientService;
    }

    @Operation(summary = "Cas de maladies par localité",
            description = "Alimente la carte du Mali et le graphe du dashboard patient.")
    @GetMapping("/maladies/localites")
    public List<MaladieLocaliteDTO> maladiesParLocalite() {
        return statistiqueMaladieService.maladiesParLocalite();
    }

    @Operation(summary = "Résumé d'un patient",
            description = "Compteurs maladies/symptômes et alerte de sa localité.")
    @GetMapping("/patients/{id}/resume")
    public ResumePatientDTO resumePatient(@PathVariable Long id) {
        return resumePatientService.resume(id);
    }
}