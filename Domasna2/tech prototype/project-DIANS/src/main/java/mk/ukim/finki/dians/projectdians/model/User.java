package mk.ukim.finki.dians.projectdians.model;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@Table(name="Map_User")
public class User implements UserDetails {
    @Id
    private String username;
    private String Name;
    private String Surname;
    private String email;
    private String password;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;
    @Enumerated(value = EnumType.STRING)
    private Role role;




    public User() {
    }
    public User(String username,String password)
    {
this.username=username;
this.password=password;
    }

    public User(String username, String name, String surname, String email, String password,Role role) {
        this.username = username;
        Name = name;
        Surname = surname;
        this.email = email;
        this.password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
