package model;

import java.io.Serializable;

public class Notification implements Serializable {
	
	private static final long serialVersionUID = -6082865406506010753L;
	private String province;
	private String municipality;
	private String notifier;
	private String tracker;
	private String title;
	private String body;
	private String link;
	private int notificationState;
	
	public Notification(String province, String municipality, String notifier, String tracker, String title,
			String body, String link, int notificationState) {
		super();
		this.province = province;
		this.municipality = municipality;
		this.notifier = notifier;
		this.tracker = tracker;
		this.title = title;
		this.body = body;
		this.link = link;
		this.notificationState = notificationState;
	}
	
	

	public Notification(String province, String municipality, String notifier, String body, String link, String title) {
		this.province = province;
		this.municipality = municipality;
		this.notifier = notifier;
		this.body = body;
		this.link = link;
		this.title = title;
		this.notificationState = 1;
	}



	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getNotifier() {
		return notifier;
	}

	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}

	public String getTracker() {
		return tracker;
	}

	public void setTracker(String tracker) {
		this.tracker = tracker;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getNotificationState() {
		return notificationState;
	}

	public void setNotificationState(int notificationState) {
		this.notificationState = notificationState;
	}

	@Override
	public String toString() {
		return "Notificacion [province=" + province + ", municipality=" + municipality + ", notifier=" + notifier
				+ ", tracker=" + tracker + ", title=" + title + ", body=" + body + ", link=" + link
				+ ", notificationState=" + notificationState + "]";
	}

}
