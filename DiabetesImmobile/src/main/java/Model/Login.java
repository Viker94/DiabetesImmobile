package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Marcin on 25.11.2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    private Long id;
    private String login;
    private String passwd;
}
