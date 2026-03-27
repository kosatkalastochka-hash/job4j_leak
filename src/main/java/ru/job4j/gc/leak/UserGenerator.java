package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "files/names.txt";
    public static final String PATH_SURNAMES = "files/surnames.txt";
    public static final String PATH_PATRONS = "files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final Integer NEW_USERS = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private final List<User> USERS = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
    }

    @Override
    public void generate() {
        readAll();
        int surnamesSize = surnames.size();
        int namesSize = names.size();
        int patronsSize = patrons.size();
        for (int i = 0; i < NEW_USERS; i++) {
            var name = surnames.get(random.nextInt(surnamesSize)) + SEPARATOR
                    + names.get(random.nextInt(namesSize)) + SEPARATOR
                    + patrons.get(random.nextInt(patronsSize));
            var user = new User();
            user.setName(name);
            USERS.add(user);
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<User> getUSERS() {
        return USERS;
    }

    public User randomUser() {
        return USERS.get(random.nextInt(USERS.size()));
    }
}