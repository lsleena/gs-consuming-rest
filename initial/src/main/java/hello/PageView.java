package hello;

public class PageView {

    private int rowTime;
    private int viewTime;
    private String rowKey;
    private String userId;
    private String pageId;


    public int getRowTime() {
        return rowTime;
    }

    public void setRowTime(int rowTime) {
        this.rowTime = rowTime;
    }

    public int getViewTime() {
        return viewTime;
    }

    public void setViewTime(int viewTime) {
        this.viewTime = viewTime;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    @Override
    public String toString() {
        return "PageView{" +
                "rowKey='" + rowKey + '\'' +
                ", userId='" + userId + '\'' +
                ", pageId='" + pageId + '\'' +
                '}';
    }
}
