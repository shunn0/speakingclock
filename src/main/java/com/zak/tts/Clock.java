package com.zak.tts;

public class Clock {
	public static void main(String[] args) throws InterruptedException {
		TimeToSpeech.init();
		new ClockFrame();
		TimeToSpeech.deallocate();
	}

}
