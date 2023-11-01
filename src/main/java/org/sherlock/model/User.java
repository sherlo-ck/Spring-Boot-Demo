package org.sherlock.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.swing.plaf.PanelUI;
import java.io.Serializable;

@Data
@ApiModel("user用户实体类")
public class User implements Serializable {
    private static final long serialVersionUID = -8272940376019635533L;
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("age")
    private Integer age;

    @ApiModelProperty("sex")
    private Integer sex;

    public User(Integer id) {
        this.id = id;
    }
    public User() {}

    public String StringValue() {
        return String.valueOf(this.id);
    }
}
