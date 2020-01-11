/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.webservices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import com.nymble.bean.mgr.BlockedUserManager;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "NymbleWebService")
public class NymbleWebService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "blockeddomain")
    public String blockeddomain(@WebParam(name = "pseudonym") String pseudonym, @WebParam(name = "domain") String domain) {
        //TODO write your implementation code here:
        BlockedUserManager blockedUserManager = new BlockedUserManager();
        blockedUserManager.insertBlockedDomain(pseudonym, domain);
        return null;
    }
}
