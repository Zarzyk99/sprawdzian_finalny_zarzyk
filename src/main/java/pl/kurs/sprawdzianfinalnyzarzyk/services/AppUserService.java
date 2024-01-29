//package pl.kurs.sprawdzianfinalnyzarzyk.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import pl.kurs.sprawdzianfinalnyzarzyk.repositories.AppUserRepository;
//
//@Service
//@RequiredArgsConstructor
//public class AppUserService implements UserDetailsService {
//    private final AppUserRepository appUserRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return appUserRepository.findByUsernameWithRoles(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//    }
//}
