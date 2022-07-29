import java.util.*;
import java.util.ArrayList;

public class User implements Comparable <User> {
	private String userName;
	private ArrayList<Meme> memesCreated;
	private TreeSet<Meme> memesViewed;
	
	
	public User (String userName){
		this.userName = userName;
		memesCreated = new ArrayList <Meme> ();
		memesViewed = new TreeSet <Meme> ();
	}
	
	public User() {
		userName = "";
		memesCreated = new ArrayList <Meme> ();
		memesViewed = new TreeSet <Meme> ();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ArrayList<Meme> getMemesCreated() {
		return memesCreated;
	}
	public void setMemesCreated(ArrayList<Meme> memesCreated) {
		this.memesCreated = memesCreated;
	}
	public TreeSet<Meme> getMemesViewed() {
		return memesViewed;
	}
	public void setMemesViewed(TreeSet<Meme> memesViewed) {
		this.memesViewed = memesViewed;
	}
	
	public void rateMeme(Meme m, int rating) {
		memesViewed.add(m);
		Rating r = new Rating(this, rating);
		m.addRating(r);
		
	}
	
	public Meme createMeme(BackgroundImage b, String caption) {
		Meme newMeme = new Meme(b, caption, this);
		this.memesCreated.add(newMeme);
		return newMeme;
	}
	
	public boolean deleteMeme(Meme m) {
		for(int i = 0; i<this.memesCreated.size(); i++) {
			if(m.isShared() == false) {
				if(m.equals(this.memesCreated.get(i))) {
					this.memesCreated.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean rateNextMemeFromFeed(Feed f, int ratingScore) {
		memesViewed.add(f.getNewMeme(this));
		Rating newRating = new Rating(this, ratingScore);
		(f.getNewMeme(this)).addRating(newRating);
		if(f.getNewMeme(this) == null) {
			return false;
		}
		return true;
	}
	
	public void shareMeme(Meme m, Feed f) {
		m.setShared(true);
		f.getMemes().add(m);
	}
	
	public double calculateReputation() {
		if (memesCreated.size() == 0) {
			return 0.0;
		}
		for (Meme m: memesCreated) {
			return m.calculateOverallRating()/(double)memesCreated.size();
		}
		return 0.0;
	}
	
	public String toString() {
		if(memesViewed.isEmpty() == true) {
			return userName + " has rated 0 memes, (0.0)";
		}
		else {
			return userName + " has rated(" + memesViewed.size() + ") memes, ("+ calculateReputation()+ ")";
		}
	}
	public boolean equals(Object o) {
		if(o instanceof User == true) {
			if(((User)o).userName.equals(this.userName)) {
				return true;
			}
		}
		return false;
	}
	
	public int compareTo(User u) {
		int compare = this.getUserName().compareTo(u.getUserName());
		if (compare!= 0) {
			return compare;
		}
		if(this.memesCreated.size() < u.memesCreated.size()) {
			return 1;
		}
		else if(this.memesCreated.size() > u.memesCreated.size()) {
			return -1;
		}
		return 0;
	}
	

	
}
