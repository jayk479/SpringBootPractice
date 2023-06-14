package com.yedam.app.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
	@Value("${jasypt.encryptor.password}")
	//변수명은길게쓰는게좋음ㅇㅇcuz인식이한눈에안되잖아
	private String password;
	
    @Bean("jasyptStringEncryptor")
    //문자와 관련된 암호화 작용
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        //상기가 작동ㅇㅇ 알고리즘? PBE
        
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("password");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256"); 
        //암호화 할 때는 랜덤한값(유니크한SEED)을 만들어서 씀
        //공식홈페이지에서 다른 내장된 알고리즘 사용가능ㅇ
        //SEED값은 어떻게 받아서 쓸거야?
        //환경변수 => Run Configuration 에서 환경변수 저장 가능
        //        Environment
        //        프로그램에 내장시켜버림
        //        =>github에도 안 올라감
        //        =>보안강화
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        //여기 이부분이 random
        config.setStringOutputType("base64");
        
        encryptor.setConfig(config);
        return encryptor;
    }
}
