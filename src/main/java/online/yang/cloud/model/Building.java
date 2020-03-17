package online.yang.cloud.model;

/**
 * 楼宇信息实体
 *
 * @author 孙嘉
 * created in 2020/2/1 11:28
 */

public class Building {
    private String buildId;

    private String buildName;

    private Integer buildLayerCount;

    private Integer buildRoomCount;

    private String buildDate;

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId == null ? null : buildId.trim();
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName == null ? null : buildName.trim();
    }

    public Integer getBuildLayerCount() {
        return buildLayerCount;
    }

    public void setBuildLayerCount(Integer buildLayerCount) {
        this.buildLayerCount = buildLayerCount;
    }

    public Integer getBuildRoomCount() {
        return buildRoomCount;
    }

    public void setBuildRoomCount(Integer buildRoomCount) {
        this.buildRoomCount = buildRoomCount;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate == null ? null : buildDate.trim();
    }
}