package com.hotel.mariam.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class RoomLevelTag extends BodyTagSupport {
    private int intType;

    public void setIntType(int intType) {
        this.intType = intType;
    }

    @Override
    public int doStartTag() throws JspException {
        ResourceBundle bundle = ResourceBundle.getBundle("language", pageContext.getResponse().getLocale());
        JspWriter out = pageContext.getOut();

        try {
            switch (intType) {
                case 1:
                    out.println(bundle.getString("roomLevel.economy"));
                    break;
                case 2:
                    out.println(bundle.getString("roomLevel.standard"));
                    break;
                case 3:
                    out.println(bundle.getString("roomLevel.improved"));
                    break;
                case 4:
                    out.println(bundle.getString("roomLevel.deluxe"));
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
