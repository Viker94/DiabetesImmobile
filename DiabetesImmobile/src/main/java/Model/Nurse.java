package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nurse {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Boolean admin;
    private List<User> patients;
}
