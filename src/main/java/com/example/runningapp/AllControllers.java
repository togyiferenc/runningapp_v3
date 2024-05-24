package com.example.runningapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class AllControllers {

    @Autowired
    private RunnerRepository runnerRepository;


    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/getRunners")
    public String getRunners(Model model) {
        model.addAttribute("runners", runnerRepository.findAll());
        return "runnerList";
    }

    @PostMapping("/addRunner")
    public String addRunner(@ModelAttribute Runner runner) {
        runnerRepository.save(runner);
        return "redirect:/api/getRunners";
    }

    @GetMapping("/getRaceRunners/{id}")
    public String getRaceRunners(@PathVariable Long id, Model model) {
        // Implement logic to retrieve race runners
        return "raceRunners";
    }

    @PostMapping("/updateRace")
    public String updateRace(@RequestBody Race race) {
        // Implement logic to update race
        return "redirect:/api/getRaceRunners/" + race.getId();
    }

    @PostMapping("/addResult")
    public String addResult(@ModelAttribute Result result) {
        resultRepository.save(result);
        return "redirect:/api/getRaceRunners/" + result.getId();
    }

    @GetMapping("/getAverageTime/{raceId}")
    public String getAverageTime(@PathVariable Long raceId, Model model) {
        // Implement logic to calculate average time
        return "averageTime";
    }
}
