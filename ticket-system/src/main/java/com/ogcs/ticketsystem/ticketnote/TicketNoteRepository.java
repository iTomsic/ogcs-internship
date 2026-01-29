package com.ogcs.ticketsystem.ticketnote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketNoteRepository extends JpaRepository<TicketNote, Integer> {
}
