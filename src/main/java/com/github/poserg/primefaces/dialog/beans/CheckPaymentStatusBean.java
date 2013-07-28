package com.github.poserg.primefaces.dialog.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import ru.it.appquiry.PostBlock;
import ru.it.appquiry.upi.Citizenship;
import ru.it.appquiry.upi.DocTypes;
import ru.it.appquiry.wsclient.AppQuiryClientImpl;
import ru.it.appquiry.wsclient.AsyncCallback;
import ru.it.artefacts.x.dob.poltava.appquiry.req_types._1.GetInquiryStateResponse;

/**
 * Диалог "Проверить наличие платежа".
 * 
 * @author Sergey Popov
 */
@ManagedBean(name = "CheckPaymentStatusBean")
@SessionScoped
public class CheckPaymentStatusBean {

    private final AppQuiryClientImpl appQuiryClientImpl;

    private String snils;
    private int docType;
    private int docNumber;
    private int citizenship;
    private String inn;
    private String kpp;
    private String startPeriod;
    private String endPeriod;

    private boolean check = false;

    private String selectedDocType = DocTypes.DT01.getType();
    private String selectedCitizenship = Citizenship.C643.getId();
    private String documentNumber;
    private Date selectedDateFrom;
    private Date selectedDateTo;
    
    private String testValue;

    public CheckPaymentStatusBean() {
        // :TODO
        // appQuiryClientImpl = new AppQuiryClientImpl(userName, pass, wsdl);
        appQuiryClientImpl = new AppQuiryClientImpl();
    }

    public void getPayments() {
        PostBlock postBlock = new PostBlock();
        String supplierBillID = null;
        appQuiryClientImpl.exportAllQuittance(postBlock, supplierBillID, new AsyncCallback<GetInquiryStateResponse>() {

            @Override
            public void onSuccess(final GetInquiryStateResponse t) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(final String snils) {
        this.snils = snils;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(final int docType) {
        this.docType = docType;
    }

    public int getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(final int docNumber) {
        this.docNumber = docNumber;
    }

    public int getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(final int citizenship) {
        this.citizenship = citizenship;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(final String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(final String kpp) {
        this.kpp = kpp;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(final String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(final String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public List<SelectItem> getDocTypesList() {
        DocTypes[] docTypes = DocTypes.values();
        List<SelectItem> docTypesList = new ArrayList<SelectItem>();
        for (DocTypes item : docTypes) {
            docTypesList.add(new SelectItem(item.getType(), item.getDescription()));
        }
        return docTypesList;
    }

    public List<SelectItem> getCitizensipList() {
        Citizenship[] citizenships = Citizenship.values();
        List<SelectItem> citizenList = new ArrayList<SelectItem>();
        for (Citizenship item : citizenships) {
            citizenList.add(new SelectItem(item.getId(), item.getShortName()));
        }
        return citizenList;
    }

    public void checkPayments() {
        AppQuiryClientImpl appQuiryClientImpl = new AppQuiryClientImpl();

        inn = "fafdadaf";
        kpp = "232323232323";
        PostBlock postBlock = new PostBlock();
        postBlock.setID(UUID.randomUUID().toString());
        postBlock.setTimeStamp(new Date().toString());
        postBlock.setSenderIdentifier("30633a");
        
        test(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String arg0) {
				testValue = arg0;
				RequestContext.getCurrentInstance().update("CheckPaymentStatus:resultInput");
			}
		});
        
        // String payerId = UPICreator.createIdentifier(snils);
        // Payers payers = new Payers();
        // payers.setPayerIdentifier(payerId);
        //
        // appQuiryClientImpl.exportPayment(postBlock, null, null,
        // payers,
        // new AsyncCallback<GetInquiryStateResponse>() {
        //
        // @Override
        // public void onSuccess(final GetInquiryStateResponse result) {
        // System.out.println("It's OK");
        // }
        //
        // @Override
        // public void onFailure(final Throwable caught) {
        // super.onFailure(caught);
        // }
        //
        // });
    }
    
    private void test(AsyncCallback<String> callback) {
    	callback.onSuccess("Ok2");
    }

    /**
     * @return the selectedDocType
     */
    public String getSelectedDocType() {
        return selectedDocType;
    }

    /**
     * @param selectedDocType the selectedDocType to set
     */
    public void setSelectedDocType(final String selectedDocType) {
        this.selectedDocType = selectedDocType;
    }

    /**
     * @return the selectedCitizenship
     */
    public String getSelectedCitizenship() {
        return selectedCitizenship;
    }

    /**
     * @param selectedCitizenship the selectedCitizenship to set
     */
    public void setSelectedCitizenship(final String selectedCitizenship) {
        this.selectedCitizenship = selectedCitizenship;
    }

    /**
     * @return the selectedDateFrom
     */
    public Date getSelectedDateFrom() {
        return selectedDateFrom;
    }

    /**
     * @param selectedDateFrom the selectedDateFrom to set
     */
    public void setSelectedDateFrom(final Date selectedDateFrom) {
        this.selectedDateFrom = selectedDateFrom;
    }

    /**
     * @return the selectedDateTo
     */
    public Date getSelectedDateTo() {
        return selectedDateTo;
    }

    /**
     * @param selectedDateTo the selectedDateTo to set
     */
    public void setSelectedDateTo(final Date selectedDateTo) {
        this.selectedDateTo = selectedDateTo;
    }

    /**
     * @return the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber the documentNumber to set
     */
    public void setDocumentNumber(final String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * @return the check
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(final boolean check) {
        this.check = check;
    }

	/**
	 * @return the testValue
	 */
	public String getTestValue() {
		System.err.println("testValue = " + testValue);
		return testValue;
	}

	/**
	 * @param testValue the testValue to set
	 */
	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}
}
