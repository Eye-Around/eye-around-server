package hongik.eyearoundserver.domain;

import hongik.eyearoundserver.domain.enums.Direction;
import hongik.eyearoundserver.domain.enums.ExerciseType;
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
public class Exercise extends DateEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "exercise_tags", joinColumns = @JoinColumn(name = "exercise_id"))
    private List<String> tags = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column
    private ExerciseType type;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Direction direction;

    @Column
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id")
    private Guide guide;

    @Builder
    public Exercise(String title, List<String> tags, ExerciseType type, Direction direction, String message, Guide guide) {
        this.title = title;
        this.tags = tags;
        this.type = type;
        this.direction = direction;
        this.message = message;
        this.guide = guide;
    }
}
