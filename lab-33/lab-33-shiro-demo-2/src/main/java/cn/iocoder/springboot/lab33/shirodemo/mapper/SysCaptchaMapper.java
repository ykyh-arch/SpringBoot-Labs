
package cn.iocoder.springboot.lab33.shirodemo.mapper;

import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysCaptcha;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptcha> {

}
