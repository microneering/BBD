package bbdSwing;

/*
 * Copyright 2009 microneering, Inc and James Gamber
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * BBD/bbdSwing/BBDTableColumnEditLocker.java
 */

/**
 *
 * @author james gamber
 */

public class BBDTableColumnEditLocker {

    int[] lockedColumns;

    /**
     * Constructor
     *
     * @param table of rows from database
     * @param lockedColumns int[] of columns that are not editable
     */
    public BBDTableColumnEditLocker(final BBDBeanJTable table, final int[] lockedColumns) {
        this.lockedColumns = lockedColumns;
        table.setColumnEditLocker(this);
    }

    /**
     * Check if column is locked from editing.
     *
     * @param columnNumber number of column to check
     * @return true is column is editable;
     */
    public boolean isColumnEditable(int columnNumber) {

        for (int i : lockedColumns) {
            if (columnNumber == i) {
                return false;
            }
        }

        return true;
    }

}
