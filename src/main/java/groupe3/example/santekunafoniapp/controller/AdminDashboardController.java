package groupe3.example.santekunafoniapp.controller;


import groupe3.example.santekunafoniapp.DTO.DashboardStatsDTO;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdminDashboardServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/administrateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminDashboardController {
    private final AdminDashboardServiceInterface adminDashboardServiceInterface;

    public AdminDashboardController(AdminDashboardServiceInterface adminDashboardServiceInterface) {
        this.adminDashboardServiceInterface = adminDashboardServiceInterface;
    }
    @GetMapping("/dashboard-stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats(){
       DashboardStatsDTO stats = adminDashboardServiceInterface.getDashboardStatsDTO();
       return ResponseEntity.ok(stats);
    }


}
