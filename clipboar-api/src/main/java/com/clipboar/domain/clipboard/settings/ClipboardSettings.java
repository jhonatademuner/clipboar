package com.clipboar.domain.clipboard.settings;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClipboardSettings {

    private boolean networkVisible;
    private String networkCode;
    private int expirationTime;

}
