package com.example.appcommon.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value = "SysUser")
@Data
public class SysUser implements Serializable {
    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer id;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String orgName;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String groupName;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String userName;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String loginName;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String password;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String gender;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String politicalStatus;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String position;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String recUnit;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String email;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String phone;
    
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String QQ;
    
    private static final long serialVersionUID = 1L;
}

