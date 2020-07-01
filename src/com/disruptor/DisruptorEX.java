package com.disruptor;

import java.util.Random;
import java.util.Scanner;

public class DisruptorEX {
	static CircularBuffer cb;
	static Scanner scan;

	public static void main(String[] args) throws InterruptedException {
		DisruptorEX disruptorEX = new DisruptorEX();
		scan = new Scanner(System.in);

		System.out.println("Circular Buffer Test\n");
		System.out.println("Enter Size of Buffer ");
		int n = scan.nextInt();

		/* creating object of class CircularBuffer */
		cb = disruptorEX.new CircularBuffer(n);

		/* Perform Circular Buffer Operations */
		WriteThread FirstThread = disruptorEX.new WriteThread();
		ReadThread SecondThread = disruptorEX.new ReadThread();
		ReadThread1 ThirdThread = disruptorEX.new ReadThread1();

		FirstThread.start();
		SecondThread.start();
		ThirdThread.start();
	}

	class CircularBuffer {
		private int maxSize;
		private int front = 0;
		private int rear = 0;
		volatile int bufLen = 0;
		volatile char[] buf;

		/** constructor **/
		public CircularBuffer(int size) {
			maxSize = size;
			front = rear = 0;
//			rear = 0;
			bufLen = 0;
			buf = new char[maxSize];
		}

		/** function to get size of buffer **/
		public int getSize() {
			return bufLen;
		}

		/** function to clear buffer **/
		public void clear() {
			front = rear = 0;
//			rear = 0;
			bufLen = 0;
			buf = new char[maxSize];
		}

		/** function to check if buffer is empty **/
		public boolean isEmpty() {
			return bufLen == 0;
		}

		/** function to check if buffer is full **/
		public boolean isFull() {
			return bufLen == maxSize;
		}

		/** insert an element **/
		public void write(char c) {
			if (!isFull()) {
				bufLen++;
				rear = (rear + 1) % maxSize;
				buf[rear] = c;
			} else
				System.out.println("Error : Underflow Exception");
		}

		/** delete an element **/
		public char read() {
			if (!isEmpty()) {
				bufLen--;
				front = (front + 1) % maxSize;
				return buf[front];
			} else {
				System.out.println("Error : Underflow Exception");
				return ' ';
			}
		}

		/** function to print buffer **/
		public void display() {
			System.out.print("\nBuffer : ");
			for (int i = 0; i < maxSize; i++)
				System.out.print(buf[i] + " ");
			System.out.println();
		}
	}

	class WriteThread extends Thread {
		public void run() {
			while (true) {
				while (!cb.isFull()) {

					try {
						char d = (char) (new Random().nextInt(122) + 97);
						System.out.println("Inserting at : " + cb.bufLen + " with char " + d);
						cb.write(d);
					} catch (Exception x) {
					}

				}
			}

		}
	}

	class ReadThread extends Thread {
		public void run() {
			while (true) {
				if (DisruptorEX.cb.bufLen > 0) {
					System.out.println("Reading an Element = " + cb.read());
				} else {
					try {
						Thread.sleep(500);

					} catch (Exception x) {
					}
				}
			}
		}
	}

	class ReadThread1 extends Thread {
		public void run() {
			while (true) {
				if (DisruptorEX.cb.bufLen > 0) {
					System.out.println("Reading an Element = " + cb.read());
				} else {
					try {
						Thread.sleep(500);

					} catch (Exception x) {
					}
				}
			}
		}
	}
}
