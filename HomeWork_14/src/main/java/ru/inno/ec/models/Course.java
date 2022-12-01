package ru.inno.ec.models;

//import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = {"lessons", "students"})
@ToString(exclude = {"lessons", "students"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "start", nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;

    @Column(name = "finish", nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finish;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<Lesson> lessons;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<User> users;
}
