package ru.inno.ec.service;

import ru.inno.ec.dto.CourseForm;
import ru.inno.ec.models.Course;
import ru.inno.ec.models.Lesson;
import ru.inno.ec.models.User;

import java.util.List;

public interface CoursesService {

    Course getCourse(Long courseId);

    void addStudentToCourse(Long courseId, Long studentId);

    List<User> getNotInCourseStudents(Long courseId);

    List<User> getInCourseStudents(Long courseId);

    List<Course> getAllCourses();

    void deleteCourse(Long courseId);

    void addCourse(CourseForm courseForm);

    void updateCourse(Long courseId, Course course);

    List<Lesson> getCourseLessons(Long courseId);

    List<Lesson> getLessonsNotInCourse(Long courseId);

    void addLessonToCourse(Long courseId, Long lessonId);
}
