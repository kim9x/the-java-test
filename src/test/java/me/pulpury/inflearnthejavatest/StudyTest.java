package me.pulpury.inflearnthejavatest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;
import java.util.function.Supplier;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@Test
	@DisplayName("스터디 만들기😭")
	// 복수 선택 가능
//	@EnabledOnOs(OS.MAC)
	@EnabledOnOs({OS.MAC, OS.LINUX})
//	@EnabledOnJre(JRE.JAVA_8)
	@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
	@EnabledIfEnvironmentVariable(named = "TEST_EVN", matches = "LOCAL")
	void create_new_study() {
		// 시스템 변수를 eclipse를 킬 때 다 읽어오므로 변경 될 시
		// eclipse 혹은 다른 IDE를 리부트 해줘야한다.
		String test_env = System.getenv("TEST_ENV");
		System.out.println(test_env);
		assumeTrue("LOCAL".equalsIgnoreCase(test_env));
		
		assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
			// TODO Something
			System.out.println("LOCAL");
		});
		
		assumingThat("taeju".equalsIgnoreCase(test_env), () -> {
			// TODO Something
		});
		
		Study actual = new Study(10);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@Test
	// @Disabled는 해당 테스트를 비활성화 시킬 때 사용한다.
	// ex) test가 깨질 때, 
//	@Disabled
	@DisabledOnOs(OS.MAC)
	@EnabledOnJre(JRE.OTHER)
	@EnabledIfEnvironmentVariable(named = "TEST_EVN", matches = "taeju")
	void create1_new_study_again() {
		Study study = new Study();
		assertNotNull(study);
		System.out.println("create1");
	}
	
	// 모든 테스트 가 실행되기 전에 딱 1번 실행 된다.
	// static과 default만 가능, private은 안됨.
	// 리턴 타입은 있으면 안됨.(void 가능)
	// 'static void'로 사용한다고 생각하면 편하다.
	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
	}
	
	// 모든 테스트가 실행된 후에 딱 1번만 실행 된다.
	// @BeforeAll과 마찬가지로
	// 'static void'로 사용한다고 생각하면 편하다.
	@AfterAll
	static void afterAll() {
		System.out.println("after all");
	}
	
	// 모든, 각각의 테스트를 실행하기 전에 실행된다.
	// staic일 필요는 없음.
	// void로 사용한다고 생각하면 편하다.
	@BeforeEach
	void beforeEach() {
		System.out.println("before each");
	}
	
	// 모든, 각각의 테스트를 실행한 후에 실행된다.
	// staic일 필요는 없음.
	// void로 사용한다고 생각하면 편하다.
	@AfterEach
	void afterEach() {
		System.out.println("after each");
	}
	
	
	// 아래 순서로 실행된다고 보면 된다.
//	before all
//	before each
//	create
//	after each
//	before each
//	create1
//	after each
//	after all
	
}
