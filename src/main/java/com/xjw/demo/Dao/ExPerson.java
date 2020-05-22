package com.xjw.demo.Dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@Repository
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExPerson {
    private Integer exPersonId;
    private Integer userId;
    private Integer signedTime;
    private Integer mouth;
    private String name;
}
