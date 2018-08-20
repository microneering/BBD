/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javax.persistence;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author James Gamber
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraint {
    String[] columnNames();
}
