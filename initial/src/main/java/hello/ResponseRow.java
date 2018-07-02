package hello;

public class ResponseRow {
    public ResponseRow(Row requestRow) {
        user = (String)requestRow.getColumns().get(3);
        page = (String)requestRow.getColumns().get(4);

    }

    String user;
    String page;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ResponseRow{" +
                "user='" + user + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
