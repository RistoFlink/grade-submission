package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {

    List<Grade> studentGrades = new ArrayList<>();

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade) {
        studentGrades.add(grade);
        return "redirect:/grades"; // redirects the users by a GET request on /grades to trigger a different
                                   // handler method
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    @GetMapping("/shows")
    public String getShows() {
        return "shows";
    }

    @GetMapping("/")
    public String gradeForm(Model model) {
        model.addAttribute("grade", new Grade());
        return "form";
    }

}
