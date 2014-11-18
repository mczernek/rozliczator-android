package pl.kap11.rozliczator.person;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

	public static final long INVALID_CONTACT_ID = -1;
	
	private String displayName;
	private long contactId;
	
	public Person(String name){
		this(name, INVALID_CONTACT_ID);
	}
	
	public Person(String name, long contactId){
		this.displayName = name;
		this.contactId = contactId;
	}
	
	public String getDisplayName(){
		return displayName;
	}
	
	public long getContactId(){
		return contactId;
	}
	
	public boolean hasCorrespondingContact(){
		return contactId != INVALID_CONTACT_ID;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
	}
	
}
