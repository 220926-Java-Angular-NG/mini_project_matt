package com.revature.service;

import com.revature.model.User;
import com.revature.repo.UserRepo;

public class UserService {
    UserRepo uR;

    public UserService(){
        this.uR = new UserRepo();
    }

    public User getUserByEmail(User user){
        return setDisplays(uR.getUser(user));}

    public User createUser(User user){
        user = setFKs(user);
        user = setDisplays(user);
        return uR.createUser(user);}

    public boolean updateMood(User user){return uR.updateMood(user);}

    private User setFKs(User user){
        if (user.getBirthdate()=="")
            return null;
        else{
            String bdate = user.getBirthdate();
            String[] ymd = bdate.split("-");
            user = setWZodiacFK(ymd, user);
            user = setCZodiacFK(ymd, user);

            return user;
        }


    }

    private User setDisplays(User user){

        if(user.getWzodiac()>0 && user.getWzodiac()<13)
            user = uR.getWSignDisplay(user);
        if(user.getCzodiac()>0 && user.getCzodiac()<13)
            user = uR.getCSignDisplay(user);
        return user;
    }

    private User setWZodiacFK(String[] ymd,User user){
        //int yearNum = Integer.parseInt(ymd[0]);
        int month = Integer.parseInt(ymd[1]);
        int day = Integer.parseInt(ymd[2]);
        if (month==12){
            if (day<20)
                user.setWzodiac(9);
            else
                user.setWzodiac(10);
        } else if (month == 1){
            if (day < 20)
                user.setWzodiac(10);
            else
                user.setWzodiac(11);
        }else if (month == 2){
            if (day < 18)
                user.setWzodiac(11);
            else
                user.setWzodiac(12);
        }

        else if(month == 3){
            if (day < 20)
                user.setWzodiac(12);
            else
                user.setWzodiac(1);
        }
        else if (month == 4){
            if (day < 20)
                user.setWzodiac(1);
            else
                user.setWzodiac(2);
        }

        else if (month == 5){
            if (day < 21)
                user.setWzodiac(2);
            else
                user.setWzodiac(3);
        }

        else if( month == 6){
            if (day < 21)
                user.setWzodiac(3);
            else
                user.setWzodiac(4);
        }

        else if (month == 7){
            if (day < 22)
                user.setWzodiac(4);
            else
                user.setWzodiac(5);
        }

        else if( month == 8){
            if (day < 23)
                user.setWzodiac(5);
            else
                user.setWzodiac(6);
        }

        else if (month == 9){
            if (day < 23)
                user.setWzodiac(6);
            else
                user.setWzodiac(7);
        }

        else if (month == 10){
            if (day < 23)
                user.setWzodiac(7);
            else
                user.setWzodiac(8);
        }

        else if (month == 11){
            if (day < 22)
                user.setWzodiac(8);
            else
                user.setWzodiac(9);
        }
        return user;
    }

    private User setCZodiacFK(String[] ymd,User user){
       int[] ZODIAC = {1900, 1901, 1902, 1903, 1904, 1905, 1906, 1907, 1908, 1909, 1910, 1911};
       int year = Integer.parseInt(ymd[0]);
        for (int i=0; i< ZODIAC.length;i++) {
            if ((year - ZODIAC[i]) %12 == 0)
                user.setCzodiac(i+1);
        }
        return user;
    }

}
