package com.sparks.editable_profile.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * Created by Nandak on Dec, 2019
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class ProfileDto extends ProfileLoginDto {
    @Id
    private String id;
    @NotBlank(message = "{profile.field.realName.required}")
    @Size(max = 256)
    private String realName;
    private String profilePicture;
    @NotNull(message = "{profile.field.birthday.required}")
    private Date birthday;
    @NotBlank(message = "{profile.field.gender.required}")
    private String gender;
    private String ethnicity;
    private String religion;
    private String height;
    private String figure;
    @NotBlank(message = "{profile.field.maritalStatus.required}")
    private String maritalStatus;
    @Size(max = 256)
    private String occupation;
    @Size(max = 5000)
    private String aboutMe;
    @NotBlank(message = "{profile.field.location.required}")
    private String location;
}
