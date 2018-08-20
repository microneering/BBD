/*
 * BBDRegEx.java
 *
 * Created on January 23, 2007, 8:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 * 

 *
 */

package bbd;
/*
 * Copyright 2007 microneering, Inc and James Gamber
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
 * BBD/bbd/BBDRegEx.java
 */
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * 
 * @author james gamber
 */
public class BBDRegEx {
	
	private final Pattern pattern;
	private final String errorMessage;

	static private final Logger log = Logger.getLogger(BBDRegEx.class.getName());
	
    /**
     * Construct reg exp class with a pattern and an error message.
     *
     * @param pattern Regular expression pattern.
     * @param errorMessage Error message.
     */
    public BBDRegEx(final Pattern pattern, final String errorMessage) {
	
		this.pattern = pattern;
		this.errorMessage = errorMessage;
	}
	

    /**
     *
     * @param text
     * @return True if text matches pattern.
     */
    public boolean isMatch(final String text) {
		return matches(text);
	}

    /**
     *
     * @param s
     * @return True if string matches pattern.
     */
    public boolean matches(final String s) {
		
		boolean matches = pattern.matcher(s).matches();
		
		if (!matches) {
			log.info(errorMessage);
		}
		
		return matches;
	}

        // check that text does not contain
        // special characters "'<>;
    /**
     *
     * @param s Text to test if it is valid for the database text types.
     *
     * @return true if input string matches pattern
     */
    public boolean isSafeText(final String s) {
		return matches(s) && BBDValidator.isSafeText(s);
	}

        // replace the occurances of s2 in string s1
    /**
     *
     * @param s1
     * @param s2
     * @return S1 with pattern matches replaced by S2.
     */
    public String replace(final String s1, final String s2) {
		return s1.replaceAll(pattern.toString(), s2);
	}

}
