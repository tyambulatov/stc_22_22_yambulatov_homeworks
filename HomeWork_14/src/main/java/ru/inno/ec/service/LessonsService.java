package ru.inno.ec.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.ec.dto.LessonForm;
import ru.inno.ec.models.Lesson;

import java.util.List;

public interface LessonsService {
    List<Lesson> getAllLessons();

    void addLesson(LessonForm lesson);

    void deleteLesson(Long lessonId);

    Lesson getLesson(Long lessonId);

    void updateLesson(Long lessonId, Lesson lesson);
}
