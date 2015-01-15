package pl.kap11.rozliczator.event;

import java.util.ArrayList;
import java.util.Collection;

import pl.kap11.rozliczator.item.Item;
import pl.kap11.rozliczator.person.Person;
import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {

	private String name;
	
	private Collection<Person> people; 
	
	private Collection<Item> items;
	
	public Event(String name){
		this.name = name;
		people = new ArrayList<Person>();
		items = new ArrayList<Item>(); 
	}
	
	public void addPerson(Person toAdd){
		people.add(toAdd);
	}
	
	public void removePerson(Person toRemove){
		people.remove(toRemove);
	}
	
	public int getPeopleCount(){
		return people.size();
	}
	
	public void addItem(Item item){
		items.add(item);
	}
	
	public void removeItem(Item toRemove){
		items.remove(toRemove);
	}
	
	public int getItemsCount(){
		return items.size();
	}
	
	public String getName(){
		return name;
	}
	
	public void getSummaryValue(){
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
        dest.writeArray(people.toArray());
        dest.writeArray(items.toArray());
	}

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Event createFromParcel(Parcel in) {
            return null;
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

}
