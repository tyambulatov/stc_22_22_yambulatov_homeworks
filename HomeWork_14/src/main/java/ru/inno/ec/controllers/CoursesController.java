package ru.inno.ec.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.ec.dto.CourseForm;
import ru.inno.ec.models.Course;
import ru.inno.ec.service.CoursesService;

@RequiredArgsConstructor
@Controller
public class CoursesController {

    private final CoursesService coursesService;

    @GetMapping("/courses")
    public String getCoursesPage(Model model) {
        model.addAttribute("courses", coursesService.getAllCourses());
        return "courses_page";
    }

    @PostMapping("/courses")
    public String addUser(CourseForm courseForm) {
        coursesService.addCourse(courseForm);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{course-id}")
    public String getCoursePage(@PathVariable("course-id") Long courseId,
                                Model model) {
        model.addAttribute("course", coursesService.getCourse(courseId));
        model.addAttribute("notInCourseStudents", coursesService.getNotInCourseStudents(courseId));
        model.addAttribute("inCourseStudents", coursesService.getInCourseStudents(courseId));
        model.addAttribute("courseLessons", coursesService.getCourseLessons(courseId));
        model.addAttribute("lessonsNotInCourse", coursesService.getLessonsNotInCourse(courseId));
        return "course_page";
    }

    @PostMapping("/courses/{course-id}/students")
    public String addStudentToCourse(@PathVariable("course-id") Long courseId,
                                     @RequestParam("student-id") Long studentId) {
        coursesService.addStudentToCourse(courseId, studentId);
        return "redirect:/courses/" + courseId;
    }

    @PostMapping("/courses/{course-id}/lessons")
    public String addLessonToCourse(@PathVariable("course-id") Long courseId,
                                     @RequestParam("lesson-id") Long lessonId) {
        coursesService.addLessonToCourse(courseId, lessonId);
        return "redirect:/courses/" + courseId;
    }

    @GetMapping("/courses/{course-id}/delete")
    public String deleteCourse(@PathVariable("course-id") Long courseId) {
        coursesService.deleteCourse(courseId);
        return "redirect:/courses";
    }

    @PostMapping("/courses/{course-id}/update")
    public String updateCourse(@PathVariable("course-id") Long courseId,
                               Course course) {
        coursesService.updateCourse(courseId, course);
        return "redirect:/courses/" + courseId;
    }
}
