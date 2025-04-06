package sunshare.services;

import java.util.ArrayList;
import java.util.Arrays;

import sunshare.entities.Address;
import sunshare.entities.Document;
import sunshare.entities.User;

public class AuthService {

    private ArrayList<User> registeredUsers = new ArrayList<User>(Arrays.asList(
        new User("User1", "user1@example.com", "password1",
            new Address("State1", "City1", "Neighborhood1", "Street1", "11111-111"),
            new Document("CPF", "111.111.111-11")),
        new User("User2", "user2@example.com", "password2",
            new Address("State2", "City2", "Neighborhood2", "Street2", "22222-222"),
            new Document("CPF", "222.222.222-22")),
        new User("User3", "user3@example.com", "password3",
            new Address("State3", "City3", "Neighborhood3", "Street3", "33333-333"),
            new Document("CPF", "333.333.333-33"))
    ));

    public User register(String name, String email, String password, Address address, Document document) {
        User newUser = new User(name, email, password, address, document);
        registeredUsers.add(newUser);
        return newUser;
    }

    public User login(String email, String senha) {
        for (User user : registeredUsers) {
            if (user.getEmail().equals(email) && user.getPassword().equals(senha)) {
                return user;
            }
        }

        return null;
    }

}
