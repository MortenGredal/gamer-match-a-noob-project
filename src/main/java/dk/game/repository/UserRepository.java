package dk.game.repository;

import dk.game.entity.Game;
import dk.game.entity.Region;
import dk.game.entity.SkillLevel;
import dk.game.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /*
    @Query("select User as u from User " +
            "where u.skill = :skillLevel " +
            "AND u.region = :region " +
            "having u.games in (:games)")
    List<User> findUsersByGameRegionAndSKill(Region region, SkillLevel skillLevel, String... games);
*/

    /**
     * select *
     * from users_games ug
     *          inner JOIN users u on ug.user_id = u.id
     * WHERE ug.game_name = 'AMOGUS'
     *   AND u.skill = 'NOOB';
     */
    /*
    @Query("SELECT User as u FROM User " +
            "WHERE u.skill = :skillLevel " +
            "AND u.region = :region " +
            "HAVING u.games IN (:games)")
    List<User> findAllBy(Skillz skillLevel, Region region, List<Game> games);
*/

    List<User> findDistinctBySkillAndRegionAndGames_NameIsIn(SkillLevel skillLevel, Region region, Set<String> games);

}


