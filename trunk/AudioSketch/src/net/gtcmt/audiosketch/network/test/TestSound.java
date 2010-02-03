package net.gtcmt.audiosketch.network.test;

import net.gtcmt.audiosketch.sound.synth.Buzz;
import processing.core.PApplet;
import ddf.minim.AudioOutput;
import ddf.minim.EffectsChain;
import ddf.minim.Minim;
import ddf.minim.effects.LowPassSP;
import ddf.minim.signals.PinkNoise;
import ddf.minim.signals.SineWave;

public class TestSound extends PApplet {

	private static final long serialVersionUID = 2749952276146686528L;
	private Minim minim;
	AudioOutput out1;
	AudioOutput out2;
	EffectsChain chain;
	
	private PinkNoise noise;
	private SineWave sine;
	private LowPassSP lpf;
	
	public TestSound(){
		minim = new Minim(this);
		
		out1 = minim.getLineOut(Minim.STEREO, 1024);
//		out2 = minim.getLineOut(Minim.STEREO, 1024);
		
//		chain = new EffectsChain();
//		out1.addEffect(chain);
//
//		lpf = new LowPassSP(100, 44100);
//		chain.add(lpf);
		
//		noise = new PinkNoise();
//		sine = new SineWave(441, 1, 44100);
//		SignalChain sc = new SignalChain();
		new Thread(new Buzz(out1, 441)).start();
//		sc.add(noise);
//		out1.addSignal(sc);
//		out2.addSignal((AudioSignal) sine);	
	}
	
	public static void main(String[] args){
		new TestSound();
	}
}
