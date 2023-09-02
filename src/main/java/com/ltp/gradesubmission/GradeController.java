package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    List<Grade> studentGrades = new ArrayList<>();

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade) {
        int index = getGradeIndex(grade.getName());
        if (index == -1000) {
            studentGrades.add(grade);
        } else {
            studentGrades.set(index, grade);
        }
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
    public String getForm(Model model, @RequestParam(required = false) String name) {
        model.addAttribute("grade",
                getGradeIndex(name) == -1000 ? new Grade() : studentGrades.get(getGradeIndex(name)));
        return "form";
    }

    public Integer getGradeIndex(String name) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getName().equals(name))
                return i;
        }
        return -1000;
    }

}
