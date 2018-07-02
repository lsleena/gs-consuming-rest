package hello;

public class PostParams {

    private String userId;
    private String pageId;

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
        return "PostParams{" +
                "userId='" + userId + '\'' +
                ", pageId='" + pageId + '\'' +
                '}';
    }
}
