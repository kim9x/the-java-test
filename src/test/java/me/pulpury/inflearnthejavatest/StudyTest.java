package me.pulpury.inflearnthejavatest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StudyTest {

	@Test
	void create() {
		Study study = new Study();
		assertNotNull(study);
		System.out.println("create");
	}
	
	@Test
	@Disabled
	void create1() {
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
