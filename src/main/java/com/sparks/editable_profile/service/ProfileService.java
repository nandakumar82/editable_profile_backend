package com.sparks.editable_profile.service;

import com.sparks.editable_profile.exception.DuplicateRecordFoundException;
import com.sparks.editable_profile.exception.RecordNotFoundException;
import com.sparks.editable_profile.mapper.ProfileMapper;
import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import com.sparks.editable_profile.repositories.EditableProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
     * @param profileDto The DTO object containing the data from the client side
     * @return returns a DTO object to be available to client
     */
    public ProfileDto saveProfile(ProfileDto profileDto, MultipartFile profilePic) {
        log.info("Inside saveProfile {}", profileDto);
        Profile profile = null;
        try {
            if(null != profilePic)
                profileDto.setProfilePicture(new Binary(BsonBinarySubType.BINARY, profilePic.getBytes()));
            profile = profileMapper.getProfile(profileDto);
            editableProfileRepository.save(profile);
        } catch (IOException e) {
            log.error("Exception Thrown while converting picture to binary " + e.getMessage());
        } catch (Exception e) {
            if (e.getMessage().contains("E11000 duplicate key error collection")) {
                log.info("Duplicate Error caught");
                throw new DuplicateRecordFoundException("The Username is already taken, please try again with another Username");

            }
        }
        return profileMapper.getProfileDto(profile);
    }

    /**
     * This method is used for Other users to view someone's profile
     *
     * @param displayName The field is used to search for a profile available online
     * @return a list of DTO objects to the client
     */
    public List<ProfileDto> getOtherUserView(String displayName) {
        log.info("Inside getOtherUserView {}", displayName);
        List<Profile> profileList = editableProfileRepository.findByDisplayNameContaining(displayName);
        if (null == profileList || profileList.isEmpty()) {
            recordNotFound();
        }
        return profileMapper.getProfileDtoList(profileList);
    }

    /**
     * This function is used for the Current users to view their profile
     *
     * @param displayName the field used to login to an user profile
     * @param passPhrase  the password used to login to an user profile
     * @return a DTO object to the client
     */
    public ProfileDto getCurrentUserView(String displayName, String passPhrase) {
        log.info("Inside getCurrentUserView {},{}", displayName, passPhrase);
        Profile profile = editableProfileRepository.findByDisplayNameAndPassPhrase(displayName, passPhrase);
        if (null == profile) {
            return recordNotFound();
        }
        return profileMapper.getProfileDto(profile);
    }

    /**
     * A common method to handle RecordNotFound exception
     *
     * @return a profile dto object
     */
    private ProfileDto recordNotFound() {
        throw new RecordNotFoundException("The Record is not found!!!!, Please try again");
    }

    /**
     * This method is used to pick a specific profile from a list of profiles on the screen
     *
     * @param profileId the id of the profile to be fetched
     * @return profileDto
     */
    public ProfileDto getProfile(String profileId) {
        log.info("Inside getProfile {}", profileId);
        Profile profile = null;
        Optional<Profile> optionalProfile = editableProfileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            profile = optionalProfile.get();
        }
        return profileMapper.getProfileDto(profile);
    }
}
