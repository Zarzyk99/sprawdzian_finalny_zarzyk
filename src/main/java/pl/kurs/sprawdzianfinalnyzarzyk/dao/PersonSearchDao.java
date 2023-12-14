package pl.kurs.sprawdzianfinalnyzarzyk.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.sprawdzianfinalnyzarzyk.models.Person;
import pl.kurs.sprawdzianfinalnyzarzyk.models.request.PersonSearchRequest;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class PersonSearchDao implements IPersonSearchDao {
    private final EntityManager em;

    @Override
    public List<Person> findByCriteria(PersonSearchRequest request) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Person> root = criteriaQuery.from(Person.class);

        if (isNotNull(request.getDtype())) {
            predicates.add(criteriaBuilder.like(root.get("dtype"), "%" + request.getDtype() + "%"));
        }
        if (isNotNull(request.getFirstName())) {
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + request.getFirstName() + "%"));
        }
        if (isNotNull(request.getLastName())) {
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + request.getLastName() + "%"));
        }
        if (isNotNull(request.getPesel())) {
            predicates.add(criteriaBuilder.like(root.get("pesel"), "%" + request.getPesel() + "%"));
        }
        if (isNotNull(request.getMinGrowth()) && isNotNull(request.getMaxGrowth())) {
            predicates.add(criteriaBuilder.between(root.get("growth"), request.getMinGrowth(), request.getMaxGrowth()));
        }
        if (isNotNull(request.getMinWeight()) && isNotNull(request.getMaxWeight())) {
            predicates.add(criteriaBuilder.between(root.get("weight"), request.getMinWeight(), request.getMaxWeight()));
        }
        if (isNotNull(request.getEmail())) {
            predicates.add(criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%"));
        }
        if (isNotNull(request.getStartRangeOfEmploymentDate()) && isNotNull(request.getEndRangeOfEmploymentDate())
                && request.getStartRangeOfEmploymentDate().isBefore(request.getEndRangeOfEmploymentDate())) {
            predicates.add(criteriaBuilder.between(root.get("dateOfEmployment"),
                    request.getStartRangeOfEmploymentDate(), request.getEndRangeOfEmploymentDate()));
        }
        if (isNotNull(request.getCurrentPosition()) && !request.getCurrentPosition().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("currentPosition"), "%" +
                    request.getCurrentPosition() + "%"));
        }
        if (isNotNull(request.getMinSalary()) && isNotNull(request.getMaxSalary())) {
            predicates.add(criteriaBuilder.between(root.get("currentSalary"),
                    request.getMinSalary(), request.getMaxSalary()));
        }
        if (isNotNull(request.getNameOfUniversity())) {
            predicates.add(criteriaBuilder.like(root.get("nameOfUniversity"), "%" +
                    request.getNameOfUniversity() + "%"));
        }
        if (isNotNull(request.getMinYearOfStudy()) && isNotNull(request.getMaxYearOfStudy())) {
            predicates.add(criteriaBuilder.between(root.get("yearOfStudy"),
                    request.getMinYearOfStudy(), request.getMaxYearOfStudy()));
        }
        if (isNotNull(request.getFieldOfStudy())) {
            predicates.add(criteriaBuilder.like(root.get("fieldOfStudy"), "%" + request.getFieldOfStudy() + "%"));
        }
        if (isNotNull(request.getMinScholarship()) && isNotNull(request.getMaxScholarship())) {
            predicates.add(criteriaBuilder.between(root.get("amountOfScholarship"),
                    request.getMinScholarship(), request.getMaxScholarship()));
        }
        if (isNotNull(request.getMinPension()) && isNotNull(request.getMaxPension())) {
            predicates.add(criteriaBuilder.between(root.get("amountOfPension"),
                    request.getMinPension(), request.getMaxPension()));
        }
        if (isNotNull(request.getMinYearsWorked()) && isNotNull(request.getMaxYearsWorked())) {
            predicates.add(criteriaBuilder.between(root.get("yearsWorked"),
                    request.getMinYearsWorked(), request.getMaxYearsWorked()));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        return query.getResultList();



//        criteriaQuery.select(root).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
//
//        return em.createQuery(criteriaQuery).getResultList();

    }

    private boolean isNotNull(Object object){
        return object != null;
    }
}
