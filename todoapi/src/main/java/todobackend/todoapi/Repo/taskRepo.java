package todobackend.todoapi.Repo;

import java.util.List;
import todobackend.todoapi.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface taskRepo extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    public List<Task> findByCompleted(@Param("completed") boolean completed);
    
}
