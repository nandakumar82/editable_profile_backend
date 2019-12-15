package com.sparks.editable_profile.service;

import com.sparks.editable_profile.exception.RecordNotFoundException;
import com.sparks.editable_profile.mapper.ProfileMapper;
import com.sparks.editable_profile.models.Profile;
import com.sparks.editable_profile.models.ProfileDto;
import com.sparks.editable_profile.repositories.EditableProfileRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by Nandak on Dec, 2019
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

    @Mock
    private EditableProfileRepository editableProfileRepository;

    @Mock
    private ProfileMapper profileMapper;

    @InjectMocks
    private ProfileService profileService;

    private ProfileDto profileDto;

    private Profile profile;
    private Profile profile1;

    private MockMultipartFile profilePic = new MockMultipartFile("profile_pic", "profile_pic.jpg", "multipart/form-data", "some image".getBytes());

    @Before
    public void setUp() {
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


    }

    @Test
    public void testSaveProfile() {
        when(profileMapper.getProfile(profileDto)).thenReturn(profile);
        profile.setId("1234");
        when(profileMapper.getProfileDto(profile)).thenReturn(profileDto);
        profileService.saveProfile(profileDto, profilePic);
        verify(editableProfileRepository, times(1)).save(profile);
    }

    @Test
    public void testSaveProfileWhenProfileIsEmpty() {
        ProfileDto profileDto = new ProfileDto();
        Profile profile = new Profile();
        when(profileMapper.getProfile(profileDto)).thenReturn(profile);
        profile.setId("1234");
        when(profileMapper.getProfileDto(profile)).thenReturn(profileDto);
        profileService.saveProfile(profileDto, profilePic);
        verify(editableProfileRepository, times(1)).save(profile);
    }

    @Test
    public void testGetOtherUserView() {
        List<Profile> profileList = new ArrayList<>();
        profileList.add(profile);
        profileList.add(profile1);
        when(editableProfileRepository.findByDisplayNameContaining("NandaK")).thenReturn(profileList);
        profileService.getOtherUserView("NandaK");
        verify(profileMapper).getProfileDtoList(profileList);
    }

    @Test(expected = RecordNotFoundException.class)
    public void testGetOtherUserViewWhenProfileIsNotAvailablePostSearch() {
        when(editableProfileRepository.findByDisplayNameContaining("NandaK")).thenReturn(null);
        List<ProfileDto> profileDtoList = profileService.getOtherUserView("NandaK");
        verify(profileMapper, times(0)).getProfileDto(null);
    }

    @Test
    public void testGetCurrentUserView() {
        when(editableProfileRepository.findByDisplayNameAndPassPhrase("NandaK", "indutti")).thenReturn(profile);
        when(profileMapper.getProfileDto(profile)).thenReturn(profileDto);
        ProfileDto currentUserView = profileService.getCurrentUserView("NandaK", "indutti");
        Assert.assertNotNull(currentUserView);
        verify(profileMapper, times(1)).getProfileDto(profile);
    }

    @Test(expected = RecordNotFoundException.class)
    public void testGetCurrentUserViewWhenProfileIsNotAvailablePostSearch() {
        when(editableProfileRepository.findByDisplayNameAndPassPhrase("NandaK", "indutti")).thenReturn(null);
        ProfileDto currentUserView = profileService.getCurrentUserView("NandaK", "indutti");
        Assert.assertNull(currentUserView);
        verify(profileMapper, times(0)).getProfileDto(profile);
    }

}