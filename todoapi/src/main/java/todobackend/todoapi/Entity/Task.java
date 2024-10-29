package todobackend.todoapi.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column
    private Long id; 
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private boolean completed;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime createdAt = LocalDateTime.now(); 
    @Column(columnDefinition = "DATETIME")// Use LocalDateTime for timestamp
    private LocalDateTime updatedAt = LocalDateTime.now();
}