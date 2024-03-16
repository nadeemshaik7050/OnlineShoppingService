package com.onlineshopping.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Token extends BaseModel {
    private String value;
    @ManyToOne
    private User user;
    private Date expiryDate;
    private boolean deleted;
}
