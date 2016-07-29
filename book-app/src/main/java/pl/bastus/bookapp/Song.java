package pl.bastus.bookapp;

class Song {
    private String title;
    private String artist;
    private String rate;
    private String bpm;

    Song(String title, String artist, String rate, String bpm) {
        this.title = title;
        this.artist = artist;
        this.rate = rate;
        this.bpm = bpm;
    }

    Song(){

    }

    @Override
    public boolean equals(Object s) {
        Song song = null;
        if(s == null) {
            return false;
        }
        if(!(s instanceof Song)) {
            return false;
        }
        try {
            song = (Song) s;
        } catch (ClassCastException cce) {
            cce.printStackTrace();
            System.out.println("Class cast exception, probably p !(instaceof Piosenka)");
        }
        return getTitle().equals(song.getTitle());
    }

    public int hashCode() {
        return title.hashCode();
    }

    void printSong() {
        System.out.println("" + getArtist() + " - " + getTitle() + ", " + getRate() + ", " + getBpm());
    }

    String getTitle() {
        return title;
    }

    String getArtist() {
        return artist;
    }

    String getRate() {
        return rate;
    }

    String getBpm() {
        return bpm;
    }

    @Override
    public String toString() {
        return title;
    }
}