package com.greenfox.tribes.game.controllers;

import com.greenfox.tribes.gameitems.services.EquipmentService;
import com.greenfox.tribes.gameuser.models.WastelandUser;
import com.greenfox.tribes.gameuser.repositories.UserRepository;
import com.greenfox.tribes.gameuser.services.CustomUserDetailService;
import com.greenfox.tribes.misc.models.CharacterEquipment;
import com.greenfox.tribes.misc.repositories.CharacterEquipmentRepo;
import com.greenfox.tribes.persona.dtos.PersonaDTO;
import com.greenfox.tribes.persona.models.Persona;
import com.greenfox.tribes.persona.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/character")
public class CharacterUI {

  @Autowired CharacterService characterService;
  @Autowired CustomUserDetailService userService;
  @Autowired UserRepository userRepository;
  @Autowired EquipmentService equipmentService;
  @Autowired CharacterEquipmentRepo pairingRepo;

  @GetMapping("/new")
  public String newCharacter() {
    return "persona-sites/character-creation";
  }

  @PostMapping("/new")
  public String finishCreation(
      @RequestParam("nick") String characterName,
      @RequestParam("faction") String faction,
      @RequestParam("atk") int atk,
      @RequestParam("dmg") int dmg,
      @RequestParam("def") int def,
      @RequestParam("hp") int hp,
      @RequestParam("lck") int lck) {

    Persona persona =
        characterService.addCharacter(characterName, hp, atk, dmg, def, lck, faction, 100);
    System.out.println(persona);
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    WastelandUser user = (WastelandUser) userService.loadUserByUsername(auth.getName());
    System.out.println(user);
    user.setPersona(persona);

    return "persona-sites/main-page";
  }

  @GetMapping("/me")
  public String myCharacter(Model model) {
    PersonaDTO dto = characterService.readCharacter();
    model.addAttribute("DTO", dto);
    return "persona-sites/main-page";
  }
}
