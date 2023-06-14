package com.yedam.app;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class Ex230613ApplicationTests {
	
	@Autowired
	StringEncryptor jasyptStringEncryptor;
	//이름이 등록되어있는 bean이라면 변수명으로 그 이름 해주면 파일도 찾아옴ㅇㅇ
	
	@Test
	void propertiesEncrypt() {
			String[] strArray = {"oracle.jdbc.driver.OracleDriver",
					"jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr"};
			//암호화하고자하는대상
			//이렇게 갯수 안 정해져있으면 .add해줘
			for(String str : strArray) {
				String enyStr = jasyptStringEncryptor.encrypt(str);
				System.out.println(enyStr);
			}
		 
	}

}
