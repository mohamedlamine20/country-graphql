package com.example.country.graphql.data;

import com.example.country.graphql.config.GraphQlConfig;
import com.example.country.graphql.model.Country;
import com.example.country.graphql.model.Events;
import com.example.country.graphql.model.States;
import com.example.country.graphql.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed implements CommandLineRunner {

    private final GraphQlConfig graphQlConfig;
    private final CountryRepository countryRepository;


    private final StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {

        List<Country> countries = graphQlConfig.getCountries().block();

        countryRepository.saveAll(countries);

        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);


    }
}
