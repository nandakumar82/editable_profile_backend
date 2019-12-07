package com.sparks.editable_profile.mapper;

import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * Created by Nandak on Dec, 2019
 */
@SpringBootTest
public class ProfileMapperTest {

    private Profile profile;

    private ProfileDto profileDto;

    @Autowired
    private ProfileMapper profileMapper;


    @Test
    void testGetProfileDto() {
        profile = new Profile();
        profile.setDisplayName("NandaK");
        profile.setRealName("Nanda Kumar");
        profile.setProfilePicture("kjdnfkjasndfkjasndfnasdkjfnasdnfkajsdnfasdnfnasdnfkansdfkasdfnnsda");
        profile.setBirthday(new Date());
        profile.setGender("MALE");
        profile.setEthnicity("Other");
        profile.setReligion("Hindu");
        profile.setFigure("Athletic");
        profile.setMaritalStatus("Married");
        profile.setOccupation("Engineer");
        profile.setAboutMe("I am a software engineer");
        profile.setLocation("Innsbruck");
        ProfileDto profileDto = profileMapper.getProfileDto(profile);
        Assert.notNull(profileDto);
    }

    @Test
    void testGetProfile() {
        profileDto = new ProfileDto();
        profileDto.setDisplayName("NandaK");
        profileDto.setRealName("Nanda Kumar");
        profileDto.setProfilePicture("kjdnfkjasndfkjasndfnasdkjfnasdnfkajsdnfasdnfnasdnfkansdfkasdfnnsda");
        profileDto.setBirthday(new Date());
        profileDto.setGender("MALE");
        profileDto.setEthnicity("Other");
        profileDto.setReligion("Hindu");
        profileDto.setFigure("Athletic");
        profileDto.setMaritalStatus("Married");
        profileDto.setOccupation("Engineer");
        profileDto.setAboutMe("I am a software engineer");
        profileDto.setLocation("Innsbruck");
        Profile profile = profileMapper.getProfile(profileDto);
        Assert.notNull(profile);
    }
}