package cn.iocoder.springboot.labs.chat03.demo4.dto;

import lombok.Builder;
import lombok.Data;

/**
 * UserFindDto
 *
 * @author fw001
 * @date 2023/09/20 16:57
 **/
@Data
@Builder
public class UserFindDto {

    private Long userId;
    private String userName;
}
