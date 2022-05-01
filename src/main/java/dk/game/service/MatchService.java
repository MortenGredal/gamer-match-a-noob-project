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
    private final GameRepository gameRepository;

    public MatchService(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public List<User> findUsersBySkillRegionAndGame(Region region, SkillLevel skillLevel, Set<String> games) {
        return userRepository.findAllBySkillAndRegionAndGames_NameIsIn(skillLevel, region, games);
    }
}
