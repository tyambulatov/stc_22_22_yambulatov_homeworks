package ru.inno.ec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inno.ec.dto.LessonForm;
import ru.inno.ec.models.Course;
import ru.inno.ec.models.Lesson;
import ru.inno.ec.repositories.CoursesRepository;
import ru.inno.ec.repositories.LessonsRepository;
import ru.inno.ec.service.LessonsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonsServiceImpl implements LessonsService {

    private final LessonsRepository lessonsRepository;

    private final CoursesRepository coursesRepository;

    @Override
    public List<Lesson> getAllLessons() {
        return lessonsRepository.findAll();
    }

    @Override
    public void addLesson(LessonForm lesson) {
        Lesson newLesson = Lesson.builder()
                .name(lesson.getName())
                .summary(lesson.getSummary())
                .build();
        lessonsRepository.save(newLesson);
    }

    @Override
    public void deleteLesson(Long lessonId) {
        Lesson lesson = lessonsRepository.findById(lessonId).orElseThrow();
        Course course = lesson.getCourse();

        if (course != null) {
            course.getLessons().remove(lesson);
            coursesRepository.save(course);
        }

        lessonsRepository.delete(lesson);
    }

    @Override
    public Lesson getLesson(Long lessonId) {
        return lessonsRepository.findById(lessonId).orElseThrow();
    }

    @Override
    public void updateLesson(Long lessonId, Lesson lesson) {
        Lesson lessonForUpdate = lessonsRepository.findById(lessonId).orElseThrow();

        lessonForUpdate.setName(lesson.getName());
        lessonForUpdate.setSummary(lesson.getSummary());
        lessonForUpdate.setStartTime(lesson.getStartTime());
        lessonForUpdate.setFinishTime(lesson.getFinishTime());

        lessonsRepository.save(lessonForUpdate);
    }
}
