package com.martinchamarro.lazystorage.model;

public class Box {

    private long id;
    private float width;
    private float height;
    private int color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override public String toString() {
        final StringBuffer sb = new StringBuffer("Box{");
        sb.append("id=").append(id);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}
