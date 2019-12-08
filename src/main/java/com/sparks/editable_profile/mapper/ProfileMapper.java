package com.sparks.editable_profile.mapper;

import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Used when there are more than one profiles returned
     *
     * @param profileList
     * @return
     */
    public List<ProfileDto> getProfileDtoList(List<Profile> profileList) {
        List<ProfileDto> profileDtoList = profileList.stream().map(profile -> modelMapper.map(profile, ProfileDto.class)).collect(Collectors.toList());
        return profileDtoList;
    }


}
