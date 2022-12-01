package ru.inno.ec.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.inno.ec.dto.LessonForm;
import ru.inno.ec.models.Lesson;
import ru.inno.ec.service.LessonsService;

@RequiredArgsConstructor
@Controller
public class LessonsController {

    private final LessonsService lessonsService;

    @GetMapping("/lessons")
    public String getLessonsPage(Model model) {
        model.addAttribute("lessons", lessonsService.getAllLessons());
        return "lessons_page";
    }

    @PostMapping("/lessons")
    public String addLesson(LessonForm lesson) {
        lessonsService.addLesson(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/lessons/{lesson-id}")
    public String getLessonPage(@PathVariable("lesson-id") Long lessonId, Model model) {
        model.addAttribute("lesson", lessonsService.getLesson(lessonId));
        return "lesson_page";
    }

    @GetMapping("/lessons/{lesson-id}/delete")
    public String deleteLesson(@PathVariable("lesson-id") Long lessonId) {
        lessonsService.deleteLesson(lessonId);
        return "redirect:/lessons";
    }

    @PostMapping("/lessons/{lesson-id}/update")
    public String updateLesson(@PathVariable("lesson-id") Long lessonId,
                               Lesson lesson) {
        lessonsService.updateLesson(lessonId, lesson);
        return "redirect:/lessons/" + lessonId;
    }
}
