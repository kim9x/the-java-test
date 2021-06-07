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
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(Lifecycle.PER_CLASS)
class StudyTest {
	
	int value = 0;

	@FastTest
	@DisplayName("스터디 만들기 fast")
	void create_new_study() {
		System.out.println(this);
		System.out.println(value++);
		Study actual = new Study(100);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@SlowTest
	@DisplayName("스터디 만들기 slow")
	void create1_new_study_again() {
		System.out.println(value++);
		System.out.println("create1");
	}
	
	@DisplayName("스터디 만들기")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
	void repeatTest(RepetitionInfo repetitionInfo) {
		System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" +
				repetitionInfo.getTotalRepetitions());
//		System.out.println("test");
	}
	
	@DisplayName("스터디 만들기")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
//	@ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
//	@ValueSource(ints = {10, 20, 40})
//	@EmptySource
//	@NullSource
//	@NullAndEmptySource
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
//	void parameterizedTest(String message) {
//	void parameterizedTest(@ConvertWith(StudyConverter.class)  Study study) {
//	void parameterizedTest(Integer limit, String name) {
//	void parameterizedTest(ArgumentsAccessor argumentsAccessor) {
	void parameterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
//		Study study = new Study(limit, name);
//		Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(0));
		System.out.println(study);
	}
	
	static class StudyAggregator implements ArgumentsAggregator {

		@Override
		public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
				throws ArgumentsAggregationException {
			return new Study(accessor.getInteger(0), accessor.getString(0));
		}
		
	}
	
	static class StudyConverter extends SimpleArgumentConverter {

		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study.class, targetType, "Can only convert to Study");
			return new Study(Integer.parseInt(source.toString()));
		}
	}
	
	// 모든 테스트 가 실행되기 전에 딱 1번 실행 된다.
	// static과 default만 가능, private은 안됨.
	// 리턴 타입은 있으면 안됨.(void 가능)
	// 'static void'로 사용한다고 생각하면 편하다.
	// 테스트 인스턴스를 하나만 생성할 시 static을 빼줘도 된다.
	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
	}
	
	// 모든 테스트가 실행된 후에 딱 1번만 실행 된다.
	// @BeforeAll과 마찬가지로
	// 'static void'로 사용한다고 생각하면 편하다.
	// 테스트 인스턴스를 하나만 생성할 시 static을 빼줘도 된다.
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
