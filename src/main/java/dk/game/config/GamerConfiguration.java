package dk.game.config;

import dk.game.entity.*;
import dk.game.repository.CreditsRepository;
import dk.game.repository.GameRepository;
import dk.game.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import static dk.game.entity.Gender.TotallyNotARobot;
import static dk.game.entity.Region.Asia;
import static dk.game.entity.Skillz.NOOB;
import static dk.game.entity.Skillz.PRO;
import static java.util.List.of;

@Configuration
public class GamerConfiguration {

    @Autowired
    UserRepository users;
    @Autowired
    GameRepository games;
    @Autowired
    CreditsRepository creditsRepository;

    @Bean
    InitializingBean sendDatabase() {
        return () -> {

            Game cod4 = new Game("Call of duty 4");
            Game amogus = new Game("AMOGUS");
            Game fortnite = new Game("fortnite");
            Game dota = new Game("dota");
            Game valhalla = new Game("valhalla");


            cod4 = games.save(cod4);
            amogus = games.save(amogus);
            valhalla = games.save(valhalla);
            fortnite = games.save(fortnite);
            dota = games.save(dota);

            // ALL GAEMS
            User invincible_gamer_usa = new User(1, "invincible_gamer_usa_1", TotallyNotARobot, Region.USA, "invincible_gamer_usa_1", Skillz.INVINCIBLE, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(invincible_gamer_usa);
            User pro_gamer_usa = new User(2, "pro_gamer_usa_2", TotallyNotARobot, Region.USA, "pro_gamer_usa_2", PRO, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(pro_gamer_usa);
            User noob_gamer_usa = new User(3, "noob_gamer_usa_3", TotallyNotARobot, Region.USA, "noob_gamer_usa_3", NOOB, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(noob_gamer_usa);

            // // EUROPE
            // //
            User invincible_gamer_europe = new User(4, "invincible_gamer_europe_4", TotallyNotARobot, Region.Europe, "invincible_gamer_europe_4", Skillz.INVINCIBLE, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(invincible_gamer_europe);
            User pro_gamer_europe = new User(5, "pro_gamer_europe_5", TotallyNotARobot, Region.Europe, "pro_gamer_europe_5", PRO, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(pro_gamer_europe);
            User noob_gamer_europe = new User(6, "noob_gamer_europe_&", TotallyNotARobot, Region.Europe, "noob_gamer_europe_6", NOOB, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(noob_gamer_europe);
            User noob_gamer_europe_2 = new User(7, "noob_gamer_europe_7", TotallyNotARobot, Region.Europe, "noob_gamer_europe_7", NOOB, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(noob_gamer_europe_2);

            // // Asia

            User invincible_gamer_asia = new User(8, "invincible_gamer_asia_8", TotallyNotARobot, Asia, "invincible_gamer_asia_8", Skillz.INVINCIBLE, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(invincible_gamer_asia);
            User pro_gamer_asia = new User(9, "pro_gamer_asia_9", TotallyNotARobot, Asia, "pro_gamer_asia_9", PRO, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(pro_gamer_asia);
            User noob_gamer_asia = new User(10, "noob_gamer_asia_10", TotallyNotARobot, Asia, "noob_gamer_asia_10", NOOB, of(
                    cod4,
                    amogus,
                    valhalla,
                    dota,
                    fortnite));
            users.save(noob_gamer_asia);


            Credits c1 = new Credits();
            c1.setGame(fortnite);
            c1.setUser(invincible_gamer_asia);
            c1.setCredit(99999999);
            Credits c2 = new Credits();
            c2.setGame(fortnite);
            c2.setUser(noob_gamer_asia);
            c2.setCredit(100);
            Credits c3 = new Credits();
            c3.setGame(cod4);
            c3.setUser(pro_gamer_asia);
            c3.setCredit(100);
            Credits c4 = new Credits();
            c4.setGame(cod4);
            c4.setUser(invincible_gamer_asia);
            c4.setCredit(10000);
            Credits c5 = new Credits();
            c5.setGame(cod4);
            c5.setUser(noob_gamer_europe);
            c5.setCredit(10001);
            Credits c6 = new Credits();
            c6.setGame(cod4);
            c6.setUser(noob_gamer_europe_2);
            c6.setCredit(100);

            creditsRepository.save(c1);
            creditsRepository.save(c2);
            creditsRepository.save(c3);
            creditsRepository.save(c4);
            creditsRepository.save(c5);
            creditsRepository.save(c6);

        };
    }
}