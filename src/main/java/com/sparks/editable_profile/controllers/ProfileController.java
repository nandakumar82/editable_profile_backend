package com.sparks.editable_profile.controllers;

import com.sparks.editable_profile.models.ProfileDto;
import com.sparks.editable_profile.models.ProfileLoginDto;
import com.sparks.editable_profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Nandak on Dec, 2019
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/profile")
    public ResponseEntity<ProfileDto> createProfile(@Valid @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.saveProfile(profileDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/profile/{displayName}")
    public List<ProfileDto> getOtherUserView(@PathVariable("displayName") String displayName) {
        return profileService.getOtherUserView(displayName);
    }

    @PostMapping(value = "/profile/{displayName}")
    public ProfileDto getCurrentUserView( @Valid @RequestBody ProfileLoginDto profileLoginDto) {
        return profileService.getCurrentUserView(profileLoginDto.getDisplayName(), profileLoginDto.getPassPhrase());
    }

}
