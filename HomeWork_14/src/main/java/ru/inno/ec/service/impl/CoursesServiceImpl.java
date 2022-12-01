package ru.inno.ec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inno.ec.dto.CourseForm;
import ru.inno.ec.models.Course;
import ru.inno.ec.models.Lesson;
import ru.inno.ec.models.User;
import ru.inno.ec.repositories.CoursesRepository;
import ru.inno.ec.repositories.LessonsRepository;
import ru.inno.ec.repositories.UsersRepository;
import ru.inno.ec.service.CoursesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;
    private final UsersRepository usersRepository;

    private final LessonsRepository lessonsRepository;

    @Override
    public Course getCourse(Long courseId) {
        return coursesRepository.findById(courseId).orElseThrow();

    }

    @Override
    public List<User> getNotInCourseStudents(Long courseId) {
        Course course = coursesRepository.findById(courseId).orElseThrow();
        return usersRepository.findAllByCoursesNotContains(course);
    }

    @Override
    public List<User> getInCourseStudents(Long courseId) {
        Course course = coursesRepository.findById(courseId).orElseThrow();
        return usersRepository.findAllByCoursesContains(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = coursesRepository.findById(courseId).orElseThrow();

        Set<User> usersSignedToCourse = course.getUsers();
        for (User user : usersSignedToCourse) {
            user.getCourses().remove(course);
            usersRepository.save(user);
        }

        Set<Lesson> lessonsOnThatCourse = course.getLessons();
        for (Lesson lesson : lessonsOnThatCourse) {
            lesson.setCourse(null);
            lessonsRepository.save(lesson);
        }
        coursesRepository.delete(course);
    }

    @Override
    public void addCourse(CourseForm courseForm) {
        Course newCourse = Course.builder()
                .title(courseForm.getTitle())
                .description(courseForm.getDescription())
                .build();
        coursesRepository.save(newCourse);
    }

    @Override
    public void updateCourse(Long courseId, Course course) {
        Course courseForUpdate = coursesRepository.findById(courseId).orElseThrow();

        courseForUpdate.setTitle(course.getTitle());
        courseForUpdate.setDescription(course.getDescription());
        courseForUpdate.setStart(course.getStart());
        courseForUpdate.setFinish(course.getFinish());

        coursesRepository.save(courseForUpdate);
    }

    @Override
    public List<Lesson> getCourseLessons(Long courseId) {
        Course course = coursesRepository.findById(courseId).orElseThrow();
        Set<Lesson> courseLessons = course.getLessons();
        return new ArrayList<>(courseLessons);
    }

    @Override
    public List<Lesson> getLessonsNotInCourse(Long courseId) {
        Course course = coursesRepository.findById(courseId).orElseThrow();
        Set<Lesson> courseLessons = course.getLessons();
        List<Lesson> allLessons = lessonsRepository.findAll();
        allLessons.removeAll(courseLessons);
        return allLessons;
    }

    @Override
    public void addStudentToCourse(Long courseId, Long studentId) {
        Course course = coursesRepository.findById(courseId).orElseThrow();
        User student = usersRepository.findById(studentId).orElseThrow();

        student.getCourses().add(course);

        usersRepository.save(student);
    }

    @Override
    public void addLessonToCourse(Long courseId, Long lessonId) {
        Course course = coursesRepository.findById(courseId).orElseThrow();
        Lesson lesson = lessonsRepository.findById(lessonId).orElseThrow();

        course.getLessons().add(lesson);
        lesson.setCourse(course);

        lessonsRepository.save(lesson);
        coursesRepository.save(course);
    }
}
