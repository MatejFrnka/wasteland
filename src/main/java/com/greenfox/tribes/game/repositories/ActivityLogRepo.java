package com.greenfox.tribes.game.repositories;

import com.greenfox.tribes.game.models.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ActivityLogRepo extends JpaRepository<ActivityLog, Long> {

  Optional<ActivityLog> findActivityLogByPersonaId(Long personaId);
}
