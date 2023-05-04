package com.example.prime.bank.assessment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;
    private Date createdAt;
    @Transient
    private Timestamp timestamp;
    private String post_messages;
    private String post_Title;

//    @ManyToOne
//    private User user;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
