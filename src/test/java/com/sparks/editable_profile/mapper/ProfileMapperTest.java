package com.sparks.editable_profile.mapper;

import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nandak on Dec, 2019
 */
@SpringBootTest
public class ProfileMapperTest {

    private Profile profile;
    private Profile profile1;


    private ProfileDto profileDto;
    private ProfileDto profileDto1;


    @Autowired
    private ProfileMapper profileMapper;

    //TODO asssert for all the values
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

    //TODO asssert for all the values
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

    @Test
    void testGetProfileDtoList() {
        List<ProfileDto> profileDtoList = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();

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

        profileDto1 = new ProfileDto();
        profileDto1.setDisplayName("NandaK");
        profileDto1.setRealName("Nanda Kumar");
        profileDto1.setProfilePicture("kjdnfkjasndfkjasndfnasdkjfnasdnfkajsdnfasdnfnasdnfkansdfkasdfnnsda");
        profileDto1.setBirthday(new Date());
        profileDto1.setGender("MALE");
        profileDto1.setEthnicity("Other");
        profileDto1.setReligion("Hindu");
        profileDto1.setFigure("Athletic");
        profileDto1.setMaritalStatus("Married");
        profileDto1.setOccupation("Engineer");
        profileDto1.setAboutMe("I am a software engineer");
        profileDto1.setLocation("Innsbruck");

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

        profile1 = new Profile();
        profile1.setDisplayName("NandaKumar");
        profile1.setRealName("Nanda Kumar");
        profile1.setProfilePicture("kjdnfkjasndfkjasndfnasdkjfnasdnfkajsdnfasdnfnasdnfkansdfkasdfnnsda");
        profile1.setBirthday(new Date());
        profile1.setGender("MALE");
        profile1.setEthnicity("Other");
        profile1.setReligion("Hindu");
        profile1.setFigure("Athletic");
        profile1.setMaritalStatus("Married");
        profile1.setOccupation("Engineer");
        profile1.setAboutMe("I am a software engineer");
        profile1.setLocation("Innsbruck");

        profileDtoList.add(profileDto);
        profileDtoList.add(profileDto1);

        profileList.add(profile);
        profileList.add(profile1);
        profileMapper.getProfileDtoList(profileList);
        Assert.isTrue(profileMapper.getProfileDtoList(profileList).size() == 2);

    }
}