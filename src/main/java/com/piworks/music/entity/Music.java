package com.piworks.music.entity;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Music {
      //use lombok for getter,setter,constructor methods
    private byte[] playId;
    private int songId;
    private int clientId;

    private Date playTs;


}
