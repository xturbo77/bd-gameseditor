package de.xturbo77.bitdefender.gameseditor.db;

/**
 *
 * @author schmidt
 */
public class Process {

    private int id;
    private int type;
    private String name;
    private String product;
    private String friendlyName;
    private String fullPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:").append(getId())
            .append(" Type:").append(getType())
            .append(" Name:").append(getName())
            .append(" Product:").append(getProduct())
            .append(" FriendlyName:").append(getFriendlyName());
        return sb.toString();
    }

}
