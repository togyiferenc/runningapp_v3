package com.example.runningapp.Config;

import com.example.runningapp.model.Runner;
import com.example.runningapp.model.Race;
import com.example.runningapp.model.Result;
import com.example.runningapp.repository.RunnerRepository;
import com.example.runningapp.repository.RaceRepository;
import com.example.runningapp.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Bean
    public ApplicationRunner initDatabase() {
        return args -> {
            // Létrehozzuk a futókat
            Runner runner1 = new Runner("Nagy Anna", 30, "nő");
            Runner runner2 = new Runner("Kis Krisztina", 25, "nő");
            Runner runner3 = new Runner("Nagy Árpád", 28, "férfi");
            Runner runner4 = new Runner("Mezei Virág", 35, "férfi");
            Runner runner5 = new Runner("Kovács Júlia", 32, "nő");
            Runner runner6 = new Runner("Katona Izsák", 27, "Apache támadó helikopter");

            runnerRepository.save(runner1);
            runnerRepository.save(runner2);
            runnerRepository.save(runner3);
            runnerRepository.save(runner4);
            runnerRepository.save(runner5);
            runnerRepository.save(runner6);

            // Létrehozzuk a versenyeket
            Race race1 = new Race("Spar BP Maraton", 42);
            Race race2 = new Race("Telekom Vivicitá", 21);
            Race race3 = new Race("UltraBalaton", 211);
            Race race4 = new Race("Szigetkör 5K", 5);

            raceRepository.save(race1);
            raceRepository.save(race2);
            raceRepository.save(race3);
            raceRepository.save(race4);

            // Létrehozzuk az eredményeket
            resultRepository.save(new Result(runner1, race1, 240));
            resultRepository.save(new Result(runner2, race1, 250));
            resultRepository.save(new Result(runner3, race2, 120));
            resultRepository.save(new Result(runner4, race2, 130));
            resultRepository.save(new Result(runner5, race3, 50));
            resultRepository.save(new Result(runner6, race3, 55));
            resultRepository.save(new Result(runner1, race4, 25));
            resultRepository.save(new Result(runner2, race4, 27));
            resultRepository.save(new Result(runner3, race1, 245));
            resultRepository.save(new Result(runner4, race1, 255));
        };
    }
}
