package com.splusz.villigo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alarm_categories")
@Getter @Setter
@NoArgsConstructor
public class AlarmCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;  // 카테고리 이름 (예: "예약 알람", "메시지 알람" 등)

    @OneToMany(mappedBy = "alarmCategory")
    private List<Alarm> alarms = new ArrayList<>();
}
