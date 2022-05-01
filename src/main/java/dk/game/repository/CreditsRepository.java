package dk.game.repository;

import dk.game.entity.Credits;
import dk.game.entity.Skillz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditsRepository extends JpaRepository<Credits, Long> {

    Optional<Credits> findTopByGame_nameAndUser_SkillOrderByCreditDesc(String gameName, Skillz skillz);

    List<Credits> findAllByGame_Name(String name);

    Optional<Credits> findByUser_Id(Integer id);

    /**
     * select c.user_id, max(c.credit), c.game_name, u.skill, u.name
     * from credits as c
     * LEFT JOIN users u on c.user_id = u.id
     * group by c.game_name, u.skill
     */

    //@Query("select Credits as c from Credits where c.id in (select c1.id, max(c1.credit) FROM Credits as c1) group by c.game.name, user.skill")
    //List<Credits> findHighScores();

}
