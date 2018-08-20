/*
 * BBDValidator.java
 *
 * Created on January 23, 2007, 9:04 AM
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
 * BBD/bbd/BBDValidator.java
 */
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Most applications written to integrate with databases use the datbase to
 * validate information. A date field is inserted into a table. The database may
 * decide the date is invalid and will throw and excepion. This exception is
 * caught by the application and an error is logged.
 *
 * This process requires a complete round trip to the database, all the layers
 * such as JDBC, operating system, network, ect are used. A similar process
 * happens on the database computer. Then the reverse of all this message
 * processing is performed to reply with the exception error.
 *
 * This can be prevented by validing the data in the application.
 *
 * This class performs the validation using compiled regular expressions. This
 * is extremely fast and is automatic every time the parameters are passed to
 * the database.
 *
 * The error condition is immediately reported and there is no loading on the
 * networks or the database.
 *
 * @param <R>
 * @param <L>
 * @author james gamber
 */
public class BBDValidator<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>> {

    static private final Logger log = Logger.getLogger( BBDValidator.class
                    .getName() );

    static private final List<String> databases = Collections
                    .synchronizedList( new ArrayList<String>() );

    static private final Map<String, ArrayList<String>> tables = Collections
                    .synchronizedMap( new HashMap<String, ArrayList<String>>() );

    static private boolean recursing = false;

    static final int UNKNOWNTYPE = -9999;

    /**
     * Check that the datbase requested does in fact exist.
     *
     * Typical databases like SQL server contain many databases within one
     * instance. Generally, the list of database is retrievable using SQL that
     * may be database dependant (Oracle, SqlServer, etc)
     *
     * The first time this is called, it will query the databse for a list of
     * databases that is cached. After that, only the cache is used.
     *
     * @param db name of database
     * @throws java.sql.SQLException
     * @return true is database exists.
     */
    static public boolean isValidDatabase( final String db ) throws SQLException {

        // database is valid during recursion
        if( recursing ) {
            return true;
        }

        // perform one time load of list of valid databases
        if( databases.size() == 0 ) {

            recursing = true;

            final BBDConnection<IBBDRow<IBBDField>, BBDRowArrayList<IBBDRow<IBBDField>>> connection
                            = new BBDConnection<IBBDRow<IBBDField>, BBDRowArrayList<IBBDRow<IBBDField>>>();

            final BBDAPI<IBBDRow<IBBDField>> api = new BBDAPI<IBBDRow<IBBDField>>( BBDProperties
                            .get( BBDProperties.BBDDatabase ), "getDB" );
            api.setBbdPrincipal( BBDBeanConnection.bbdDBPrincipal );

            // this call would be recursive, except we inhibit recursion with
            // the 'recursing' local variable.
            final BBDRowArrayList<IBBDRow<IBBDField>> sqlRowAL = connection
                            .executeQuery( ( IBBDAPI<IBBDRow<IBBDField>> ) api );

            for( final IBBDRow<IBBDField> dsr : sqlRowAL ) {
                databases.add( ( String ) dsr.getBBDField( 0 ).getValue() );
            }

            recursing = false;

        }

        if( databases.contains( db ) ) {
            return true;
        } else {
            log.severe( "Invalid database " + db );
            return false;
        }

    }

    /**
     * Check that the table requested does in fact exist. \ * The first time
     * this is called, it will query the databse for a list of tables that is
     * cached. After that, only the cache is used.
     *
     * @param db name of database
     * @param table name of table
     * @throws java.sql.SQLException
     * @return true is database exists.
     */
    static public boolean isValidTable( final String db, final String table )
                    throws SQLException {

        // perform one time load of list of tables for a given database
        if( tables.get( db ) == null ) {

            tables.put( db, new ArrayList<String>() );

            final BBDConnection<IBBDRow<IBBDField>, BBDRowArrayList<IBBDRow<IBBDField>>> connection
                            = new BBDConnection<IBBDRow<IBBDField>, BBDRowArrayList<IBBDRow<IBBDField>>>();

            final BBDAPI<IBBDRow<IBBDField>> api = new BBDAPI<IBBDRow<IBBDField>>( BBDProperties
                            .get( BBDProperties.BBDDatabase ), "getTableNames" );
            api.setBbdPrincipal( BBDBeanConnection.bbdDBPrincipal );
            final Object[] params = {db};

            final BBDRowArrayList<IBBDRow<IBBDField>> sqlRowAL = connection.executeQuery(
                            ( IBBDAPI<IBBDRow<IBBDField>> ) api, params );

            final ArrayList<String> listTableNames = tables.get( db );

            for( final IBBDRow<IBBDField> dsr : sqlRowAL ) {
                listTableNames.add( ( ( String ) dsr.getBBDField( 0 ).getValue() )
                                .toLowerCase() );
            }
        }

        if( tables.get( db ) != null ) {
            final ArrayList<String> listTableNames = tables.get( db );
            if( listTableNames.contains( table.toLowerCase() ) ) {
                return true;
            }
        }

        log.severe( "Invalid table name " + db + ":" + table );
        return false;

    }

    /**
     * Validate one field of data being passed to a stored procedure.
     *
     * @param field IBBDField to validate
     * @param fieldType
     * @param fieldName
     * @throws SQLException
     * @return true for valid data
     */
    static public boolean isValidSqlData( final IBBDField field,
                                          final int fieldType, final String fieldName ) throws SQLException {
        return isValidSqlData( field.getValue(), fieldType, fieldName );
    }

    /**
     * Validate one field of data being passed to a stored procedure.
     *
     * @param field Java Object to validate, for example a String
     * @param type SQL data type
     * @param fieldName Name of field in stored procedure arguments list
     * @throws SQLException
     * @return true for valid data
     */
    static public boolean isValidSqlData( final Object field, final int type,
                                          final String fieldName ) throws SQLException {

        // make all nulls valid, let db reject them by throwing sql exception
        // if they are not null fields in target tables.
        if( field == null ) {
            return true;
        }

        switch( type ) {
            case Types.TINYINT:
            case Types.INTEGER:
                try {
                    Integer i = Integer.valueOf( field.toString() );
                } catch( Exception e ) {
                    return false;
                }
                break;

            case Types.BIGINT:
                try {
                    BigInteger i = new BigInteger( field.toString() );
                } catch( Exception e ) {
                    return false;
                }
                break;

            case Types.FLOAT:
                break;

            case Types.REAL:
                break;

            case Types.VARCHAR:
            case Types.CHAR:
            case Types.LONGVARCHAR:
                return ansiPrintableCharsExpression.matches( field.toString() );

            case Types.DATE:
                break;

            case Types.DECIMAL:

                break;

            case Types.BOOLEAN:
                return isValidBoolean( field.toString() );

            // types without validation
            case Types.BINARY:
            case Types.BIT:
            case Types.VARBINARY:
                break;

            case Types.TIME:
            case Types.TIMESTAMP:
                break;

            case Types.NULL:
                break;

            case UNKNOWNTYPE:
                log.info( "no validation on " + fieldName );
                break;

            default:
                throw new SQLException( "DBValidator:Validate Unknown dbType type "
                                + type + " for field " + fieldName );
        }

        return true;
    }

    // ***
    // Regular Expression useful in Validation
    //
    // Accepts only digits. No spaces before or after are
    // allowed. No monetary symbol is allowed. No decimal
    // separator is allowed.
    /**
     *
     */
    static public final Pattern ansiNumericPattern = Pattern.compile( "^\\d*$" );

    /**
     *
     */
    static public final String ansiNumericError = "Limit characters to ANSI digits";

    /**
     *
     */
    static public final BBDRegEx ansiNumericExpression = new BBDRegEx(
                    ansiNumericPattern, ansiNumericError );

    // Accepts only digits, sign, and/or decimal. 
    // 
    /**
     *
     */
    static public final Pattern numericPattern = Pattern.compile( "[-+]?\\d*\\.?\\d+$" );

    /**
     *
     */
    static public final String numericError = "Limit characters to sign, digits, decimal";

    /**
     *
     */
    static public final BBDRegEx numericExpression = new BBDRegEx(
                    numericPattern, numericError );

    // Accepts only basic ANSI letters, digits, the period
    // character, and space characters. Requires that at
    // least one character exist to be validated.
    /**
     *
     */
    static public final Pattern ansiNamePattern = Pattern
                    .compile( "^[\\w\\d\\.\\s\\,\\']*$" );

    /**
     *
     */
    static public final String ansiNameError = "Limit characters to alphanumeric, spaces, and these symbols .,'";

    /**
     *
     */
    static public final BBDRegEx ansiNameExpression = new BBDRegEx(
                    ansiNamePattern, ansiNameError );

    // Accepts only basic ANSI letters, digits, the period
    // character, and space characters. Requires that at
    // least one character exist to be validated.
    /**
     *
     */
    static public final Pattern ansiPrintableCharsPattern = Pattern
                    .compile( "^[\\w\\d\\.\\s\\,\\'\\(\\)\\[\\]\\{\\}\\;\\/\\-\\#\\+\"\\?\\&\\*\\:\\=\\\\\\&\\%\\^_@<>]*$" );

    /**
     *
     */
    static public final String ansiError = "Limit characters to alphanumeric, spaces, and these symbols [].,'_(){}/-#+?@&*:=;<>&%^\\��";

    /**
     *
     */
    static public final BBDRegEx ansiPrintableCharsExpression = new BBDRegEx(
                    ansiPrintableCharsPattern, ansiError );

    // Looks for characters that can be considered unsafe text.
    // Items to watch out for include quotes, apostrophes,
    // semi-colons, and the less than and greater than signs.
    // Unknown malicious characters can easily bypass the
    // isSafeText expression.
    /**
     *
     */
    static public final Pattern safeTextPattern = Pattern
                    .compile( "[\\\"\\'\\<\\>\\;]" );

    /**
     *
     */
    static public final String safeTextError = "String cannot contain these symbols <>;'\"";

    /**
     *
     */
    static public final BBDRegEx safeTextExpression = new BBDRegEx(
                    safeTextPattern, safeTextError );

    // Accepts only valid email characters.
    // Valid email characters are limited to
    // Alphanumeric, periods, underscores, and the
    // at symbol.
    /**
     *
     */
    static public final Pattern ansiEmailPattern = Pattern
                    .compile( "^[\\w-]+(?:\\.[\\w-]+)*@(?:[\\w-]+\\.)+[a-zA-Z]{2,7}$" );

    // static public final String AnsiEmailPattern = "^[\\w\\d\\.\\@_]*$";
    /**
     *
     */
    static public final String ansiEmailError = "Limit characters to alphanumeric and these symbols _.@";

    /**
     *
     */
    static public final BBDRegEx ansiEmailExpression = new BBDRegEx(
                    ansiEmailPattern, ansiEmailError );

    // Accepts only valid telephone number characters.
    // This restricts the input to digits, hyphens, parentheses,
    // periods, commas, space characters, the plus symbol,
    // and ansi letters.
    // This expression can be customized to add better parsing
    // and to limit input types.
    //
    // Examples of valid input
    // 111 111 1111
    // (111) 111-1111
    // (111) 111-1111 ext. 555
    /**
     *
     */
    static public final Pattern ansiPhonePattern = Pattern
                    .compile( "^[\\w\\d\\.\\s\\(\\)\\+\\,\\-]*$" );

    /**
     *
     */
    static public final String ansiPhoneError = "Limit characters to ANSI alphanumerics, spaces, and these symbols .,+-()";

    /**
     *
     */
    static public final BBDRegEx ansiPhoneExpression = new BBDRegEx(
                    ansiPhonePattern, ansiPhoneError );

    // Accepts only valid address input characters.
    // This restricts the input to ansi letters, digits,
    // commas, hyphens, periods, semi-colons, and space
    // characters.
    /**
     *
     */
    static public final Pattern ansiAddressPattern = Pattern
                    .compile( "^[\\w\\d\\.\\s\\;\\,\\-]*$" );

    /**
     *
     */
    static public final String ansiAddressError = "Limit characters to ANSI alphanumerics, spaces, and these symbols .,-;";

    /**
     *
     */
    static public final BBDRegEx ansiAddressExpression = new BBDRegEx(
                    ansiAddressPattern, ansiAddressError );

    // Accepts only two character states
    // This can be extended to actually check against a valid
    // listing of states. This is a first tier approach that
    // can be much easier to implement.
    /**
     *
     */
    static public final Pattern ansiTwoCharacterStatePattern = Pattern
                    .compile( "^[\\w]{2}$" );

    /**
     *
     */
    static public final String ansiTwoCharacterStateError = "Limit to 2 ANSI alpha characters";

    /**
     *
     */
    static public final BBDRegEx ansiTwoCharacterStateExpression = new BBDRegEx(
                    ansiTwoCharacterStatePattern, ansiTwoCharacterStateError );

    // Accepts on US state two chaaracter codes
    /**
     *
     */
    static public final Pattern usTwoCharacterStatePattern = Pattern
                    .compile( "^(AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY)$" );

    /**
     *
     */
    static public final String usTwodCharacterStateError = "Limit to 2 ANSI apha characters for US states";

    /**
     *
     */
    static public final BBDRegEx usTwoCharacterStateExpression = new BBDRegEx(
                    usTwoCharacterStatePattern, usTwodCharacterStateError );

    // Accepts only alpha state or city names with periods.
    // This can be extended to check against actual
    // state names. This is a first tier approach that
    // can be much easier to implement.
    /**
     *
     */
    static public final Pattern ansiCityStatePattern = Pattern
                    .compile( "^[\\w\\s\\.\\'\\,]*$" );

    /**
     *
     */
    static public final String ansiCityStateError = "Limit characters to ANSI alpha characters, spaces, and these symbols .,'";

    /**
     *
     */
    static public final BBDRegEx ansiCityStateExpression = new BBDRegEx(
                    ansiCityStatePattern, ansiCityStateError );

    // Accepts only 5 character zip codes
    /**
     *
     */
    static public final Pattern ansiBasicZipCodePattern = Pattern
                    .compile( "^\\d{5}$" );

    /**
     *
     */
    static public final String ansiBasicZipCodeError = "Limit to 5 ANSI digits";

    /**
     *
     */
    static public final BBDRegEx ansiBasicZipCodeExpression = new BBDRegEx(
                    ansiBasicZipCodePattern, ansiBasicZipCodeError );

    // Accepts only 9 character zip codes
    // with a hyphen separator between digits
    // 5 and 6
    /**
     *
     */
    static public final Pattern ansiExtendedZipCodePattern = Pattern
                    .compile( "^\\d{5}\\-\\d{4}$" );

    /**
     *
     */
    static public final String ansiExtendedZipCodeError = "Limit to 5 ANSI digits, a hyphen, and 4 more ANSI digits";

    /**
     *
     */
    static public final BBDRegEx ansiExtendedZipCodeExpression = new BBDRegEx(
                    ansiExtendedZipCodePattern, ansiExtendedZipCodeError );

    // Accepts only 9 social security number
    // with a hyphen separators
    /**
     *
     */
    static public final Pattern socialSecurityPattern = Pattern
                    .compile( "^\\d{3}-\\d{2}-\\d{4}$" );

    /**
     *
     */
    static public final String socialSecurityError = "Limit to 3 digits, hyphen, 2 digits, hyphen, 4 digits ";

    /**
     *
     */
    static public final BBDRegEx socialSecurityExpression = new BBDRegEx(
                    socialSecurityPattern, socialSecurityError );

    // ***
    // Regular Expression useful in String stripping and conversion
    //
    // static public final Pattern StripHtmlTagsPattern = "\\<\\.\\*\\>";
    /**
     *
     */
    static public final Pattern stripHtmlTagsPattern = Pattern.compile( "<.*>" );

    /**
     *
     */
    static public final BBDRegEx stripHtmlTagsExpression = new BBDRegEx(
                    stripHtmlTagsPattern, "" );

    /**
     *
     */
    static public final Pattern stripHtmlTagCharactersPattern = Pattern
                    .compile( "[\\<\\>]" );

    /**
     *
     */
    static public final BBDRegEx stripHtmlTagCharactersExpression = new BBDRegEx(
                    stripHtmlTagCharactersPattern, "" );

    /**
     *
     */
    static public final Pattern escapeApostrophesPattern = Pattern
                    .compile( "[\\']" );

    /**
     *
     */
    static public final BBDRegEx escapeApostrophesExpression = new BBDRegEx(
                    escapeApostrophesPattern, "" );

    /**
     *
     */
    static public final Pattern escapeNewLinesToHtmlPattern = null;/*
																	 * Pattern.compile
																	 * ("(?:\\\\r\\\\n|\\\\n\\\\r|\\\\r|\\\\n");
     */

    /**
     *
     */
    static public final BBDRegEx escapeNewLinesToHtmlExpression = new BBDRegEx(
                    escapeNewLinesToHtmlPattern, "" );

    // ***
    // String Normalization Methods
    //
    /**
     *
     * @param text
     * @return True if valid HTML tag.
     */
    static public final String stripHtmlTags( final String text ) {
        return stripHtmlTagsExpression.replace( text, "" );
    }

    // This method is multi input hack safe. This means
    // that tags spread over multiple inputs won't miss
    // being stripped. For instance:
    //
    // input 1: <script
    // input 2: language=javascript>alert('me');
    //
    // This would get parsed out because the tag
    // begin and end symbols get ripped. Another
    // stripping approach could be to strip the tag
    // characters plus whatever is inside, but this
    // doesn't work when input is spread over multiple
    // controls.
    /**
     *
     * @param text
     * @return String with tags removed.
     */
    static public final String stripHtmlTagsSafe( final String text ) {
        return stripHtmlTagCharactersExpression.replace( text, "" );
    }

    /**
     *
     * @param text
     * @return String with replacements
     */
    static public final String escapeApostrophes( final String text ) {
        return escapeApostrophesExpression.replace( text, "''" );
    }

    // If you are going to use this method then call
    // StripHtmlTags first, then call this method. Else
    // you'll strip out the BR's.
    /**
     *
     * @param text
     * @return String with replacements
     */
    static public final String escapeNewLinesToHtml( final String text ) {
        return escapeNewLinesToHtmlExpression.replace( text, "<p>" );
    }

    // ***
    // Numeric Validator Methods
    //
    // Parses valid ansi numbers
    /**
     *
     * @param number
     * @return True if valid number.
     */
    static public boolean isValidAnsiNumber( final String number ) {
        return ansiNumericExpression.isMatch( number );
    }

    // Parses 5 digit zip codes
    /**
     *
     * @param zip
     * @return True if valid zip code.
     */
    static public boolean isValidFiveDigitZipCode( final String zip ) {
        return ansiBasicZipCodeExpression.isMatch( zip );
    }

    // Parses 9 digit zip codes
    /**
     *
     * @param zip
     * @return True is 9 digit zip code.
     */
    static public boolean isValidNineDigitZipCode( final String zip ) {
        return ansiExtendedZipCodeExpression.isMatch( zip );
    }

    // email xxxx@yyy.zzz
    /**
     *
     * @param email
     * @return True if valid email address.
     */
    static public boolean isValidEmail( final String email ) {
        return ansiEmailExpression.isMatch( email );
    }

    // social security 123-12-1234
    /**
     *
     * @param ss
     * @return True if valid social security number.
     */
    static public boolean isValidSocialSecurity( final String ss ) {
        return socialSecurityExpression.isMatch( ss );
    }

    // Attempts to parse the number based on the current
    // locale. This can be used for international support.
    /**
     *
     * @param number
     * @return True if is a number.
     */
    static public boolean isParseableNumber( final String number ) {

        return numericExpression.isMatch( number );
    }

    // ***
    // String Data Validators
    //
    // Processes a name value
    /**
     *
     * @param name
     * @return True if is an ASNI Name
     */
    static public boolean isValidAnsiName( final String name ) {
        return ansiNameExpression.isMatch( name );
    }

    // Processes phone number values
    /**
     *
     * @param name
     * @return True if is a phone number.
     */
    static public boolean isValidAnsiPhoneNumber( final String name ) {
        return ansiPhoneExpression.isMatch( name );
    }

    // Processes address values
    /**
     *
     * @param address
     * @return True if is an address.
     */
    static public boolean isValidAnsiAddress( final String address ) {
        return ansiAddressExpression.isMatch( address );
    }

    // Processes two character state strings
    /**
     *
     * @param state
     * @return True if is a valid state.
     */
    static public boolean isValidAnsiTwoCharacterState( final String state ) {
        return ansiTwoCharacterStateExpression.isMatch( state );
    }

    // Processes two character us state strings
    /**
     *
     * @param state
     * @return True if is a valid state abbreviation.
     */
    static public boolean isValidAnsiTwoCharacterUSState( final String state ) {
        boolean twoChar = ansiTwoCharacterStateExpression.isMatch( state );
        if( !twoChar ) {
            return false;
        }
        return usTwoCharacterStateExpression.isMatch( state );

    }

    // Processes city or state strings
    /**
     *
     * @param citystate
     * @return True if is a city and state.
     */
    static public boolean isValidAnsiCityOrState( final String citystate ) {
        return ansiCityStateExpression.isMatch( citystate );
    }

    // Processes safe text
    /**
     *
     * @param text
     * @return True is text is safe for database.
     */
    static public boolean isSafeText( final String text ) {
        return !safeTextExpression.matches( text );
    }

    /* Clear cached data */
    static void clear() {
        databases.clear();
        tables.clear();
    }

    /**
     *
     * @param string
     * @return True if a valid boolean.
     */
    public static boolean isValidBoolean( String string ) {
        if( "true".equals( string.toLowerCase() )
                        || "false".equals( string.toLowerCase() ) ) {
            return true;
        }

        return false;
    }

}
