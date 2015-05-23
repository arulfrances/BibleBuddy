package model;

/**
 * Created by Arul Frances on 5/22/2015.
 */
public class Verse {
    private String verse, author, chapter, verseUrl, date;

    public Verse()
    {}

    public Verse(String verse, String author, String chapter,String verseUrl, String date)
    {
        this.verse = verse;
        this.author = author;
        this.verseUrl =verseUrl;
        this.chapter = chapter;
        this.date = date;
    }

    public String getVerse()
    {
        return verse;
    }

    public void setVerse(String verse)
    {
        this.verse = verse;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVerseUrl() {
        return verseUrl;
    }

    public void setVerseUrl(String verseUrl) {
        this.verseUrl = verseUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
}
