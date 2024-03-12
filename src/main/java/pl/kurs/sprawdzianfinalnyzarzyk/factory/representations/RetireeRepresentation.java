package pl.kurs.sprawdzianfinalnyzarzyk.factory.representations;

import org.springframework.stereotype.Service;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Retiree;
import pl.kurs.sprawdzianfinalnyzarzyk.models.representation.PeopleCsvRepresentation;

@Service
public class RetireeRepresentation implements PersonRepresentation {
    @Override
    public String getType() {
        return "retiree";
    }

    @Override
    public Person create(PeopleCsvRepresentation representation) {
        var retiree = new Retiree();
        retiree.setFirstName(representation.getFirstName());
        retiree.setLastName(representation.getLastName());
        retiree.setPesel(representation.getPesel());
        retiree.setGrowth(representation.getGrowth());
        retiree.setWeight(representation.getWeight());
        retiree.setEmail(representation.getEmail());
        retiree.setAmountOfPension(representation.getAmountOfPension());
        retiree.setYearsWorked(representation.getYearsWorked());
        return retiree;
    }
}
