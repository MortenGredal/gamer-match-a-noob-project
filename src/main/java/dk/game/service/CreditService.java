package dk.game.service;

import dk.game.entity.Credit;
import dk.game.entity.SkillLevel;
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
        Credit credit = creditsRepository.findByUser_Id(userId).orElseThrow(() -> new IllegalArgumentException("No user was found with ID" + userId));
        credit.setCredit(credit.getCredit() + creditsToAdd);
        creditsRepository.save(credit);
    }

    public Optional<Credit> getHighestForGameAndSkill(String game, SkillLevel skillLevel) {
        return creditsRepository.findTopByGame_nameAndUser_SkillOrderByCreditDesc(game, skillLevel);
    }

    public List<Credit> getCreditsByGame(String game) {
        return creditsRepository.findAllByGame_Name(game);
    }

    public List<Credit> getHighScores() {
        return null;
    }
}
