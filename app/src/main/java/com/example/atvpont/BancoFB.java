package com.example.atvpont;

import com.google.firebase.auth.FirebaseAuth;

public class BancoFB {
    private static FirebaseAuth auth;

    public static FirebaseAuth DBAuthentication(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
