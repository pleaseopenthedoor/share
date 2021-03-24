package com.example;

public class ThreadExample {
	public int count = 0;
	
	public static void main(String[] args) {
		ThreadExample ex = new ThreadExample();
		
		new Thread(() ->  {
			System.out.println("Thread_001 begin");
			try {
				Thread.sleep(1000);
				ex.count++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread_001 end");
		},"Thread_01").start();
		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(() ->  {
			
			System.err.println("Thread_002 begin");
			try {
				System.err.println("count0:"+ex.count);
				Thread.sleep(1000);
				System.err.println("count1:"+ex.count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.err.println("Thread_002 end");
			
		},"Thread_02").start();
		
	}
	
	
}
