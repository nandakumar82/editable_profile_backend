package com.sparks.editable_profile.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * Created by Nandak on Dec, 2019
 */
@Getter
@Setter
@NoArgsConstructor
@Component
public class ProfileLoginDto {
    @NotEmpty(message = "{profileLogin.field.displayName.required}")
    @Size(max = 256)
    @Indexed(unique = true)
    private String displayName;
    @NotBlank(message = "{profileLogin.field.passPhrase.required}")
    @Size(max = 8)
    private String passPhrase;
}
