import java.util.*;

public class CompareMemeByRating implements Comparator <Meme>{
	
	public CompareMemeByRating() {
		
	}
	@Override
	public int compare(Meme memeOne, Meme memeTwo) {
		int compare;
		if(memeOne.calculateOverallRating() < memeTwo.calculateOverallRating()) {
			return 1;
		}
		else if(memeOne.calculateOverallRating() > memeTwo.calculateOverallRating()) {
			return -1;
		}
		compare = memeOne.getCaption().compareTo(memeTwo.getCaption());
		if(compare !=0) {
			return compare;
		}
		compare = memeOne.getBackgroundImage().compareTo(memeTwo.getBackgroundImage());
		if(compare !=0) {
			return compare;
		}
		return memeOne.getCreator().compareTo(memeTwo.getCreator());
	}
}
