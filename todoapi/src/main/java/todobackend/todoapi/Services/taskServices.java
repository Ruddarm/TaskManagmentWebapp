package todobackend.todoapi.Services;

// import org.hibernate.mapping.List;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import todobackend.todoapi.Entity.Task;
import todobackend.todoapi.Repo.taskRepo;

@Service
public class taskServices {

    @Autowired
    private taskRepo repo;

    public Task createTask(Task task) {
        return repo.save(task);
    }

    public List<Task> getAllTask() {
        return repo.findAll();
    }

    public void deleteTask(Task task) {
        repo.delete(task);
    }

    public Task taskById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found with id " + id));
    }

    public Task updatedTask(Long id, Task updatedTask) {
        return repo.findById(id).map(existingTask -> {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            existingTask.setUpdatedAt(updatedTask.getUpdatedAt());
            return repo.save(existingTask);
        }).orElseThrow(() -> new EntityNotFoundException("Task not found with id : " + id));
    }

    public List<Task> getTaskByStatus(boolean completed) {
        return repo.findByCompleted(completed);
    }
}
