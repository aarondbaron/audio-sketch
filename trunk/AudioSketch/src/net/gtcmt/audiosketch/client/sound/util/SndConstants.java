package net.gtcmt.audiosketch.client.sound.util;

public class SndConstants {

	//Effect related
	public enum EffectType{
		REVERB, DISTORTION, DELAY, LPF, HPF, BPF
	}
	public static final int NUM_EFFECT = 6;

	public static final int MIN_MIDI = 12;
	public static final int MAX_MIDI = 127;
	
	public enum SndType{
		BUZZ, RANDOM, INHARMONIC_BELL, RING, BLIP, SHIR
	}
	
	public static final String[] EFFECT_NAME = {"Reverb","Distortion","Delay","Lowpass","Hipass","Bandpass"};
	public static final String[] SOUND_NAME = {"Buzz", "Random", "Inharmonic Bell", "Ring", "Blip", "Shir"};
	public static final String[] MIDI_NAME = {"C  ", "C#", "D  ", "D#", "E  ", "F  ", "F#", "G  ", "G#", "A  ", "A#", "B  "};
}
