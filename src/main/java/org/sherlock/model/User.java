package org.sherlock.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("user用户")
public class User {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("username")
    private String username;
}
