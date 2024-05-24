package com.example.runningapp;

//import com.example.runningapp.Result;
//import com.example.runningapp.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @GetMapping
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Long id) {
        return resultRepository.findById(id)
                .map(result -> ResponseEntity.ok().body(result))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Result createResult(@RequestBody Result result) {
        return resultRepository.save(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id, @RequestBody Result resultDetails) {
        return resultRepository.findById(id)
                .map(result -> {
                    result.setRunner(resultDetails.getRunner());
                    result.setRace(resultDetails.getRace());
                    result.setTime(resultDetails.getTime());
                    Result updatedResult = resultRepository.save(result);
                    return ResponseEntity.ok().body(updatedResult);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        return resultRepository.findById(id)
                .map(result -> {
                    resultRepository.delete(result);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
