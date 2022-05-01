package dk.game.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int credit;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Game game;

}
