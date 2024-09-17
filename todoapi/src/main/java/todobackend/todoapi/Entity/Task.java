package todobackend.todoapi.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data  // Lombok annotation for getters, setters, etc.
@Table(name = "tasks") // Specify the table name explicitly
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming auto-increment
    private Long id; // Use Long for database compatibility
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt = LocalDateTime.now(); // Use LocalDateTime for timestamp
    private LocalDateTime updatedAt = LocalDateTime.now();
}