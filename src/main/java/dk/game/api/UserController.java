package dk.game.api;

import dk.game.entity.Region;
import dk.game.entity.SkillLevel;
import dk.game.entity.User;
import dk.game.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Validated
@Tag(name = "Users")
public class UserController {

    private final MatchService matchService;

    public UserController(MatchService matchService) {
        this.matchService = matchService;
    }



    @GetMapping("/match")
    @Operation(summary = "Get all users with skill, region and games")
    public ResponseEntity<List<User>> findUsersBySkillRegionAndGame(@NotNull(message = "Region cannot be blank") @RequestParam("region") Region region,
                                                                    @NotNull(message = "Diagnosis = skill issue") @RequestParam("skill") SkillLevel skillLevel,
                                                                    @NotEmpty(message = "List of game names cannot be empty") @RequestParam("games") String... games) {
        return ResponseEntity.ok(matchService.findUsersBySkillRegionAndGame(region, skillLevel, Set.of(games)));
    }

}
