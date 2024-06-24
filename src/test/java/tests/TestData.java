package tests;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestData {

    private static Faker faker = new Faker();
    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String email = faker.internet().emailAddress();
    public static String gender = getGender();
    public static String mobile = faker.number().digits(10);
    public static String dateOfBirth = getDateOfBirth();
    public static String subject = getSubject();
    public static String hobby = getHobby();
    public static String fileName = getFile();
    public static String address = faker.address().streetAddress();
    public static String state = getState();
    public static String city = getCity(state);

    public static String getDateOfBirth() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH);
        Date date = faker.date().birthday();
        return formatter.format(date);
    }

    public static String getGender() {
        List<String> genders = List.of("Male", "Female", "Other");
        int index = faker.number().numberBetween(0, genders.size() - 1);
        return genders.get(index);
    }

    public static String getHobby() {
        List<String> hobbies = List.of("Sports", "Reading", "Music");
        int index = faker.number().numberBetween(0, hobbies.size() - 1);
        return hobbies.get(index);
    }

    public static String getFile() {
        List<String> files = List.of("img1.png", "img2.png", "img3.png");
        int index = faker.number().numberBetween(0, files.size() - 1);
        return files.get(index);
    }

    public static String getSubject() {
        List<String> subjects = List.of("Math", "Biology", "Computer Science", "Commerce", "Accounting", "Economics"
                , "Social Studies", "History", "Physics");
        int index = faker.number().numberBetween(0, subjects.size() - 1);
        return subjects.get(index);
    }

    public static String getState() {
        List<String> states = List.of("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        int index = faker.number().numberBetween(0, states.size() - 1);
        return states.get(index);
    }

    public static String getCity(String state) {
        List<String> cities = switch (state) {
            case "Rajasthan" -> List.of("Jaipur", "Jaiselmer");
            case "Haryana" -> List.of("Karnal", "Panipat");
            case "Uttar Pradesh" -> List.of("Agra", "Lucknow", "Merrut");
            case "NCR" -> List.of("Delhi", "Gurgaon", "Noida");
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
        int index = faker.number().numberBetween(0, cities.size() - 1);
        return cities.get(index);
    }
}