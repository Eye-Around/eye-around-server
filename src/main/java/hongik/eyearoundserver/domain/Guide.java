package hongik.eyearoundserver.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Guide extends DateEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @Column
    private String effect;

    @Builder
    public Guide(String effect) {
        this.effect = effect;
    }
}
