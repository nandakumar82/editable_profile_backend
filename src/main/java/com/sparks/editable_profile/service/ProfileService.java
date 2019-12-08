package com.sparks.editable_profile.service;

import com.sparks.editable_profile.exception.RecordNotFoundException;
import com.sparks.editable_profile.mapper.ProfileMapper;
import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import com.sparks.editable_profile.repositories.EditableProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nandak on Dec, 2019
 */
@Slf4j
@Service
public class ProfileService {

    @Autowired
    private EditableProfileRepository editableProfileRepository;
    @Autowired
    private ProfileMapper profileMapper;

    /**
     * This method is used for New Profile creations, if an id is present then Update the profile
     *
     * @param profileDto
     * @return
     */
    public ProfileDto saveProfile(ProfileDto profileDto) {
        log.info("Inside saveProfile {}", profileDto);
        Profile profile = profileMapper.getProfile(profileDto);
        try {
            editableProfileRepository.save(profile);
        } catch (Exception e) {
           if(e.getMessage().contains("E11000 duplicate key error collection")) {
               log.info("Duplicate Error caught");
           }
        }
        return profileMapper.getProfileDto(profile);
    }

    /**
     * This method is used for Other users to view someone's profile
     *
     * @param displayName
     * @return
     */
    public List<ProfileDto> getOtherUserView(String displayName) {
        log.info("Inside getOtherUserView {}", displayName);
        List<Profile> profileList = editableProfileRepository.findByDisplayNameContaining(displayName);
        if (null == profileList || profileList.isEmpty()) {
            throw new RecordNotFoundException("The Record is not found!!!!, Please try again");
        }
        return profileMapper.getProfileDtoList(profileList);
    }

    /**
     * This function is used for the Current users to view their profile
     *
     * @param displayName
     * @param passPhrase
     * @return
     */
    public ProfileDto getCurrentUserView(String displayName, String passPhrase) {
        log.info("Inside getCurrentUserView {},{}", displayName, passPhrase);
        Profile profile = editableProfileRepository.findByDisplayNameAndPassPhrase(displayName, passPhrase);
        if (null == profile) {
            throw new RecordNotFoundException("The Record is not found!!!!, Please try again");
        }
        return profileMapper.getProfileDto(profile);
    }

}
