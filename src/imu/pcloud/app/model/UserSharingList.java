package imu.pcloud.app.model;

import imu.pcloud.server.been.SharingRecord;

import java.util.ArrayList;
import java.util.List;

public class UserSharingList extends BaseModel {

	List<SharingRecord> sharingRecords = new ArrayList();

	
	
	public UserSharingList() {
		super();
	}

	public UserSharingList(List<SharingRecord> sharingRecords) {
		super();
		this.sharingRecords = sharingRecords;
	}

	public List<SharingRecord> getSharingRecords() {
		return sharingRecords;
	}

	public void setSharingRecords(List<SharingRecord> sharingRecords) {
		this.sharingRecords = sharingRecords;
	}

	
	
	
}
