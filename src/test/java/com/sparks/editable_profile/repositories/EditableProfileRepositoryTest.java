package com.sparks.editable_profile.repositories;

import com.sparks.editable_profile.models.Profile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Nandak on Dec, 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EditableProfileRepositoryTest {

    @Autowired
    private EditableProfileRepository editableProfileRepository;
    private Profile save;

    @Before
    public void setUp() {
        Profile profile = new Profile();
        profile.setDisplayName("NandaK");
        profile.setPassPhrase("indutti");
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
        save = editableProfileRepository.save(profile);
    }


    @Test
    public void testSaveProfile() {
        Assert.assertNotNull(save.getId());
    }

    @Test
    public void testFetchProfileForMyProfile() {
        Profile byDisplayNameAndPassPhrase = editableProfileRepository.findByDisplayNameAndPassPhrase(save.getDisplayName(), save.getPassPhrase());
        Assert.assertNotNull(byDisplayNameAndPassPhrase.getId());
        Assert.assertEquals("indutti", byDisplayNameAndPassPhrase.getPassPhrase());
    }

    @Test
    public void testFetchProfileOtherUserView() {
        Profile profile = editableProfileRepository.findByDisplayName(save.getDisplayName());
        Assert.assertNotNull(profile);
    }

    @After
    public void tearDown() {
        editableProfileRepository.deleteAll();
    }
}