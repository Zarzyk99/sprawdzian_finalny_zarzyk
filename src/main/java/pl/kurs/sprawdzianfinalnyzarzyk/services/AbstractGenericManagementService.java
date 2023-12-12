//package pl.kurs.sprawdzianfinalnyzarzyk.services;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.transaction.annotation.Transactional;
//import pl.kurs.sprawdzianfinalnyzarzyk.exceptions.BadEntityException;
//import pl.kurs.sprawdzianfinalnyzarzyk.exceptions.BadIdException;
//import pl.kurs.sprawdzianfinalnyzarzyk.models.Identifiable;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Transactional
//public abstract class AbstractGenericManagementService<T extends Identifiable, R extends JpaRepository<T, Long>> implements IManagementService<T> {
//    protected R repository;
//
//    public AbstractGenericManagementService(R repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public T add(T entity) {
//        return repository.save(
//                Optional.ofNullable(entity)
//                        .filter(x -> Objects.isNull(x.getId()))
//                        .orElseThrow(() -> new BadEntityException("Błędna encja do zapisywania")));
//
//    }
//
//    @Override
//    public void delete(Long id) {
//        try {
//            repository.deleteById(
//                    Optional.ofNullable(id)
//                            .filter(x -> x > 0)
//                            .orElseThrow(() -> new BadIdException("Błędne id")));
//        } catch (EmptyResultDataAccessException e) {
//            throw new EntityNotFoundException("Nie znalezono elemntu o takim id do skasowania");
//        }
//    }
//
//    @Override
//    public T edit(T entity) {
//        return repository.save(
//                Optional.ofNullable(entity)
//                        .filter(x -> Objects.nonNull(x.getId()))
//                        .orElseThrow(() -> new BadEntityException("Podano błędną encję do edycji")));
//    }
//
//    @Override
//    public T get(Long id) {
//        return repository.findById(
//                Optional.ofNullable(id)
//                        .orElseThrow(() -> new BadIdException("Podano błędne id")))
//                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono encji o danym id"));
//    }
//
//    @Override
//    public List<T> getAll() {
//        return repository.findAll();
//    }
//}
