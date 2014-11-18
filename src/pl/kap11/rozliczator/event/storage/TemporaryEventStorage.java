package pl.kap11.rozliczator.event.storage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import pl.kap11.rozliczator.event.Event;
import pl.kap11.rozliczator.item.Item;
import pl.kap11.rozliczator.person.Person;
import android.widget.ListView;

public class TemporaryEventStorage implements EventStorage {

	private static final List<Event> eventsList;

	private final static int EVENTS_COUNT = 10;

	private static final String[] eventNames = new String[] { "chlanie",
			"wakacje", "Weekend", "Planszówki + wódka", "Impreza na mieście",
			"Burdel z chłopakami", "Mecz Wisły", "Zakupy z rodzicami",
			"Nic ciekawego" };

	private static final String[] peopleGivenNames = new String[] { "Andrzej",
			"Stefan", "Bożena", "Lucyna", "Wiktoriusz", "Buba" };

	private static final String[] peopleLastNames = new String[] { "Jeden",
			"Drógi", "Czeci", "Trzwarty", "Pionty", "Szusty" };

	private static final String[] itemNames = new String[] { "Chlyb", "Jajka",
			"Wódka", "Mąka", "Twaróg", "Kupa" };

	static {
		eventsList = new LinkedList<>();
		Random rand = new Random();
		for (int i = 0; i < EVENTS_COUNT; ++i) {
			Event toAdd = new Event(eventNames[i]);
			for (int j = 0; j < rand.nextInt(5); ++j) {
				Person person = generateRandomPerson(rand);
				toAdd.addPerson(person);
			}
			for (int j = 0; j < rand.nextInt(5); ++j) {
				Item item = generateRandomItem(rand);
				toAdd.addItem(item);
			}
		}
	}

	private static Person generateRandomPerson(Random rand) {
		return new Person(
				peopleGivenNames[rand.nextInt(peopleGivenNames.length - 1)]
						+ " "
						+ peopleLastNames[rand
								.nextInt(peopleLastNames.length - 1)]);
	}

	private static Item generateRandomItem(Random rand) {
		return new Item(itemNames[rand.nextInt(itemNames.length - 1)],
				rand.nextFloat() * 100);
	}

	@Override
	public void saveEvent(Event event) {
		eventsList.add(event);
	}

	@Override
	public void setEventDisplay(ListView list) {
		// TODO Auto-generated method stub
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
	}

}
