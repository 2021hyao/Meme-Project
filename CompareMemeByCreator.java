import java.util.Comparator;

public class CompareMemeByCreator implements Comparator <Meme>{
	public CompareMemeByCreator() {
	}
	@Override
	public int compare(Meme memeOne, Meme memeTwo) {
		int compare = memeOne.getCreator().compareTo(memeTwo.getCreator());
		if(compare != 0) {
			return compare;
		}
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
		if(compare != 0) {
			return compare;
		}
		if(memeOne.isShared() == true  && memeTwo.isShared() == false) {
			return -1;
		}
		else if(memeTwo.isShared() == true && memeOne.isShared() == false) {
			return 1;
		}
		return 0;
	}
}
