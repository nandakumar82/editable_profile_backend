package com.sparks.editable_profile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparks.editable_profile.models.ProfileDto;
import com.sparks.editable_profile.models.ProfileLoginDto;
import com.sparks.editable_profile.service.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by Nandak on Dec, 2019
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    private MockMvc mvc;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    private ProfileDto profileDto;


    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(profileController)
                .build();
        profileDto = new ProfileDto();
        profileDto.setDisplayName("nandak");
        profileDto.setPassPhrase("indutti");
        profileDto.setRealName("Nanda Kumar");
        profileDto.setProfilePicture("kjdnfkjasndfkjasndfnasdkjfnasdnfkajsdnfasdnfnasdnfkansdfkasdfnnsda");
        profileDto.setGender("MALE");
        profileDto.setEthnicity("Other");
        profileDto.setReligion("Hindu");
        profileDto.setFigure("Athletic");
        profileDto.setHeight("173");
        profileDto.setMaritalStatus("Married");
        profileDto.setOccupation("Engineer");
        profileDto.setAboutMe("I am a software engineer");
        profileDto.setLocation("Innsbruck");

    }

    @Test
    public void testCreateProfile() throws Exception {

        MockHttpServletResponse response = mvc.perform(
                post("/api/profile").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"displayName\": \"nandak\",\n" +
                                "    \"passPhrase\": \"1nduti\",\n" +
                                "    \"realName\": \"NandaKumar\",\n" +
                                "    \"profilePicture\": \"giuhojflajfasfk;lasdkfa;sdfk;asdfk;asdkfasd;lk\",\n" +
                                "    \"birthday\": \"1982-12-14T00:00:00.000+0000\",\n" +
                                "    \"gender\": \"Male\",\n" +
                                "    \"ethnicity\": \"Others\",\n" +
                                "    \"religion\": \"Hindu\",\n" +
                                "    \"height\": \"173\",\n" +
                                "    \"figure\": \"Atheletic\",\n" +
                                "    \"maritalStatus\": \"Married\",\n" +
                                "    \"occupation\": \"Engineer\",\n" +
                                "    \"aboutMe\": \"lksdflsmfs;dlf;asdlfka\",\n" +
                                "    \"location\": \"sdfasdfsadfasdfasdfadsfads\"\n" +
                                "}"
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

 /*   @Test
    public void testGetOtherUserView() throws Exception {


        when(profileService.getOtherUserView("nandak")).thenReturn(profileDto);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/profile/nandak")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"displayName\":\"nandak\",\"passPhrase\":\"indutti\",\"id\":null,\"realName\":\"Nanda Kumar\",\"profilePicture\":\"kjdnfkjasndfkjasndfnasdkjfnasdnfkajsdnfasdnfnasdnfkansdfkasdfnnsda\",\"birthday\":null,\"gender\":\"MALE\",\"ethnicity\":\"Other\",\"religion\":\"Hindu\",\"height\":\"173\",\"figure\":\"Athletic\",\"maritalStatus\":\"Married\",\"occupation\":\"Engineer\",\"aboutMe\":\"I am a software engineer\",\"location\":\"Innsbruck\"}"
        );
    }
*/

    @Test
    public void testCurrentUserView() throws Exception {
        ProfileLoginDto profileLoginDto = new ProfileLoginDto();
        profileLoginDto.setPassPhrase("indutti");
        profileLoginDto.setDisplayName("nandak");
        when(profileService.getCurrentUserView("nandak", "indutti")).thenReturn(profileDto);

        MockHttpServletResponse response = mvc.perform(
                post("/api/profile/nandak").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"displayName\": \"nandak\",\n" +
                                "    \"passPhrase\": \"indutti\"\n" +
                                "}"
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"displayName\":\"nandak\",\"passPhrase\":\"indutti\",\"id\":null,\"realName\":\"Nanda Kumar\",\"profilePicture\":\"kjdnfkjasndfkjasndfnasdkjfnasdnfkajsdnfasdnfnasdnfkansdfkasdfnnsda\",\"birthday\":null,\"gender\":\"MALE\",\"ethnicity\":\"Other\",\"religion\":\"Hindu\",\"height\":\"173\",\"figure\":\"Athletic\",\"maritalStatus\":\"Married\",\"occupation\":\"Engineer\",\"aboutMe\":\"I am a software engineer\",\"location\":\"Innsbruck\"}"
        );

    }
}