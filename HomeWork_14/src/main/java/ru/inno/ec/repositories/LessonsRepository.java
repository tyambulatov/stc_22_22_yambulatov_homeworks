package ru.inno.ec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.ec.models.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
