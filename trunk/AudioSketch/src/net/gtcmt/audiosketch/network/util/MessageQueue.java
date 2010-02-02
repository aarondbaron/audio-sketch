package net.gtcmt.audiosketch.network.util;

import java.util.LinkedList;

import net.gtcmt.audiosketch.network.data.AudioSketchData;

/**
 * Implementation of Blocking Queue. This is simply a queue data structure that
 * has a blocking pop() method. The running thread blocks until an element is
 * avaible in the queue.
 * 
 * @author akito
 * 
 */
public class MessageQueue {

	// Linked list treaded as queue
	private LinkedList<AudioSketchData> message;

	/**
	 * Constructor: initialize linked list
	 */
	public MessageQueue() {
		message = new LinkedList<AudioSketchData>();
	}

	/**
	 * Push message
	 * Pushes in new event to queue and notifies all threads that
	 * are waiting for this object's monitor.
	 * @param event
	 */
	public synchronized void push(AudioSketchData data) {
		message.addLast(data);
		notifyAll();
	}

	/**
	 * Pops message as soon as message is pushed in synchronized manner
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized AudioSketchData pop() throws InterruptedException {
		while (message.size() == 0)
			wait();

		return message.removeFirst();
	}

	/**
	 * Retrun size of queue
	 * 
	 * @return
	 */
	public synchronized int size() {
		return message.size();
	}
}
