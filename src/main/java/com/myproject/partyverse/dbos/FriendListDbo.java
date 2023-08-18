package com.myproject.partyverse.dbos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friend_list")
public class FriendListDbo {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "friends", length = 255)
    private String friends;

    @Column(name = "req_sent", length = 255)
    private String reqSent;

    @Column(name = "req_pending", length = 255)
    private String reqPending;

    //ToDo created_at, updated_at
}
