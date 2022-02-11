//package az.gov.mia.grps.auth.service;
//
//import az.gov.mia.grps.auth.entity.Privilege;
//import az.gov.mia.grps.auth.entity.Role;
//import az.gov.mia.grps.auth.entity.User;
//
//import az.gov.mia.grps.auth.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Component
//        (value = "basepackage.service")
//public class CustomUserDetails implements UserDetailsService {
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails userDetails = null;
//        try {
//            User user = userRepository.findByUsername(username);
//            List<GrantedAuthority> roles = user.getRoles()
//                    .stream()
//                    .map(r -> new SimpleGrantedAuthority(r.getName()))
//                    .collect(Collectors.toList());
//
////    userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), getAuthorities(user.getRoles()));
//            userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
//                    user.getPassword(), getAuthorities(user.getRoles()));
//        } catch (RuntimeException e) {
//            throw new UsernameNotFoundException(username);
////    System.out.println("-------");
//
//        }
//        return userDetails;
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        return getGrantedAuthorities(getPrivileges(roles));
//    }
//
//    private List<String> getPrivileges(Collection<Role> roles) {
//
//        List<String> privileges = new ArrayList<>();
//        List<Privilege> collection = new ArrayList<>();
//        for (Role role : roles) {
//            collection.addAll(role.getPrivileges());
//        }
//        for (Privilege item : collection) {
//            privileges.add(item.getName());
//        }
//        return privileges;
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String privilege : privileges) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }
//
//
//}
//
