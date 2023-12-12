//package pl.kurs.sprawdzianfinalnyzarzyk.models;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//public abstract class VersionableEntity implements Serializable, Identifiable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Version
//    private int version;
//
//    @CreatedDate
//    private LocalDateTime createdDate;
//
//    @CreatedBy
//    private String createdBy;
//
//    @LastModifiedBy
//    private LocalDateTime lastModifiedDate;
//
//    @LastModifiedBy
//    private String lastModifiedBy;
//
//}
