package com.example.workout_app.dto.outgoing;

import com.example.workout_app.models.Account;

import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    public AccountDTO(Account account){
        id = account.getId();
    }
}
