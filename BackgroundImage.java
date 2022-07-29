
public class BackgroundImage implements Comparable <BackgroundImage>{
	private String imageFileName;
	private String title;
	private String description;

	
	public BackgroundImage(String imageFileName, String title, String description) {
		setImageFileName(imageFileName);
		imageFileName = getImageFileName();
		
		setTitle(title);
		title = getTitle();
		
		setDescription(description);
		description = getDescription();
	}
	public BackgroundImage() {
		imageFileName = "";
		title = "";
		description ="";
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return title + " <" + description +"> ";
	}
	
	public boolean equals(Object o) {
		if(o instanceof BackgroundImage == true) {
			if(((BackgroundImage) o).imageFileName.equals(imageFileName) == true) {
				if(((BackgroundImage) o).title.equals(title) == true) {
					if(((BackgroundImage) o).description.equals(description) == true) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int compareTo(BackgroundImage b){
		int compare = this.getImageFileName().compareTo(b.getImageFileName());
		if(compare != 0){
			return compare;
		}
		compare = this.getTitle().compareTo(b.getTitle());
		if(compare != 0) {
			return compare;
		}
		return this.getDescription().compareTo(b.getDescription());
	}
	
}


