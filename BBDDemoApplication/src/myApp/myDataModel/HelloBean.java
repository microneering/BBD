/**
 * 
 */
package myApp.myDataModel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * @author james gamber
 *
 */
@Entity
public class HelloBean implements Serializable {
    
        @Id
        @Column(name = "ID")
        private Long id;
	
	private String hello = "n/a";

	/**
	 * @return the hello
	 */
	public String getHello() {
		return hello;
	}

	/**
	 * @param hello the hello to set
	 */
	public void setHello(final String hello) {
		this.hello = hello;
	}
	
	/**
	 * @param hello the hello to set
	 */
	public void setHello(final Long id, final String hello) {
		this.hello = hello;
                this.id = id;
	}
	
	@Override
	public int hashCode()
	{		
		return hello.hashCode();
	}
	
	@Override
	public boolean equals(final Object o) {
		
		if ( !(o instanceof HelloBean)) {
			return false;
		}
		
		
		if (hello.equals(((HelloBean)o).getHello())) {
			return true;
		}
		
		return false;
	}

    public Long getId() {
        return id;
    }

}
