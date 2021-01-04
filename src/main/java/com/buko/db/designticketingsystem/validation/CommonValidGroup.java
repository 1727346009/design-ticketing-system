package com.buko.db.designticketingsystem.validation;

import javax.validation.groups.Default;

/**
 * @author Mr.徐健威
 */
public class CommonValidGroup {
    public interface Common extends Default {
    }

    public interface Insert extends Default {
    }

    public interface Update extends Default {
    }

    public interface Select extends Default {
    }
}