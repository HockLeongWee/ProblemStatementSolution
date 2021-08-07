package sg.edu.rp.c346.id20046765.problemstatementsolution;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String singers;
    private int year;
    private float stars;

    public Song(int id, String title, String singers, int year, float stars) { // Constructor
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() {  return id;  }

    public String getTitle() {  return title;  }

    public String getSingers() {  return singers;  }

    public int getYear() {  return year;  }

    public float getStars() {  return stars;  }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setSingers(String newSinger) {

        this.singers = newSinger;
    }

    public void setYear(int newYear) {

        this.year = newYear;
    }

    public String getYearString(){
        String strYear = "";
        strYear = String.valueOf(year);
        return strYear;

    }

    public void setStars(float newStars) {

        this.stars = newStars;
    }

    public String getStarsStars(){
        String showStarStar = "";

        for(int i = 0; i < getStars(); i++){
            showStarStar += "* ";
        }

        return showStarStar;
    }

    @Override //What I put here = What the data will be shown
    public String toString() {
        String showStar = "";
        for(int i = 0; i < getStars(); i++){
            showStar += "*";
        }

        return title + "\n" + singers + " - " + year + "\n" + showStar;  }

}
