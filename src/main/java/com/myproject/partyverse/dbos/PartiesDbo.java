package com.myproject.partyverse.dbos;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "parties")
public class PartiesDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "organizer_id")
    private int organizerId;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "venue")
    private String venue;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "invited_guests")
    private String invitedGuest;

    @Column(name = "present_guests")
    private String presentGuest;

    @Column(name = "description", length = 255)
    private String description;
}
