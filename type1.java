/*
    Listener Pattern
    
      1. Define an interface or abstract class as an event contract
         with methods that define events and arguments which are relevant data.
         
      2. Define a listener member variable in the object which can be assigned 
         any object that implements the interface.
         
      3. Fire events on the defined listener when the object wants to 
         communicate events to it's owner
         
      4. Owner creates this object and then passes in a listener which 
         implements the interface and handles the events from the object.
      
*/

public class MyCustomObject {
	// Step 1 - This interface defines the type of messages I want to communicate to my owner  
	public interface MyCustomObjectListener {
		// These methods are the different events and need to pass relevant arguments with the event
		public void onItemSelected(Item item);
		public void onTouchDown(MotionEvent event, String text);
		public void onTouchUp(MotionEvent event, String text);
	}
	
	// Step 2- This variable represents the listener passed in by the owning object
	// The listener must implement the events interface and passes messages up to the parent.
	private MyCustomObjectListener listener;
	
	// Constructor where listener events are ignored
	public MyCustomObject() {
		this.listener = null; // set null listener
	}
	
	// Assign the listener implementing events interface that will receive the events (passed in by the owner)
	public void setCustomObjectListener(MyCustomObjectListener listener) {
		this.listener = listener;
	}
	
	// This is a hypothetical method that gets called when the object is touched
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			// Step 3 - Fire listener event to communicate to parent
			listener.onTouchUp(event, "UP");
			return false;
		} else {
			// Fire listener event to communicate to parent
			listener.onTouchDown(event, "DOWN");
			return true;
		}
	}
	
	// Hypothetical item
	public class Item {
		// ... fields and methods...
	}
}

public class MyParentActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// ...
		// Create the custom object
		MyCustomObject object = new MyCustomObject();
		// Step 4 - Setup the listener for this object
		// Note that you can then use the events defined here to communicate the information to OTHER objects owned
		object.setCustomObjectListener(new MyCustomObjectListener() {
			@Override
			public void onTouchUp(MotionEvent event, String text) {
				// Code to handle touch up
			}
			
			@Override
			public void onTouchDown(MotionEvent event, String text) {
				// Code to handle touch down
			}
			
			@Override
			public void onItemSelected(Item item) {
				// Code to handle item selected
			}
		});
	}
}