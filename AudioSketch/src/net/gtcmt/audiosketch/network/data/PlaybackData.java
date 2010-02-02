package net.gtcmt.audiosketch.network.data;

import net.gtcmt.audiosketch.client.util.P5Points2D;
import net.gtcmt.audiosketch.client.visual.util.VisualConstants.PlayBackType;

public final class PlaybackData implements MessageData {

	private static final long serialVersionUID = -951558808321594048L;
	private P5Points2D mousePoints;
	private float playbackSpeed;
	private float angle;
	private PlayBackType playbackType;
	private int size;
	
	/**
	 * Constructor creates playback data that will be passed between client and server
	 * @param pbType
	 * @param mouse
	 * @param speed
	 * @param angle
	 * @param size
	 */
	public PlaybackData(PlayBackType pbType, P5Points2D mouse, float speed, float angle, int size){
		this.playbackType = pbType;
		this.mousePoints = mouse;
		this.playbackSpeed = speed;
		this.angle = angle;
		this.size = size;
	}
	
	/*--------------------- Getter/Setter --------------------*/
	public P5Points2D getMousePoints() {
		return mousePoints;
	}
	public void setMousePoints(P5Points2D mousePoints) {
		this.mousePoints = mousePoints;
	}
	public int getMouseX() {
		return mousePoints.getPosX();
	}
	public void setMouseX(int mouseX) {
		this.mousePoints.setPosX(mouseX);
	}
	public int getMouseY() {
		return mousePoints.getPosY();
	}
	public void setMouseY(int mouseY) {
		this.mousePoints.setPosY(mouseY);
	}
	public float getPlaybackSpeed() {
		return playbackSpeed;
	}
	public void setPlaybackSpeed(float playbackSpeed) {
		this.playbackSpeed = playbackSpeed;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public PlayBackType getPlaybackType() {
		return playbackType;
	}
	public void setPlaybackType(PlayBackType playbackType) {
		this.playbackType = playbackType;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
