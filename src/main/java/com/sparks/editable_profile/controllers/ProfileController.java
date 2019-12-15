package com.sparks.editable_profile.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller exposes apis used for consumption by the UI tier
 * <p>
 * Created by Nandak on Dec, 2019
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "view/allprofile/{displayName}")
    public List<ProfileDto> getOtherUserView(@PathVariable("displayName") String displayName) {
        return profileService.getOtherUserView(displayName);
    }

    @PostMapping("/create/profile")
    public ResponseEntity<ProfileDto> createProfile(@RequestParam("profile") String profileDtoJsonString, @RequestParam("image") MultipartFile profilePic) throws JsonProcessingException {
        ProfileDto profileDto = new ObjectMapper().readValue(profileDtoJsonString, ProfileDto.class);
        System.out.println(profileDto);
        return new ResponseEntity<>(profileService.saveProfile(profileDto, profilePic), HttpStatus.CREATED);
    }

    @PostMapping(value = "view/myprofile")
    public ResponseEntity<ProfileDto> getCurrentUserView(@Valid @RequestBody ProfileLoginDto profileLoginDto) {
        ProfileDto profileDto = profileService.getCurrentUserView(profileLoginDto.getDisplayName(), profileLoginDto.getPassPhrase());
        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @GetMapping(value = "view/profile/{profileId}")
    public ProfileDto getProfile(@PathVariable("profileId") String profileId) {
        return profileService.getProfile(profileId);
    }

}
