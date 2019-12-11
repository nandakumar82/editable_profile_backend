package com.sparks.editable_profile.repositories;

import com.sparks.editable_profile.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The cllass used to establish connection to MongoDB
 * Created by Nandak on Dec, 2019
 */
@Repository
public interface EditableProfileRepository extends MongoRepository<Profile, String> {
    /**
     * Repository method for fetching data based on the displayName
     * @param displayName the input param used for the selection
     * @return List of Profiles matching the criteria
     */
    List<Profile> findByDisplayNameContaining(String displayName);

    /**
     * Repository method used for login in to the user's profile
     * @param displayName the displayname for the user
     * @param passPhrase the password to identify the user and grant access
     * @return the user's profile is returned
     */
    Profile findByDisplayNameAndPassPhrase(String displayName, String passPhrase);
}
