package com.github.poserg.primefaces.dialog.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.model.SelectableDataModel;

import ru.it.appquiry.Payments;
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
    
    private PaymentDataModel payments;
    private Payment[] selectedPayments;
    
    public CheckPaymentStatusBean() {
        // :TODO
        // appQuiryClientImpl = new AppQuiryClientImpl(userName, pass, wsdl);
        appQuiryClientImpl = new AppQuiryClientImpl();
    }

//    public void getPayments() {
//        PostBlock postBlock = new PostBlock();
//        String supplierBillID = null;
//        appQuiryClientImpl.exportAllQuittance(postBlock, supplierBillID, new AsyncCallback<GetInquiryStateResponse>() {
//
//            @Override
//            public void onSuccess(final GetInquiryStateResponse t) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//    }
//
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
        
        List<Payment> paymentList = new ArrayList<CheckPaymentStatusBean.Payment>();
        Payment p = new Payment();
        p.setDate("21321");
        p.setDestination("213");
        paymentList.add(p);
        
        p = new Payment();
        p.setDate("21323233243234");
        p.setDestination("fdsf");
        p.setUid("fsdfds");
        paymentList.add(p);
        
        payments = new PaymentDataModel(paymentList);
        System.err.println("set update");
        RequestContext.getCurrentInstance().update("CheckPaymentStatus:resultPanel");

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
    
    public class Payment {
    	String date;
    	String uid;
    	String destination;
    	String summ;
    	String upi;
    	String bill;
    	String billId;
    	
		/**
		 * @return the date
		 */
		public String getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(String date) {
			this.date = date;
		}
		/**
		 * @return the uid
		 */
		public String getUid() {
			return uid;
		}
		/**
		 * @param uid the uid to set
		 */
		public void setUid(String uid) {
			this.uid = uid;
		}
		/**
		 * @return the destination
		 */
		public String getDestination() {
			return destination;
		}
		/**
		 * @param destination the destination to set
		 */
		public void setDestination(String destination) {
			this.destination = destination;
		}
		/**
		 * @return the summ
		 */
		public String getSumm() {
			return summ;
		}
		/**
		 * @param summ the summ to set
		 */
		public void setSumm(String summ) {
			this.summ = summ;
		}
		/**
		 * @return the upi
		 */
		public String getUpi() {
			return upi;
		}
		/**
		 * @param upi the upi to set
		 */
		public void setUpi(String upi) {
			this.upi = upi;
		}
		/**
		 * @return the bill
		 */
		public String getBill() {
			return bill;
		}
		/**
		 * @param bill the bill to set
		 */
		public void setBill(String bill) {
			this.bill = bill;
		}
		/**
		 * @return the billId
		 */
		public String getBillId() {
			return billId;
		}
		/**
		 * @param billId the billId to set
		 */
		public void setBillId(String billId) {
			this.billId = billId;
		}
    }
    
    class PaymentDataModel extends ListDataModel<Payment> implements SelectableDataModel<Payment> {
    	
    	public PaymentDataModel(List<Payment> paymentList) {
    		super(paymentList);
		}

		@Override
		public Payment getRowData(String arg0) {
			@SuppressWarnings("unchecked")
			List<Payment> list = (List<Payment>) getWrappedData();
			for (Payment payment : list) {
				if (payment.getBillId().equals(arg0))
					return payment;
			}
			return null;
		}

		@Override
		public Object getRowKey(Payment arg0) {
			return arg0.getBillId();
		}
    	
    }
    
    private void test(AsyncCallback<String> callback) {
    	callback.onSuccess("null");
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
	 * @return the selectedPayments
	 */
	public Payment[] getSelectedPayments() {
		return selectedPayments;
	}

	/**
	 * @param selectedPayments the selectedPayments to set
	 */
	public void setSelectedPayments(Payment[] selectedPayments) {
		this.selectedPayments = selectedPayments;
	}

	/**
	 * @param payments the payments to set
	 */
	public void setPayments(PaymentDataModel payments) {
		this.payments = payments;
	}
	
	public PaymentDataModel getPayments() {
		return payments;
	}
}
