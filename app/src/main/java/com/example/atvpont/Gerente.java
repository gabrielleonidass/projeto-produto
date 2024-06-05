package com.example.atvpont;

import java.util.ArrayList;
import java.util.List;

public class Gerente {
    private static Gerente instance;
    private List<Usuario> users;

    private Gerente() {
        users = new ArrayList<>();
    }

    public static synchronized Gerente getInstance() {
        if (instance == null) {
            instance = new Gerente();
        }
        return instance;
    }

    public void addUser(Usuario user) {
        users.add(user);
    }

    public Usuario getUser(String email, String password) {
        for (Usuario user : users) {
            if (user.getEmail().equals(email) && user.getSenha().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
