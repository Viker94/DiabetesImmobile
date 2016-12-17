package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private double potassium;
    private double water;
    private double sodium;
    private double limitPotassium;
    private double limitWater;
    private double limitSodium;
    private Date nextVisit;
    private Login login;
    private List<Consumption> consumed;
}
