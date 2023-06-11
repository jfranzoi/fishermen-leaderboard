package my.projects.fishermenleaderboard.api.web;

public class Pagination {
    private int size;
    private int page;

    public Pagination(int size, int page) {
        this.size = size;
        this.page = page;
    }

    public int from() {
        return size * (page - 1);
    }

    public int to() {
        return size;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "size=" + size + ",page=" + page +
                '}';
    }
}
