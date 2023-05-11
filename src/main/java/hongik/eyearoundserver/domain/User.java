package hongik.eyearoundserver.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends DateEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean state;

    @Column
    @ColumnDefault("int default 0")
    private Integer weekCount;

    @Builder
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.state = false;
        this.weekCount = 0;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TODO: 매일 state 리셋 (scheduling)
    public void changeState() {
        if (!this.state) {
            this.state = true;
            this.addWeekCount();
        }
    }

    // TODO: 매주 count 리셋 (scheduling)
    public void addWeekCount() {
        if (this.weekCount < 7) {
            this.weekCount += 1;
        } else {
            this.weekCount = 0;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
