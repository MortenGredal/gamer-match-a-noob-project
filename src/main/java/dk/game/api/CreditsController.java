package dk.game.api;

import dk.game.entity.Credit;
import dk.game.entity.SkillLevel;
import dk.game.service.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
@Validated
@Tag(name = "Credits")
public class CreditsController {

    private final CreditService service;

    @GetMapping("/game/{game}")
    @Operation(summary = "Get all credits for a game")
    public ResponseEntity<List<Credit>> getCreditsForGame(@Parameter(example = "fortnite") @PathVariable("game") @NotBlank(message = "Game name cannot be empty") String game) {
        return ok(service.getCreditsByGame(game));
    }

    @PostMapping("/user/{userid}/credit")
    @Operation(summary = "Update credits for user")
    public ResponseEntity<Void> updateCreditsForUser(@PathVariable("userid") @NotNull Integer userid,
                                                     @RequestParam("credits") @Min(value = 0, message = "The value must be positive") Integer credits) {
        service.addCreditsToUser(userid, credits);
        return noContent().build();
    }

    @GetMapping("/winner/{game}/{skill}")
    @Operation(summary = "Get high score for game and skill")
    public ResponseEntity<Credit> getMaxCreditForGame(@Parameter(example = "fortnite") @PathVariable("game") @NotBlank(message = "Game name cannot be empty") String game,
                                                      @NotNull @PathVariable("skill") SkillLevel skillLevel) {
        return of(service.getHighestForGameAndSkill(game, skillLevel));
    }

    @GetMapping("/highscores")
    @Operation(summary = "Not working :( - Get high scores grouped by game and skill")
    public ResponseEntity<List<Credit>> gethighScores() {
        return ok(service.getHighScores());
    }

}
