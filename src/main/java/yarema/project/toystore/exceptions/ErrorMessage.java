package yarema.project.toystore.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
}
