package com.revature;

import com.revature.controllers.UserController;
import io.javalin.Javalin;

public class Main {
        public static void main(String[] args) {

            Javalin app = Javalin.create(config ->{
                config.enableCorsForAllOrigins();
            }).start(8080);

            UserController userController = new UserController();

            //users uris
            app.get("/user",userController.getUserByEmail);
            app.post("/user",userController.createNewUser);
            app.put("/user",userController.updateMood);
            app.post("/login", userController.loginUser);

    }
}