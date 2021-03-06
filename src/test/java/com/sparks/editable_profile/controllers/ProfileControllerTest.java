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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import com.sparks.editable_profile.validator.CustomBeanValidator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by Nandak on Dec, 2019
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    public static final String PROFILE_ID = "1234";
    private MockMvc mvc;

    @Mock
    private ProfileService profileService;

    @Mock
    private CustomBeanValidator customBeanValidator;

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
                post("/api/create/profile").contentType(MediaType.ALL_VALUE).param("profile", "{\"displayName\":\"kooper\",\"passPhrase\":\"kooper\",\"realName\":\"kooper\",\"birthday\":\"2019-12-02\",\"gender\":\"Male\",\"ethnicity\":\"South Asian\",\"religion\":\"Atheist\",\"height\":\"173\",\"figure\":\"Normal\",\"maritalStatus\":\"Separated\",\"occupation\":\"\",\"aboutMe\":\"\",\"location\":\"test\"}")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void testGetOtherUserView() throws Exception {
        List<ProfileDto> profileDtoList = new ArrayList<>();
        profileDtoList.add(profileDto);
        profileDtoList.add(profileDto);
        when(profileService.getOtherUserView("nandak")).thenReturn(profileDtoList);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/view/allprofile/nandak")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[{\"displayName\":\"nandak\",\"passPhrase\":\"indutti\",\"id\":null,\"realName\":\"Nanda Kumar\",\"profilePicture\":null,\"birthday\":null,\"gender\":\"MALE\",\"ethnicity\":\"Other\",\"religion\":\"Hindu\",\"height\":\"173\",\"figure\":\"Athletic\",\"maritalStatus\":\"Married\",\"occupation\":\"Engineer\",\"aboutMe\":\"I am a software engineer\",\"location\":\"Innsbruck\"},{\"displayName\":\"nandak\",\"passPhrase\":\"indutti\",\"id\":null,\"realName\":\"Nanda Kumar\",\"profilePicture\":null,\"birthday\":null,\"gender\":\"MALE\",\"ethnicity\":\"Other\",\"religion\":\"Hindu\",\"height\":\"173\",\"figure\":\"Athletic\",\"maritalStatus\":\"Married\",\"occupation\":\"Engineer\",\"aboutMe\":\"I am a software engineer\",\"location\":\"Innsbruck\"}]"
        );
    }

    @Test
    public void testCurrentUserView() throws Exception {
        ProfileLoginDto profileLoginDto = new ProfileLoginDto();
        profileLoginDto.setPassPhrase("indutti");
        profileLoginDto.setDisplayName("nandak");
        when(profileService.getCurrentUserView("nandak", "indutti")).thenReturn(profileDto);

        MockHttpServletResponse response = mvc.perform(
                post("/api/view/myprofile/").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"displayName\": \"nandak\",\n" +
                                "    \"passPhrase\": \"indutti\"\n" +
                                "}"
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"displayName\":\"nandak\",\"passPhrase\":\"indutti\",\"id\":null,\"realName\":\"Nanda Kumar\",\"profilePicture\":null,\"birthday\":null,\"gender\":\"MALE\",\"ethnicity\":\"Other\",\"religion\":\"Hindu\",\"height\":\"173\",\"figure\":\"Athletic\",\"maritalStatus\":\"Married\",\"occupation\":\"Engineer\",\"aboutMe\":\"I am a software engineer\",\"location\":\"Innsbruck\"}"
        );

    }

    @Test
    public void when_delete_success_then_return_ok() throws Exception {
        when(profileService.deleteProfile(PROFILE_ID)).thenReturn(true);
        MockHttpServletResponse response = deleteProfile(PROFILE_ID);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void when_delete_unsuccessful_return_500() throws Exception {
        when(profileService.deleteProfile(PROFILE_ID)).thenReturn(false);
        MockHttpServletResponse response = deleteProfile(PROFILE_ID);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private MockHttpServletResponse deleteProfile(String profileId) throws Exception {
        return mvc.perform(
                delete("/api/profile/" + profileId)).andReturn().getResponse();
    }

}