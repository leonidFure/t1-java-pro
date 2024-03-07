package org.example;

import org.example.executorservice.CustomExecutorService;

public class Main {
	public static void main(String[] args) {
		final var executorService = new CustomExecutorService(5);
			for (int i = 0; i < 25; i++) {
				int ii = i;
				executorService.execute(() -> {
					System.out.println(ii +" thr start- " + Thread.currentThread().getName());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}

					System.out.println(ii +" thr end- " + Thread.currentThread().getName());
				});
			}
		executorService.shotDown();
	}
}