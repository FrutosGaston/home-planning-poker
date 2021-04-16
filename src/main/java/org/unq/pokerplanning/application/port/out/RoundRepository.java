package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Round;

import java.util.List;

public interface RoundRepository {
    List<Round> findByRoom(Integer roomId);
}
