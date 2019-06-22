package com.hotel.mariam.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class RoomTypeTag extends BodyTagSupport {
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
                    out.println(bundle.getString("roomType.single"));
                    break;
                case 2:
                    out.println(bundle.getString("roomType.double"));
                    break;
                case 3:
                    out.println(bundle.getString("roomType.triple"));
                    break;
                case 4:
                    out.println(bundle.getString("roomType.quad"));
                    break;
                case 5:
                    out.println(bundle.getString("roomType.king"));
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
