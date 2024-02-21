package framework.elements;

import org.openqa.selenium.By;

public class CheckBox extends BaseElements {
    public CheckBox(By by) {
        super(by);
    }

    @Override
    protected String getElementType() {
        return getLoc("log.check.box");
    }
}