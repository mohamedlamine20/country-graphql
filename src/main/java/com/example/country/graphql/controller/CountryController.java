package com.example.country.graphql.controller;

import com.example.country.graphql.model.Country;
import com.example.country.graphql.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CountryController {

    private final CountryRepository countryRepository;

    @QueryMapping
    public List<Country>getCountries(){
        return countryRepository.findAll();
    }
}
