package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public class HomeController {

    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String ListJobs(Model model){
        model.addAttribute("jobs", jobRepository.findAll()); //jobs is an array of job objects I think
        return "list";
    }

    @GetMapping("/add")
    public String loadJobForm(Model model){
        model.addAttribute("jobform", new Job());
        return "jobform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Job job, BindingResult result){
        if(result.hasErrors()){
            return("/jobform");
        }

        jobRepository.save(job);
        return "redirect:/";
    }


}
