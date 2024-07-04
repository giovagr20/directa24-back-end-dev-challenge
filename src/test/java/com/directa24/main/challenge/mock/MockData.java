package com.directa24.main.challenge.mock;

import com.directa24.main.challenge.dtos.MoviesDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

public class MockData {
    public static Optional<MoviesDto> getMockMoviesDto() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return Optional.of(objectMapper.readValue(dataMock(), MoviesDto.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getDirectorsMock() {
        return List.of("Martin Scorssece", "Christopher Nolan");
    }

    private static String dataMock() {
        return "{\"page\":2,\"per_page\":10,\"total\":26,\"total_pages\":3,\"data\":[{\"Title\":\"KillBill:TheWholeBloodyAffair\",\"Year\":\"2011\",\"Rated\":\"NotRated\",\"Released\":\"27Mar2011\",\"Runtime\":\"247min\",\"Genre\":\"Action,Crime,Thriller\",\"Director\":\"QuentinTarantino\",\"Writer\":\"QuentinTarantino,UmaThurman\",\"Actors\":\"UmaThurman,VivicaA.Fox,MichaelMadsen\"},{\"Title\":\"IrrationalMan\",\"Year\":\"2015\",\"Rated\":\"R\",\"Released\":\"07Aug2015\",\"Runtime\":\"95min\",\"Genre\":\"Comedy,Crime,Drama\",\"Director\":\"WoodyAllen\",\"Writer\":\"WoodyAllen\",\"Actors\":\"JoaquinPhoenix,EmmaStone,ParkerPosey\"},{\"Title\":\"Underdogs\",\"Year\":\"2013\",\"Rated\":\"PG\",\"Released\":\"18Jul2013\",\"Runtime\":\"85min\",\"Genre\":\"Animation,Adventure,Family\",\"Director\":\"JuanJosÃ©Campanella\",\"Writer\":\"EduardoSacheri,GastÃ³nGorali,JuanJosÃ©Campanella\",\"Actors\":\"GabrielAlmirÃ³n,FedericoCecere,EzequielCipols\"},{\"Title\":\"TheWolfofWallStreet\",\"Year\":\"2013\",\"Rated\":\"R\",\"Released\":\"25Dec2013\",\"Runtime\":\"180min\",\"Genre\":\"Biography,Comedy,Crime\",\"Director\":\"MartinScorsese\",\"Writer\":\"TerenceWinter,JordanBelfort\",\"Actors\":\"LeonardoDiCaprio,JonahHill,MargotRobbie\"},{\"Title\":\"ShutterIsland\",\"Year\":\"2010\",\"Rated\":\"R\",\"Released\":\"19Feb2010\",\"Runtime\":\"138min\",\"Genre\":\"Mystery,Thriller\",\"Director\":\"MartinScorsese\",\"Writer\":\"LaetaKalogridis,DennisLehane\",\"Actors\":\"LeonardoDiCaprio,EmilyMortimer,MarkRuffalo\"},{\"Title\":\"Sully\",\"Year\":\"2016\",\"Rated\":\"PG-13\",\"Released\":\"09Sep2016\",\"Runtime\":\"96min\",\"Genre\":\"Biography,Drama\",\"Director\":\"ClintEastwood\",\"Writer\":\"ToddKomarnicki,ChesleySullenberger,JeffreyZaslow\",\"Actors\":\"TomHanks,AaronEckhart,LauraLinney\"},{\"Title\":\"ToRomewithLove\",\"Year\":\"2012\",\"Rated\":\"R\",\"Released\":\"06Jul2012\",\"Runtime\":\"112min\",\"Genre\":\"Comedy,Music,Romance\",\"Director\":\"WoodyAllen\",\"Writer\":\"WoodyAllen\",\"Actors\":\"WoodyAllen,PenÃ©lopeCruz,JesseEisenberg\"},{\"Title\":\"CafÃ©Society\",\"Year\":\"2016\",\"Rated\":\"PG-13\",\"Released\":\"05Aug2016\",\"Runtime\":\"96min\",\"Genre\":\"Comedy,Drama,Romance\",\"Director\":\"WoodyAllen\",\"Writer\":\"WoodyAllen\",\"Actors\":\"JesseEisenberg,KristenStewart,SteveCarell\"},{\"Title\":\"Split\",\"Year\":\"2016\",\"Rated\":\"PG-13\",\"Released\":\"20Jan2017\",\"Runtime\":\"117min\",\"Genre\":\"Horror,Thriller\",\"Director\":\"M.NightShyamalan\",\"Writer\":\"M.NightShyamalan\",\"Actors\":\"JamesMcAvoy,AnyaTaylor-Joy,HaleyLuRichardson\"},{\"Title\":\"YouWillMeetaTallDarkStranger\",\"Year\":\"2010\",\"Rated\":\"R\",\"Released\":\"22Oct2010\",\"Runtime\":\"98min\",\"Genre\":\"Comedy,Drama,Romance\",\"Director\":\"WoodyAllen\",\"Writer\":\"WoodyAllen\",\"Actors\":\"AnthonyHopkins,NaomiWatts,JoshBrolin\"}]}";
    }
}
