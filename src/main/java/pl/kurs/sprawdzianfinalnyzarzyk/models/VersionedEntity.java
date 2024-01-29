//package pl.kurs.sprawdzianfinalnyzarzyk.models;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//public class VersionedEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Version
//    private int version;
//    @CreatedDate
//    private LocalDateTime createdDate;
//    @CreatedBy
//    private String createdBy;
//    @LastModifiedDate
//    private LocalDateTime lastModifiedDate;
//    @LastModifiedBy
//    private String lastModifiedBy;
//}
// TODO: 23.01.2024 securit jutro rano najpierw oglądnąć w amigos code a potem waldka  