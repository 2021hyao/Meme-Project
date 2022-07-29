

public class Rating {
	private int score;
	private User user;
	

	public Rating(User user, int score) {
		if(setScore(score) == false) {
			score = 0;
		}
		else {
			this.score = score;
		}
		setUser(user);
		user = getUser();
		
	}
	
	public Rating() {
		score = 0;
		user = new User();
	}
	
	public int getScore() {
		return score;
	}
	public boolean setScore (int score) {
		if(score == -1 || score == 0 || score == 1) {
			this.score = score;
			return true;
		}
		return false;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String toString() {
		if(score == 1) {
			return user.getUserName() + " rated as an upvote";
		}
		else if(score == -1) {
			return user.getUserName()+ " rated as a downvote";
		}
			return user.getUserName() + " rated as a pass";

	}
	public boolean equals(Object o) {
		if(o instanceof Rating == true &&  ((Rating)o).score== score && ((Rating)o).user.equals(user)) {
			return true;
		}
		return false;
	}



}
