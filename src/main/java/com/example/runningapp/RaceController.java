package com.example.runningapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {

    @Autowired
    private RaceRepository raceRepository;

    @GetMapping
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRaceById(@PathVariable Long id) {
        return raceRepository.findById(id)
                .map(race -> ResponseEntity.ok().body(race))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Race createRace(@RequestBody Race race) {
        return raceRepository.save(race);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable Long id, @RequestBody Race raceDetails) {
        return raceRepository.findById(id)
                .map(race -> {
                    race.setName(raceDetails.getName());
                    race.setDistance(raceDetails.getDistance());
                    Race updatedRace = raceRepository.save(race);
                    return ResponseEntity.ok().body(updatedRace);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        return raceRepository.findById(id)
                .map(race -> {
                    raceRepository.delete(race);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
