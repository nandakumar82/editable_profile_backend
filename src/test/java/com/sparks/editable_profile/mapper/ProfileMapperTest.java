package com.sparks.editable_profile.mapper;

import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
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

    @Test
    void testGetProfileDto() {
        profile = new Profile();
        profile.setDisplayName("NandaK");
        profile.setRealName("Nanda Kumar");
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
        Assert.assertNotNull(profileDto);
        Assert.assertEquals("Display name", profileDto.getDisplayName(), profile.getDisplayName());
        Assert.assertEquals("Real Name", profileDto.getRealName(), profile.getRealName());
        Assert.assertEquals("Birthday", profileDto.getBirthday(), profile.getBirthday());
        Assert.assertEquals("Gender", profileDto.getGender(), profile.getGender());
        Assert.assertEquals("Ethnicity", profileDto.getEthnicity(), profile.getEthnicity());
        Assert.assertEquals("Religion", profileDto.getReligion(), profile.getReligion());
        Assert.assertEquals("Figure", profileDto.getFigure(), profile.getFigure());
        Assert.assertEquals("MaritalStatus", profileDto.getMaritalStatus(), profile.getMaritalStatus());
        Assert.assertEquals("Occupation", profileDto.getOccupation(), profile.getOccupation());
        Assert.assertEquals("AboutMe", profileDto.getAboutMe(), profile.getAboutMe());
        Assert.assertEquals("Location", profileDto.getLocation(), profile.getLocation());
    }

    @Test
    void testGetProfile() {
        profileDto = new ProfileDto();
        profileDto.setDisplayName("NandaK");
        profileDto.setRealName("Nanda Kumar");
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

        Assert.assertNotNull(profile);
        Assert.assertEquals("Display name", profile.getDisplayName(), profileDto.getDisplayName());
        Assert.assertEquals("Real Name", profile.getRealName(), profileDto.getRealName());
        Assert.assertEquals("Birthday", profile.getBirthday(), profileDto.getBirthday());
        Assert.assertEquals("Gender", profile.getGender(), profileDto.getGender());
        Assert.assertEquals("Ethnicity", profile.getEthnicity(), profileDto.getEthnicity());
        Assert.assertEquals("Religion", profile.getReligion(), profileDto.getReligion());
        Assert.assertEquals("Figure", profile.getFigure(), profileDto.getFigure());
        Assert.assertEquals("MaritalStatus", profile.getMaritalStatus(), profileDto.getMaritalStatus());
        Assert.assertEquals("Occupation", profile.getOccupation(), profileDto.getOccupation());
        Assert.assertEquals("AboutMe", profile.getAboutMe(), profileDto.getAboutMe());
        Assert.assertEquals("Location", profile.getLocation(), profileDto.getLocation());
    }

    @Test
    void testGetProfileDtoList() {
        List<ProfileDto> profileDtoList = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();

        profileDto = new ProfileDto();
        profileDto.setDisplayName("NandaK");
        profileDto.setRealName("Nanda Kumar");
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
        Assert.assertEquals(2, profileMapper.getProfileDtoList(profileList).size());
    }
}