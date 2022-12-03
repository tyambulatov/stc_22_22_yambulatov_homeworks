package ru.inno.messenger.models;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
//import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = {"chat"})
@ToString(exclude = {"chat"})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "send_time", updatable = false,
            columnDefinition = "timestamp(0)")
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    @CreationTimestamp
    private LocalDateTime sendTime;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
