package com.sparks.editable_profile.repositories;

import com.sparks.editable_profile.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nandak on Dec, 2019
 */
@Repository
public interface EditableProfileRepository extends MongoRepository<Profile, String> {
    List<Profile> findByDisplayNameContaining(String displayName);

    Profile findByDisplayNameAndPassPhrase(String displayName, String passPhrase);
}
