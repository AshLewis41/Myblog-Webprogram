package com.myblog.controller;

import com.myblog.model.BlogProfile;
import com.myblog.model.PersonalProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class profile {
    @GetMapping("/profile")
    public PersonalProfile profile() {
        PersonalProfile profile = new PersonalProfile();
        profile.blogName = "March's blog!";
        profile.owner = "March";
        profile.introduction = "A university student in Guangdong province China.";
        profile.interests = "Computer Game, Photograph";
        profile.vision = "1.0.0";
        return profile;
    }
}
