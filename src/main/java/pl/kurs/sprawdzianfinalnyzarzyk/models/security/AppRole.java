//package pl.kurs.sprawdzianfinalnyzarzyk.models.security;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//
//@Entity
//@Getter
//@Setter
//public class AppRole implements GrantedAuthority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//
//    @Override
//    public String getAuthority() {
//        return name;
//    }
//}
