package com.example.wordpuzzle;

public class adminholder {

    String name,level,catg;
    int score;

    public adminholder() {
    }

    public adminholder(String name, String level, int score, String catg) {
        this.name = name;
        this.level = level;
        this.score = score;
        this.catg = catg;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getlevel() {
        return level;
    }

    public void setlevel(String level) {
        this.level = level;
    }

    public int getscore() {
        return score;
    }

    public void setscore(int score) {
        this.score = score;
    }

    public String getcatg() {
        return catg;
    }

    public void setcatg(String catg) {
        this.catg = catg;
    }
}
