package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumption {

    private Long id;
    private Product product;
    private Date date;
    private Integer amount;
}
