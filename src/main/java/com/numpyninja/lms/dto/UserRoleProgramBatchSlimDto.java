package com.numpyninja.lms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleProgramBatchSlimDto {

    @NotNull(message = "Batch Id is mandatory")
    @DecimalMin(value = "1", message = "Batch Id must be greater than or equal to 1")
    @JsonProperty("batchId")
    private Integer batchId;

    @NotEmpty(message = "User-Role-Program-Batch Status is Mandatory")
    @Pattern(regexp = "active|inactive", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "User-Role-Program-Batch Status can be Active or Inactive")
    @JsonProperty("userRoleProgramBatchStatus")
    private String userRoleProgramBatchStatus;

}
