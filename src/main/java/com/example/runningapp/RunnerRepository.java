package com.example.runningapp;

//import com.example.runningapp.Runner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunnerRepository extends JpaRepository<Runner, Long> {
}
