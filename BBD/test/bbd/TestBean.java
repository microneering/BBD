/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbd;

/**
 *
 * @author james gamber
 */
public class TestBean {

    private String bbdsql;
    private boolean depreciated;
    private boolean testonly;

    /**
     *
     */
    public TestBean() {
    }

    /**
     *
     * @return
     */
    public String getBbdsql() {
        return bbdsql;
    }

    /**
     *
     * @param bbdsql
     */
    public void setBbdsql(String bbdsql) {
        this.bbdsql = bbdsql;
    }

    /**
     *
     * @return
     */
    public boolean isDepreciated() {
        return depreciated;
    }

    /**
     *
     * @param depreciated
     */
    public void setDepreciated(boolean depreciated) {
        this.depreciated = depreciated;
    }

    /**
     *
     * @return
     */
    public boolean isTestonly() {
        return testonly;
    }

    /**
     *
     * @param testonly
     */
    public void setTestonly(boolean testonly) {
        this.testonly = testonly;
    }
    
    @Override
    public boolean equals(Object tb) {
        
        if (tb == null) return false;
        if (!(tb instanceof TestBean)) return false;
        
        TestBean t = (TestBean)tb;
        return t.isDepreciated()==isDepreciated()&&t.isTestonly()==isTestonly()&&
                t.getBbdsql().equals(getBbdsql());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.bbdsql != null ? this.bbdsql.hashCode() : 0);
        hash = 73 * hash + (this.depreciated ? 1 : 0);
        hash = 73 * hash + (this.testonly ? 1 : 0);
        return hash;
    }
    }

