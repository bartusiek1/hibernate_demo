package pl.sda.arppl4.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data                       // gettery i settery

                            // klasa mapowana jest na tabelę
                            // każda instancja w tej klasie mapowana jest na rekord w bazie
@Entity                     // wymaga dorzucenia tego @Id,  encja = rekord = instancja = wpis w bazie - tworzy tabelę z pól w klasie
@NoArgsConstructor          // konstruktor bezparametrowy
@AllArgsConstructor         // konstruktor z pozostałymi parametrami
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // AUTO_INCREMENT
    private Long id;

    private String name;
    private String surname;
    private String indexNumber; // niegdy nie wolno nam zastosować samego słowa "index"
    private LocalDate birthDate;
}
