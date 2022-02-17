package com.zak.tts;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class TimeToSpeech {

	public static Synthesizer synthesizer = null;

	public static void init() {
		try {
			// Set property as Kevin Dictionary
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

			// Register Engine
			Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");

			// Create a Synthesizer
			synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

		}

		catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (synthesizer != null) {
					synthesizer.deallocate();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void deallocate() {
		try {
			if (synthesizer != null) {
				synthesizer.deallocate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void speakOutTime(String timeStr) {
		try {
			// Allocate synthesizer
			synthesizer.allocate();

			// Resume Synthesizer
			synthesizer.resume();
			
			//Speak out
			System.out.println(timeStr);
			synthesizer.speakPlainText(timeStr, null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void processTimeSpeaker(int hour, int min) {
		String timeStr = "";
		if (hour == 0 && min == 0) {
			timeStr = "Its mid night";
		} else if (hour == 12 && min == 0) {
			timeStr = "Its mid day";
		} else if (min == 0) {
			timeStr = "Its" + hour + " oclock";
		} else {
			timeStr = "Its" + hour + " " + min;
		}
		speakOutTime(timeStr);
	}
}