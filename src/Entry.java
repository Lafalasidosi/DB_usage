public class Entry {
    private String date;
    private String wakeupbs;
    private String breakfast;
    private String twohrbs1;
    private String prelunchbs;
    private String lunch;
    private String twohrbs2;
    private String presupperbs;
    private String supper;
    private String twohrbs3;
    private String prebedbs;
    private String exercise;
    private String comments;

    public String getDate() {
        return date;
    }

    public String getWakeupbs() {
        return wakeupbs;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getTwohrbs1() {
        return twohrbs1;
    }

    public String getPrelunchbs() {
        return prelunchbs;
    }

    public String getLunch() {
        return lunch;
    }

    public String getTwohrbs2() {
        return twohrbs2;
    }

    public String getPresupperbs() {
        return presupperbs;
    }

    public String getSupper() {
        return supper;
    }

    public String getTwohrbs3() {
        return twohrbs3;
    }

    public String getPrebedbs() {
        return prebedbs;
    }

    public String getExercise() {
        return exercise;
    }

    public String getComments() {
        return comments;
    }

    public Entry(String date, String wakeupbs, String breakfast, String twohrbs1, String prelunchbs, String lunch, String twohrbs2, String presupperbs, String supper, String twohrbs3, String prebedbs, String exercise, String comments) {
        this.date = date;
        this.wakeupbs = wakeupbs;
        this.breakfast = breakfast;
        this.twohrbs1 = twohrbs1;
        this.prelunchbs = prelunchbs;
        this.lunch = lunch;
        this.twohrbs2 = twohrbs2;
        this.presupperbs = presupperbs;
        this.supper = supper;
        this.twohrbs3 = twohrbs3;
        this.prebedbs = prebedbs;
        this.exercise = exercise;
        this.comments = comments;
    }
}