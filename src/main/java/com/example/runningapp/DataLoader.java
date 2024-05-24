package com.example.runningapp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataLoader implements CommandLineRunner {

    private final RunnerRepository runnerRepository;
    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public DataLoader(RunnerRepository runnerRepository, RaceRepository raceRepository, ResultRepository resultRepository) {
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and save runners
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

        // Create and save races
        Race race1 = new Race("Spar BP Maraton", 42);
        Race race2 = new Race("Telekom Vivicitá", 21);
        Race race3 = new Race("UltraBalaton", 211);
        Race race4 = new Race("Szigetkör 5K", 5);

        raceRepository.save(race1);
        raceRepository.save(race2);
        raceRepository.save(race3);
        raceRepository.save(race4);

        // Create and save results
        Result result1 = new Result(runner1, race1, 240);
        Result result2 = new Result(runner2, race1, 250);
        Result result3 = new Result(runner3, race2, 120);
        Result result4 = new Result(runner4, race2, 130);
        Result result5 = new Result(runner5, race3, 50);
        Result result6 = new Result(runner6, race3, 55);
        Result result7 = new Result(runner1, race4, 25);
        Result result8 = new Result(runner2, race4, 27);
        Result result9 = new Result(runner3, race1, 245);
        Result result10 = new Result(runner4, race1, 255);

        resultRepository.save(result1);
        resultRepository.save(result2);
        resultRepository.save(result3);
        resultRepository.save(result4);
        resultRepository.save(result5);
        resultRepository.save(result6);
        resultRepository.save(result7);
        resultRepository.save(result8);
        resultRepository.save(result9);
        resultRepository.save(result10);
    }
}
