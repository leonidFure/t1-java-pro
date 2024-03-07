package org.example.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;
import java.util.Optional;

public final class ReflectionUtils {

	private ReflectionUtils() {
	}

	public static <T> T newInstanceSafely(Constructor<T> constructor, Object... params) {
		try {
			return constructor.newInstance(params);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			return null;
		}
	}

	public static boolean isAccessFlagPresent(Executable executable, AccessFlag accessFlag) {
		return Optional.ofNullable(executable)
				.map(Executable::accessFlags)
				.map(it -> it.contains(accessFlag))
				.orElse(false);
	}

	public static boolean isAnnotationPresent(List<Method> methods, Class<? extends Annotation> annotation) {
		if (methods == null || methods.isEmpty()) {
			return false;
		}
		return methods.stream()
				.filter(method -> method.isAnnotationPresent(annotation))
				.count() > 1L;
	}

	public static boolean isAnnotatedMethodNotUnique(List<Method> methods,
													 Class<? extends Annotation> annotation) {
		if (methods == null || methods.isEmpty()) {
			return false;
		}
		return methods.stream()
				.filter(method -> method.isAnnotationPresent(annotation))
				.count() > 1L;
	}

	public static Optional<Constructor<?>> findConstructor(Class<?> tClass, Class<?>... parameterTypes) {
		return findConstructorSafely(tClass, parameterTypes)
				.map(constructor -> constructor.trySetAccessible() ? constructor : null)
				.map(constructor -> isAccessFlagPresent(constructor, AccessFlag.PUBLIC) ? constructor : null);
	}

	public static Object invokeMethod(Object object, Method method, Object... args) {
		try {
			if (!method.trySetAccessible()) {
				System.out.printf("Method %s not accessible", method.getName());
				return null;
			}
			return method.invoke(object, args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			System.out.printf("Error %s when called method %s", e.getMessage(), method.getName());
			return null;
		}
	}

	public static boolean isEmptyMethod(Method method) {
		return method.getParameterCount() == 0;
	}

	private static Optional<Constructor<?>> findConstructorSafely(Class<?> tClass, Class<?>... parameterTypes) {
		try {
			return Optional.of(tClass.getDeclaredConstructor(parameterTypes));
		} catch (NoSuchMethodException e) {
			return Optional.empty();
		}
	}

	public static Class<?> getWrapperClassIfRequired(Class<?> primitiveType) {
		if (primitiveType == null) return null;
		if (primitiveType == byte.class) {
			return Byte.class;
		} else if (primitiveType == short.class) {
			return Short.class;
		} else if (primitiveType == int.class) {
			return Integer.class;
		} else if (primitiveType == boolean.class) {
			return Boolean.class;
		} else if (primitiveType == double.class) {
			return Double.class;
		} else if (primitiveType == float.class) {
			return Float.class;
		} else if (primitiveType == long.class) {
			return Long.class;
		} else if (primitiveType == char.class) {
			return Character.class;
		} else {
			return primitiveType;
		}
	}
}
