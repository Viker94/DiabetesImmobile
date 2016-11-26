package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * Created by Marcin on 26.11.2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumption {

    private Long id;
    private Product product;
    private Date date;
}
