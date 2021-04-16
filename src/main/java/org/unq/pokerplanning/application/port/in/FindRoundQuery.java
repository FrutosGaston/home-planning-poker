package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Round;

import java.util.Optional;

public interface FindRoundQuery {
    Optional<Round> execute(Integer roomId);
}
