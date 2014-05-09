package org.rendersnake;

public class ImageAttributes extends AbstractAttributes {

    public ImageAttributes src(String src) {
        this.add("src",src);
        return this;
    }
}
