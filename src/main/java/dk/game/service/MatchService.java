package dk.game.service;

import dk.game.entity.Game;
import dk.game.entity.Region;
import dk.game.entity.SkillLevel;
import dk.game.entity.User;
import dk.game.repository.GameRepository;
import dk.game.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MatchService {

    private final UserRepository userRepository;

    public MatchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsersBySkillRegionAndGame(Region region, SkillLevel skillLevel, Set<String> games) {
        return userRepository.findDistinctBySkillAndRegionAndGames_NameIsIn(skillLevel, region, games);
    }
}
