package com.example.demo.processor;

import com.example.demo.dto.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(Person person) throws Exception {
        // 为每个对象年龄+1
        person.setAge(person.getAge() + 1);
        return person;
    }
}
