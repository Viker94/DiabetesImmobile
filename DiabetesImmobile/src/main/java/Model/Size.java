package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//  Byt tymczasowy
//  Klasa stworzona do dekodowania liczby pacjentów przypisanych do pielęgniarki/liczby
//  pielęgniarek przypisanych do pacjenta z JSONa, pewnie da sie to prościej zrobić

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    private int size;
}
