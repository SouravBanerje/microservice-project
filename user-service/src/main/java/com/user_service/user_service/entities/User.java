package com.user_service.user_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name= "ID")
    private  String userId;

    @Column(name = "NAME",length = 45)
    private  String name;

    @Column(name = "EMAIL",length = 45)
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
