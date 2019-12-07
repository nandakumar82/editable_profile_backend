package com.sparks.editable_profile.mapper;

import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Created by Nandak on Dec, 2019
 */
@Component
public class ProfileMapper {


    private ModelMapper modelMapper = new ModelMapper();

    /**
     * Used during the fetch operations
     *
     * @param profile
     * @return
     */
    public ProfileDto getProfileDto(Profile profile) {
        return modelMapper.map(profile, ProfileDto.class);
    }

    /**
     * Used during the Save operations
     *
     * @param profileDto
     * @return
     */
    public Profile getProfile(ProfileDto profileDto) {
        return modelMapper.map(profileDto, Profile.class);
    }
}
