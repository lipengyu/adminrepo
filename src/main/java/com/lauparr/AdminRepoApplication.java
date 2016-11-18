package com.lauparr;

import com.google.common.collect.Maps;
import com.lauparr.configuration.SecurityConfig;
import com.lauparr.model.Profile;
import com.lauparr.model.Role;
import com.lauparr.model.User;
import com.lauparr.repository.ProfileRepository;
import com.lauparr.repository.RoleRepository;
import com.lauparr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.util.Map;

@SpringBootApplication
public class AdminRepoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        try {
            SpringApplication.run(AdminRepoApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        profileRepository.deleteAll();
        roleRepository.deleteAll();

        Map<String, Role> roles = getRoles();
        Map<String, Profile> profiles = getProfiles(roles);
        Map<String, User> users = getUsers(profiles);
    }

    private Map<String, Role> getRoles() {
        Map<String, Role> result = Maps.newHashMap();
        result.put("ROLE_REFERENTIEL", new Role("ROLE_REFERENTIEL", "Gestion des référentiel"));
        result.put("ROLE_PROJET", new Role("ROLE_PROJET", "Gestion des projets"));
        result.put("ROLE_UTILISATEUR", new Role("ROLE_UTILISATEUR", "Gestion des utilisateurs"));
        roleRepository.save(result.values());
        return result;
    }

    private Map<String, Profile> getProfiles(Map<String, Role> roles) {
        Map<String, Profile> result = Maps.newHashMap();
        result.put("ADMIN", new Profile("ADMIN", "Compte administrateur", true, roles.get("ROLE_REFERENTIEL"), roles.get("ROLE_PROJET"), roles.get("ROLE_UTILISATEUR")));
        result.put("USER", new Profile("USER", "Compte utilisateur", true, roles.get("ROLE_PROJET")));
        result.put("INVITED", new Profile("INVITED", "Compte invité", true));
        profileRepository.save(result.values());
        return result;
    }

    private Map<String, User> getUsers(Map<String, Profile> profiles) {
        Map<String, User> result = Maps.newHashMap();

        User lparrot = new User("laurent.parrot@gmail.com", SecurityConfig.PASSWORD_ENCODER.encode("123"), "PARROT", "Laurent");
        lparrot.setBirthday(LocalDate.of(1983, 9, 5));
        lparrot.setProfile(profiles.get("ADMIN"));

        User lvaugeois = new User("loic.vaugeois@gmail.com", SecurityConfig.PASSWORD_ENCODER.encode("123"), "VAUGEOIS", "Loic");
        lvaugeois.setBirthday(LocalDate.of(1983, 9, 4));
        lvaugeois.setProfile(profiles.get("USER"));

        result.put("lparrot", lparrot);
        result.put("lvaugeois", lvaugeois);

        userRepository.save(result.values());

        return result;
    }
}
