package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

        @ManyToOne
        @JoinColumn(name = "utilisateur_id")
        private groupe3.example.santekunafoniapp.Entity.Utilisateur utilisateur;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotif")
    private Long id;

    @Column(nullable = false)
    private String titre;

    private LocalDateTime datePublication;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;
}