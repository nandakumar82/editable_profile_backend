package com.sparks.editable_profile.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * Created by Nandak on Dec, 2019
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "profile")
public class Profile {
    @Id
    private String id;
    @Indexed(unique = true)
    private String displayName;
    private String realName;
    private String passPhrase;
    private String profilePicture;
    private Date birthday;
    private String gender;
    private String ethnicity;
    private String religion;
    private String height;
    private String figure;
    private String maritalStatus;
    private String occupation;
    private String aboutMe;
    private String location;
}
