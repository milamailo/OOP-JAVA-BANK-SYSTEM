package oop.bank.system.classes;

/**
 * Interface to define the archiving capabilities for transaction details.
 * Classes implementing this interface can archive transaction information,
 * which is essential for maintaining records and ensuring compliance.
 */
public interface IArchive {
    /**
     * Archives transaction details.
     * @param transactionDetails String containing details of the transaction to be archived.
     */
    void archiveTransaction(String transactionDetails);
}
