package org.demo.app.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate joinDate;

}


