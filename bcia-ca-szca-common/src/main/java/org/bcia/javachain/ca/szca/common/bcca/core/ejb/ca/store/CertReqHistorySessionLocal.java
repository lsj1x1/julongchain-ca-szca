
package org.bcia.javachain.ca.szca.common.bcca.core.ejb.ca.store;

import java.math.BigInteger;
import java.security.cert.Certificate;
import java.util.List;

import javax.ejb.Local;

import org.cesecore.certificates.certificate.CertificateInfo;
import org.cesecore.certificates.endentity.EndEntityInformation;
import org.ejbca.core.model.ca.store.CertReqHistory;


@Local
public interface CertReqHistorySessionLocal extends CertReqHistorySession {

    /**
     * Method used to add a CertReqHistory to database
     * 
     * @param cert the certificate to store (Only X509Certificate used for now)
     * @param endEntityInformation the user information used when issuing the certificate.
     */
    void addCertReqHistoryData(Certificate cert, EndEntityInformation endEntityInformation);
    
	CertificateInfo findFirstCertificateInfo(String issuerDN, BigInteger serno);
	
    /**
     * Method to remove CertReqHistory data.
     *
     * @param certFingerprint the primary key.
     */
    void removeCertReqHistoryData(String certFingerprint);
    
    /**
     * Retrieves the certificate request data belonging to given certificate serialnumber and issuerdn
     * 
     * NOTE: This method will try to repair broken XML and will in that case
     * update the database. This means that this method must always run in a
     * transaction! 
     * 
     * @param certificateSN serial number of the certificate
     * @param issuerDN
     * @return the CertReqHistory or null if no data is stored with the certificate.
     */
    CertReqHistory retrieveCertReqHistory(BigInteger certificateSN, String issuerDN);

    /**
     * NOTE: This method will try to repair broken XML and will in that case
     * update the database. This means that this method must always run in a
     * transaction! 
     * 
     * @return all certificate request data belonging to a user.
     */
    List<CertReqHistory> retrieveCertReqHistory(String username);
}