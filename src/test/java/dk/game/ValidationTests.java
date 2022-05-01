package dk.game;

import dk.game.entity.Credits;
import dk.game.service.CreditService;
import dk.game.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class ValidationTests {

    @MockBean
    CreditService creditService;
    @MockBean
    MatchService matchService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenValidGame_whenQueryOnGame_serviceProducesNoEntities_shouldReturnOkbutEmptyBody() throws Exception {
        this.mockMvc.perform(get("/credit/game/somegame")).andExpect(status().isOk())
                .andExpect(content().string("[]")); // expect an empty list
    }

    @Test
    public void givenValidGame_whenQueryOnGame_serviceProducesAnEntity_shouldReturnOkWithBody_shouldReturnOk() throws Exception {

        when(creditService.getCreditsByGame(eq("somegame"))).thenReturn(List.of(new Credits()));

        this.mockMvc.perform(get("/credit/game/somegame")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists()); // expect an empty list
    }

    @Test
    public void givenNoCredits_whenWithNoNameForGame_ShouldReturn400() throws Exception {
        String allBlank = "    ";

        this.mockMvc.perform(get("/credit/game/" + allBlank))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void givenNoCredits_whenQueryForGameAndSkill_shouldReturn404() throws Exception {
        this.mockMvc.perform(get("/credit/winner/AMOGUS/NOOB"))
                .andExpect(status().isNotFound()); // the age-old problem, is the 404 because I looked in the wrong place?
    }

    @Test
    public void givenNoCredits_whenQueryForGameAndIncorrectSkill_shouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/credit/winner/AMOGUS/MEGANOOB"))
                .andExpect(status().isBadRequest()); // the age-old problem, is the 404 because I looked in the wrong place?
    }


}
