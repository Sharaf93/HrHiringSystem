package pagecode;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletRequest;

import model.Phas;
import model.PhasesDetail;


public class ManagedBean extends PageCodeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@EJB
//	Bean mybean;
//	List<Phas> phasesList;
//	List<PhasesDetail>phaseDetailsByPhaseIdList;
//	
//	public void getPhasesList() {
//		System.out.println("visited getPhasesList function inside the managed beannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
//		if ( phasesList == null)
//		{
//		//phasesList = mybean.getAllPhases();
//		System.out.println("phases size" + phasesList.size());
//		}
//		//return phasesList;
//	}
//
//	public void setPhasesList(List<Phas> phasesList) {
//		this.phasesList = phasesList;
//	}
//
//	public List<PhasesDetail> getPhaseDetailsByPhaseIdList() {
//		return phaseDetailsByPhaseIdList;
//	}
//
//	public void setPhaseDetailsByPhaseIdList(
//			List<PhasesDetail> phaseDetailsByPhaseIdList) {
//		this.phaseDetailsByPhaseIdList = phaseDetailsByPhaseIdList;
//	}
//
//	public String viewPhaseTableByPhaseID()
//	{
//		String temp;
//		Long phaseId;
//		HttpServletRequest request;
//		request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		
//		if ( request != null )
//		{
//			temp = request.getParameter("phaseName");
//			if ( !temp.equals(""))
//			{
//				phaseId = Long.valueOf(temp);
//				System.out.println("value of HTTP request paramter after parsing = " + phaseId);
//				phaseDetailsByPhaseIdList = mybean.getPhaesDetailsByPhaseId(phaseId);
//			}
//			else
//			{
//				// TODO throw an exception
//				System.out.println("HTTP request has been sent with no paramter");
//			}
//		}
//		else
//		{
//			// TODO Throw an exception !
//			System.out.println("No HTTP Request Sent");
//		}
//		
//		return "View Candidate";
//	}
}