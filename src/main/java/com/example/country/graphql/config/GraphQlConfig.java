package com.example.country.graphql.config;


import com.example.country.graphql.model.Country;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GraphQlConfig {


    private final HttpGraphQlClient graphQlClient;

    public GraphQlConfig() {
        WebClient client = WebClient.builder()
                .baseUrl("https://countries.trevorblades.com")
                .build();
        graphQlClient = HttpGraphQlClient.builder(client).build();

    }

    public Mono<List<Country>> getCountries() {
        //language=GraphQL
        String document = """
                query {
                    countries{
                      awsRegion
                      capital
                      code
                      currencies
                      emoji
                      emojiU
                      name
                      native
                      phone
                      phones
                      continent{
                        code
                        name
                      }
                      languages{
                        code
                        name
                        native
                        rtl
                      }
                      states{
                        code
                        name
                      }
                      subdivisions{
                        code
                        emoji
                        name
                      }
                    }
                }
                """;

        return graphQlClient.document(document)
                .retrieve("countries")
                .toEntityList(Country.class);
    }
}
