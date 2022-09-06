package cn.iocoder.springboot.lab85.demo;

import cn.iocoder.springboot.lab85.demo.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 数据脱敏测试
 *
 * @author Jaquez
 * @date 2022/09/05 18:46
 */
@Slf4j
public class SensitiveTests {

	@Test
	public void test() {
		UserEntity userEntity = UserEntity.builder()
				.userNamePattern("张三四")
				.userNameLength("张三四")
				.passwordPattern("122345676543")
				.passwordLength("122345676543")
				.idCardPattern("432145167805126789")
				.idCardLength("432145167805126789")
				.fixedPhonePattern("076512344321")
				.fixedPhoneLength("076512344321")
				.mobilePattern("15678900987")
				.mobileLength("15678900987")
				.addressPattern("北京市东城区东华门街道北京香江戴斯酒店")
				.addressLength("北京市东城区东华门街道北京香江戴斯酒店")
				.emailPattern("23345@qq.com")
				.emailLength("23345@qq.com")
				.bankCardPattern("6212262502009182455")
				.bankCardCustomizePattern("6212262502009182455")
				.bankCardLength("6212262502009182455")
				.build();
		log.info(JsonMapper.nonNullMapper().toJson(userEntity));
	}

}
