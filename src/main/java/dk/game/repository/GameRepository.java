package dk.game.repository;

import dk.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    Set<Game> findAllByNameIsIn(Set<String> names);

}
