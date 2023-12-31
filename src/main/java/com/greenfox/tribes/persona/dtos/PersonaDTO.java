package com.greenfox.tribes.persona.dtos;

import com.greenfox.tribes.gameitems.models.Equipment;
import com.greenfox.tribes.misc.models.CharacterEquipment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonaDTO {

  private Long id;

  private String characterName;
  private String faction;

  private Integer atk; // chance to hit
  private Integer def; // chance to not be hit
  private Integer dmg; // how much damage is dealt in case of hit
  private Integer lck; // chance to crit, i.e. deal double damage
  private Integer hp;

  private Integer pullRing;

  private List<Equipment> inventory = new ArrayList<>();

  public void setInventory(List<CharacterEquipment> bundle) {
    for (CharacterEquipment e : bundle) {
      Equipment equipment = e.getEquipment();

      inventory.add(equipment);
    }
  }
}
