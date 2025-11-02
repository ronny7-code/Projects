package org.bj.HospitalManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bj.HospitalManagementSystem.entity.BloodGroup;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class bloodGroupCountResponseEntity {

    private BloodGroup bloodGroup;
    private long count;
}