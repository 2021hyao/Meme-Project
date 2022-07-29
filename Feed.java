import java.util.ArrayList;

public class Feed {
	private ArrayList<Meme> memes;

	public Feed() {
		memes = new ArrayList<Meme>();
	}
	public ArrayList<Meme> getMemes() {
		return memes;
	}

	public void setMemes(ArrayList<Meme> memes) {
		this.memes = memes;
	}
	
	public Meme getNewMeme(User u) {
		for(Meme m: memes) {
			if(u.getMemesCreated().isEmpty() == true)
				break;
			for(Meme n: u.getMemesCreated()) {
				if(m.equals(n)) {
					return m;
				}
			}
		}
		return null;
	}
	
	public String toString() {
		String feed = "";
		for(Meme m: memes) {
			feed = feed+m;
		}
		return feed;
	}

}
