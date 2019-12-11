package com.sparks.editable_profile.mapper;

import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to convert the DTOs to DB models and Vice versa
 * Created by Nandak on Dec, 2019
 */
@Component
public class ProfileMapper {


    private ModelMapper modelMapper = new ModelMapper();

    /**
     * Used during the fetch operations
     *
     * @param profile a profile object
     * @return a profile DTO object
     */
    public ProfileDto getProfileDto(Profile profile) {
        return modelMapper.map(profile, ProfileDto.class);
    }

    /**
     * Used during the Save operations
     *
     * @param profileDto a profile DTO object
     * @return a profile object
     */
    public Profile getProfile(ProfileDto profileDto) {
        return modelMapper.map(profileDto, Profile.class);
    }

    /**
     * Used when there are more than one profiles returned
     *
     * @param profileList input a list of profile objects returned from the DB to be converted to DTO objects
     * @return a list of profile dto objects
     */
    public List<ProfileDto> getProfileDtoList(List<Profile> profileList) {
        return profileList.stream().map(profile -> modelMapper.map(profile, ProfileDto.class)).collect(Collectors.toList());
    }


}
