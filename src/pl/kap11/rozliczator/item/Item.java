package pl.kap11.rozliczator.item;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{

	private String name;
	private float value;
	
	public Item(String name, float value){
		this.name = name;
		this.value = value;
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
