package com.ait;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        ObjectMapper mapper = new ObjectMapper();

        Person p = new Person("Jack", 18);

        Person[] people = {

                new Person("John", 22),
                new Person("Anna", 21),
                new Person("Paul", 17),
                new Person("Lena", 27),

        };



        try {
            String json = mapper.writeValueAsString(p);
            System.out.println(json);
            mapper.writeValue(new File("p1.json"), p);
            mapper.writeValue(new File("p2.json"), people);

            System.out.println(json);
            Person person = mapper.readValue(new File("p1.json"), Person.class);
            Person[] people1 = mapper.readValue(new File("p2.json"), Person[].class);

            System.out.println("person" + person);
            System.out.println(Arrays.toString(people1));

        }catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}