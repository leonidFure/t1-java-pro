package org.example.runner;

import org.example.common.Executor;
import org.example.utils.ReflectionUtils;
import org.example.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.example.utils.ReflectionUtils.*;

public final class TestRunner {

	private TestRunner() {
	}

	public static <T> void runTest(Class<T> tClass) {
		final var methods = Arrays.asList(tClass.getDeclaredMethods());
		validateBeforeTests(methods);
		final var testedObject = findConstructor(tClass)
				.map(ReflectionUtils::newInstanceSafely)
				.orElseThrow(() -> new IllegalStateException("tested class instantiate error"));
		getStaticMethodExecutorByAnnotation(methods, BeforeSuite.class)
				.ifPresent(Executor::execute);
		executeTestMethods(methods, testedObject);
		getStaticMethodExecutorByAnnotation(methods, AfterSuite.class)
				.ifPresent(Executor::execute);
	}

	private static void executeTestMethods(List<Method> methods, Object testedObject) {
		final var before = getStaticMethodExecutorByAnnotation(methods, BeforeTest.class);
		final var after = getStaticMethodExecutorByAnnotation(methods, AfterTest.class);
		methods.stream()
				.filter(method -> !isAccessFlagPresent(method, AccessFlag.STATIC)
						&& method.isAnnotationPresent(Test.class)
						&& isTestAnnotationValid(method)
						&& (isEmptyMethod(method) || method.isAnnotationPresent(CsvSource.class)))
				.sorted((m1, m2) -> {
					final var m1Annotation = m1.getAnnotation(Test.class);
					final var m2Annotation = m2.getAnnotation(Test.class);
					return Integer.compare(m1Annotation.priority(), m2Annotation.priority());
				})
				.forEach(method -> {
					final var params = getParamsFromAnnotation(method);
					method.trySetAccessible();
					before.ifPresent(Executor::execute);
					invokeMethod(testedObject, method, params);
					after.ifPresent(Executor::execute);
				});
	}

	private static boolean isTestAnnotationValid(Method method) {
		int priority = method.getAnnotation(Test.class).priority();
		return priority >= 1 && priority <= 10;
	}

	private static Object[] getParamsFromAnnotation(Method method) {
		final var annotation = method.getAnnotation(CsvSource.class);
		if (annotation == null) {
			return new Object[0];
		}
		final var strValues = annotation.params().split(",");
		final var parameterTypes = method.getParameterTypes();
		final var params = new Object[parameterTypes.length];
		if (strValues.length != parameterTypes.length) {
			throw new IllegalStateException("csvSource values count not equals params count in method");
		}
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = getWrapperClassIfRequired(parameterTypes[i]);
			if (parameterTypes[i] == null) {
				throw new IllegalArgumentException("parameter type not found");
			}
			final var strValue = strValues[i];
			final var parameterType = parameterTypes[i];
			params[i] = findConstructor(parameterType, String.class)
					.map(constructor -> newInstanceSafely(constructor, strValue))
					.orElseThrow(() -> new IllegalArgumentException("incorrect argument"));
		}
		return params;
	}

	private static Optional<Executor> getStaticMethodExecutorByAnnotation(List<Method> methods,
																		  Class<? extends Annotation> annotation) {
		return methods.stream()
				.filter(method -> isAccessFlagPresent(method, AccessFlag.STATIC)
						&& method.isAnnotationPresent(annotation)
						&& isEmptyMethod(method))
				.findFirst()
				.map(method -> () -> invokeMethod(null, method));
	}

	private static void validateBeforeTests(List<Method> methods) {
		if (isAnnotatedMethodNotUniquePerClass(methods, BeforeSuite.class)) {
			throw new IllegalStateException("methods annotated by @BeforeSuite more then 1 in class");
		}
		if (isAnnotatedMethodNotUniquePerClass(methods, AfterSuite.class)) {
			throw new IllegalStateException("methods annotated by @AfterSuite more then 1 in class");
		}
		if (isAnnotatedMethodNotUniquePerClass(methods, BeforeTest.class)) {
			throw new IllegalStateException("methods annotated by @BeforeTest more then 1 in class");
		}
		if (isAnnotatedMethodNotUniquePerClass(methods, AfterTest.class)) {
			throw new IllegalStateException("methods annotated by @AfterTest more then 1 in class");
		}
	}

	private static boolean isAnnotatedMethodNotUniquePerClass(List<Method> methods,
															  Class<? extends Annotation> annotation) {
		return methods.stream()
				.filter(it -> it.isAnnotationPresent(annotation))
				.count() > 1L;
	}
}
