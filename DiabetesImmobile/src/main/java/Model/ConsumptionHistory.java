package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionHistory {
    private String data;
    private String produkt;
    private int ilosc;
    private String deltaPotasu;
    private String deltaSodu;
    private String deltaWody;
}
