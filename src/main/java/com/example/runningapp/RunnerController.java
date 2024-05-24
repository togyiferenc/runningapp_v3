package com.example.runningapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {

    @Autowired
    private RunnerRepository runnerRepository;

    @GetMapping
    public List<Runner> getAllRunners() {
        return runnerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Runner> getRunnerById(@PathVariable Long id) {
        return runnerRepository.findById(id)
                .map(runner -> ResponseEntity.ok().body(runner))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Runner createRunner(@RequestBody Runner runner) {
        return runnerRepository.save(runner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Runner> updateRunner(@PathVariable Long id, @RequestBody Runner runnerDetails) {
        return runnerRepository.findById(id)
                .map(runner -> {
                    runner.setName(runnerDetails.getName());
                    runner.setAge(runnerDetails.getAge());
                    runner.setGender(runnerDetails.getGender());
                    Runner updatedRunner = runnerRepository.save(runner);
                    return ResponseEntity.ok().body(updatedRunner);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRunner(@PathVariable Long id) {
        return runnerRepository.findById(id)
                .map(runner -> {
                    runnerRepository.delete(runner);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
