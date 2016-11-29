package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by fishe on 29.11.2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersForTable {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String haslo;
}
