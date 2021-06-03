package me.pulpury.inflearnthejavatest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@Test
	@DisplayName("스터디 만들기😭")
	void create_new_study() {
//		Study study = new Study(-10);
//		assertNotNull(study);
//		assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태 값이 " + StudyStatus.DRAFT + "여야 한다.");
		
		// 위에처럼 넘기면 문자열 연상을 무조건(테스트 성공, 실패에 상관없이) 하게되는
		// 아래처럼 넘기면 실패했을 때만 연산해서 부담감(성능의 입장에서)을 많이 줄여줄 수 있다. 
//		assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태 값이 " + StudyStatus.DRAFT + "여야 한다.");
		/* assertEquals(StudyStatus.ENDED, study.getStatus(), new Supplier<String>() {
			@Override
			public String get() {
				System.out.println("스터디를 처음 만들면 상태 값이 DRAFT여야 한다.");
				return "스터디를 처음 만들면 상태 값이 DRAFT여야 한다.";
			}
		}); */
//		assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.");
		
		// 한번에 실행한 후에 실패가 된 것들을 알려준다.
		/* assertAll(
				() -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태 값이 " + StudyStatus.DRAFT + "여야 한다."),
				() -> assertNotNull(study),
				() -> assertTrue (study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
		); */
		
		// 에러 메세지도 받을 수 있다.
		/* IllegalArgumentException exception = 
				assertThrows(IllegalArgumentException.class, () -> new Study(-10));
		String message = exception.getMessage();
		assertEquals("limit은 0보다 커야 한다.", message); */
		
		// 안에 들어있는 ex. Thread.sleep(300);가 끝날 때 까지 기다린다.
		/* assertTimeout(Duration.ofMillis(100), () -> {
			new Study(10);
			Thread.sleep(300);
		}); */
		
		// 100밀리초가 지나면 그냥 바로 끝난다.
		// Thread Local 설정을 갖고 있는 코드가 있을 땐(ex. Transaction)
		// assertTimeout를 쓰는게 낫다.
		/* assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			new Study(10);
			Thread.sleep(300);
		}); */
		
		Study actual = new Study(10);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@Test
	// @Disabled는 해당 테스트를 비활성화 시킬 때 사용한다.
	// ex) test가 깨질 때, 
//	@Disabled
//	void create1_new_study_again() {
//		Study study = new Study();
//		assertNotNull(study);
//		System.out.println("create1");
//	}
	
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
