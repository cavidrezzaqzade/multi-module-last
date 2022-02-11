package az.gov.mia.grps.auth.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="grp_users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private String username;



    @Column(nullable = false)
    private String password;


    @Column(name = "email")
    private String email;


    public User(String username,String password, Set<Role> roles,String email ) {
    this.username=username;
    this.password=password;
    this.roles=roles;
    this.email=email;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="grp_users_roles",
            joinColumns =
            @JoinColumn(name="user_id",referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> roles=new HashSet<>();




}
