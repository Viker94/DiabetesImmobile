package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//Klasa zbierająca dane o Userach do wypełnienia tabel

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersForTable {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String haslo;
    private double potassium;
    private double water;
    private double sodium;
    private double limitPotassium;
    private double limitWater;
    private double limitSodium;
    private Date nextVisit;
    private List<Consumption> consumed;
}
