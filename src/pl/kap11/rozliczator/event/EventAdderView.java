package pl.kap11.rozliczator.event;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pl.kap11.rozliczator.R;
import pl.kap11.rozliczator.event.storage.EventStorage;
import pl.kap11.rozliczator.event.storage.EventStorageFactory;

public class EventAdderView implements TextWatcher, View.OnClickListener{

    EditText eventName;
    Button saveButton;
    EventsDisplayer displayer;


   public EventAdderView(LayoutInflater inflater, ViewGroup root, EventsDisplayer displayer){
       View parent = inflater.inflate(R.layout.main_bottom_fragment, root, true);
       eventName = (EditText)parent.findViewById(R.id.event_name);
       saveButton = (Button)parent.findViewById(R.id.save_button);


       this.displayer = displayer;
       eventName.addTextChangedListener(this);
       saveButton.setOnClickListener(this);
   }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        saveButton.setEnabled(!TextUtils.isEmpty(s));
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onClick(View v) {
        String text = eventName.getText().toString();
        EventStorage storage = EventStorageFactory.getEventStorage();
        Event event = new Event(text);
        storage.saveEvent(event);
        displayer.displayEvent(event);
    }
}
