package com.wordwise.domain.score.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class DateTimeService {

    public static boolean isSameDay(LocalDateTime dateTime) {
        return LocalDate.now().isEqual(dateTime.toLocalDate());
    }
}
