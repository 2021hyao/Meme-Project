

public class Meme implements Comparable <Meme> {
	private User creator;
	private BackgroundImage backgroundImage;
	private Rating[] ratings;
	private String caption;
	private String captionVerticalAlign;
	private boolean shared;
	
	public Meme(BackgroundImage backgroundImage, String caption, User creator) {
		setBackgroundImage(backgroundImage);
		backgroundImage = getBackgroundImage();
		
		setCreator(creator);
		creator = getCreator();
		
		setCaption(caption);
		caption = getCaption();
		
		ratings = new Rating [10];
		
		captionVerticalAlign = "bottom";
	}
	
	public Meme() {
		backgroundImage = new BackgroundImage();
		creator = new User();
		ratings = new Rating[10];
		caption = "";
		captionVerticalAlign = "bottom";
		shared = false;
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BackgroundImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Rating[] getRatings() {
		return ratings;
	}

	public void setRatings(Rating[] r) {
		for(int i = 0; i<r.length;i++) {
			ratings[i] = r[i];
		}
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaptionVerticalAlign() {
		return captionVerticalAlign;
	}

	public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
		if(captionVerticalAlign.equals("top")||captionVerticalAlign.equals("middle")||captionVerticalAlign.equals("bottom")) {
		this.captionVerticalAlign = captionVerticalAlign;
		return true;
		}
		return false;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}
	
	public boolean addRating (Rating r) {
		for(int i = 0; i<ratings.length;i++) {
			if (ratings[i] == null) {
				ratings[i] = r;
				break;
			}
		}
		
		for(int i = 0; i<ratings.length;i++) {
			if(ratings[i].equals(r)) {
				return true;
			}
		}
		
		for(int i = 0; i<ratings.length-1;i++) {
			ratings[i] = ratings[i+1];
		}
		ratings [ratings.length-1] = r;
		
		for(int i = 0; i<ratings.length;i++) {
			if(ratings[i].equals(r)) {
				return true;
			}
		}
		return false;
		
	}
	
	public double calculateOverallRating() {
		double count = 0;
			for(int i = 0; i<ratings.length;i++) {
				if(ratings[i] == null) {
					break;
				}
				else {
					count = count + ratings[i].getScore();
				}
		}
		return count;
	}
	
	
	public String toString() {
		int posCount = 0;
		int negCount = 0;
		
		for(int i = 0; i < ratings.length; i++) {
			if(ratings[i]==null) {
				break;
			}
			else if(ratings[i].getScore() >0) {
				posCount ++;
			}
		}

		for(int i = 0; i < ratings.length; i++) {
			if(ratings[i]==null) {
				break;
			}
			else if(ratings[i].getScore() <0) {
				negCount ++;
			}
		}
		
		return backgroundImage.toString() +" '"+caption+"' "+calculateOverallRating()+ " [+1: "+posCount +", -1: "+negCount+"]"+"-created by "+this.creator.getUserName();
	}
	
	public boolean equals (Object o) {
		if(o instanceof Meme) {
			if(((Meme) o).creator.equals(creator)) {
				if(((Meme) o).caption.equals(this.caption)) {
					if(((Meme) o).backgroundImage.equals(this.backgroundImage)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int compareTo(Meme m) {
		int compare = this.getCaption().compareTo(m.getCaption());
		if(compare !=0) {
			return compare;
		}
		compare = this.getBackgroundImage().compareTo(m.getBackgroundImage());
		if(compare != 0) {
			return compare;
		}
		if(this.calculateOverallRating() < m.calculateOverallRating()) {
			return 1;
		}
		else if(this.calculateOverallRating() > m.calculateOverallRating()) {
			return -1;
		}
		if(this.isShared() == true  && m.isShared() == false) {
			return -1;
		}
		else if(m.isShared() == true && this.isShared() == false) {
			return 1;
		}
		return 0;
	}
}
