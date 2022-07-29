import java.util.Collections;

public class OrderableFeed extends Feed{
	public OrderableFeed() {
	}
	public void sortByCaption() {
		Collections.sort(this.getMemes());
	}
	
	public void sortByRating(){
		CompareMemeByRating a = new CompareMemeByRating();
			Collections.sort(this.getMemes(),a);
	}
	
	public void sortByCreator() {
		CompareMemeByCreator b = new CompareMemeByCreator();
		Collections.sort(this.getMemes(),b);
	}
}
  