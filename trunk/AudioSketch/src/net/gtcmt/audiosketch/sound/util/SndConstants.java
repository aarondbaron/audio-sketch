package net.gtcmt.audiosketch.sound.util;

public class SndConstants {

	//Effect related
	public enum EffectType{
		REVERB, DISTORTION, DELAY, LPF, HPF, BPF
	}
	public static final int NUM_EFFECT = 6;

	public static final int MIN_MIDI = 12;
	public static final int MAX_MIDI = 127;
	
	public enum SndType{
		BUZZ, BANJO, CHING, GUITAR_ELEC, GUITAR_CLASSIC, CLARINET, POP, SAX, FEMALE, TOY_PIANO, VIOLA, ZAP
	}
	
	public static final String[] EFFECT_NAME = {"Reverb","Distortion","Delay","Lowpass","Hipass","Bandpass"};
	public static final String[] SOUND_NAME = {"buzz", "banjo", "ching", "guitar-elec", "guitar-classic", 
		"clarinet","pop","sax","female","toy-piano","viola","zap"};
	public static final String[] MIDI_NAME = {"C  ", "C#", "D  ", "D#", "E  ", "F  ", "F#", "G  ", "G#", "A  ", "A#", "B  "};
}
