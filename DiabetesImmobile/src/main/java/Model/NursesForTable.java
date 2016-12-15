package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NursesForTable {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private int liczbaPacjentow;
}
