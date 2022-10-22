package com.revature.controllers;

import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.http.Handler;

public class UserController {
    UserService userService;
    public UserController(){
        this.userService = new UserService();
    }

    public Handler getUserByEmail = context-> {
        User user = context.bodyAsClass(User.class);

        if (user!=null){
            user = userService.getUserByEmail(user);
        } else {
            context.result("Not a valid user").status(400);
        }
    };

    public Handler createNewUser = context -> {
        User user = context.bodyAsClass(User.class);

        if (user!=null) {
            user = userService.createUser(user);
            if (user.getId()>0){
                context.json(user);
            } else {
                context.result("Something went wrong").status(400);
            }
        } else {
            context.result("Not a valid user").status(400);
        }
    };

    public Handler loginUser = context -> {
        User user = context.bodyAsClass(User.class);

        if (user!=null) {
            User userToCompare = userService.getUserByEmail(user);
            if (userToCompare!=null){
                if (user.getPassword().equals(userToCompare.getPassword())){
                    context.json(userToCompare);
                }
            } else {
                context.result("User does not exist").status(400);
            }
        } else {
            context.result("Not a valid user").status(400);
        }

    };

    public Handler updateMood = context -> {
        User user = context.bodyAsClass(User.class);

        if (user!=null){
            User userToCompare = userService.getUserByEmail(user);
            if (userToCompare!=null){
                userToCompare.setMood(user.getMood());
                if(userService.updateMood(userToCompare)){
                    context.result("Mood updated.").status(200);
                } else{
                    context.result("Error with database.").status(400);
                }
            }else{
                context.result("Can't find user.").status(400);
            }
        }else{
            context.result("Not a valid user").status(400);
        }


    };
}
