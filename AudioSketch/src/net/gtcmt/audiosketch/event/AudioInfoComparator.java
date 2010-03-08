package net.gtcmt.audiosketch.event;

import java.util.Comparator;

public class AudioInfoComparator implements Comparator<AudioInfo> {

	public int compare(AudioInfo o1, AudioInfo o2) {
		if(o1.getTrigTime()<o2.getTrigTime()){
			return -1;
		}
		if(o1.getTrigTime()>o2.getTrigTime()){
			return 1;
		}
		return 0;
	}
}
