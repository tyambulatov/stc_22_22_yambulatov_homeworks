package ru.inno.ec.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

//import javax.persistence.*;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = {"course"})
@ToString(exclude = {"course"})
public class Lesson {
    @Id
    // генерация базой данных
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 1000)
    private String summary;

    @Column(name = "start_time", nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime startTime;

    @Column(name = "finish_time", nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime finishTime;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
