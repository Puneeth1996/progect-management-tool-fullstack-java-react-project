package com.ticketingtool.modal;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;



    @OneToOne
    private Project project;


    private List<Message> messages;


}
