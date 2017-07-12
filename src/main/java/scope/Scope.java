/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scope;

import java.util.Hashtable;

/**
 *
 * @author taleb
 */
public class Scope {

    public  enum   ApplicationContext {
        CURRENT_USER, CURRENT_ACCOUNTID
    };
    
    private static final  Hashtable<ApplicationContext, Object> hashtable = new Hashtable<>();

    public  static Object getAttribute(ApplicationContext contextAttribute) {
        try {
            return hashtable.get(contextAttribute);
        } catch (Exception e) {
        }
        return null;
    }

    public  static void   setAttribute(ApplicationContext contextAttribute, Object value) {
        hashtable.put(contextAttribute, value);
    }

}
