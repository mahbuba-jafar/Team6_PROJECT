package entity;

public class Products {
    private Integer id;
    private Integer item_id;
    private String name;
    private String category;
    private Float price;
    private Float old_price;
    private boolean sellable_online;
    private String link;
    private String other_colors;
    private String short_description;
    private String designer;
    private Float depth;
    private Float height;
    private Float width;

    public Products(Integer id, Integer item_id, String name, String category, Float price, Float old_price,
                    boolean sellable_online, String link, String other_colors, String short_description, String designer,
                    Float depth, Float height, Float width) {
        this.id = id;
        this.item_id = item_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.old_price = old_price;
        this.sellable_online = sellable_online;
        this.link = link;
        this.other_colors = other_colors;
        this.short_description = short_description;
        this.designer = designer;
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getOld_price() {
        return old_price;
    }

    public void setOld_price(Float old_price) {
        this.old_price = old_price;
    }

    public boolean isSellable_online() {
        return sellable_online;
    }

    public void setSellable_online(boolean sellable_online) {
        this.sellable_online = sellable_online;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOther_colors() {
        return other_colors;
    }

    public void setOther_colors(String other_colors) {
        this.other_colors = other_colors;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public Float getDepth() {
        return depth;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", item_id=" + item_id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", old_price=" + old_price +
                ", sellable_online=" + sellable_online +
                ", link='" + link + '\'' +
                ", other_colors='" + other_colors + '\'' +
                ", short_description='" + short_description + '\'' +
                ", designer='" + designer + '\'' +
                ", depth=" + depth +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
