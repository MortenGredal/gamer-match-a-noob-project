package dk.game.service;

import dk.game.entity.Credits;
import dk.game.entity.Skillz;
import dk.game.repository.CreditsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditsRepository creditsRepository;

    public void addCreditsToUser(Integer userId, int creditsToAdd) {
        Credits credits = creditsRepository.findByUser_Id(userId).orElseThrow(() -> new IllegalArgumentException("No user was found with ID" + userId));
        credits.setCredit(credits.getCredit() + creditsToAdd);
        creditsRepository.save(credits);
    }

    public Optional<Credits> getHighestForGameAndSkill(String game, Skillz skillz) {
        return creditsRepository.findTopByGame_nameAndUser_SkillOrderByCreditDesc(game, skillz);
    }

    public List<Credits> getCreditsByGame(String game) {
        return creditsRepository.findAllByGame_Name(game);
    }

    public List<Credits> getHighScores() {
        return null;
    }
}
