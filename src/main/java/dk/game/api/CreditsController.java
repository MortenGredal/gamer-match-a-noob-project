package dk.game.api;

import dk.game.entity.Credits;
import dk.game.entity.Skillz;
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
    public ResponseEntity<List<Credits>> getCreditsForGame(@Parameter(example = "fortnite") @PathVariable("game") @NotBlank(message = "Game name cannot be empty") String game) {
        return ok(service.getCreditsByGame(game));
    }

    @PostMapping("/user/credit/{userid}")
    @Operation(summary = "Update credits for user")
    public ResponseEntity<Void> updateCreditsForUser(@PathVariable("userid") @NotNull Integer userid,
                                                     @RequestParam("credits") @Min(value = 0, message = "The value must be positive") Integer creditsToAdd) {
        service.addCreditsToUser(userid, creditsToAdd);
        return noContent().build();
    }

    @GetMapping("/winner/{game}/{skill}")
    @Operation(summary = "Get high score for game and skill")
    public ResponseEntity<Credits> getMaxCreditForGame(@Parameter(example = "fortnite") @PathVariable("game") @NotBlank(message = "Game name cannot be empty") String game,
                                                       @NotNull @PathVariable("skill")Skillz skillz) {
        return of(service.getHighestForGameAndSkill(game, skillz));
    }

    @GetMapping("/highscores")
    @Operation(summary = "Not working :( - Get high scores grouped by game and skill")
    public ResponseEntity<List<Credits>> gethighScores() {
        return ok(service.getHighScores());
    }

}
