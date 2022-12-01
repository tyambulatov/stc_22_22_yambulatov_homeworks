package ru.inno.ec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.ec.models.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {
    
}
