package beans;

import java.io.Serializable;
import java.util.Date;

public class beans01 implements Serializable {

    private Date date;
    
    public beans01() {
        this.date= new Date();
    }

    public Date getDate() {
        return date;
    }
}
