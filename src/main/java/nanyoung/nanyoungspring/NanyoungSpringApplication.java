package nanyoung.nanyoungspring;
//이 패키지를 포함한 하위 디렉토리는 spring이 Component 스캔해 spring bin으로 등록함.
//등록하려면 추가 설정 필요. 디폴트로는 안됨.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NanyoungSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NanyoungSpringApplication.class, args);
	}

}
