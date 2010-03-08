package net.gtcmt.audiosketch.event;



import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Implementation of Blocking Queue. This is simply a queue data structure that
 * has a blocking pop() method. The running thread blocks until an element is
 * available in the queue.
 * 
 * @author akito
 * 
 */
public class AudioPriorityQueue {

	Comparator<AudioInfo> audioInfoCmpr;
	// Linked list treaded as queue
	private PriorityQueue<AudioInfo> audioInfo;

	/**
	 * Constructor: initialize linked list
	 */
	public AudioPriorityQueue() {
		audioInfoCmpr = new AudioInfoComparator();
		audioInfo = new PriorityQueue<AudioInfo>(10, audioInfoCmpr);
	}

	/**
	 * Push message
	 * Pushes in new event to queue and notifies all threads that
	 * are waiting for this object's monitor.
	 * @param event
	 */
	public synchronized void push(AudioInfo data) {
		audioInfo.add(data);
		notifyAll();
	}
	
	/**
	 * Pops message as soon as message is pushed in synchronized manner
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized AudioInfo pop() throws InterruptedException {
		while (audioInfo.size() == 0)
			wait();
		return audioInfo.poll();
	}
	
	/**
	 * Retrun size of queue
	 * 
	 * @return
	 */
	public synchronized int size() {
		return audioInfo.size();
	}
}
