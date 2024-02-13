package org.example.executorservice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomExecutorService {
	private final List<Thread> threads; // пул потоков
	private final LinkedList<Runnable> taskQueue; // очередь задач
	private boolean isRunning = true; // параметр работы пула

	public CustomExecutorService(int poolSize) {
		this.threads = new ArrayList<>(poolSize);
		this.taskQueue = new LinkedList<>();

		for (int i = 0; i < poolSize; i++) {
			final var thread = new Thread(() -> {
				Runnable task;
				while (isRunning) {
					synchronized (taskQueue) {
						// пока очередь пуста и пул работает - ждем добавления задач
						while (taskQueue.isEmpty() && isRunning) {
							try {
								taskQueue.wait();
							} catch (InterruptedException e) {
								if (!isRunning) {
									// если ошибка возникает при остановке пула - завершаем ожидание
									break;
								}
							}
						}
						// как только задача появилась - берем ее в работу и удаляем из очереди
						if (!taskQueue.isEmpty()) {
							task = taskQueue.removeFirst();
						} else {
							// проверка на случай, если задачу возьмет другой поток
							continue;
						}
					}

					try {
						// запускаем задачу, вне блока synchronized,
						// чтобы не мешать другим потокам брать задачи в работу
						task.run();
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}
			});
			threads.add(thread);
			thread.start(); // запускаем задачи
		}
	}

		public synchronized void execute(Runnable task) {
		if (!isRunning) {
			throw new IllegalStateException("pool is not running");
		}
		synchronized (taskQueue) {
			if (isRunning) {
				taskQueue.addLast(task);
				taskQueue.notify(); //при добавлении задачи имеет смысл
			}
		}
	}

	public void shotDown() {
		this.isRunning = false;
		this.threads.forEach(Thread::interrupt);
	}
}
